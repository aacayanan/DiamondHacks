import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame {
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
        JTextField userIdField = new JTextField(20);
        JButton loginButton = new JButton("Login");

        title.setFont(new Font("Arial", Font.PLAIN, 25));
        title.setBorder(new EmptyBorder(0, 0, 0, 30));


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
}
