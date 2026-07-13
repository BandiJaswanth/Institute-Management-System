package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentChangePassword {

    JFrame frame;

    JPasswordField oldPass, newPass, confirmPass;
    JButton updateBtn, backBtn;

    String username;

    StudentChangePassword(String username){

        this.username = username;

        frame = new JFrame("Change Password");
        frame.setSize(550,400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245,247,250));

        JLabel title = new JLabel("Change Password");
        title.setBounds(140,20,300,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));
        frame.add(title);

        JLabel l1 = new JLabel("Old Password");
        l1.setBounds(50,90,150,30);
        frame.add(l1);

        oldPass = new JPasswordField();
        oldPass.setBounds(220,90,220,30);
        frame.add(oldPass);

        JLabel l2 = new JLabel("New Password");
        l2.setBounds(50,150,150,30);
        frame.add(l2);

        newPass = new JPasswordField();
        newPass.setBounds(220,150,220,30);
        frame.add(newPass);

        JLabel l3 = new JLabel("Confirm Password");
        l3.setBounds(50,210,150,30);
        frame.add(l3);

        confirmPass = new JPasswordField();
        confirmPass.setBounds(220,210,220,30);
        frame.add(confirmPass);

        updateBtn = new JButton("UPDATE");
        updateBtn.setBounds(110,290,120,40);
        updateBtn.setBackground(new Color(40,167,69));
        updateBtn.setForeground(Color.WHITE);
        frame.add(updateBtn);

        backBtn = new JButton("BACK");
        backBtn.setBounds(270,290,120,40);
        backBtn.setBackground(Color.GRAY);
        backBtn.setForeground(Color.WHITE);
        frame.add(backBtn);

        backBtn.addActionListener(e -> frame.dispose());

        updateBtn.addActionListener(e -> {

            try {

                Connection con = DBconnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT password FROM students WHERE username=?"
                );

                ps.setString(1, username);

                ResultSet rs = ps.executeQuery();

                if(rs.next()){

                    String dbPass = rs.getString("password");

                    if(!dbPass.equals(new String(oldPass.getPassword()))){

                        JOptionPane.showMessageDialog(frame, "Old password incorrect");
                        return;
                    }

                    String newP = new String(newPass.getPassword());
                    String confirm = new String(confirmPass.getPassword());

                    if(!newP.equals(confirm)){

                        JOptionPane.showMessageDialog(frame, "Passwords do not match");
                        return;
                    }

                    PreparedStatement ps2 = con.prepareStatement(
                            "UPDATE students SET password=? WHERE username=?"
                    );

                    ps2.setString(1, newP);
                    ps2.setString(2, username);

                    ps2.executeUpdate();

                    JOptionPane.showMessageDialog(frame, "Password updated successfully");

                    frame.dispose();

                }

                con.close();

            } catch(Exception ex){
                ex.printStackTrace();
            }

        });

        frame.setVisible(true);
    }
}