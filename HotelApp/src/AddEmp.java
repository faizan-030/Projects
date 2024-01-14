import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmp extends JFrame {
    Container c;
    Font font1 = new Font("Nirmala UI", Font.BOLD, 14);
    Font font2 = new Font("Nirmala UI", Font.BOLD, 25);
    Font font3 = new Font("Nirmala UI", Font.PLAIN, 12);

    JButton b1, b2;
    JLabel l1, l2, l3,l4,l5,l6,l7;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6;

    public static void main(String[] args) {
        new AddEmp();
    }


    public AddEmp(){

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(48, 48, 48));
        setTitle("digital services agency");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(450,0,400,680);

        CustomerSignupAL cal = new CustomerSignupAL();

        l7 = new JLabel("ADD EMPLOYEE");
        l7.setFont(font2);
        l7.setBounds(90, 120, 300, 35);
        l7.setForeground(Color.lightGray);
        l7.setBackground(new Color(70, 191, 147, 255));

        c.add(l7);

        l4 = new JLabel("MEMBER ID");
        l4.setFont(font1);
        l4.setBounds(50, 250, 100,35);
        l4.setForeground(Color.lightGray);
        c.add(l4);

        tf4 = new JTextField(20);
        tf4.setBounds(180,250, 150, 22);
        tf4.setFont(font3);
        tf4.setBackground(new Color(203, 201, 201, 255));
        c.add(tf4);

        l1 = new JLabel("FIRST NAME");
        l1.setFont(font1);
        l1.setBounds(50, 300, 100,35);
        l1.setForeground(Color.lightGray);
        c.add(l1);

        tf1 = new JTextField(20);
        tf1.setBounds(180,300, 150, 22);
        tf1.setFont(font3);
        tf1.setBackground(new Color(203, 201, 201, 255));
        c.add(tf1);

        l2 = new JLabel("LAST NAME");
        l2.setFont(font1);
        l2.setBounds(50, 350, 100,35);
        l2.setForeground(Color.lightGray);
        c.add(l2);

        tf2 = new JTextField(20);
        tf2.setBounds(180,350, 150, 22);
        tf2.setFont(font3);
        tf2.setBackground(new Color(203, 201, 201, 255));
        c.add(tf2);

        l3 = new JLabel("EMAIL");
        l3.setFont(font1);
        l3.setBounds(50, 400, 100,35);
        l3.setForeground(Color.lightGray);
        c.add(l3);

        tf3 = new JTextField(20);
        tf3.setBounds(180,400, 150, 22);
        tf3.setFont(font3);
        tf3.setBackground(new Color(203, 201, 201, 255));
        c.add(tf3);


        b1 = new JButton("SUBMIT");
        b1.setFont(font1);
        b1.setForeground(Color.DARK_GRAY);
        b1.setBackground(new Color(70, 191, 147, 255));
        b1.setFocusable(false);
        b1.setBounds(130,520,120,35);
        b1.addActionListener(cal);
        c.add(b1);

        b2 = new JButton("<- BACK");
        b2.setFont(font1);
        b2.setForeground(Color.DARK_GRAY);
        b2.setBackground(Color.GRAY);
        b2.setFocusable(false);
        b2.setBounds(30,590,90,25);
        b2.addActionListener(cal);
        c.add(b2);
        setVisible(true);

    }

    public class CustomerSignupAL implements ActionListener{
        public void actionPerformed(ActionEvent ae) {

            if(ae.getActionCommand().equalsIgnoreCase("SUBMIT")){

                if(tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("") || tf4.getText().equals("")  ){
                    JOptionPane.showMessageDialog(null, "Fill ALL fields to SIGN UP");
                }

                else if(!tf1.getText().equals("") && !tf2.getText().equals("") && !tf3.getText().equals("")&& !tf4.getText().equals("") ){
                    try {
                        MongoClient mongoClient = new MongoClient("localhost", 27017);
                        MongoDatabase database = mongoClient.getDatabase("agency");
                        MongoCollection<Document> collection = database.getCollection("member");


                        Document d = new Document("member_id", Integer.parseInt(tf4.getText()))
                                .append("first_name", tf1.getText())
                                .append("last_name",tf2.getText())
                                .append("email", tf3.getText());

                        collection.insertOne(d);

                        JOptionPane.showMessageDialog(null, "done!");
                        mongoClient.close();
                        dispose();
                        new ManagerMenu();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            if(ae.getActionCommand().equalsIgnoreCase("<- BACK")){
                dispose();
                new ManagerMenu();
            }
        }
    }
}
