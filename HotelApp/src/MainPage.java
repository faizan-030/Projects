import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    Container c;
    Font font1 = new Font("Nirmala UI", Font.BOLD, 14);
    Font font2 = new Font("Nirmala UI", Font.BOLD, 25);
    Font font3 = new Font("Nirmala UI", Font.ITALIC, 14);

    JButton b1, b2, b3, b4, b5;
    JLabel l1, l2, l3, logo;
    JPanel p1;
//    ImageIcon logo;
     String[] emp = {"Web developer","Content Writer","Digital Marketer"};
    JComboBox comboBox;

    public static void main(String[] args)  {
        new MainPage();
    }

    public MainPage()  {

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(48, 48, 48));
        setTitle("digital services agency");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(450,0,400,680);

        MainPageActionListener mp = new MainPageActionListener();

        p1 = new JPanel(null);
        p1.setBounds(0,0,400,200);
        p1.setBackground(new Color(48, 48, 48));



        l1 = new JLabel("WELCOME TO ");
        l1.setFont(font2);
        l1.setBounds(100, 130, 300, 35);
        l1.setFocusable(false);
        l1.setForeground(Color.lightGray);

        l2 = new JLabel("DIGITAL SERVICES AGENCY");
        l2.setFont(font2);
        l2.setBounds(30, 200, 400, 35);
        l2.setFocusable(false);
        l2.setForeground(Color.lightGray);


        l3 = new JLabel("are you ...?");
        l3.setFont(font3);
        l3.setBounds(150,330,100,25);
        l3.setFocusable(false);
        l3.setForeground(Color.lightGray);

        b1 = new JButton("MANAGER");
        b1.setBounds(130, 380, 120, 35);
        b1.setFont(font1);
        b1.setBackground(new Color(88, 88, 88));
        b1.setForeground(Color.LIGHT_GRAY);
        b1.setFocusable(false);
        b1.addActionListener(mp);

        b2 = new JButton("EMPLOYEE");
        b2.setFont(font1);
        b2.setBackground(new Color(88, 88, 88));
        b2.setBounds(130, 430, 120, 35);
        b2.setForeground(Color.LIGHT_GRAY);
        b2.setFocusable(false);
        b2.addActionListener(mp);

        comboBox = new JComboBox(emp);
        comboBox.setForeground(Color.LIGHT_GRAY);
        comboBox.setFocusable(false);
        comboBox.addActionListener(mp);
        comboBox.setBackground(new Color(88, 88, 88));
        comboBox.setBounds(130, 480, 120, 35);


        b5 = new JButton("CUSTOMER");
        b5.setFont(font1);
        b5.setBackground(new Color(88, 88, 88));
        b5.setBounds(130, 430, 120, 35);
        b5.setForeground(Color.LIGHT_GRAY);
        b5.setFocusable(false);
        b5.addActionListener(mp);

        b3 = new JButton("SUBMIT");
        b3.setFont(font1);
        b3.setForeground(Color.DARK_GRAY);
        b3.setBackground(new Color(70, 191, 147, 255));
        b3.setFocusable(false);
        b3.setBounds(130,550,120,35);
        b3.addActionListener(mp);

        b4 = new JButton("EXIT");
        b4.setFont(font1);
        b4.setForeground(Color.DARK_GRAY);
        b4.setBackground(Color.GRAY);
        b4.setFocusable(false);
        b4.setBounds(25,600,90,25);
        b4.addActionListener(mp);
        c.add(b4);

        c.add(l1);
        c.add(l2);
        c.add(l3);

        c.add(b1);
        c.add(comboBox);
        //c.add(b2);
        c.add(b3);
        c.add(b5);
        setVisible(true);
    }

    public class MainPageActionListener implements ActionListener{
        public void actionPerformed(ActionEvent ae) {

            if(ae.getActionCommand().equalsIgnoreCase("MANAGER")){
                dispose();
                new ManagerSignInPage();
            }

            else if(ae.getActionCommand().equalsIgnoreCase("CUSTOMER")){
                dispose();
                new CustomerMenu();
            }

            else if(ae.getActionCommand().equalsIgnoreCase("EXIT")){
                System.exit(0);
            }

            else if(ae.getActionCommand().equalsIgnoreCase("SUBMIT")){
                    if (comboBox.getSelectedItem().equals("Web developer")) {
                        dispose();
                        new web();
                    }
                    else if (comboBox.getSelectedItem().equals("Content Writer")) {
                        dispose();
                        new Content();
                    }
                    else if (comboBox.getSelectedItem().equals("Service Agent")) {
                        dispose();
                        new Content();
                    }
                    else {
                        dispose();
                        new DM();
                    }
            }
        }
    }

}
