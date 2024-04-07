import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginPage extends JFrame {
    JTextField userIdField = new JTextField(20);
    private HashMap<String, GUI> userLogs = new HashMap<>();

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

        // BOTTOM PANEL
        JPanel panelBottom = new JPanel();
        JButton infoButton = new JButton("?");
        infoButton.addActionListener(new InfoButtonListener());
        panelBottom.add(infoButton);
        add(panelBottom);

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

            // check if a GUI instance already exists for this userId
            GUI existingGui = userLogs.get(userId);
            if (existingGui == null) {
                // if not create a new GUI instance
                existingGui = new GUI(userId, LoginPage.this);
                userLogs.put(userId, existingGui);
            }

            // check if GUI instance is null
            // new GUI(userId);
            existingGui.setVisible(true);
            setVisible(false);
        }
    }

    // create private method to handle info button click
    private class InfoButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String info = "<html>Scurvy is a disease that occurs when you have a severe lack of vitamin C in your diet.<br>It causes general weakness, anemia, gum disease, poor wound healing, and skin hemorrhages (Mount Sinai Health).<br>60 mg daily is all that is required to prevent scurvy for healthy adults (Medscape Medicine).<br><br>Scurvy be a curse that strikes when ye be severely lackin' vitamin C in yer grub.<br>It brings ye to yer knees with weakness, turns yer blood thin as bilge water, rots yer gums, makes healin' slower than a calm sea,<br>and gives ye skin as spotted as a treasure map (by the accounts o' Mount Sinai Health).<br>A mere 60 mg o' vitamin C each day be all it takes to keep the scurvy at bay for healthy sea dogs (as per the scrolls of Medscape Medicine).</html>";
            JOptionPane.showMessageDialog(null, info, "Information about Scurvy", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
