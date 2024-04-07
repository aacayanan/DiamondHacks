import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private String userId;
    private JTextField inputField = new JTextField(20);
    private User user = new User(userId);
    JTextArea logArea = new JTextArea(5, 10);

    public GUI(String userId) {
        this.userId = userId;

        setTitle("ScurvyScout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelCenter = new JPanel();

        // NORTH PANEL
        JLabel header = new JLabel("Daily Vitamin C Intake Tracker");
        header.setFont(new Font("Arial", Font.PLAIN, 30));
        panelNorth.add(header, BorderLayout.NORTH);

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
        JLabel warningLabel = new JLabel("<html><div style='text-align: center'>WARNING: You are below your daily goal of Vitamin C.<br>! AT RISK OF SCURVY !<BR>EAT MORE VITAMIN C!</div></html>");
        warningLabel.setForeground(Color.RED);
        warningLabel.setFont(new Font("Arial", Font.BOLD, 20));
        warningPanel.add(warningLabel, BorderLayout.CENTER);

        // panel for progress bar
        ProgressBar progress = new ProgressBar();
        progressPanel.add(leftValue, BorderLayout.WEST);
        progressPanel.add(progress.progressBar, BorderLayout.CENTER);
        progressPanel.add(rightValue, BorderLayout.EAST);
        
        panelCenter.add(progressPanel);
        panelCenter.add(warningPanel);

        // panel for the logs
        JPanel logPanel = new JPanel();
        logArea.setEditable(false);
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setPreferredSize(new Dimension(200, 80));
        logPanel.add(logScroll);
        panelCenter.add(logPanel);

        // ADD PANELS TO FRAME
        add(panelNorth, BorderLayout.NORTH);
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
            int vitaminAmount = 30;
            // update the user's intake log
            user.updateIntake(userId, foodItem, vitaminAmount);
            // update the JTextArea with the new log
            logArea.setText(user.getLogs(userId));
        }
    }
}