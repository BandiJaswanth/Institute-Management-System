package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UpdateEmployee {

    JFrame frame;

    JTextField idField,nameField,dobField,phoneField,emailField;
    JTextField experienceField,usernameField,passwordField;

    JComboBox<String> genderBox,departmentBox,qualificationBox;

    JButton searchButton,updateButton,clearButton,backButton;

    UpdateEmployee(){

        frame=new JFrame("Update Employee");
        frame.setSize(750,720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245,247,250));

        JLabel title=new JLabel("UPDATE EMPLOYEE");
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));
        title.setBounds(210,20,350,40);
        frame.add(title);

        Font f=new Font("Segoe UI",Font.BOLD,15);

        JLabel l0=new JLabel("Employee ID");
        l0.setBounds(60,80,130,30);
        l0.setFont(f);
        frame.add(l0);

        idField=new JTextField();
        idField.setBounds(230,80,180,30);
        frame.add(idField);

        searchButton=new JButton("SEARCH");
        searchButton.setBounds(450,80,120,30);
        searchButton.setBackground(new Color(25,118,210));
        searchButton.setForeground(Color.WHITE);
        frame.add(searchButton);

        JLabel l1=new JLabel("Full Name");
        l1.setBounds(60,130,130,30);
        l1.setFont(f);
        frame.add(l1);

        nameField=new JTextField();
        nameField.setBounds(230,130,320,30);
        frame.add(nameField);

        JLabel l2=new JLabel("Gender");
        l2.setBounds(60,175,130,30);
        l2.setFont(f);
        frame.add(l2);

        genderBox=new JComboBox<>(new String[]{"Male","Female"});
        genderBox.setBounds(230,175,320,30);
        frame.add(genderBox);

        JLabel l3=new JLabel("DOB");
        l3.setBounds(60,220,130,30);
        l3.setFont(f);
        frame.add(l3);

        dobField=new JTextField();
        dobField.setBounds(230,220,320,30);
        frame.add(dobField);

        JLabel l4=new JLabel("Phone");
        l4.setBounds(60,265,130,30);
        l4.setFont(f);
        frame.add(l4);

        phoneField=new JTextField();
        phoneField.setBounds(230,265,320,30);
        frame.add(phoneField);

        JLabel l5=new JLabel("Email");
        l5.setBounds(60,310,130,30);
        l5.setFont(f);
        frame.add(l5);

        emailField=new JTextField();
        emailField.setBounds(230,310,320,30);
        frame.add(emailField);

        JLabel l6=new JLabel("Department");
        l6.setBounds(60,355,130,30);
        l6.setFont(f);
        frame.add(l6);

        departmentBox=new JComboBox<>(new String[]{
                "Computer Science",
                "Information Technology",
                "Mathematics",
                "Physics",
                "Commerce"
        });

        departmentBox.setBounds(230,355,320,30);
        frame.add(departmentBox);

        JLabel l7=new JLabel("Qualification");
        l7.setBounds(60,400,130,30);
        l7.setFont(f);
        frame.add(l7);

        qualificationBox=new JComboBox<>(new String[]{
                "B.Tech",
                "M.Tech",
                "BCA",
                "MCA",
                "PhD"
        });

        qualificationBox.setBounds(230,400,320,30);
        frame.add(qualificationBox);

        JLabel l8=new JLabel("Experience");
        l8.setBounds(60,445,130,30);
        l8.setFont(f);
        frame.add(l8);

        experienceField=new JTextField();
        experienceField.setBounds(230,445,320,30);
        frame.add(experienceField);

        JLabel l9=new JLabel("Username");
        l9.setBounds(60,490,130,30);
        l9.setFont(f);
        frame.add(l9);

        usernameField=new JTextField();
        usernameField.setBounds(230,490,320,30);
        frame.add(usernameField);

        JLabel l10=new JLabel("Password");
        l10.setBounds(60,535,130,30);
        l10.setFont(f);
        frame.add(l10);

        passwordField=new JTextField();
        passwordField.setBounds(230,535,320,30);
        frame.add(passwordField);

        updateButton=new JButton("UPDATE");
        updateButton.setBounds(40,610,130,40);
        updateButton.setBackground(new Color(40,167,69));
        updateButton.setForeground(Color.WHITE);

        clearButton=new JButton("CLEAR");
        clearButton.setBounds(220,610,130,40);

        backButton=new JButton("BACK");
        backButton.setBounds(400,610,130,40);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);

        frame.add(updateButton);
        frame.add(clearButton);
        frame.add(backButton);

        searchButton.addActionListener(e->{

            try{

                Connection con=DBconnection.getConnection();

                PreparedStatement ps=con.prepareStatement(
                "select * from employ where employeeId=?");

                ps.setInt(1,Integer.parseInt(idField.getText()));

                ResultSet rs=ps.executeQuery();

                if(rs.next()){

                    nameField.setText(rs.getString("fullName"));
                    genderBox.setSelectedItem(rs.getString("gender"));
                    dobField.setText(rs.getString("dob"));
                    phoneField.setText(rs.getString("phone"));
                    emailField.setText(rs.getString("email"));
                    departmentBox.setSelectedItem(rs.getString("department"));
                    qualificationBox.setSelectedItem(rs.getString("qualification"));
                    experienceField.setText(rs.getString("experience"));
                    usernameField.setText(rs.getString("username"));
                    passwordField.setText(rs.getString("password"));

                }else{

                    JOptionPane.showMessageDialog(frame,"Employee Not Found");

                }

                con.close();

            }catch(Exception ex){

                ex.printStackTrace();

            }
        });

        // ================= UPDATE =================

        updateButton.addActionListener(e -> {

            try {

                Connection con = DBconnection.getConnection();

                PreparedStatement ps = con.prepareStatement(

                        "update employ set fullName=?,gender=?,dob=?,phone=?,email=?,department=?,qualification=?,experience=?,username=?,password=? where employeeId=?"

                );

                ps.setString(1, nameField.getText());
                ps.setString(2, genderBox.getSelectedItem().toString());
                ps.setString(3, dobField.getText());
                ps.setString(4, phoneField.getText());
                ps.setString(5, emailField.getText());
                ps.setString(6, departmentBox.getSelectedItem().toString());
                ps.setString(7, qualificationBox.getSelectedItem().toString());
                ps.setInt(8, Integer.parseInt(experienceField.getText()));
                ps.setString(9, usernameField.getText());
                ps.setString(10, passwordField.getText());
                ps.setInt(11, Integer.parseInt(idField.getText()));

                int x = ps.executeUpdate();

                if (x > 0) {

                    JOptionPane.showMessageDialog(frame,
                            "Employee Updated Successfully");

                } else {

                    JOptionPane.showMessageDialog(frame,
                            "Employee ID Not Found");

                }

                con.close();

            }

            catch (Exception ex) {

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
            experienceField.setText("");
            usernameField.setText("");
            passwordField.setText("");

            genderBox.setSelectedIndex(0);
            departmentBox.setSelectedIndex(0);
            qualificationBox.setSelectedIndex(0);

        });

        // ================= BACK =================

        backButton.addActionListener(e -> {

            frame.dispose();
            new AdminDashboard();

        });

        frame.setVisible(true);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new UpdateEmployee());

    }

}

     
        