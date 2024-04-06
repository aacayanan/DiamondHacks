import javax.swing.*;
import java.awt.*;
// import java.awt.event.FocusAdapter;
// import java.awt.event.FocusEvent;

public class GUI extends JFrame {
    public GUI() {
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
        JTextField inputField = new JTextField(20);
        JLabel inputLabel = new JLabel("Enter food item: ");
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
        ProgressBar progress = new ProgressBar(100);
        progressPanel.add(leftValue, BorderLayout.WEST);
        progressPanel.add(progress.progressBar, BorderLayout.CENTER);
        progressPanel.add(rightValue, BorderLayout.EAST);
        
        panelCenter.add(progressPanel);
        panelCenter.add(warningPanel);

        // ADD PANELS TO FRAME
        add(panelNorth, BorderLayout.NORTH);
        add(panelSouth, BorderLayout.SOUTH);
        add(panelCenter, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }
}