package InstitutionManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ApproveEmploy {

    JFrame frame;
    JTable table;
    DefaultTableModel model;

    JButton approveButton;
    JButton refreshButton;
    JButton backButton;

    ApproveEmploy() {

        frame = new JFrame("Approve Employees");
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Pending Employee Approvals",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,26));
        title.setForeground(new Color(25,118,210));
        frame.add(title,BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("Employee ID");
        model.addColumn("Full Name");
        model.addColumn("Department");
        model.addColumn("Username");
        model.addColumn("Status");

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI",Font.PLAIN,15));
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15));

        JScrollPane sp = new JScrollPane(table);

        frame.add(sp,BorderLayout.CENTER);

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

        frame.add(panel,BorderLayout.SOUTH);

        loadEmployees();

        refreshButton.addActionListener(e->{

            loadEmployees();

        });

        approveButton.addActionListener(e->{

            int row=table.getSelectedRow();

            if(row==-1){

                JOptionPane.showMessageDialog(frame,"Please Select Employee");

                return;

            }

            int id=Integer.parseInt(model.getValueAt(row,0).toString());

            try{

                Connection con=DBconnection.getConnection();

                PreparedStatement ps=con.prepareStatement(
                        "update employ set status=? where employeeId=?");

                ps.setString(1,"Approved");
                ps.setInt(2,id);

                int x=ps.executeUpdate();

                if(x>0){

                    JOptionPane.showMessageDialog(frame,
                            "Employee Approved Successfully");

                    loadEmployees();

                }

                con.close();

            }
            catch(Exception ex){

                ex.printStackTrace();

            }

        });

        backButton.addActionListener(e->{

            frame.dispose();

        });

        frame.setVisible(true);

    }

    void loadEmployees(){

        try{

            model.setRowCount(0);

            Connection con=DBconnection.getConnection();

            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery(
                    "select employeeId,fullName,department,username,status from employ where status='Pending'");

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("employeeId"),
                        rs.getString("fullName"),
                        rs.getString("department"),
                        rs.getString("username"),
                        rs.getString("status")

                });

            }

            con.close();

        }
        catch(Exception ex){

            ex.printStackTrace();

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new ApproveEmploy());

    }

}