package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EmployeeDashboard {

    JFrame frame;

    JButton profileButton;
    JButton studentsButton;
    JButton coursesButton;
    JButton noticeButton;
    JButton changePasswordButton;
    JButton logoutButton;

    String username;

    EmployeeDashboard(String username){

        this.username = username;

        String department = "";
        String status = "";
        String totalStudents = "0";
        String totalCourses = "0";

        try{

            Connection con = DBconnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT department,status FROM employ WHERE username=?");

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                department = rs.getString("department");
                status = rs.getString("status");

            }

            PreparedStatement ps2 = con.prepareStatement(
                    "SELECT COUNT(*) FROM student WHERE department=?");

            ps2.setString(1, department);

            ResultSet rs2 = ps2.executeQuery();

            if(rs2.next()){

                totalStudents = rs2.getString(1);

            }

            PreparedStatement ps3 = con.prepareStatement(
                    "SELECT COUNT(*) FROM cours WHERE department=?");

            ps3.setString(1, department);

            ResultSet rs3 = ps3.executeQuery();

            if(rs3.next()){

                totalCourses = rs3.getString(1);

            }

            con.close();

        }catch(Exception ex){

            ex.printStackTrace();

        }

        frame = new JFrame("Greenfield Institute - Employee Dashboard");
        frame.setSize(1300,700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        JPanel left = new JPanel();
        left.setBounds(0,0,260,700);
        left.setBackground(new Color(25,118,210));
        left.setLayout(null);

        JLabel title = new JLabel("Greenfield");
        title.setBounds(40,25,200,35);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));

        JLabel emp = new JLabel("EMPLOYEE PANEL");
        emp.setBounds(35,65,220,30);
        emp.setForeground(Color.WHITE);
        emp.setFont(new Font("Segoe UI",Font.BOLD,18));

        left.add(title);
        left.add(emp);

        profileButton = createButton("My Profile",140);
        studentsButton = createButton("Students",190);
        coursesButton = createButton("Courses",240);
        noticeButton = createButton("Notice Board",290);
        changePasswordButton = createButton("Change Password",340);
        logoutButton = createButton("Logout",590);

        left.add(profileButton);
        left.add(studentsButton);
        left.add(coursesButton);
        left.add(noticeButton);
        left.add(changePasswordButton);
        left.add(logoutButton);

        JPanel right = new JPanel();
        right.setBounds(260,0,1040,700);
        right.setBackground(new Color(245,247,250));
        right.setLayout(null);

        JLabel welcome = new JLabel("Welcome Employee");
        welcome.setBounds(300,35,450,40);
        welcome.setFont(new Font("Segoe UI",Font.BOLD,34));
        welcome.setForeground(new Color(25,118,210));

        right.add(welcome);

        JPanel info = new JPanel();
        info.setBounds(70,120,900,280);
        info.setBackground(Color.WHITE);
        info.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
        info.setLayout(null);

        JLabel welcome1 = new JLabel("Welcome to Greenfield Institute");
        welcome1.setBounds(180,20,550,35);
        welcome1.setFont(new Font("Segoe UI",Font.BOLD,30));
        welcome1.setForeground(new Color(25,118,210));

        JLabel name = new JLabel("Username : " + username);
        name.setBounds(80,90,350,30);
        name.setFont(new Font("Segoe UI",Font.BOLD,20));

        JLabel dept = new JLabel("Department : " + department);
        dept.setBounds(80,140,350,30);
        dept.setFont(new Font("Segoe UI",Font.BOLD,20));

        JLabel stat = new JLabel("Status : " + status);
        stat.setBounds(80,190,350,30);
        stat.setFont(new Font("Segoe UI",Font.BOLD,20));

        JLabel wish = new JLabel("\"A good teacher inspires hope, ignites imagination and instills a love of learning.\"");
        wish.setBounds(80,240,760,30);
        welcome1.setForeground(new Color(25,118,210));
        wish.setFont(new Font("Segoe UI",Font.ITALIC,18));
        wish.setForeground(Color.GRAY);

        info.add(welcome1);
        info.add(name);
        info.add(dept);
        info.add(stat);
        info.add(wish);
        
         right.add(info);
        JTextArea quote = new JTextArea(
                "“Education is the most powerful weapon which you can use to change the world.”\n\n"
             
        );
        welcome1.setForeground(new Color(25,118,210));
        quote.setBounds(90,430,860,180);
        quote.setEditable(false);
        quote.setLineWrap(true);
        quote.setWrapStyleWord(true);
        quote.setOpaque(false);
        quote.setFont(new Font("Segoe UI",Font.PLAIN,20));
        quote.setForeground(Color.DARK_GRAY);

        right.add(quote);
        frame.add(left);
        frame.add(right);
        profileButton.addActionListener(e -> {

            new EmployeeProfile(username);

        });

        studentsButton.addActionListener(e -> {

            new ViewStudents();

        });

        coursesButton.addActionListener(e -> {

            new ViewCourses();

        });

        noticeButton.addActionListener(e -> {

            new EmployeeNotice(username);

        });

        changePasswordButton.addActionListener(e -> {

            new EmployeeChangePassword(username);

        });

        logoutButton.addActionListener(e -> {

            frame.dispose();
            new Home();

        });

        frame.setVisible(true);

    }

    JButton createButton(String text,int y){

        JButton b = new JButton(text);

        b.setBounds(20,y,210,40);

        b.setBackground(Color.WHITE);

        b.setForeground(new Color(25,118,210));

        b.setFocusPainted(false);

        b.setFont(new Font("Segoe UI",Font.BOLD,15));

        return b;

    }

    JPanel createCard(String title,String value){

        JPanel p = new JPanel();

        p.setLayout(null);

        p.setBackground(Color.WHITE);

        p.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));

        JLabel t = new JLabel(title,SwingConstants.CENTER);

        t.setBounds(0,20,220,30);

        t.setFont(new Font("Segoe UI",Font.BOLD,18));

        t.setForeground(new Color(25,118,210));

        JLabel v = new JLabel(value,SwingConstants.CENTER);

        v.setBounds(0,60,220,35);

        v.setFont(new Font("Segoe UI", Font.BOLD, 18));

        v.setForeground(Color.DARK_GRAY);

        p.add(t);

        p.add(v);

        return p;

    }

    public static void main(String args[]){

        SwingUtilities.invokeLater(() -> new EmployeeDashboard("jaswanthbandi"));

    }

}