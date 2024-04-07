import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private String userId;
    private JTextField inputField = new JTextField(20);
    private User user = new User(userId);
    JTextArea logArea = new JTextArea(5, 10);
    private IntakeLog currentLog = user.getLogObject(userId);
    private LoginPage loginPage;
    private JLabel warningLabel = new JLabel("<html><div style='text-align: center'>WARNING: You are below your daily goal of Vitamin C.<br>! AT RISK OF SCURVY !<BR>EAT MORE VITAMIN C!</div></html>");
    private JLabel goalMetLabel = new JLabel("<html><div style='text-align: center'>You have met your daily goal of Vitamin C!<br>Looks like you won't be getting scurvy today!</div></html>");

    public GUI(String userId, LoginPage loginPage) {
        this.userId = userId;
        this.loginPage = loginPage;

        setTitle("ScurvyScout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelCenter = new JPanel();

        // NORTH PANEL
        JPanel panelTopNorth = new JPanel();
        panelTopNorth.setLayout(new BoxLayout(panelTopNorth, BoxLayout.Y_AXIS));
        JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel welcomeLabel = new JLabel("Ahoy, " + userId + "!");
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(50, 25));
        logoutButton.setFont(new Font("Arial", Font.ITALIC, 8));
        logoutButton.addActionListener(new LogoutButtonListener());
        panelTop.add(welcomeLabel);
        panelTop.add(logoutButton);

        JLabel header = new JLabel("Daily Vitamin C Intake Tracker");
        header.setFont(new Font("Arial", Font.PLAIN, 30));
        panelNorth.add(header);

        // add the top panel and north panel to topnorth panel
        panelTopNorth.add(panelTop);
        panelTopNorth.add(panelNorth);

        // SOUTH PANEL
        JPanel inputPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        JLabel inputLabel = new JLabel("Enter food item: ");
        submitButton.addActionListener(new SubmitButtonListener());
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(inputField);
        inputPanel.add(submitButton);
        panelSouth.add(inputLabel, BorderLayout.WEST);
        panelSouth.add(inputPanel);

        // CENTER PANEL
        JLabel leftValue = new JLabel("0mg");
        JLabel rightValue = new JLabel("90mg");
        JPanel progressPanel = new JPanel();
        JPanel warningPanel = new JPanel();

        // set box layout for center panel (allows for vertical stacking)
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));

        // panel for warning message
        // JLabel warningLabel = new JLabel("<html><div style='text-align: center'>WARNING: You are below your daily goal of Vitamin C.<br>! AT RISK OF SCURVY !<BR>EAT MORE VITAMIN C!</div></html>");
        warningLabel.setForeground(Color.RED);
        warningLabel.setFont(new Font("Arial", Font.BOLD, 20));
        // JLabel goalMetLabel = new JLabel("You have met your daily goal of Vitamin C! Looks like you won't be getting scurvy today!");
        goalMetLabel.setForeground(Color.GREEN);
        goalMetLabel.setFont(new Font("Arial", Font.BOLD, 20));
        updateLabelVisibility();
        warningPanel.add(warningLabel, BorderLayout.CENTER);
        warningPanel.add(goalMetLabel, BorderLayout.CENTER);

        // panel for progress bar
        // ProgressBar progress = new ProgressBar();
        user.updateProgressBar();
        ProgressBar guiProgress = user.getProgressBar();
        user.updateProgressBar();
        progressPanel.add(leftValue, BorderLayout.WEST);
        progressPanel.add(guiProgress.progressBar, BorderLayout.CENTER);
        progressPanel.add(rightValue, BorderLayout.EAST);
        
        panelCenter.add(progressPanel);
        panelCenter.add(warningPanel);

        // panel for the logs
        JPanel logPanel = new JPanel();
        logArea.setEditable(false);
        if (currentLog != null) {
            logArea.setText(currentLog.getEntries());
        } else {
            logArea.setText("No logs found for user: " + userId);
        }
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setPreferredSize(new Dimension(200, 80));
        logPanel.add(logScroll);
        panelCenter.add(logPanel);

        // ADD PANELS TO FRAME
        add(panelTopNorth, BorderLayout.PAGE_START);
        // add(panelNorth, BorderLayout.NORTH);
        add(panelSouth, BorderLayout.SOUTH);
        add(panelCenter, BorderLayout.CENTER);
        setVisible(true);
    }

    // create a private class for the submit button listener
    private class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // get the food item from the input field
            String foodItem = inputField.getText();
            // get the vitamin amount from the food item
            int vitaminAmount = GPT.integerVitaminC(foodItem);
            // update the user's intake log
            user.updateIntake(userId, foodItem, vitaminAmount);
            // update the JTextArea with the new log
            logArea.setText(user.getLogs(userId));
            // update progress
            user.updateProgressBar(vitaminAmount);
            // System.out.println("Progress: " + user.getProgressBar().getProgress() + " Goal: " + user.getProgressBar().getGoal());
            // update the label warning
            updateLabelVisibility();
        }
    }

    // create private method to handle logout button click
    private class LogoutButtonListener implements ActionListener {
            
        @Override
        public void actionPerformed(ActionEvent e) {
            // close the GUI window
            setVisible(false);
            dispose();

            // create new instance of the login page
            // LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
        }
    }

    public void updateLabelVisibility() {
        if (user.getProgressBar().getProgress() < user.getProgressBar().getGoal()) {
            warningLabel.setVisible(true);
            goalMetLabel.setVisible(false);
        } else {
            warningLabel.setVisible(false);
            goalMetLabel.setVisible(true);
        }
    }
}
