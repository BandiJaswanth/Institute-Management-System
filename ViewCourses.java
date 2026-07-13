package InstitutionManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewCourses {

    JFrame frame;
    JTable table;
    DefaultTableModel model;

    JTextField searchField;

    JButton searchButton;
    JButton refreshButton;
    JButton deleteButton;
    JButton backButton;

    ViewCourses() {

        frame = new JFrame("View Courses");
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("ALL COURSES",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));

        frame.add(title,BorderLayout.NORTH);

        JPanel topPanel = new JPanel();

        JLabel searchLabel = new JLabel("Course Name");

        searchField = new JTextField(20);

        searchButton = new JButton("SEARCH");
        searchButton.setBackground(new Color(25,118,210));
        searchButton.setForeground(Color.WHITE);

        refreshButton = new JButton("REFRESH");
        refreshButton.setBackground(new Color(40,167,69));
        refreshButton.setForeground(Color.WHITE);

        topPanel.add(searchLabel);
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(refreshButton);

        frame.add(topPanel,BorderLayout.SOUTH);

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

        JPanel buttonPanel = new JPanel();

        deleteButton = new JButton("DELETE");

        deleteButton.setBackground(new Color(220,53,69));
        deleteButton.setForeground(Color.WHITE);

        backButton = new JButton("BACK");

        backButton.setBackground(Color.GRAY);
        backButton.setForeground(Color.WHITE);

        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);

        frame.add(buttonPanel,BorderLayout.EAST);

        loadCourses();

        refreshButton.addActionListener(e->{

            loadCourses();

        });

        searchButton.addActionListener(e->{

            try{

                model.setRowCount(0);

                Connection con=DBconnection.getConnection();

                PreparedStatement ps=con.prepareStatement(

                "select * from courses where course_name like ?"

                );

                ps.setString(1,"%"+searchField.getText()+"%");

                ResultSet rs=ps.executeQuery();

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

        });
        deleteButton.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row == -1){

                JOptionPane.showMessageDialog(frame,"Please Select a Course");
                return;

            }

            int id = Integer.parseInt(model.getValueAt(row,0).toString());

            int option = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to delete this course?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if(option == JOptionPane.YES_OPTION){

                try{

                    Connection con = DBconnection.getConnection();

                    PreparedStatement ps = con.prepareStatement(
                            "DELETE FROM courses WHERE course_id=?");

                    ps.setInt(1,id);

                    int x = ps.executeUpdate();

                    if(x>0){

                        JOptionPane.showMessageDialog(frame,
                                "Course Deleted Successfully");

                        loadCourses();

                    }

                    con.close();

                }
                catch(Exception ex){

                    ex.printStackTrace();

                }

            }

        });

        backButton.addActionListener(e -> {

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
                    "SELECT * FROM courses");

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

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new ViewCourses();

        });

    }

}