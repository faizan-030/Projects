import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerViewBooking extends JFrame {

    Container c;
    Font font1 = new Font("Nirmala UI", Font.BOLD, 14);
    Font font2 = new Font("Nirmala UI", Font.BOLD, 25);
    Font font3 = new Font("Nirmala UI", Font.ITALIC, 12);

    JLabel l1;
    JTextArea t1;
    JScrollPane scroll;
    JButton b1;

    public static void main(String[] args) {
        new CustomerViewBooking();
    }

    public CustomerViewBooking(){

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(48, 48, 48));

        setTitle("VIEW ALL BOOKINGS");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //setResizable(false);
        setBounds(450,0,400,680);

        ManagerViewAllBookingsAL mvabal = new ManagerViewAllBookingsAL();

        l1 = new JLabel("ALL BOOKINGS");
        l1.setFont(font2);
        l1.setBounds(90, 100, 300, 35);
        l1.setForeground(Color.lightGray);
        l1.setBackground(new Color(70, 191, 147, 255));
        c.add(l1);

        t1 = new JTextArea(40, 50);
        scroll = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(55,180, 280, 320);
        t1.setFont(font3);
        t1.setBounds(55,180,280,370);



        //ArrayList<Booking> bookings = FileOperations.ReadAllFromBooking();

        try {
            JTextArea textArea = new JTextArea();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("agency");
            MongoCollection<Document> collection = database.getCollection("customer");
            for (Document document : collection.find()) {
                String data = document.toJson() + "\n";

                System.out.println(data);
//                textArea.append(data);
//                textArea.setEditable(false);

                t1.setText(t1.getText() + "\n------------------------------\n"  + data);
            }
            //JOptionPane.showMessageDialog(null,textArea);

            mongoClient.close();
//
        } catch (Exception e) {
            e.printStackTrace();
        }

//        for(int i = 0;i <bookings.size();i++){
//            String str = bookings.get(i).toString();
//
//            t1.setText(t1.getText() + "\n------------------------------\n"  + str);
//        }

        c.add(scroll);

        b1 = new JButton("<- BACK");
        b1.setFont(font1);
        b1.setForeground(Color.DARK_GRAY);
        b1.setBackground(Color.GRAY);
        b1.setFocusable(false);
        b1.setBounds(30,580,90,25);
        b1.addActionListener( mvabal);
        c.add(b1);
    }

    public class ManagerViewAllBookingsAL implements ActionListener {
        public void actionPerformed(ActionEvent ae) {

            if (ae.getActionCommand().equalsIgnoreCase("<- BACK")) {
                dispose();
                ManagerMenu mm = new ManagerMenu();
            }
        }

    }
}
