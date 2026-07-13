package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EmployeeProfile {

    JFrame frame;

    JLabel idLabel,nameLabel,genderLabel,dobLabel,phoneLabel;
    JLabel emailLabel,departmentLabel,qualificationLabel;
    JLabel experienceLabel,usernameLabel,statusLabel;
    Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
    Font valueFont = new Font("Segoe UI", Font.PLAIN, 18);

    EmployeeProfile(String username){

        frame=new JFrame("Employee Profile");
        frame.setSize(900,650);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JLabel title=new JLabel("Employee Profile");
        title.setBounds(300,20,350,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,30));
        title.setForeground(new Color(25,118,210));
        frame.add(title);

        JLabel l;

        int y = 90;
        int x1 = 120;
        int x2 = 350;
        int y2 = 100;
        int gap = 45;

        JLabel l1 = new JLabel("Employee ID :");
        l1.setBounds(x1,y,180,30);
        l1.setFont(labelFont);
        frame.add(l1);

        idLabel=new JLabel();
        idLabel.setBounds(x2,y,350,30);
        idLabel.setFont(valueFont);
        frame.add(idLabel);

        y+=gap;

        JLabel l2=new JLabel("Full Name :");
        l2.setBounds(x1,y,180,30);
        l2.setFont(labelFont);
        frame.add(l2);

        nameLabel=new JLabel();
        nameLabel.setBounds(x2,y,350,30);
        nameLabel.setFont(valueFont);
        frame.add(nameLabel);

        y+=gap;

        JLabel l3=new JLabel("Gender :");
        l3.setBounds(x1,y,180,30);
        l3.setFont(labelFont);
        frame.add(l3);

        genderLabel=new JLabel();
        genderLabel.setBounds(x2,y,350,30);
        genderLabel.setFont(valueFont);
        frame.add(genderLabel);

        y+=gap;

        JLabel l4=new JLabel("Date Of Birth :");
        l4.setBounds(x1,y,180,30);
        l4.setFont(labelFont);
        frame.add(l4);

        dobLabel=new JLabel();
        dobLabel.setBounds(x2,y,350,30);
        dobLabel.setFont(valueFont);
        frame.add(dobLabel);

        y+=gap;

        JLabel l5=new JLabel("Phone :");
        l5.setBounds(x1,y,180,30);
        l5.setFont(labelFont);
        frame.add(l5);

        phoneLabel=new JLabel();
        phoneLabel.setBounds(x2,y,350,30);
        phoneLabel.setFont(valueFont);
        frame.add(phoneLabel);

        y+=gap;

        JLabel l6=new JLabel("Email :");
        l6.setBounds(x1,y,180,30);
        l6.setFont(labelFont);
        frame.add(l6);

        emailLabel=new JLabel();
        emailLabel.setBounds(x2,y,350,30);
        emailLabel.setFont(valueFont);
        frame.add(emailLabel);

        y+=gap;

        JLabel l7=new JLabel("Department :");
        l7.setBounds(x1,y,180,30);
        l7.setFont(labelFont);
        frame.add(l7);

        departmentLabel=new JLabel();
        departmentLabel.setBounds(x2,y,350,30);
        departmentLabel.setFont(valueFont);
        frame.add(departmentLabel);

        y+=gap;

        JLabel l8=new JLabel("Qualification :");
        l8.setBounds(x1,y,180,30);
        l8.setFont(labelFont);
        frame.add(l8);

        qualificationLabel=new JLabel();
        qualificationLabel.setBounds(x2,y,350,30);
        qualificationLabel.setFont(valueFont);
        frame.add(qualificationLabel);

        y+=gap;

        JLabel l9=new JLabel("Experience :");
        l9.setBounds(x1,y,180,30);
        l9.setFont(labelFont);
        frame.add(l9);

        experienceLabel=new JLabel();
        experienceLabel.setBounds(x2,y,350,30);
        experienceLabel.setFont(valueFont);
        frame.add(experienceLabel);

        y+=gap;

        JLabel l10=new JLabel("Username :");
        l10.setBounds(x1,y,180,30);
        l10.setFont(labelFont);
        frame.add(l10);

        usernameLabel=new JLabel();
        usernameLabel.setBounds(x2,y,350,30);
        usernameLabel.setFont(valueFont);
        frame.add(usernameLabel);

        y+=gap;

        JLabel l11=new JLabel("Status :");
        l11.setBounds(x1,y,180,30);
        l11.setFont(labelFont);
        frame.add(l11);

        statusLabel=new JLabel();
        statusLabel.setBounds(x2,y,350,30);
        statusLabel.setFont(valueFont);
        frame.add(statusLabel);
        JButton back = new JButton("← Back");
        back.setBounds(350,600,180,45);
        back.setFont(new Font("Segoe UI",Font.BOLD,18));
        back.setBackground(new Color(25,118,210));
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        back.setBorder(BorderFactory.createEmptyBorder());

        back.addActionListener(e->frame.dispose());

        frame.add(back);

        back.addActionListener(e->frame.dispose());

        loadData(username);
       
        System.out.println("Username = " + username);
        frame.setVisible(true);
    }

    void loadData(String username){

        try{

            Connection con = DBconnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM employ WHERE username=?");

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                idLabel.setText(rs.getString("employeeId"));
                nameLabel.setText(rs.getString("fullName"));
                genderLabel.setText(rs.getString("gender"));
                dobLabel.setText(rs.getString("dob"));
                phoneLabel.setText(rs.getString("phone"));
                emailLabel.setText(rs.getString("email"));
                departmentLabel.setText(rs.getString("department"));
                qualificationLabel.setText(rs.getString("qualification"));
                experienceLabel.setText(rs.getString("experience"));
                usernameLabel.setText(rs.getString("username"));
                statusLabel.setText(rs.getString("status"));

            }
            else{

                JOptionPane.showMessageDialog(frame,"Employee Record Not Found!");

            }

            rs.close();
            ps.close();
            con.close();

        }
        catch(Exception ex){

            ex.printStackTrace();

        }

   
    }

    public static void main(String args[]){

    	new EmployeeProfile("jaswanthbandi");

    }

}