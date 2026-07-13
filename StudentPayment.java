package InstitutionManagement;

import javax.swing.*;
import java.awt.*;

import java.sql.*;

public class StudentPayment {

    JFrame frame;

    JComboBox<String> courseBox;
    JLabel feeLabel;

    JRadioButton upiBtn, cardBtn;
    JTextField paymentField;

    JButton payButton, backButton;

    String username;

	private JLabel courseLabel;

	private JLabel statusLabel;

    public StudentPayment(String username) {

        this.username = username;

        frame = new JFrame("Student Payment");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("PAYMENT");
        title.setBounds(240, 20, 200, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        frame.add(title);

        // COURSE
        JLabel c1 = new JLabel("Course");
        c1.setBounds(50, 80, 100, 25);
        frame.add(c1);

     // Course ComboBox
        courseBox = new JComboBox<>();
        courseBox.setBounds(220, 60, 250, 30);
        frame.add(courseBox);

        // Fee Label
        feeLabel = new JLabel("₹ 0");
        feeLabel.setBounds(220, 110, 250, 30);
        feeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        frame.add(feeLabel);

        // Load courses from database
        loadCourses();

        // Show fee for first course
        if (courseBox.getItemCount() > 0) {
            courseBox.setSelectedIndex(0);
            loadFee();
        }

        // Whenever user changes course, update fee
        courseBox.addActionListener(e -> loadFee());
        


        // PAYMENT TYPE
        upiBtn = new JRadioButton("UPI");
        cardBtn = new JRadioButton("CARD");

        upiBtn.setBounds(180, 160, 80, 25);
        cardBtn.setBounds(280, 160, 80, 25);

        ButtonGroup bg = new ButtonGroup();
        bg.add(upiBtn);
        bg.add(cardBtn);

        frame.add(upiBtn);
        frame.add(cardBtn);

        // INPUT
        JLabel p1 = new JLabel("UPI / Card No");
        p1.setBounds(50, 200, 120, 25);
        frame.add(p1);

        paymentField = new JTextField();
        paymentField.setBounds(180, 200, 300, 25);
        frame.add(paymentField);

        // BUTTONS
        payButton = new JButton("PAY");
        payButton.setBounds(150, 270, 100, 35);
        frame.add(payButton);

        backButton = new JButton("BACK");
        backButton.setBounds(280, 270, 100, 35);
        frame.add(backButton);

     // Load courses only once
        loadCourses();

        if (courseBox.getItemCount() > 0) {
            courseBox.setSelectedIndex(0);
            loadFee();
        }

        courseBox.addActionListener(e -> loadFee());
        // ACTIONS
        payButton.addActionListener(e -> makePayment());

        backButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    // ================= LOAD COURSES =================
    void loadCourses() {

        try {

            courseBox.removeAllItems();

            Connection con = DBconnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT course_name FROM courses");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                courseBox.addItem(rs.getString("course_name"));

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ================= LOAD FEE =================
    void loadFee() {

        try {

            if (courseBox.getSelectedItem() == null) {
                feeLabel.setText("₹ 0");
                return;
            }

            Connection con = DBconnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT course_fee FROM courses WHERE course_name=?");

            ps.setString(1, courseBox.getSelectedItem().toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                feeLabel.setText("₹ " + rs.getString("course_fee"));

            } else {

                feeLabel.setText("₹ 0");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ================= PAYMENT =================
    void makePayment() {

        String course = courseBox.getSelectedItem().toString();
        double amount = Double.parseDouble(
                feeLabel.getText().replace("₹", "").trim()
        );
        String input = paymentField.getText().trim();

        String method;

        // VALIDATION
        if (upiBtn.isSelected()) {

            method = "UPI";

            if (!input.contains("@")) {
                JOptionPane.showMessageDialog(frame, "Invalid UPI ID");
                return;
            }

        } else if (cardBtn.isSelected()) {

            method = "CARD";

            input = input.replaceAll("\\s", "");

            if (!input.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(frame, "Invalid Card Number");
                return;
            }

        } else {
            JOptionPane.showMessageDialog(frame, "Select Payment Method");
            return;
        }

        try {

            Connection con = DBconnection.getConnection();

            // 1. INSERT PAYMENT
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO payments(student_username,course_name,amount,payment_method,payment_ref,status) VALUES(?,?,?,?,?,?)"
            );

            ps.setString(1, username);
            ps.setString(2, course);
            ps.setDouble(3, amount);
            ps.setString(4, method);
            ps.setString(5, input);
            ps.setString(6, "SUCCESS");

            ps.executeUpdate();

            // 2. INSERT ENROLLMENT (THIS FIXES YOUR "MY COURSE" ISSUE)
            PreparedStatement ps2 = con.prepareStatement(
                    "INSERT INTO enrollments(student_username,course_name,status) VALUES(?,?,?)"
            );

            ps2.setString(1, username);
            ps2.setString(2, course);
            ps2.setString(3, "PAID");

            ps2.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Payment Successful");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentPayment("kiran");
    }
}