package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EmployeeChangePassword {

    JFrame frame;

    JPasswordField oldPass,newPass,confirmPass;

    JButton update,back;

    String username;

    EmployeeChangePassword(String username){

        this.username=username;

        frame=new JFrame("Change Password");

        frame.setSize(550,420);

        frame.setLocationRelativeTo(null);

        frame.setLayout(null);

        JLabel title=new JLabel("Change Password");

        title.setBounds(140,20,300,35);

        title.setFont(new Font("Segoe UI",Font.BOLD,28));

        title.setForeground(new Color(25,118,210));

        frame.add(title);

        JLabel l1=new JLabel("Old Password");

        l1.setBounds(50,90,150,30);

        frame.add(l1);

        oldPass=new JPasswordField();

        oldPass.setBounds(220,90,220,30);

        frame.add(oldPass);

        JLabel l2=new JLabel("New Password");

        l2.setBounds(50,150,150,30);

        frame.add(l2);

        newPass=new JPasswordField();

        newPass.setBounds(220,150,220,30);

        frame.add(newPass);

        JLabel l3=new JLabel("Confirm Password");

        l3.setBounds(50,210,150,30);

        frame.add(l3);

        confirmPass=new JPasswordField();

        confirmPass.setBounds(220,210,220,30);

        frame.add(confirmPass);

        update=new JButton("Update");

        update.setBounds(110,290,120,40);

        update.setBackground(new Color(25,118,210));

        update.setForeground(Color.WHITE);

        frame.add(update);

        back=new JButton("Back");

        back.setBounds(270,290,120,40);

        back.setBackground(Color.GRAY);

        back.setForeground(Color.WHITE);

        frame.add(back);

        back.addActionListener(e->frame.dispose());

        update.addActionListener(e->{

            try{

                Connection con=DBconnection.getConnection();

                PreparedStatement ps=con.prepareStatement(
                        "select password from employ where username=?");

                ps.setString(1,username);

                ResultSet rs=ps.executeQuery();

                if(rs.next()){

                    String db=rs.getString("password");

                    if(!db.equals(new String(oldPass.getPassword()))){

                        JOptionPane.showMessageDialog(frame,"Old Password Incorrect");

                        return;

                    }

                    if(!new String(newPass.getPassword()).equals(new String(confirmPass.getPassword()))){

                        JOptionPane.showMessageDialog(frame,"Passwords Not Match");

                        return;

                    }

                    PreparedStatement ps2=con.prepareStatement(
                            "update employ set password=? where username=?");

                    ps2.setString(1,new String(newPass.getPassword()));

                    ps2.setString(2,username);

                    ps2.executeUpdate();

                    JOptionPane.showMessageDialog(frame,"Password Updated Successfully");

                    frame.dispose();

                }

                con.close();

            }catch(Exception ex){

                ex.printStackTrace();

            }

        });

        frame.setVisible(true);

    }

}