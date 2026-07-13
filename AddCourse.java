package InstitutionManagement;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class AddCourse {

    JFrame frame;

    JTextField courseField;
    JTextField durationField;
    JTextField feeField;
    JTextField trainerField;

    JButton saveButton;
    JButton clearButton;
    JButton backButton;

    AddCourse() {

        frame = new JFrame("Add Course");
        frame.setSize(650,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245,248,255));

        JLabel title=new JLabel("ADD COURSE");
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));
        title.setBounds(220,20,250,40);
        frame.add(title);

        JLabel l1=new JLabel("Course Name");
        l1.setBounds(90,100,150,30);
        l1.setFont(new Font("Segoe UI",Font.BOLD,16));
        frame.add(l1);

        courseField=new JTextField();
        courseField.setBounds(250,100,250,32);
        frame.add(courseField);

        JLabel l2=new JLabel("Duration");
        l2.setBounds(90,150,150,30);
        l2.setFont(new Font("Segoe UI",Font.BOLD,16));
        frame.add(l2);

        durationField=new JTextField();
        durationField.setBounds(250,150,250,32);
        frame.add(durationField);

        JLabel l3=new JLabel("Course Fee");
        l3.setBounds(90,200,150,30);
        l3.setFont(new Font("Segoe UI",Font.BOLD,16));
        frame.add(l3);

        feeField=new JTextField();
        feeField.setBounds(250,200,250,32);
        frame.add(feeField);

        JLabel l4=new JLabel("Trainer");
        l4.setBounds(90,250,150,30);
        l4.setFont(new Font("Segoe UI",Font.BOLD,16));
        frame.add(l4);

        trainerField=new JTextField();
        trainerField.setBounds(250,250,250,32);
        frame.add(trainerField);

        saveButton=new JButton("SAVE");
        saveButton.setBounds(80,350,130,40);
        saveButton.setBackground(new Color(25,118,210));
        saveButton.setForeground(Color.WHITE);

        clearButton=new JButton("CLEAR");
        clearButton.setBounds(250,350,130,40);
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);

        backButton=new JButton("BACK");
        backButton.setBounds(420,350,130,40);
        backButton.setBackground(new Color(233,30,99));
        backButton.setForeground(Color.WHITE);

        frame.add(saveButton);
        frame.add(clearButton);
        frame.add(backButton);

        saveButton.addActionListener(e->saveCourse());

        clearButton.addActionListener(e->{

            courseField.setText("");
            durationField.setText("");
            feeField.setText("");
            trainerField.setText("");

        });

        backButton.addActionListener(e->{

            frame.dispose();
            new AdminDashboard();

        });

        frame.setVisible(true);

    }

    void saveCourse(){

        String name=courseField.getText();
        String duration=durationField.getText();
        String fee=feeField.getText();
        String trainer=trainerField.getText();

        if(name.isEmpty()||duration.isEmpty()||fee.isEmpty()||trainer.isEmpty()){

            JOptionPane.showMessageDialog(frame,"Fill all fields");
            return;

        }

        try{

            Connection con=DBconnection.getConnection();

            PreparedStatement ps=con.prepareStatement("insert into courses(course_name,course_duration,course_fee,course_trainer) values(?,?,?,?)");

            ps.setString(1,name);
            ps.setString(2,duration);
            ps.setDouble(3,Double.parseDouble(fee));
            ps.setString(4,trainer);

            int i=ps.executeUpdate();

            if(i>0){

                JOptionPane.showMessageDialog(frame,"Course Added Successfully");

                courseField.setText("");
                durationField.setText("");
                feeField.setText("");
                trainerField.setText("");

            }

            con.close();

        }

        catch(Exception ex){

            ex.printStackTrace();

        }

    }

    public static void main(String args[]){

        new AddCourse();

    }

}