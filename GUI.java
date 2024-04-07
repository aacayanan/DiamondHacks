import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GUI extends JFrame {
    private String userId;
    private JTextField inputField = new JTextField(20);
    private User user = new User(userId);
    JTextArea logArea = new JTextArea(5, 10);
    private IntakeLog currentLog = user.getLogObject(userId);
    private LoginPage loginPage;
    private JLabel warningLabel = new JLabel("<html><div style='text-align: center'>BEWARE, ye scallywag!<br>! YE BE COURSIN' TOWARDS SCURVY !<BR>Hoist the sails and set course for more Vitamin C</div></html>");
    private JLabel goalMetLabel = new JLabel("<html><div style='text-align: center'>Ahoy, shipmate! Ye have met yer daily goal for Vitamin C!<br>Shiver me timbers, looks like ye won't<br>be joinin' the ranks o' scurvy sea dogs today!</div></html>");

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
        logoutButton.addActionListener(new LogoutButtonListener());
        panelTop.add(welcomeLabel);
        panelTop.add(logoutButton);

        // end log
        JButton endLogButton = new JButton("End Log");
        endLogButton.addActionListener(new EndLogButtonListener());
        panelTop.add(endLogButton);

        JLabel header = new JLabel("Logbook o' Daily Vitamin SEA");
        header.setFont(new Font("Arial", Font.PLAIN, 30));
        panelNorth.add(header);

        // add the top panel and north panel to topnorth panel
        panelTopNorth.add(panelTop);
        panelTopNorth.add(panelNorth);

        // SOUTH PANEL
        JPanel inputPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        JLabel inputLabel = new JLabel("Chart yer course: ");
        submitButton.addActionListener(new SubmitButtonListener());
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.add(inputField);
        inputPanel.add(submitButton);
        panelSouth.add(inputLabel, BorderLayout.WEST);
        panelSouth.add(inputPanel);

        // CENTER PANEL
        JLabel leftValue = new JLabel("0mg");
        JLabel rightValue = new JLabel("60mg");
        JPanel progressPanel = new JPanel();
        JPanel warningPanel = new JPanel();

        // set box layout for center panel (allows for vertical stacking)
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));

        // panel for warning message
        warningLabel.setForeground(Color.RED);
        warningLabel.setFont(new Font("Arial", Font.BOLD, 18));
        goalMetLabel.setForeground(new Color(124,181,24));
        goalMetLabel.setFont(new Font("Arial", Font.BOLD, 18));
        updateLabelVisibility();
        warningPanel.add(warningLabel, BorderLayout.CENTER);
        warningPanel.add(goalMetLabel, BorderLayout.CENTER);

        // panel for progress bar
        user.updateProgressBar();
        ProgressBar guiProgress = user.getProgressBar();
        user.updateProgressBar();
        progressPanel.add(leftValue, BorderLayout.WEST);
        progressPanel.add(guiProgress.progressBar, BorderLayout.CENTER);
        progressPanel.add(rightValue, BorderLayout.EAST);
        
        panelCenter.add(progressPanel);
        panelCenter.add(warningPanel);

        // panel for the logs
        JPanel panelTextArea = new JPanel();
        panelTextArea.setLayout(new BoxLayout(panelTextArea, BoxLayout.Y_AXIS));
        JLabel labelFormat = new JLabel("Date : Time : Food : Vitamin C");
        JPanel logPanel = new JPanel();
        panelTextArea.add(labelFormat);
        logArea.setEditable(false);
        if (currentLog != null) {
            logArea.setText(currentLog.getEntries());
        } else {
            logArea.setText("No logs found for user: " + userId);
        }
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setPreferredSize(new Dimension(200, 80));
        panelTextArea.setBorder(new EmptyBorder(0, 115, 0, 0));
        panelCenter.add(panelTextArea);
        logPanel.add(logScroll);
        panelCenter.add(logPanel);

        // ADD PANELS TO FRAME
        add(panelTopNorth, BorderLayout.PAGE_START);
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
            loginPage.setVisible(true);
        }
    }

    // create private method to handel end log button click
    private class EndLogButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String logText = logArea.getText();
            
            // write the text to a new text file
            try (PrintWriter out = new PrintWriter("intakelog.txt")) {
                out.println(logText);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                dispose();
                loginPage.setVisible(true);
            }
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
