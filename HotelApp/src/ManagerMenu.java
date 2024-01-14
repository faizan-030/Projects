import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMenu extends JFrame {

    Container c;
    Font font1 = new Font("Nirmala UI", Font.BOLD, 14);
    Font font2 = new Font("Nirmala UI", Font.BOLD, 25);
    Font font3 = new Font("Nirmala UI", Font.ITALIC, 12);

    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;

    public static void main(String[] args) {
        new ManagerMenu();
    }

    public ManagerMenu(){
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(48, 48, 48));
        setTitle("digital services agency");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(450,0,400,680);

        setTitle("MANAGER MENU");

        ManagerMenuAL mmal = new ManagerMenuAL();

        l1 = new JLabel("MAIN MENU");
        l1.setFont(font2);
        l1.setBounds(110, 80, 300, 35);
        l1.setForeground(Color.lightGray);
        l1.setBackground(new Color(70, 191, 147, 255));
        c.add(l1);


        b9 = new JButton("ADD A CUSTOMER");
        b9.setFont(font1);
        b9.setForeground(Color.DARK_GRAY);
        b9.setBackground(new Color(70, 191, 147, 255));
        b9.setFocusable(false);
        b9.setBounds(85, 120, 210, 35);

        b9.addActionListener(mmal);
        c.add(b9);


        b7 = new JButton("VIEW ALL EMPLOYEES");
        b7.setFont(font1);
        b7.setForeground(Color.DARK_GRAY);
        b7.setBackground(new Color(70, 191, 147, 255));
        b7.setFocusable(false);
        b7.setBounds(85, 180, 210, 35);

        b7.addActionListener(mmal);
        c.add(b7);

        b1 = new JButton("VIEW ALL CUSTOMERS");
        b1.setFont(font1);
        b1.setForeground(Color.DARK_GRAY);
        b1.setBackground(new Color(70, 191, 147, 255));
        b1.setFocusable(false);
        b1.setBounds(85, 240, 210, 35);

        b1.addActionListener(mmal);
        c.add(b1);

        b2 = new JButton("VIEW COMPLETED PROJECTS");
        b2.setFont(font1);
        b2.setForeground(Color.DARK_GRAY);
        b2.setBackground(new Color(70, 191, 147, 255));
        b2.setFocusable(false);
        b2.setBounds(85, 300, 210, 35);
        b2.addActionListener(mmal);
        c.add(b2);

        b3 = new JButton("VIEW PENDING PROJECTS");
        b3.setFont(font1);
        b3.setForeground(Color.DARK_GRAY);
        b3.setBackground(new Color(70, 191, 147, 255));
        b3.setFocusable(false);
        b3.setBounds(85, 360, 210, 35);
        b3.addActionListener(mmal);
        c.add(b3);

        b4 = new JButton("VIEW PAYMENTS");
        b4.setFont(font1);
        b4.setForeground(Color.DARK_GRAY);
        b4.setBackground(new Color(70, 191, 147, 255));
        b4.setFocusable(false);
        b4.setBounds(85, 420, 210, 35);
        b4.addActionListener(mmal);
        c.add(b4);

        b8 = new JButton("ADD EMPLOYEE");
        b8.setFont(font1);
        b8.setForeground(Color.DARK_GRAY);
        b8.setBackground(new Color(70, 191, 147, 255));
        b8.setFocusable(false);
        b8.setBounds(85, 480, 210, 35);
        b8.addActionListener(mmal);
        c.add(b8);

        b5 = new JButton("EXIT");
        b5.setFont(font1);
        b5.setForeground(Color.DARK_GRAY);
        b5.setBackground(Color.GRAY);
        b5.setFocusable(false);
        b5.setBounds(140,530,100,30);
        b5.addActionListener(mmal);
        c.add(b5);

        b6 = new JButton("<- BACK");
        b6.setFont(font1);
        b6.setForeground(Color.DARK_GRAY);
        b6.setBackground(Color.GRAY);
        b6.setFocusable(false);
        b6.setBounds(30,580,90,25);
        b6.addActionListener(mmal);
        c.add(b6);
        setVisible(true);
    }

    public class ManagerMenuAL implements ActionListener{
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand().equalsIgnoreCase("ADD A CUSTOMER")){
                dispose();
                new AddCust();
            }
            else if (ae.getActionCommand().equalsIgnoreCase("VIEW ALL EMPLOYEES")){
                dispose();
                ManagerViewEmployees mvab = new ManagerViewEmployees();
            }

            else if (ae.getActionCommand().equalsIgnoreCase("VIEW ALL CUSTOMERS")) {
                dispose();
                ManagerViewCustomers mvc = new ManagerViewCustomers();

            }
            else if (ae.getActionCommand().equalsIgnoreCase("VIEW COMPLETED PROJECTS")) {
                dispose();
                ManagerViewCP mvb = new ManagerViewCP();
            }

            else if(ae.getActionCommand().equalsIgnoreCase("VIEW PENDING PROJECTS")){
                dispose();
                ManagerViewPending msc = new ManagerViewPending();
            }

            else if(ae.getActionCommand().equalsIgnoreCase("VIEW PAYMENTS")){
                dispose();
                ManagerViewBilling mua = new ManagerViewBilling();
            }

            else if(ae.getActionCommand().equalsIgnoreCase("ADD EMPLOYEE")){
                dispose();
                new AddEmp();
            }

            else if (ae.getActionCommand().equalsIgnoreCase("<- BACK")) {
                dispose();
                MainPage mp = new MainPage();
            }
            else if(ae.getActionCommand().equalsIgnoreCase("EXIT")){
                System.exit(0);
            }
        }
    }
}
