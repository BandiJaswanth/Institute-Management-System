package InstitutionManagement;

import javax.swing.*;
import java.awt.*;

public class EmployeeNotice {

    JFrame frame;

    EmployeeNotice(String username){

        frame = new JFrame("Notice Board");
        frame.setSize(700,500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel title = new JLabel("Institute Notice Board");
        title.setBounds(180,20,350,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(new Color(25,118,210));
        frame.add(title);

        JTextArea area = new JTextArea();

        area.setEditable(false);

        area.setFont(new Font("Segoe UI",Font.PLAIN,18));

        area.setText(
                "• Welcome to Greenfield Institute.\n\n"+
                "• Semester Classes Started.\n\n"+
                "• Internal Exams from August 10.\n\n"+
                "• Staff Meeting on Friday.\n\n"+
                "• Submit Attendance Before 5 PM Daily.");

        JScrollPane sp = new JScrollPane(area);

        sp.setBounds(40,80,600,300);

        frame.add(sp);

        JButton back = new JButton("Back");

        back.setBounds(270,400,120,40);

        back.setBackground(new Color(25,118,210));

        back.setForeground(Color.WHITE);

        back.addActionListener(e->frame.dispose());

        frame.add(back);

        frame.setVisible(true);

    }

}