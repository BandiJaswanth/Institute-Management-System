package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentLogin {

    JFrame frame;

    JTextField usernameField;
    JPasswordField passwordField;

    JCheckBox showPassword;

    JButton loginButton;
    JButton clearButton;
    JButton backButton;

    StudentLogin() {

        frame = new JFrame("Student Login");
        frame.setSize(700,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(120,40,450,360);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Student Login");
        title.setBounds(120,20,250,35);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));
        panel.add(title);

        JLabel userLabel = new JLabel("Username / Email");
        userLabel.setBounds(50,90,150,30);
        userLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200,90,200,35);
        usernameField.setFont(new Font("Segoe UI",Font.PLAIN,16));
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(50,150,120,30);
        passLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200,150,200,35);
        passwordField.setFont(new Font("Segoe UI",Font.PLAIN,16));
        panel.add(passwordField);

        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(200,195,150,25);
        showPassword.setBackground(Color.WHITE);
        panel.add(showPassword);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(35,260,110,40);
        loginButton.setBackground(new Color(25,118,210));
        loginButton.setForeground(Color.WHITE);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(170,260,110,40);
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);

        backButton = new JButton("BACK");
        backButton.setBounds(305,260,110,40);
        backButton.setBackground(new Color(220,53,69));
        backButton.setForeground(Color.WHITE);

        panel.add(loginButton);
        panel.add(clearButton);
        panel.add(backButton);

        frame.add(panel);

        frame.getContentPane().setBackground(new Color(235,242,250));
        frame.setVisible(true);

        // ================= SHOW PASSWORD =================
        showPassword.addActionListener(e -> {
            if(showPassword.isSelected()) {
                passwordField.setEchoChar((char)0);
            } else {
                passwordField.setEchoChar('•');
            }
        });

        // ================= CLEAR =================
        clearButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });

        // ================= BACK =================
        backButton.addActionListener(e -> {
            frame.dispose();
            new Home();
        });

        // ================= LOGIN =================
        loginButton.addActionListener(e -> {

            try {

                Connection con = DBconnection.getConnection();

                String sql = "SELECT * FROM students WHERE username=? AND password=?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, usernameField.getText());
                ps.setString(2, new String(passwordField.getPassword()));

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {

                    String status = rs.getString("status");

                    if(status.equalsIgnoreCase("Pending")) {

                        JOptionPane.showMessageDialog(frame,
                                "Waiting for Admin Approval!");

                    } else {

                        JOptionPane.showMessageDialog(frame,
                                "Login Successful!");

                       

                    	String username = rs.getString("username");

                    	frame.dispose();

                    	new StudentDashboard(username);

                    }

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "Invalid Username or Password!");

                }

                con.close();

            } catch(Exception ex) {
                ex.printStackTrace();
            }

        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentLogin());
    }
}