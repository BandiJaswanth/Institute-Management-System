package InstitutionManagement;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class AdminDashboard {

    JFrame frame;

    JButton dashboardButton;

    JButton approveStudentButton;
    JButton viewStudentButton;
    JButton addStudentButton;
    JButton updateStudentButton;
    JButton deleteStudentButton;

    JButton approveEmployeeButton;
    JButton viewEmployeeButton;
    JButton addEmployeeButton;
    JButton updateEmployeeButton;
    JButton deleteEmployeeButton;

    JButton addCourseButton;
    JButton updateCourseButton;
    JButton removeCourseButton;
    JButton viewCourseButton;

    JButton logoutButton;

    AdminDashboard(){

        frame = new JFrame("Greenfield Institute - Admin Dashboard");
        frame.setSize(1400,760);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        //================ LEFT PANEL =================

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(25,118,210));
        leftPanel.setPreferredSize(new Dimension(280,1050));

        JLabel college = new JLabel("Greenfield");
        college.setBounds(45,20,220,35);
        college.setForeground(Color.WHITE);
        college.setFont(new Font("Segoe UI",Font.BOLD,28));

        JLabel admin = new JLabel("ADMIN PANEL");
        admin.setBounds(65,60,180,25);
        admin.setForeground(Color.WHITE);
        admin.setFont(new Font("Segoe UI",Font.BOLD,17));

        leftPanel.add(college);
        leftPanel.add(admin);

        int y = 110;

        dashboardButton = createButton("Dashboard",y);
        leftPanel.add(dashboardButton);

        y += 50;

        //================ STUDENT =================

        JLabel stu = new JLabel("STUDENT MANAGEMENT");
        stu.setBounds(20,y,240,25);
        stu.setForeground(Color.WHITE);
        stu.setFont(new Font("Segoe UI",Font.BOLD,14));
        leftPanel.add(stu);

        y += 30;

        approveStudentButton = createButton("Approve Students",y);
        leftPanel.add(approveStudentButton);
        y += 40;

        viewStudentButton = createButton("View Students",y);
        leftPanel.add(viewStudentButton);
        y += 40;

        addStudentButton = createButton("Add Student",y);
        leftPanel.add(addStudentButton);
        y += 40;

        updateStudentButton = createButton("Update Student",y);
        leftPanel.add(updateStudentButton);
        y += 40;

        deleteStudentButton = createButton("Delete Student",y);
        leftPanel.add(deleteStudentButton);

        y += 55;

        //================ EMPLOYEE =================

        JLabel emp = new JLabel("EMPLOYEE MANAGEMENT");
        emp.setBounds(20,y,240,25);
        emp.setForeground(Color.WHITE);
        emp.setFont(new Font("Segoe UI",Font.BOLD,14));
        leftPanel.add(emp);

        y += 30;

        approveEmployeeButton = createButton("Approve Employees",y);
        leftPanel.add(approveEmployeeButton);
        y += 40;

        viewEmployeeButton = createButton("View Employees",y);
        leftPanel.add(viewEmployeeButton);
        y += 40;

        addEmployeeButton = createButton("Add Employee",y);
        leftPanel.add(addEmployeeButton);
        y += 40;

        updateEmployeeButton = createButton("Update Employee",y);
        leftPanel.add(updateEmployeeButton);
        y += 40;

        deleteEmployeeButton = createButton("Delete Employee",y);
        leftPanel.add(deleteEmployeeButton);

        y += 55;

        //================ COURSE =================

        JLabel course = new JLabel("COURSE MANAGEMENT");
        course.setBounds(20,y,240,25);
        course.setForeground(Color.WHITE);
        course.setFont(new Font("Segoe UI",Font.BOLD,14));
        leftPanel.add(course);

        y += 30;

        addCourseButton = createButton("Add Course",y);
        leftPanel.add(addCourseButton);
        y += 40;

        updateCourseButton = createButton("Update Course",y);
        leftPanel.add(updateCourseButton);
        y += 40;

        removeCourseButton = createButton("Remove Course",y);
        leftPanel.add(removeCourseButton);
        y += 40;

        viewCourseButton = createButton("View Courses",y);
        leftPanel.add(viewCourseButton);
        y += 50;

        logoutButton = createButton("Logout",y);
        leftPanel.add(logoutButton);

        JScrollPane scroll = new JScrollPane(leftPanel);
        scroll.setBounds(0,0,300,760);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);

        frame.add(scroll);
        //================ RIGHT PANEL =================

        JPanel rightPanel = new JPanel();
        rightPanel.setBounds(300,0,1100,760);
        rightPanel.setBackground(new Color(245,247,250));
        rightPanel.setLayout(null);
        JLabel heading = new JLabel("Welcome Administrator");
        heading.setBounds(300,30,500,40);
        heading.setFont(new Font("Segoe UI",Font.BOLD,36));
        heading.setForeground(new Color(25,118,210));

        JLabel sub = new JLabel("Greenfield Institute Management System");
        sub.setBounds(295,80,480,25);
        sub.setFont(new Font("Segoe UI",Font.PLAIN,20));
        sub.setForeground(Color.DARK_GRAY);

        rightPanel.add(heading);
        rightPanel.add(sub);

        

        //================ COLOURFUL QUOTES =================

        JLabel q1 = new JLabel("“Education is the passport to the future");
        q1.setBounds(170,430,800,30);
        q1.setFont(new Font("Segoe UI",Font.BOLD,28));
        q1.setForeground(new Color(25,118,210));

        JLabel q2 = new JLabel("Knowledge empowers every generation");
        q2.setBounds(250,470,650,30);
        q2.setFont(new Font("Segoe UI",Font.BOLD,24));
        q2.setForeground(new Color(25,118,210));

        JLabel q3 = new JLabel("Lead • Inspire • Organize • Achieve");
        q3.setBounds(250,515,650,30);
        q3.setFont(new Font("Segoe UI",Font.BOLD,24));
        q3.setForeground(new Color(25,118,210));

        JLabel q4 = new JLabel("Every great institution begins with great leadership.");
        q4.setBounds(120,560,900,30);
        q4.setFont(new Font("Segoe UI",Font.BOLD,24));
        q4.setForeground(new Color(25,118,210));

        JLabel q5 = new JLabel("Have a Productive Day, Administrator!");
        q5.setBounds(230,610,650,35);
        q5.setFont(new Font("Segoe UI",Font.BOLD,28));
        q5.setForeground(new Color(25,118,210));

        rightPanel.add(q1);
        rightPanel.add(q2);
        rightPanel.add(q3);
        rightPanel.add(q4);
        rightPanel.add(q5);

        frame.add(rightPanel);

        //================ ACTIONS =================
        addCourseButton.addActionListener(e -> {
            new AddCourse();
        });

        removeCourseButton.addActionListener(e -> {
            new RemoveCourse();
        });

        viewCourseButton.addActionListener(e -> {
            new ViewCourses();
        });
        updateCourseButton.addActionListener(e -> {
            new UpdateCourse();
        });
        approveStudentButton.addActionListener(e -> {
            new ApproveStudent();
        });

        viewStudentButton.addActionListener(e -> {
            new ViewStudents();
        });

        addStudentButton.addActionListener(e -> {
            new AddStudent();
        });

        updateStudentButton.addActionListener(e -> {
            new UpdateStudent();
        });

        deleteStudentButton.addActionListener(e -> {
            new DeleteStudent();
        });

        approveEmployeeButton.addActionListener(e -> {
            new ApproveEmploy();
        });

        viewEmployeeButton.addActionListener(e -> {
            new ViewEmployees();
        });

        addEmployeeButton.addActionListener(e -> {
            new AddEmployee();
        });

        updateEmployeeButton.addActionListener(e -> {
            new UpdateEmployee();
        });

        deleteEmployeeButton.addActionListener(e -> {
            new DeleteEmployee();
        });

        logoutButton.addActionListener(e -> {
            frame.dispose();
            new Home();
        });

        frame.setVisible(true);
    }

    //================ BUTTON =================

    JButton createButton(String text,int y){

        JButton b = new JButton(text);

        b.setBounds(20,y,240,35);
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(25,118,210));
        b.setFont(new Font("Segoe UI",Font.BOLD,14));
        b.setFocusPainted(false);

        return b;
    }

    //================ CARD =================

    JPanel createCard(String title,String value){

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(220,220,220),1,true));

        JLabel t = new JLabel(title,SwingConstants.CENTER);
        t.setBounds(0,20,220,30);
        t.setFont(new Font("Segoe UI",Font.BOLD,18));
        t.setForeground(new Color(25,118,210));

        JLabel v = new JLabel(value,SwingConstants.CENTER);
        v.setBounds(0,60,220,35);
        v.setFont(new Font("Segoe UI",Font.BOLD,30));
        v.setForeground(Color.DARK_GRAY);

        panel.add(t);
        panel.add(v);

        return panel;
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> new AdminDashboard());

    }
}