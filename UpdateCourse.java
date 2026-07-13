package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UpdateCourse {

    JFrame frame;

    JTextField idField;
    JTextField courseField;
    JTextField durationField;
    JTextField feeField;
    JTextField trainerField;

    JButton searchButton;
    JButton updateButton;
    JButton clearButton;
    JButton backButton;

    UpdateCourse(){

        frame=new JFrame("Update Course");
        frame.setSize(700,550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245,247,250));

        JLabel title=new JLabel("UPDATE COURSE");
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));
        title.setBounds(210,20,300,40);
        frame.add(title);

        JLabel idLabel=new JLabel("Course ID");
        idLabel.setBounds(80,90,120,30);
        idLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        frame.add(idLabel);

        idField=new JTextField();
        idField.setBounds(220,90,180,32);
        frame.add(idField);

        searchButton=new JButton("SEARCH");
        searchButton.setBounds(430,90,120,32);
        searchButton.setBackground(new Color(25,118,210));
        searchButton.setForeground(Color.WHITE);
        frame.add(searchButton);

        JLabel nameLabel=new JLabel("Course Name");
        nameLabel.setBounds(80,150,120,30);
        nameLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        frame.add(nameLabel);

        courseField=new JTextField();
        courseField.setBounds(220,150,330,32);
        frame.add(courseField);

        JLabel durationLabel=new JLabel("Duration");
        durationLabel.setBounds(80,210,120,30);
        durationLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        frame.add(durationLabel);

        durationField=new JTextField();
        durationField.setBounds(220,210,330,32);
        frame.add(durationField);

        JLabel feeLabel=new JLabel("Course Fee");
        feeLabel.setBounds(80,270,120,30);
        feeLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        frame.add(feeLabel);

        feeField=new JTextField();
        feeField.setBounds(220,270,330,32);
        frame.add(feeField);

        JLabel trainerLabel=new JLabel("Trainer");
        trainerLabel.setBounds(80,330,120,30);
        trainerLabel.setFont(new Font("Segoe UI",Font.BOLD,16));
        frame.add(trainerLabel);

        trainerField=new JTextField();
        trainerField.setBounds(220,330,330,32);
        frame.add(trainerField);

        updateButton=new JButton("UPDATE");
        updateButton.setBounds(60,420,130,40);
        updateButton.setBackground(new Color(40,167,69));
        updateButton.setForeground(Color.WHITE);

        clearButton=new JButton("CLEAR");
        clearButton.setBounds(220,420,130,40);
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);

        backButton=new JButton("BACK");
        backButton.setBounds(380,420,130,40);
        backButton.setBackground(new Color(220,53,69));
        backButton.setForeground(Color.WHITE);

        frame.add(updateButton);
        frame.add(clearButton);
        frame.add(backButton);

        searchButton.addActionListener(e->{

            try{

                Connection con=DBconnection.getConnection();

                PreparedStatement ps=con.prepareStatement(
                "select * from courses where course_id=?");

                ps.setInt(1,Integer.parseInt(idField.getText()));

                ResultSet rs=ps.executeQuery();

                if(rs.next()){

                    courseField.setText(rs.getString("course_name"));
                    durationField.setText(rs.getString("course_duration"));
                    feeField.setText(rs.getString("course_fee"));
                    trainerField.setText(rs.getString("course_trainer"));

                }
                else{

                    JOptionPane.showMessageDialog(frame,"Course Not Found");

                }

                con.close();

            }

            catch(Exception ex){

                ex.printStackTrace();

            }

        });
        updateButton.addActionListener(e->{

            try{

                Connection con=DBconnection.getConnection();

                PreparedStatement ps=con.prepareStatement(

                "update courses set course_name=?,course_duration=?,course_fee=?,course_trainer=? where course_id=?"

                );

                ps.setString(1,courseField.getText());
                ps.setString(2,durationField.getText());
                ps.setDouble(3,Double.parseDouble(feeField.getText()));
                ps.setString(4,trainerField.getText());
                ps.setInt(5,Integer.parseInt(idField.getText()));

                int x=ps.executeUpdate();

                if(x>0){

                    JOptionPane.showMessageDialog(frame,
                    "Course Updated Successfully");

                }

                con.close();

            }

            catch(Exception ex){

                ex.printStackTrace();

            }

        });

        clearButton.addActionListener(e->{

            idField.setText("");
            courseField.setText("");
            durationField.setText("");
            feeField.setText("");
            trainerField.setText("");

        });

        backButton.addActionListener(e->{

           
            new AdminDashboard();

        });

        frame.setVisible(true);

    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(()->{

            new UpdateCourse();

        });

    }

}