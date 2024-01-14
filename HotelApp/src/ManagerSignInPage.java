import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerSignInPage extends JFrame {
    Container c;
    Font font1 = new Font("Nirmala UI", Font.BOLD, 14);
    Font font2 = new Font("Nirmala UI", Font.BOLD, 25);
    Font font3 = new Font("Nirmala UI", Font.PLAIN, 12);

    JButton b1, b2;
    JLabel l1, l2, l3;
    JTextField tf1, tf2;

    public ManagerSignInPage(){
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(48, 48, 48));
        AdminPageActionListener ap = new AdminPageActionListener();

        setTitle("MANAGER SIGN-IN");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setBounds(450,0,400,550);

        l1 = new JLabel("SIGN-IN");
        l1.setFont(font2);
        l1.setBounds(135, 120, 300, 35);
        l1.setForeground(Color.lightGray);
        l1.setBackground(new Color(70, 191, 147, 255));
        c.add(l1);


        l2 = new JLabel("USERNAME ");
        l2.setFont(font1);
        l2.setBounds(50, 200, 100,35);
        l2.setFocusable(false);
        l2.setForeground(Color.lightGray);
        c.add(l2);

        tf1 = new JTextField(20);
        tf1.setBounds(150,200, 150, 25);
        tf1.setFont(font3);
        c.add(tf1);

        l3 = new JLabel("PASSWORD");
        l3.setFont(font1);
        l3.setBounds(50, 250, 100,35);
        l3.setFocusable(false);
        l3.setForeground(Color.lightGray);
        c.add(l3);

        tf2 = new JTextField(20);
        tf2.setBounds(150,250, 150, 25);
        tf2.setFont(font3);
        c.add(tf2);


        b1 = new JButton("LOGIN");
        b1.setFont(font1);
        b1.setForeground(Color.DARK_GRAY);
        b1.setBackground(new Color(70, 191, 147, 255));
        b1.setFocusable(false);
        b1.setBounds(130,360,120,35);
        b1.addActionListener(ap);
        c.add(b1);

        b2 = new JButton("<- BACK");
        b2.setFont(font1);
        b2.setForeground(Color.DARK_GRAY);
        b2.setBackground(Color.GRAY);
        b2.setFocusable(false);
        b2.setBounds(30,450,90,25);
        b2.addActionListener(ap);
        c.add(b2);

    }

    public class AdminPageActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            String username = "admin";
            String password = "admin123";

            String user = tf1.getText();
            String pass = tf2.getText();


            if (ae.getActionCommand().equalsIgnoreCase("LOGIN")) {

                if(tf1.getText().equals("") || tf2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Fill ALL Fields");
                    dispose();
                }

                else if (!tf1.getText().equals("") && !tf2.getText().equals("")){
                    if (pass.equals(password) && user.equals(username)) {
                        JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFUL !");
                        dispose();

                        ManagerMenu mm = new ManagerMenu();

                    } else {
                        JOptionPane.showMessageDialog(null, "WRONG CREDENTIALS, TRY AGAIN !");
                    }
                }
            }

            else if(ae.getActionCommand().equalsIgnoreCase("<- BACK")){
                dispose();
                new MainPage();
            }
        }
    }
}

