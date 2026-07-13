package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class StudentCourses {

    JFrame frame;

    JLabel courseLabel;
    JLabel statusLabel;
    JLabel trainerLabel;
    JLabel feeLabel;

    String username;

    public StudentCourses(String username) {

        this.username = username;

        frame = new JFrame("My Course");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245,247,250));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("MY COURSE");
        title.setBounds(220, 20, 200, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(25,118,210));
        frame.add(title);

        Font f = new Font("Segoe UI", Font.BOLD, 16);

        JLabel l1 = new JLabel("Course:");
        l1.setBounds(50, 90, 120, 25);
        l1.setFont(f);
        frame.add(l1);

        courseLabel = new JLabel("-");
        courseLabel.setBounds(200, 90, 300, 25);
        frame.add(courseLabel);

        JLabel l2 = new JLabel("Status:");
        l2.setBounds(50, 140, 120, 25);
        l2.setFont(f);
        frame.add(l2);

        statusLabel = new JLabel("-");
        statusLabel.setBounds(200, 140, 300, 25);
        frame.add(statusLabel);

        JLabel l3 = new JLabel("Trainer:");
        l3.setBounds(50, 190, 120, 25);
        l3.setFont(f);
        frame.add(l3);

        trainerLabel = new JLabel("-");
        trainerLabel.setBounds(200, 190, 300, 25);
        frame.add(trainerLabel);

        JLabel l4 = new JLabel("Fee:");
        l4.setBounds(50, 240, 120, 25);
        l4.setFont(f);
        frame.add(l4);

        feeLabel = new JLabel("-");
        feeLabel.setBounds(200, 240, 300, 25);
        frame.add(feeLabel);

        JButton refresh = new JButton("REFRESH");
        refresh.setBounds(220, 300, 120, 35);
        refresh.setBackground(new Color(25,118,210));
        refresh.setForeground(Color.WHITE);
        frame.add(refresh);

        refresh.addActionListener(e -> loadCourse());

        loadCourse();

        frame.setVisible(true);
    }

    // ================= LOAD COURSE =================
    void loadCourse() {

        try {

            Connection con = DBconnection.getConnection();

            // STEP 1: GET ENROLLMENT
            PreparedStatement ps = con.prepareStatement(
                    "SELECT course_name, status FROM enrollments WHERE student_username=?"
            );

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String course = rs.getString("course_name");

                courseLabel.setText(course);
                statusLabel.setText(rs.getString("status"));

                // STEP 2: GET COURSE DETAILS
                PreparedStatement ps2 = con.prepareStatement(
                        "SELECT course_trainer, course_fee FROM courses WHERE course_name=?"
                );

                ps2.setString(1, course);

                ResultSet rs2 = ps2.executeQuery();

                if (rs2.next()) {
                    trainerLabel.setText(rs2.getString("course_trainer"));
                    feeLabel.setText(rs2.getString("course_fee"));
                }

            } else {

                courseLabel.setText("No course enrolled");
                statusLabel.setText("-");
                trainerLabel.setText("-");
                feeLabel.setText("-");

            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {
        new StudentCourses("suresh");
    }
}