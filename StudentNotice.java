package InstitutionManagement;

import javax.swing.*;
import java.awt.*;

public class StudentNotice {

    JFrame frame;

    StudentNotice(String username){

        frame = new JFrame("Student Notice Board");
        frame.setSize(750,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245,247,250));

        JLabel title = new JLabel("Institute Notice Board");
        title.setBounds(220,20,400,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));
        frame.add(title);

        JTextArea area = new JTextArea();

        area.setEditable(false);
        area.setFont(new Font("Segoe UI",Font.PLAIN,18));

        area.setText(
                "• Welcome Student to Greenfield Institute.\n\n" +
                "• Classes for new semester started.\n\n" +
                "• Internal Exams scheduled next month.\n\n" +
                "• Fee payment last date is 25th.\n\n" +
                "• Attendance must be above 75%.\n\n" +
                "• Library open from 9 AM to 6 PM."
        );

        JScrollPane sp = new JScrollPane(area);
        sp.setBounds(60,90,620,300);
        frame.add(sp);

        JButton back = new JButton("BACK");
        back.setBounds(300,410,120,35);
        back.setBackground(new Color(25,118,210));
        back.setForeground(Color.WHITE);

        back.addActionListener(e -> frame.dispose());

        frame.add(back);

        frame.setVisible(true);
    }
}