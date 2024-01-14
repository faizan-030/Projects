import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class CustomerBook extends JFrame {
    Container c;
    Font font1 = new Font("Nirmala UI", Font.BOLD, 14);
    Font font2 = new Font("Nirmala UI", Font.BOLD, 25);
    Font font3 = new Font("Nirmala UI", Font.PLAIN, 12);

    JButton b1, b2;
    JLabel l1, l2, l3,l4,l5,l6,l7;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6;

    public static void main(String[] args) {
        new CustomerBook();
    }



    public CustomerBook(){

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(48, 48, 48));
        setTitle("digital services agency");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(450,0,400,680);

        CustomerSignupAL cal = new CustomerSignupAL();

        l7 = new JLabel("ADD PROJECT");
        l7.setFont(font2);
        l7.setBounds(70, 120, 300, 35);
        l7.setForeground(Color.lightGray);
        l7.setBackground(new Color(70, 191, 147, 255));

        c.add(l7);

        l4 = new JLabel("REQUEST ID");
        l4.setFont(font1);
        l4.setBounds(50, 200, 100,35);
        l4.setForeground(Color.lightGray);
        c.add(l4);

        tf4 = new JTextField(20);
        tf4.setBounds(180,200, 150, 22);
        tf4.setFont(font3);
        tf4.setBackground(new Color(203, 201, 201, 255));
        c.add(tf4);

        l1 = new JLabel("CUSTOMER ID");
        l1.setFont(font1);
        l1.setBounds(50, 350, 100,35);
        l1.setForeground(Color.lightGray);
        c.add(l1);

        tf1 = new JTextField(20);
        tf1.setBounds(180,350, 150, 22);
        tf1.setFont(font3);
        tf1.setBackground(new Color(203, 201, 201, 255));
        c.add(tf1);

        l2 = new JLabel("REQUEST CODE");
        l2.setFont(font1);
        l2.setBounds(50, 250, 100,35);
        l2.setForeground(Color.lightGray);
        c.add(l2);

        tf2 = new JTextField(20);
        tf2.setBounds(180,250, 150, 22);
        tf2.setFont(font3);
        tf2.setBackground(new Color(203, 201, 201, 255));
        c.add(tf2);

        l3 = new JLabel("REQUEST DESCRIPTION");
        l3.setFont(font1);
        l3.setBounds(50, 300, 100,35);
        l3.setForeground(Color.lightGray);
        c.add(l3);

        tf3 = new JTextField(20);
        tf3.setBounds(180,300, 150, 22);
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

                else if(!tf1.getText().equals("") && !tf2.getText().equals("") && !tf3.getText().equals("") && !tf4.getText().equals("") ){
                    try {
                        JTextArea textArea = new JTextArea();
                        MongoClient mongoClient = new MongoClient("localhost", 27017);
                        MongoDatabase database = mongoClient.getDatabase("agency");
                        MongoCollection<Document> collection = database.getCollection("request");


                        Document d = new Document("request_id", Integer.parseInt(tf4.getText()))
                                .append("customer_id", Integer.parseInt(tf1.getText()))
                                .append("request_code", Integer.parseInt(tf2.getText()))
                                .append("request_desc",tf3.getText());

                        collection.insertOne(d);

                        String project_type;

                        MongoCollection<Document> web = database.getCollection("web_developer");

                        ArrayList<Object> valuesList = new ArrayList<>();
                        FindIterable<Document> web1 = web.find(new Document("id", new Document("$exists", true)));
                        for (Document document : web1) {
                            Object value = document.get("id");
                            valuesList.add(value);
                        }

                        MongoCollection<Document> dm = database.getCollection("digital_marketer");
                        ArrayList<Object> valuesList1 = new ArrayList<>();
                        FindIterable<Document> dm1 = dm.find(new Document("id", new Document("$exists", true)));
                        for (Document document : dm1) {
                            Object value = document.get("id");
                            valuesList1.add(value);
                        }

                        MongoCollection<Document> content = database.getCollection("content_writer");
                        ArrayList<Object> valuesList2 = new ArrayList<>();
                        FindIterable<Document> content1 = content.find(new Document("id", new Document("$exists", true)));
                        for (Document document : content1) {
                            Object value = document.get("id");
                            valuesList2.add(value);
                        }

                        Random random = new Random();

                        String id = null;
                        if (Integer.parseInt(tf2.getText()) == 1) {
                            project_type = "web";
                            int r = random.nextInt(valuesList.size());
                            id =  String.valueOf(valuesList.get(r));
                        }
                        else if (Integer.parseInt(tf2.getText()) == 2) {
                            project_type = "content";
                            int r = random.nextInt(valuesList2.size());
                            id =  String.valueOf(valuesList2.get(r));
                        }
                        else if (Integer.parseInt(tf2.getText()) == 3) {
                            project_type = "digital marketing";
                            int r = random.nextInt(valuesList1.size());
                            id =  String.valueOf(valuesList1.get(r));
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "invalid request code!");
                            return;
                        }

                        MongoCollection<Document> project = database.getCollection("project");

                        int randid = random.nextInt(10,30);
                        Document proj = new Document("project_id", randid)
                                .append("request_id",Integer.parseInt(tf4.getText()))
                                .append("project_type",project_type)
                                .append("status","not done")
                                .append("member_id", id);

                        project.insertOne(proj);

                        JOptionPane.showMessageDialog(null, "done!");
                        mongoClient.close();
                        dispose();
                        new CustomerMenu();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            if(ae.getActionCommand().equalsIgnoreCase("<- BACK")){
                dispose();
               new CustomerMenu();
            }
        }
    }
}
