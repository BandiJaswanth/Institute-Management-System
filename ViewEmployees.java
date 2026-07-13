package InstitutionManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class ViewEmployees {

    JFrame frame;
    JTable table;
    DefaultTableModel model;

    JButton refreshBtn, backBtn;

    ViewEmployees() {

        frame = new JFrame("EduSphere - Employees");
        frame.setSize(1100, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ================= BACKGROUND =================
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // ================= TOP BAR =================
        JPanel topBar = new JPanel();
        topBar.setBackground(new Color(44, 62, 80));
        topBar.setPreferredSize(new Dimension(1100, 70));
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel title = new JLabel("  EMPLOYEE MANAGEMENT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));

        JLabel subtitle = new JLabel("   |  View all registered employees");
        subtitle.setForeground(new Color(180, 180, 180));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        topBar.add(title);
        topBar.add(subtitle);

        // ================= TABLE CARD =================
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Department");
        model.addColumn("Email");
        model.addColumn("Username");
        model.addColumn("Status");

        table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setShowVerticalLines(false);
        table.setGridColor(new Color(230, 230, 230));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(52, 73, 94));
        header.setForeground(Color.WHITE);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder());

        card.add(sp, BorderLayout.CENTER);

        // ================= BOTTOM =================
        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(245, 247, 250));
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 15));

        refreshBtn = new JButton("Refresh");
        backBtn = new JButton("Back");

        styleButton(refreshBtn, new Color(39, 174, 96));
        styleButton(backBtn, new Color(231, 76, 60));

        bottom.add(refreshBtn);
        bottom.add(backBtn);
        backBtn.addActionListener(e -> {
            frame.dispose();
            new AdminDashboard();
        });
        // ================= ADD =================
        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(card, BorderLayout.CENTER);
        mainPanel.add(bottom, BorderLayout.SOUTH);

        frame.setContentPane(mainPanel);

        // ================= LOAD DATA =================
        loadEmployees();

        refreshBtn.addActionListener(e -> loadEmployees());

        backBtn.addActionListener(e -> {
            frame.dispose();
            new AdminDashboard();
        });

        frame.setVisible(true);
    }

    void loadEmployees() {

        try {

            model.setRowCount(0);

            Connection con = DBconnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT employeeId, fullName, department, email, username, status FROM employ"
            );

            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("employeeId"),
                        rs.getString("fullName"),
                        rs.getString("department"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("status")
                });
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void styleButton(JButton b, Color c) {
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setPreferredSize(new Dimension(120, 35));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewEmployees::new);
    }
}