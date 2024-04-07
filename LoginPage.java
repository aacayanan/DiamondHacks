import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame {
    JTextField userIdField = new JTextField(20);

    public LoginPage() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLayout(new GridBagLayout());
        
        // CENTER PANEL
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("<html>Welcome to ScurvyScout!<br>Enter User ID:</html>");
        JButton loginButton = new JButton("Login");

        title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setBorder(new EmptyBorder(0, 0, 0, 30));

        loginButton.addActionListener(new LoginButtonListener());

        JPanel fieldPanel = new JPanel(new FlowLayout());
        fieldPanel.add(userIdField);
        fieldPanel.add(loginButton);

        panelCenter.add(title);
        panelCenter.add(fieldPanel);

        add(panelCenter);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }

    // create private method to handle login button click
    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String userId = userIdField.getText();

            // open new GUI window
            new GUI(userId);
            setVisible(false);
        }
    }
}
