package InstitutionManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class RemoveCourse {

    JFrame frame;

    JTable table;
    DefaultTableModel model;

    JButton removeButton;
    JButton refreshButton;
    JButton backButton;

    RemoveCourse() {

        frame = new JFrame("Remove Course");
        frame.setSize(900,550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("REMOVE COURSE",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));

        frame.add(title,BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("Course ID");
        model.addColumn("Course Name");
        model.addColumn("Duration");
        model.addColumn("Fee");
        model.addColumn("Trainer");

        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI",Font.PLAIN,15));
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15));

        JScrollPane sp = new JScrollPane(table);

        frame.add(sp,BorderLayout.CENTER);

        JPanel panel = new JPanel();

        removeButton = new JButton("REMOVE");
        removeButton.setBackground(new Color(220,53,69));
        removeButton.setForeground(Color.WHITE);

        refreshButton = new JButton("REFRESH");
        refreshButton.setBackground(new Color(25,118,210));
        refreshButton.setForeground(Color.WHITE);

        backButton = new JButton("BACK");
        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);

        panel.add(removeButton);
        panel.add(refreshButton);
        panel.add(backButton);

        frame.add(panel,BorderLayout.SOUTH);

        loadCourses();

        refreshButton.addActionListener(e->{

            loadCourses();

        });
        removeButton.addActionListener(e->{

            int row = table.getSelectedRow();

            if(row==-1){

                JOptionPane.showMessageDialog(frame,
                        "Select a Course");

                return;

            }

            int id = Integer.parseInt(
                    model.getValueAt(row,0).toString());

            int option = JOptionPane.showConfirmDialog(
                    frame,
                    "Delete this Course?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);

            if(option==JOptionPane.YES_OPTION){

                try{

                    Connection con = DBconnection.getConnection();

                    PreparedStatement ps =
                            con.prepareStatement(
                            "delete from courses where course_id=?");

                    ps.setInt(1,id);

                    int x = ps.executeUpdate();

                    if(x>0){

                        JOptionPane.showMessageDialog(frame,
                                "Course Removed Successfully");

                        loadCourses();

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

    void loadCourses(){

        try{

            model.setRowCount(0);

            Connection con = DBconnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "select * from courses");

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("course_duration"),
                        rs.getDouble("course_fee"),
                        rs.getString("course_trainer")

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

            new RemoveCourse();

        });

    }

}