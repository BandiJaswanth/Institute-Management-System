package InstitutionManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ApproveStudent {

    JFrame frame;
    JTable table;
    DefaultTableModel model;

    JButton approveButton;
    JButton refreshButton;
    JButton backButton;

    ApproveStudent() {

        frame = new JFrame("Approve Students");
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Pending Student Approvals", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(25,118,210));
        frame.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("Student ID");
        model.addColumn("Name");
        model.addColumn("Course");
        model.addColumn("Username");
        model.addColumn("Status");

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));

        JScrollPane sp = new JScrollPane(table);
        frame.add(sp, BorderLayout.CENTER);

        JPanel panel = new JPanel();

        approveButton = new JButton("APPROVE");
        approveButton.setBackground(new Color(40,167,69));
        approveButton.setForeground(Color.WHITE);

        refreshButton = new JButton("REFRESH");
        refreshButton.setBackground(new Color(25,118,210));
        refreshButton.setForeground(Color.WHITE);

        backButton = new JButton("BACK");
        backButton.setBackground(new Color(220,53,69));
        backButton.setForeground(Color.WHITE);

        panel.add(approveButton);
        panel.add(refreshButton);
        panel.add(backButton);

        frame.add(panel, BorderLayout.SOUTH);

        loadStudents();

        // REFRESH
        refreshButton.addActionListener(e -> loadStudents());

        // APPROVE
        approveButton.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Please Select Student");
                return;
            }

            int id = Integer.parseInt(model.getValueAt(row, 0).toString());

            try {

                Connection con = DBconnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE students SET status=? WHERE id=?"
                );

                ps.setString(1, "Approved");
                ps.setInt(2, id);

                int x = ps.executeUpdate();

                if (x > 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Student Approved Successfully");
                    loadStudents();
                }

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        // BACK
        backButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    void loadStudents() {

        try {

            model.setRowCount(0);

            Connection con = DBconnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT id, name, course, username, status FROM students WHERE status='pending'"
            );

            while (rs.next()) {

                model.addRow(new Object[] {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getString("username"),
                        rs.getString("status")
                });

            }

            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ApproveStudent());
    }
}