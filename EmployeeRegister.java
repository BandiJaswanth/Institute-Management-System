package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class EmployeeRegister {

    JFrame frame;

    JTextField nameField;
    JTextField dobField;
    JTextField phoneField;
    JTextField emailField;
    JTextField experienceField;
    JTextField usernameField;

    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    JComboBox<String> departmentBox;
    JComboBox<String> qualificationBox;

    JRadioButton male;
    JRadioButton female;

    JButton registerButton;
    JButton clearButton;
    JButton backButton;

    EmployeeRegister() {

        frame = new JFrame("Employee Register");
        frame.setSize(900,700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setLayout(null);

        frame.setVisible(true);
    
        JPanel panel = new JPanel();
        panel.setBounds(120,40,650,570);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Employee Register");
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(new Color(25,118,210));
        title.setBounds(180,20,300,30);
        panel.add(title);

        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(50,80,150,30);
        panel.add(nameLabel);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 16);

       

        nameLabel.setFont(labelFont);
       
        nameField = new JTextField();
        nameField.setBounds(250,80,300,30);
        panel.add(nameField);
     // Gender
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50,120,150,30);
        panel.add(genderLabel);
        Font labelFont1 = new Font("Segoe UI", Font.BOLD, 16);

      
        nameLabel.setFont(labelFont);
        genderLabel.setFont(labelFont);
      
        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBackground(Color.WHITE);
        female.setBackground(Color.WHITE);

        male.setBounds(250,120,80,30);
        female.setBounds(350,120,100,30);

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        panel.add(male);
        panel.add(female);

        // DOB
        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(50,160,150,30);
        panel.add(dobLabel);
        Font labelFont2 = new Font("Segoe UI", Font.BOLD, 16);

        dobLabel.setFont(labelFont);
        dobField = new JTextField();
        dobField.setBounds(250,160,300,30);
        panel.add(dobField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setBounds(50,200,150,30);
        panel.add(phoneLabel);
        Font labelFont3 = new Font("Segoe UI", Font.BOLD, 16);

        phoneLabel.setFont(labelFont);
        phoneField = new JTextField();
        phoneField.setBounds(250,200,300,30);
        panel.add(phoneField);

        // Email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50,240,150,30);
        panel.add(emailLabel);
        Font labelFont5 = new Font("Segoe UI", Font.BOLD, 16);

        emailLabel.setFont(labelFont);
        emailField = new JTextField();
        emailField.setBounds(250,240,300,30);
        panel.add(emailField);

        // Department
        JLabel depLabel = new JLabel("Department");
        depLabel.setBounds(50,280,150,30);
        panel.add(depLabel);
        Font labelFont6= new Font("Segoe UI", Font.BOLD, 16);

        depLabel.setFont(labelFont);
        String departments[] = {
                "Computer Science",
                "Information Technology",
                "Mathematics",
                "Physics",
                "Commerce"
        };

        departmentBox = new JComboBox<>(departments);
        departmentBox.setBounds(250,280,300,30);
        panel.add(departmentBox);

        // Qualification
        JLabel qLabel = new JLabel("Qualification");
        qLabel.setBounds(50,320,150,30);
        panel.add(qLabel);
        Font labelFont7 = new Font("Segoe UI", Font.BOLD, 16);

        qLabel.setFont(labelFont);
        String qualifications[] = {
                "B.Tech",
                "M.Tech",
                "BCA",
                "MCA",
                "PhD"
        };

        qualificationBox = new JComboBox<>(qualifications);
        qualificationBox.setBounds(250,320,300,30);
        panel.add(qualificationBox);

        // Experience
        JLabel expLabel = new JLabel("Experience");
        expLabel.setBounds(50,360,150,30);
        panel.add(expLabel);
        Font labelFont8 = new Font("Segoe UI", Font.BOLD, 16);

        expLabel.setFont(labelFont);
        experienceField = new JTextField();
        experienceField.setBounds(250,360,300,30);
        panel.add(experienceField);
     // Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50,400,150,30);
        panel.add(usernameLabel);
        Font labelFont9 = new Font("Segoe UI", Font.BOLD, 16);

        usernameLabel.setFont(labelFont);
        usernameField = new JTextField();
        usernameField.setBounds(250,400,300,30);
        panel.add(usernameField);

     // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50,440,150,30);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(250,440,300,30);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(passwordField);


        // Confirm Password
        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setBounds(50,480,150,30);
        confirmLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panel.add(confirmLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(250,480,300,30);
        confirmPasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(confirmPasswordField);

        // Buttons
        registerButton = new JButton("REGISTER");
        registerButton.setBounds(70,530,150,40);
        registerButton.setBackground(new Color(25,118,210));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(250,530,150,40);
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);

        backButton = new JButton("BACK");
        backButton.setBounds(430,530,120,40);
        backButton.setBackground(new Color(220,53,69));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        panel.add(registerButton);
        panel.add(clearButton);
        panel.add(backButton);
        registerButton.addActionListener(e -> {

            try {

                String fullName = nameField.getText();

                String gender = "";

                if (male.isSelected())
                    gender = "Male";
                else if (female.isSelected())
                    gender = "Female";

                String dob = dobField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                String department = departmentBox.getSelectedItem().toString();
                String qualification = qualificationBox.getSelectedItem().toString();

                int experience = Integer.parseInt(experienceField.getText());

                String username = usernameField.getText();

                String password = new String(passwordField.getPassword());

                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (!password.equals(confirmPassword)) {

                    JOptionPane.showMessageDialog(frame,
                            "Passwords do not match!");

                    return;
                }

                java.sql.Connection con = DBconnection.getConnection();

                String sql = "INSERT INTO employ(fullName,gender,dob,phone,email,department,qualification,experience,username,password,status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

                java.sql.PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, fullName);
                ps.setString(2, gender);
                ps.setString(3, dob);
                ps.setString(4, phone);
                ps.setString(5, email);
                ps.setString(6, department);
                ps.setString(7, qualification);
                ps.setInt(8, experience);
                ps.setString(9, username);
                ps.setString(10, password);
                ps.setString(11, "Pending");

                int rows = ps.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(frame,
                            "Employee Registered Successfully!\nWaiting for Admin Approval.");

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "Registration Failed!");

                }

                con.close();

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        });
        // Add panel to frame
        frame.add(panel);

        // Display frame
        frame.setVisible(true);
    }
        public static void main(String[] args) {

            SwingUtilities.invokeLater(() -> {

                new EmployeeRegister();

            });

        
        }
}