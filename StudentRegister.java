package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentRegister {

    JFrame frame;

    JTextField nameField;
    JTextField dobField;
    JTextField phoneField;
    JTextField emailField;
    JTextField addressField;
    JTextField usernameField;

    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    JRadioButton male, female;

    JComboBox<String> deptBox;
    JComboBox<String> courseBox;

    JButton registerButton, clearButton, backButton;

    StudentRegister() {

        frame = new JFrame("Student Register");
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(120, 40, 650, 580);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Student Register");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(25,118,210));
        title.setBounds(200, 20, 300, 30);
        panel.add(title);

        // NAME
        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(50, 70, 150, 30);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(250, 70, 300, 30);
        panel.add(nameField);

        // GENDER
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50, 110, 150, 30);
        panel.add(genderLabel);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBounds(250, 110, 80, 30);
        female.setBounds(340, 110, 100, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        panel.add(male);
        panel.add(female);

        // DOB
        JLabel dobLabel = new JLabel("DOB");
        dobLabel.setBounds(50, 150, 150, 30);
        panel.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(250, 150, 300, 30);
        panel.add(dobField);

        // PHONE
        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(50, 190, 150, 30);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(250, 190, 300, 30);
        panel.add(phoneField);

        // EMAIL
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 230, 150, 30);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 230, 300, 30);
        panel.add(emailField);

        // DEPARTMENT
        JLabel deptLabel = new JLabel("Department");
        deptLabel.setBounds(50, 270, 150, 30);
        panel.add(deptLabel);

        String[] departments = {
                "Information Technology",
                "Computer Science",
                "Commerce"
        };

        deptBox = new JComboBox<>(departments);
        deptBox.setBounds(250, 270, 300, 30);
        panel.add(deptBox);

        // COURSE
        JLabel courseLabel = new JLabel("Course");
        courseLabel.setBounds(50, 310, 150, 30);
        panel.add(courseLabel);

        courseBox = new JComboBox<>();
        courseBox.setBounds(250, 310, 300, 30);
        panel.add(courseBox);

        updateCourses("Information Technology");

        deptBox.addActionListener(e -> {
            updateCourses(deptBox.getSelectedItem().toString());
        });

        // ADDRESS
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(50, 350, 150, 30);
        panel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(250, 350, 300, 30);
        panel.add(addressField);

        // USERNAME
        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(50, 390, 150, 30);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(250, 390, 300, 30);
        panel.add(usernameField);

        // PASSWORD
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(50, 430, 150, 30);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 430, 300, 30);
        panel.add(passwordField);

        // CONFIRM PASSWORD
        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setBounds(50, 470, 150, 30);
        panel.add(confirmLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(250, 470, 300, 30);
        panel.add(confirmPasswordField);

        // ================= BUTTONS (FIXED THEME ONLY) =================

        registerButton = new JButton("REGISTER");
        registerButton.setBounds(80, 520, 150, 40);
        registerButton.setBackground(new Color(25,118,210));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(250, 520, 150, 40);
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);

        backButton = new JButton("BACK");
        backButton.setBounds(420, 520, 120, 40);
        backButton.setBackground(new Color(220,53,69));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        panel.add(registerButton);
        panel.add(clearButton);
        panel.add(backButton);

        frame.add(panel);
        frame.setVisible(true);

        // ================= ACTIONS =================

        clearButton.addActionListener(e -> {
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");
            addressField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new Home();
        });

        registerButton.addActionListener(e -> {

            try {

                String name = nameField.getText();
                String gender = male.isSelected() ? "Male" : "Female";

                String dob = dobField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                String course = courseBox.getSelectedItem().toString();
                String address = addressField.getText();
                String username = usernameField.getText();

                String password = new String(passwordField.getPassword());
                String confirm = new String(confirmPasswordField.getPassword());

                if (!password.equals(confirm)) {
                    JOptionPane.showMessageDialog(frame, "Password mismatch!");
                    return;
                }

                Connection con = DBconnection.getConnection();

                String sql = "INSERT INTO students(name, gender, dob, phone, email, course, username, password, address, status) VALUES(?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, gender);
                ps.setString(3, dob);
                ps.setString(4, phone);
                ps.setString(5, email);
                ps.setString(6, course);
                ps.setString(7, username);
                ps.setString(8, password);
                ps.setString(9, address);
                ps.setString(10, "Pending");

                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Student Registered Successfully!");

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }

    void updateCourses(String dept) {

        courseBox.removeAllItems();

        if (dept.equals("Information Technology")) {
            courseBox.addItem("Java");
            courseBox.addItem("Python");
            courseBox.addItem("Web Development");
        }
        else if (dept.equals("Computer Science")) {
            courseBox.addItem("C Programming");
            courseBox.addItem("Data Structures");
            courseBox.addItem("AI Basics");
        }
        else if (dept.equals("Commerce")) {
            courseBox.addItem("Accounting");
            courseBox.addItem("Business Studies");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentRegister::new);
    }
}