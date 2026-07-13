package InstitutionManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DeleteEmployee {

    JFrame frame;

    JTable table;
    DefaultTableModel model;

    JButton deleteButton;
    JButton refreshButton;
    JButton backButton;

    DeleteEmployee(){

        frame=new JFrame("Delete Employee");
        frame.setSize(950,550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title=new JLabel("DELETE EMPLOYEE",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));
        frame.add(title,BorderLayout.NORTH);

        model=new DefaultTableModel();

        model.addColumn("Employee ID");
        model.addColumn("Name");
        model.addColumn("Department");
        model.addColumn("Phone");
        model.addColumn("Email");

        table=new JTable(model);

        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI",Font.PLAIN,15));
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15));

        JScrollPane sp=new JScrollPane(table);

        frame.add(sp,BorderLayout.CENTER);

        JPanel panel=new JPanel();

        deleteButton=new JButton("DELETE");
        deleteButton.setBackground(new Color(220,53,69));
        deleteButton.setForeground(Color.WHITE);

        refreshButton=new JButton("REFRESH");
        refreshButton.setBackground(new Color(25,118,210));
        refreshButton.setForeground(Color.WHITE);

        backButton=new JButton("BACK");
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);

        panel.add(deleteButton);
        panel.add(refreshButton);
        panel.add(backButton);

        frame.add(panel,BorderLayout.SOUTH);

        loadEmployees();

        refreshButton.addActionListener(e->{

            loadEmployees();

        });
        deleteButton.addActionListener(e->{

            int row=table.getSelectedRow();

            if(row==-1){

                JOptionPane.showMessageDialog(frame,
                        "Please Select Employee");

                return;

            }

            int id=Integer.parseInt(
                    model.getValueAt(row,0).toString());

            int option=JOptionPane.showConfirmDialog(
                    frame,
                    "Delete this Employee?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if(option==JOptionPane.YES_OPTION){

                try{

                    Connection con=DBconnection.getConnection();

                    PreparedStatement ps=con.prepareStatement(
                            "delete from employ where employeeId=?");

                    ps.setInt(1,id);

                    int x=ps.executeUpdate();

                    if(x>0){

                        JOptionPane.showMessageDialog(frame,
                                "Employee Deleted Successfully");

                        loadEmployees();

                    }

                    con.close();

                }

                catch(Exception ex){

                    ex.printStackTrace();

                }

            }

        });

        backButton.addActionListener(e->{

            frame.dispose();
            new AdminDashboard();

        });

        frame.setVisible(true);

    }

    void loadEmployees(){

        try{

            model.setRowCount(0);

            Connection con=DBconnection.getConnection();

            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery(
                    "select employeeId,fullName,department,phone,email from employ");

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("employeeId"),
                        rs.getString("fullName"),
                        rs.getString("department"),
                        rs.getString("phone"),
                        rs.getString("email")

                });

            }

            con.close();

        }

        catch(Exception ex){

            ex.printStackTrace();

        }

    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(() ->{

            new DeleteEmployee();

        });

    }

}