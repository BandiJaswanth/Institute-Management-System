package InstitutionManagement;

import javax.swing.*;
import java.awt.*;

public class StudentDashboard {

    JFrame frame;

    JButton profileButton;
    JButton coursesButton;
    JButton noticeButton;
    JButton paymentButton;
    JButton changePasswordButton;
    JButton logoutButton;

    String username;

    StudentDashboard(String username){

        this.username = username;

        frame = new JFrame("Greenfield Institute - Student Dashboard");
        frame.setSize(1300,700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // LEFT PANEL
        JPanel left = new JPanel();
        left.setBounds(0,0,260,700);
        left.setBackground(new Color(25,118,210));
        left.setLayout(null);

        JLabel title = new JLabel("Greenfield");
        title.setBounds(40,25,200,35);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));

        JLabel stu = new JLabel("STUDENT PANEL");
        stu.setBounds(35,65,220,30);
        stu.setForeground(Color.WHITE);
        stu.setFont(new Font("Segoe UI",Font.BOLD,18));

        left.add(title);
        left.add(stu);

        profileButton = createButton("My Profile",140);
        coursesButton = createButton("My Courses",190);
        noticeButton = createButton("Notice Board",240);
        paymentButton = createButton("Payments",290);
        changePasswordButton = createButton("Change Password",340);
        logoutButton = createButton("Logout",590);

        left.add(profileButton);
        left.add(coursesButton);
        left.add(noticeButton);
        left.add(paymentButton);
        left.add(changePasswordButton);
        left.add(logoutButton);

        // RIGHT PANEL
        JPanel right = new JPanel();
        right.setBounds(260,0,1040,700);
        right.setBackground(new Color(245,247,250));
        right.setLayout(null);

        JLabel welcome = new JLabel("Welcome Student");
        welcome.setBounds(300,35,450,40);
        welcome.setFont(new Font("Segoe UI",Font.BOLD,34));
        welcome.setForeground(new Color(25,118,210));

        JLabel info = new JLabel("Logged in as: " + username);
        info.setBounds(300,90,500,30);
        info.setFont(new Font("Segoe UI",Font.PLAIN,18));

        right.add(welcome);
        right.add(info);

        frame.add(left);
        frame.add(right);

        // ACTIONS

        profileButton.addActionListener(e -> new StudentProfile(username));

        coursesButton.addActionListener(e -> new StudentCourses(username));

        noticeButton.addActionListener(e -> new StudentNotice(username));

        paymentButton.addActionListener(e -> new StudentPayment(username));

        changePasswordButton.addActionListener(e -> new StudentChangePassword(username));

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
        b.setFont(new Font("Segoe UI",Font.BOLD,15));
        return b;
    }

    public static void main(String[] args){
        new StudentDashboard("jaswanthbandi");
    }
}