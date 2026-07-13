package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddEmployee {

    JFrame frame;

    JTextField nameField;
    JTextField dobField;
    JTextField phoneField;
    JTextField emailField;
    JTextField experienceField;
    JTextField usernameField;

    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    JRadioButton male;
    JRadioButton female;

    JComboBox<String> departmentBox;
    JComboBox<String> qualificationBox;

    JButton saveButton;
    JButton clearButton;
    JButton backButton;

    AddEmployee() {

        frame = new JFrame("Add Employee");
        frame.setSize(750,780);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245,247,250));

        JLabel title = new JLabel("ADD EMPLOYEE");
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));
        title.setBounds(220,20,320,40);
        frame.add(title);

        Font f = new Font("Segoe UI",Font.BOLD,15);

        //================ NAME =================

        JLabel l1 = new JLabel("Full Name");
        l1.setBounds(70,90,150,30);
        l1.setFont(f);
        frame.add(l1);

        nameField = new JTextField();
        nameField.setBounds(250,90,320,30);
        frame.add(nameField);

        //================ GENDER =================

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(70,135,150,30);
        genderLabel.setFont(f);
        frame.add(genderLabel);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBounds(250,135,80,30);
        female.setBounds(350,135,100,30);

        male.setBackground(new Color(245,247,250));
        female.setBackground(new Color(245,247,250));

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        frame.add(male);
        frame.add(female);

        //================ DOB =================

        JLabel l3 = new JLabel("Date of Birth");
        l3.setBounds(70,180,150,30);
        l3.setFont(f);
        frame.add(l3);

        dobField = new JTextField();
        dobField.setBounds(250,180,320,30);
        frame.add(dobField);

        //================ PHONE =================

        JLabel l4 = new JLabel("Phone");
        l4.setBounds(70,225,150,30);
        l4.setFont(f);
        frame.add(l4);

        phoneField = new JTextField();
        phoneField.setBounds(250,225,320,30);
        frame.add(phoneField);

        //================ EMAIL =================

        JLabel l5 = new JLabel("Email");
        l5.setBounds(70,270,150,30);
        l5.setFont(f);
        frame.add(l5);

        emailField = new JTextField();
        emailField.setBounds(250,270,320,30);
        frame.add(emailField);

        //================ DEPARTMENT =================

        JLabel l6 = new JLabel("Department");
        l6.setBounds(70,315,150,30);
        l6.setFont(f);
        frame.add(l6);

        departmentBox = new JComboBox<>(new String[]{
                "Computer Science",
                "Information Technology",
                "Mathematics",
                "Physics",
                "Commerce"
        });

        departmentBox.setBounds(250,315,320,30);
        frame.add(departmentBox);

        //================ QUALIFICATION =================

        JLabel l7 = new JLabel("Qualification");
        l7.setBounds(70,360,150,30);
        l7.setFont(f);
        frame.add(l7);

        qualificationBox = new JComboBox<>(new String[]{
                "B.Tech",
                "M.Tech",
                "BCA",
                "MCA",
                "PhD"
        });

        qualificationBox.setBounds(250,360,320,30);
        frame.add(qualificationBox);

        //================ EXPERIENCE =================

        JLabel l8 = new JLabel("Experience");
        l8.setBounds(70,405,150,30);
        l8.setFont(f);
        frame.add(l8);

        experienceField = new JTextField();
        experienceField.setBounds(250,405,320,30);
        frame.add(experienceField);

        //================ USERNAME =================

        JLabel l9 = new JLabel("Username");
        l9.setBounds(70,450,150,30);
        l9.setFont(f);
        frame.add(l9);

        usernameField = new JTextField();
        usernameField.setBounds(250,450,320,30);
        frame.add(usernameField);

        //================ PASSWORD =================

        JLabel l10 = new JLabel("Password");
        l10.setBounds(70,495,150,30);
        l10.setFont(f);
        frame.add(l10);

        passwordField = new JPasswordField();
        passwordField.setBounds(250,495,320,30);
        frame.add(passwordField);
        //================ CONFIRM PASSWORD =================

        JLabel l11 = new JLabel("Confirm Password");
        l11.setBounds(70,540,150,30);
        l11.setFont(f);
        frame.add(l11);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(250,540,320,30);
        frame.add(confirmPasswordField);

        //================ BUTTONS =================

        saveButton = new JButton("SAVE");
        saveButton.setBounds(60,620,120,40);
        saveButton.setBackground(new Color(25,118,210));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(250,620,120,40);
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFocusPainted(false);

        backButton = new JButton("BACK");
        backButton.setBounds(450,620,120,40);
        backButton.setBackground(new Color(220,53,69));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        frame.add(saveButton);
        frame.add(clearButton);
        frame.add(backButton);

        //================ SAVE =================

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

                PreparedStatement ps = con.prepareStatement(

                        "INSERT INTO employ(fullName,gender,dob,phone,email,department,qualification,experience,username,password,status) VALUES(?,?,?,?,?,?,?,?,?,?,?)"

                );

                ps.setString(1, nameField.getText());
                ps.setString(2, gender);
                ps.setString(3, dobField.getText());
                ps.setString(4, phoneField.getText());
                ps.setString(5, emailField.getText());
                ps.setString(6, departmentBox.getSelectedItem().toString());
                ps.setString(7, qualificationBox.getSelectedItem().toString());
                ps.setInt(8, Integer.parseInt(experienceField.getText()));
                ps.setString(9, usernameField.getText());
                ps.setString(10, password);
                ps.setString(11, "Approved");

                int x = ps.executeUpdate();

                if (x > 0) {

                    JOptionPane.showMessageDialog(frame,
                            "Employee Added Successfully");

                }

                con.close();

            }

            catch (Exception ex) {

                ex.printStackTrace();

            }

        });

        //================ CLEAR =================

        clearButton.addActionListener(e -> {

            nameField.setText("");
            dobField.setText("");
            phoneField.setText("");
            emailField.setText("");
            experienceField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");

            male.setSelected(false);
            female.setSelected(false);

            departmentBox.setSelectedIndex(0);
            qualificationBox.setSelectedIndex(0);

        });
        //================ BACK BUTTON =================

        backButton.addActionListener(e -> {

            frame.dispose();
            new AdminDashboard();

        });

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new AddEmployee());

    }

}