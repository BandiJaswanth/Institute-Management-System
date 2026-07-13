package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UpdateStudent {

    JFrame frame;

    JTextField idField;
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

    JButton searchButton;
    JButton updateButton;
    JButton clearButton;
    JButton backButton;

    UpdateStudent() {

        frame = new JFrame("Update Student");
        frame.setSize(900,750);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245,247,250));

        JPanel panel = new JPanel();
        panel.setBounds(100,30,680,650);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        JLabel title = new JLabel("UPDATE STUDENT");
        title.setFont(new Font("Segoe UI",Font.BOLD,26));
        title.setForeground(new Color(25,118,210));
        title.setBounds(200,20,300,35);
        panel.add(title);

        Font f = new Font("Segoe UI",Font.BOLD,15);

        JLabel idLabel = new JLabel("Student ID");
        idLabel.setBounds(40,70,140,30);
        idLabel.setFont(f);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(210,70,170,30);
        panel.add(idField);

        searchButton = new JButton("SEARCH");
        searchButton.setBounds(420,70,120,30);
        searchButton.setBackground(new Color(25,118,210));
        searchButton.setForeground(Color.WHITE);
        panel.add(searchButton);

        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(40,115,140,30);
        nameLabel.setFont(f);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(210,115,320,30);
        panel.add(nameField);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(40,160,140,30);
        genderLabel.setFont(f);
        panel.add(genderLabel);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        male.setBackground(Color.WHITE);
        female.setBackground(Color.WHITE);

        male.setBounds(210,160,90,30);
        female.setBounds(320,160,100,30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);

        panel.add(male);
        panel.add(female);

        JLabel dobLabel = new JLabel("DOB");
        dobLabel.setBounds(40,205,140,30);
        dobLabel.setFont(f);
        panel.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(210,205,320,30);
        panel.add(dobField);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(40,250,140,30);
        phoneLabel.setFont(f);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(210,250,320,30);
        panel.add(phoneField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(40,295,140,30);
        emailLabel.setFont(f);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(210,295,320,30);
        panel.add(emailField);

        JLabel deptLabel = new JLabel("Department");
        deptLabel.setBounds(40,340,140,30);
        deptLabel.setFont(f);
        panel.add(deptLabel);

        deptBox = new JComboBox<>(new String[]{
                "Information Technology",
                "Computer Science",
                "Commerce"
        });

        deptBox.setBounds(210,340,320,30);
        panel.add(deptBox);

        JLabel courseLabel = new JLabel("Course");
        courseLabel.setBounds(40,385,140,30);
        courseLabel.setFont(f);
        panel.add(courseLabel);

        courseBox = new JComboBox<>();
        courseBox.setBounds(210,385,320,30);
        panel.add(courseBox);

        updateCourses("Information Technology");

        deptBox.addActionListener(e -> {
            updateCourses(deptBox.getSelectedItem().toString());
        });

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(40,430,140,30);
        addressLabel.setFont(f);
        panel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(210,430,320,30);
        panel.add(addressField);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(40,475,140,30);
        userLabel.setFont(f);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(210,475,320,30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(40,520,140,30);
        passLabel.setFont(f);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(210,520,320,30);
        panel.add(passwordField);
        JLabel confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setBounds(40,565,140,30);
        confirmLabel.setFont(f);
        panel.add(confirmLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(210,565,320,30);
        panel.add(confirmPasswordField);

        updateButton = new JButton("UPDATE");
        updateButton.setBounds(60,610,120,40);
        updateButton.setBackground(new Color(40,167,69));
        updateButton.setForeground(Color.WHITE);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(250,610,120,40);
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);

        backButton = new JButton("BACK");
        backButton.setBounds(440,610,120,40);
        backButton.setBackground(new Color(220,53,69));
        backButton.setForeground(Color.WHITE);

        panel.add(updateButton);
        panel.add(clearButton);
        panel.add(backButton);

        frame.add(panel);

        // ================= SEARCH =================

        searchButton.addActionListener(e -> {

            try {

                Connection con = DBconnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM students WHERE id=?");

                ps.setInt(1, Integer.parseInt(idField.getText()));

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    nameField.setText(rs.getString("name"));

                    if (rs.getString("gender").equalsIgnoreCase("Male"))
                        male.setSelected(true);
                    else
                        female.setSelected(true);

                    dobField.setText(rs.getString("dob"));
                    phoneField.setText(rs.getString("phone"));
                    emailField.setText(rs.getString("email"));

                    addressField.setText(rs.getString("address"));

                    usernameField.setText(rs.getString("username"));

                    passwordField.setText(rs.getString("password"));
                    confirmPasswordField.setText(rs.getString("password"));

                    String course = rs.getString("course");

                    if(course.equals("Java") || course.equals("Python") || course.equals("Web Development")){

                        deptBox.setSelectedItem("Information Technology");

                    }else if(course.equals("C Programming") ||
                             course.equals("Data Structures") ||
                             course.equals("Artificial Intelligence")){

                        deptBox.setSelectedItem("Computer Science");

                    }else{

                        deptBox.setSelectedItem("Commerce");

                    }

                    updateCourses(deptBox.getSelectedItem().toString());

                    courseBox.setSelectedItem(course);

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "Student Not Found");

                }

                con.close();

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        });

        // ================= UPDATE =================

        updateButton.addActionListener(e -> {

            String pass = new String(passwordField.getPassword());
            String confirm = new String(confirmPasswordField.getPassword());

            if(!pass.equals(confirm)){

                JOptionPane.showMessageDialog(frame,
                        "Passwords do not match!");

                return;

            }

            try{

                Connection con = DBconnection.getConnection();

                PreparedStatement ps = con.prepareStatement(

                        "UPDATE students SET name=?,gender=?,dob=?,phone=?,email=?,course=?,username=?,password=?,address=? WHERE id=?"

                );

                ps.setString(1,nameField.getText());

                ps.setString(2,
                        male.isSelected() ? "Male" : "Female");

                ps.setString(3,dobField.getText());
                ps.setString(4,phoneField.getText());
                ps.setString(5,emailField.getText());

                ps.setString(6,
                        courseBox.getSelectedItem().toString());

                ps.setString(7,usernameField.getText());

                ps.setString(8,pass);

                ps.setString(9,addressField.getText());

                ps.setInt(10,
                        Integer.parseInt(idField.getText()));

                int x = ps.executeUpdate();

                if(x>0){

                    JOptionPane.showMessageDialog(frame,
                            "Student Updated Successfully");

                }

                con.close();

            }catch(Exception ex){

                ex.printStackTrace();

            }

        });
        // ================= CLEAR =================

        clearButton.addActionListener(e -> {

            idField.setText("");
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
            courseBox.setSelectedIndex(0);

        });

        // ================= BACK =================

        backButton.addActionListener(e -> {

            frame.dispose();
            new AdminDashboard();

        });

        frame.setVisible(true);

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

        SwingUtilities.invokeLater(() -> new UpdateStudent());

    }

}