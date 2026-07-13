package InstitutionManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Home {	

    JFrame frame;

    JButton adminButton;
    JButton employeeButton;
    JButton studentButton;
    JButton registerEmployeeButton;
    JButton registerStudentButton;

    Home() {

        frame = new JFrame("EduSphere - Institution Management System");
        frame.setSize(1366, 768);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // ================= BACKGROUND =================
        ImageIcon bg = new ImageIcon(Home.class.getResource("/image/edusphere.png"));

        JLabel background = new JLabel(bg);
        background.setLayout(null);

        // ================= LOGIN PANEL =================
        JPanel panel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(255, 255, 255, 180));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 35, 35);

                super.paintComponent(g);
            }
        };

        panel.setOpaque(false);
        panel.setLayout(null);
        panel.setBounds(60, 170, 500, 390);
        background.add(panel);

        // ================= TITLE =================
        JLabel login = new JLabel("LOGIN PORTAL", SwingConstants.CENTER);
        login.setBounds(0, 20, 500, 40);
        login.setFont(new Font("Segoe UI", Font.BOLD, 28));
        login.setForeground(new Color(40, 40, 40));
        panel.add(login);

        // ================= BUTTONS =================
        adminButton = createButton("ADMIN LOGIN", new Color(52, 152, 219));
        employeeButton = createButton("EMPLOYEE LOGIN", new Color(39, 174, 96));
        studentButton = createButton("STUDENT LOGIN", new Color(155, 89, 182));
        registerEmployeeButton = createButton("REGISTER EMPLOYEE", new Color(241, 196, 15));
        registerStudentButton = createButton("REGISTER STUDENT", new Color(231, 76, 60));

        adminButton.setBounds(90, 90, 320, 50);
        employeeButton.setBounds(90, 155, 320, 50);
        studentButton.setBounds(90, 220, 320, 50);

        registerEmployeeButton.setBounds(35, 305, 210, 50);
        registerStudentButton.setBounds(255, 305, 210, 50);

        panel.add(adminButton);
        panel.add(employeeButton);
        panel.add(studentButton);
        panel.add(registerEmployeeButton);
        panel.add(registerStudentButton);

        // ================= NAVIGATION =================

        adminButton.addActionListener(e -> {
            frame.dispose();
            new AdminLogin();
        });

        employeeButton.addActionListener(e -> {
            frame.dispose();
            new EmployeeLogin();
        });

        studentButton.addActionListener(e -> {
            frame.dispose();
            new StudentLogin();
        });

        registerEmployeeButton.addActionListener(e -> {
            frame.dispose();
            new EmployeeRegister();
        });

        registerStudentButton.addActionListener(e -> {
            frame.dispose();
            new StudentRegister();
        });

        // ================= FOOTER =================
        JLabel footer = new JLabel(
                "© 2026 Greenfield Institute of Technology - EduSphere IMS",
                SwingConstants.CENTER);

        footer.setBounds(0, 720, 1366, 20);
        footer.setForeground(Color.WHITE);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        background.add(footer);

        frame.setContentPane(background);
        frame.setVisible(true);
    }

    private JButton createButton(String text, Color color) {

        RoundedButton button = new RoundedButton(text);
        button.setBackground(color);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Home::new);
    }
}

// ================= ROUNDED BUTTON =================
class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 16));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed())
            g2.setColor(getBackground().darker());
        else if (getModel().isRollover())
            g2.setColor(getBackground().brighter());
        else
            g2.setColor(getBackground());

        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {}

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 40, 40);
        return shape.contains(x, y);
    }
}