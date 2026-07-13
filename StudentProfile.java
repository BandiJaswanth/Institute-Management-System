package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentProfile {

    JFrame frame;

    JLabel idLabel,nameLabel,genderLabel,dobLabel,phoneLabel;
    JLabel emailLabel,courseLabel,addressLabel,usernameLabel,statusLabel;

    Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
    Font valueFont = new Font("Segoe UI", Font.PLAIN, 18);

    StudentProfile(String username){

        frame = new JFrame("Student Profile");
        frame.setSize(900,650);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel title = new JLabel("Student Profile");
        title.setBounds(320,20,350,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,30));
        title.setForeground(new Color(25,118,210));
        frame.add(title);

        int y = 90;
        int x1 = 120;
        int x2 = 350;
        int gap = 45;

        idLabel = createRow("Student ID:", x1, x2, y); y+=gap;
        nameLabel = createRow("Full Name:", x1, x2, y); y+=gap;
        genderLabel = createRow("Gender:", x1, x2, y); y+=gap;
        dobLabel = createRow("DOB:", x1, x2, y); y+=gap;
        phoneLabel = createRow("Phone:", x1, x2, y); y+=gap;
        emailLabel = createRow("Email:", x1, x2, y); y+=gap;
        courseLabel = createRow("Course:", x1, x2, y); y+=gap;
        addressLabel = createRow("Address:", x1, x2, y); y+=gap;
        usernameLabel = createRow("Username:", x1, x2, y); y+=gap;
        statusLabel = createRow("Status:", x1, x2, y);

        JButton back = new JButton("Back");
        back.setBounds(350,580,150,40);
        back.setBackground(new Color(25,118,210));
        back.setForeground(Color.WHITE);

        back.addActionListener(e -> frame.dispose());

        frame.add(back);

        loadData(username);

        frame.setVisible(true);
    }

    JLabel createRow(String text, int x1, int x2, int y){

        JLabel l1 = new JLabel(text);
        l1.setBounds(x1,y,180,30);
        l1.setFont(labelFont);
        frame.add(l1);

        JLabel l2 = new JLabel();
        l2.setBounds(x2,y,350,30);
        l2.setFont(valueFont);
        frame.add(l2);

        return l2;
    }

    void loadData(String username){

        try{

            Connection con = DBconnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM students WHERE username=?");

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                idLabel.setText(rs.getString("id"));
                nameLabel.setText(rs.getString("name"));
                genderLabel.setText(rs.getString("gender"));
                dobLabel.setText(rs.getString("dob"));
                phoneLabel.setText(rs.getString("phone"));
                emailLabel.setText(rs.getString("email"));
                courseLabel.setText(rs.getString("course"));
                addressLabel.setText(rs.getString("address"));
                usernameLabel.setText(rs.getString("username"));
                statusLabel.setText(rs.getString("status"));

            }

            con.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}