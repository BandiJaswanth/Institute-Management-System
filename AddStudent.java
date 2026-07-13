package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudent {

    JFrame frame;

    JTextField nameField;
    JTextField dobField;
    JTextField phoneField;
    JTextField emailField;
    JTextField addressField;
    JTextField usernameField;

    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    JRadioButton male;
    JRadioButton female;

    JComboBox<String> deptBox;
    JComboBox<String> courseBox;

    JButton saveButton;
    JButton clearButton;
    JButton backButton;

    AddStudent() {

        frame = new JFrame("Add Student");
        frame.setSize(900,720);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245,247,250));

        JPanel panel = new JPanel();
        panel.setBounds(120,40,650,610);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("ADD STUDENT");
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(new Color(25,118,210));
        title.setBounds(210,20,250,30);
        panel.add(title);

        Font f = new Font("Segoe UI",Font.BOLD,15);

        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(50,70,150,30);
        nameLabel.setFont(f);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(250,70,300,30);
        panel.add(nameField);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50,110,150,30);
        genderLabel.setFont(f);
        panel.add(genderLabel);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBounds(250,110,80,30);
        female.setBounds(340,110,100,30);

        male.setBackground(Color.WHITE);
        female.setBackground(Color.WHITE);

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        panel.add(male);
        panel.add(female);

        JLabel dobLabel = new JLabel("DOB");
        dobLabel.setBounds(50,150,150,30);
        dobLabel.setFont(f);
        panel.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(250,150,300,30);
        panel.add(dobField);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(50,190,150,30);
        phoneLabel.setFont(f);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(250,190,300,30);
        panel.add(phoneField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50,230,150,30);
        emailLabel.setFont(f);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250,230,300,30);
        panel.add(emailField);

        JLabel deptLabel = new JLabel("Department");
        deptLabel.setBounds(50,270,150,30);
        deptLabel.setFont(f);
        panel.add(deptLabel);

        deptBox = new JComboBox<>(new String[]{
                "Information Technology",
                "Computer Science",
                "Commerce"
        });

        deptBox.setBounds(250,270,300,30);
        panel.add(deptBox);

        JLabel courseLabel = new JLabel("Course");
        courseLabel.setBounds(50,310,150,30);
        courseLabel.setFont(f);
        panel.add(courseLabel);

        courseBox = new JComboBox<>();
        courseBox.setBounds(250,310,300,30);
        panel.add(courseBox);

        updateCourses("Information Technology");

        deptBox.addActionListener(e ->
                updateCourses(deptBox.getSelectedItem().toString())
        );

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(50,350,150,30);
        addressLabel.setFont(f);
        panel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(250,350,300,30);
        panel.add(addressField);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(50,390,150,30);
        userLabel.setFont(f);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(250,390,300,30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(50,430,150,30);
        passLabel.setFont(f);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(250,430,300,30);
        panel.add(passwordField);
        // ================= CONFIRM PASSWORD =================

        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setBounds(50,470,150,30);
        confirmLabel.setFont(f);
        panel.add(confirmLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(250,470,300,30);
        panel.add(confirmPasswordField);

        // ================= BUTTONS =================

        saveButton = new JButton("SAVE");
        saveButton.setBounds(80,530,120,40);
        saveButton.setBackground(new Color(25,118,210));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(260,530,120,40);
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);

        backButton = new JButton("BACK");
        backButton.setBounds(440,530,120,40);
        backButton.setBackground(new Color(220,53,69));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        panel.add(saveButton);
        panel.add(clearButton);
        panel.add(backButton);

        frame.add(panel);
        frame.setVisible(true);

        // ================= SAVE =================

        saveButton.addActionListener(e -> {

            try {

                String password = new String(passwordField.getPassword());
                String confirm = new String(confirmPasswordField.getPassword());

                if (!password.equals(confirm)) {
                    JOptionPane.showMessageDialog(frame,
                            "Password and Confirm Password do not match!");
                    return;
                }

                String gender = "";

                if (male.isSelected())
                    gender = "Male";
                else if (female.isSelected())
                    gender = "Female";
                else {
                    JOptionPane.showMessageDialog(frame,
                            "Please Select Gender");
                    return;
                }

                Connection con = DBconnection.getConnection();

                String sql = "INSERT INTO students(name,gender,dob,phone,email,course,username,password,address,status) VALUES(?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, nameField.getText());
                ps.setString(2, gender);
                ps.setString(3, dobField.getText());
                ps.setString(4, phoneField.getText());
                ps.setString(5, emailField.getText());
                ps.setString(6, courseBox.getSelectedItem().toString());
                ps.setString(7, usernameField.getText());
                ps.setString(8, password);
                ps.setString(9, addressField.getText());
                ps.setString(10, "Approved");

                int x = ps.executeUpdate();

                if (x > 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Student Added Successfully");
                }

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        // ================= CLEAR =================

        clearButton.addActionListener(e -> {

            nameField.setText("");
            dobField.setText("");
            phoneField.setText("");
            emailField.setText("");
            addressField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");

            male.setSelected(false);
            female.setSelected(false);

            deptBox.setSelectedIndex(0);
            updateCourses(deptBox.getSelectedItem().toString());

        });
        // ================= BACK =================

        backButton.addActionListener(e -> {

            frame.dispose();
            new AdminDashboard();

        });

    }

    // ================= COURSE DROPDOWN =================

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
            courseBox.addItem("Artificial Intelligence");

        }

        else if (dept.equals("Commerce")) {

            courseBox.addItem("Accounting");
            courseBox.addItem("Business Studies");
            courseBox.addItem("Financial Management");

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new AddStudent());

    }

}