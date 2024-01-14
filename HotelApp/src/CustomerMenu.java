import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerMenu extends JFrame{

    Container c;
    Font font1 = new Font("Nirmala UI", Font.BOLD, 14);
    Font font2 = new Font("Nirmala UI", Font.BOLD, 25);
    Font font3 = new Font("Nirmala UI", Font.ITALIC, 12);

    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6;

    public static void main(String[] args) {
        new CustomerMenu();
    }

    public static Map<String, Object> convertJsonToDictionary(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        Map<String, Object> dictionary = new HashMap<>();

        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject && ((JSONObject) value).has("$date")) {
                // Handle date format
                long dateValue = ((JSONObject) value).getLong("$date");
                value = new java.util.Date(dateValue);
            }
            dictionary.put(key, value);
        }

        return dictionary;
    }

    public static ArrayList<Map> retrieveRecordsFromTable(Document query, String tableName) {
        ArrayList<Map> rows = new ArrayList<>();
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("agency");
            MongoCollection<Document> collection = database.getCollection(tableName);

            // Find documents that match the query
            MongoCursor<Document> cursor = collection.find(query).iterator();

            // Iterate through the result set and display the documents

            while (cursor.hasNext()) {
                Document document = cursor.next();
                String data = document.toJson() + "\n";
                rows.add(convertJsonToDictionary(data));
            }

            for(int i=0; i<rows.size(); i++){
                System.out.println("------------------------------------------------");
                for (Object key : rows.get(i).keySet()) {
                    Object value = rows.get(i).get(key);
                    System.out.println(key + ": " + value);
                }
                System.out.println("------------------------------------------------");
            }

            // Close the cursor and MongoDB client
            cursor.close();
            mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows;
    }

    public CustomerMenu(){
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(48, 48, 48));
        setTitle("digital services agency");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(450,0,400,680);

        setTitle("CUSTOMER MENU");
        CustomerMenuAL cmal = new CustomerMenuAL();

        l1 = new JLabel("CUSTOMER MENU");
        l1.setFont(font2);
        l1.setBounds(80, 160, 300, 35);
        l1.setForeground(Color.lightGray);
        l1.setBackground(new Color(70, 191, 147, 255));
        c.add(l1);

        b1 = new JButton("MAKE BOOKING");
        b1.setFont(font1);
        b1.setForeground(Color.DARK_GRAY);
        b1.setBackground(new Color(70, 191, 147, 255));
        b1.setFocusable(false);
        b1.setBounds(85, 280, 210, 35);
        b1.addActionListener(cmal);

        c.add(b1);


        b2 = new JButton("CANCEL PROJECT");
        b2.setFont(font1);
        b2.setForeground(Color.DARK_GRAY);
        b2.setBackground(new Color(70, 191, 147, 255));
        b2.setFocusable(false);
        b2.setBounds(85, 340, 210, 35);
        b2.addActionListener(cmal);
        c.add(b2);

        b3 = new JButton("VIEW DATA");
        b3.setFont(font1);
        b3.setForeground(Color.DARK_GRAY);
        b3.setBackground(new Color(70, 191, 147, 255));
        b3.setFocusable(false);
        b3.setBounds(85, 400, 210, 35);
        b3.addActionListener(cmal);

        c.add(b3);

        b6 = new JButton("ADD A CUSTOMER");
        b6.setFont(font1);
        b6.setForeground(Color.DARK_GRAY);
        b6.setBackground(new Color(70, 191, 147, 255));
        b6.setFocusable(false);
        b6.setBounds(85, 460, 210, 35);
        b6.addActionListener(cmal);
        c.add(b6);

        b4 = new JButton("EXIT");
        b4.setFont(font1);
        b4.setForeground(Color.DARK_GRAY);
        b4.setBackground(Color.GRAY);
        b4.setFocusable(false);
        b4.setBounds(140,520,100,30);
        b4.addActionListener(cmal);
        c.add(b4);

        b5 = new JButton("<- BACK");
        b5.setFont(font1);
        b5.setForeground(Color.DARK_GRAY);
        b5.setBackground(Color.GRAY);
        b5.setFocusable(false);
        b5.setBounds(30,580,90,25);
        b5.addActionListener(cmal);
        c.add(b5);
        setVisible(true);
    }

    public class CustomerMenuAL implements ActionListener{
        public void actionPerformed(ActionEvent ae) {

            if (ae.getActionCommand().equalsIgnoreCase("<- BACK")) {
                dispose();
                    MainPage mp = new MainPage();
            }

            else if (ae.getActionCommand().equalsIgnoreCase("MAKE BOOKING")){
                dispose();
                new CustomerBook();
            }


            else if (ae.getActionCommand().equalsIgnoreCase("VIEW DATA")){
                try {
                    JTextArea textArea = new JTextArea();
                    textArea.setBackground(Color.white);
                    MongoClient mongoClient = new MongoClient("localhost", 27017);


                    String id = JOptionPane.showInputDialog("Enter Customer ID");

                    ArrayList<Map> record  = retrieveRecordsFromTable(new Document("customer_id",Integer.parseInt(id)),"customer");
                    Map m = record.get(0);
                    m.forEach((key, value) -> {
                        System.out.println(value.toString());
                        if(key.toString().equalsIgnoreCase("customer_id")){
                            textArea.append("\nID: "+value);
                        }
                        else if(key.toString().equalsIgnoreCase("name")){
                            textArea.append("Name: "+value);
                        }
                        else if(key.toString().equalsIgnoreCase("email")){
                            textArea.append("\nEmail: "+value);
                        }
                    });

                    ArrayList<Map> record3  = retrieveRecordsFromTable(new Document("customer_id",Integer.parseInt(id)),"completed_projects");
                    if (record3 !=null) {
                        textArea.append("\nProject Completed");
                    }
                    else {
                        textArea.append("\nProject Incomplete");
                    }
                    ArrayList<Map> record2  = retrieveRecordsFromTable(new Document("customer_id",Integer.parseInt(id)),"billing");
                    Map m1 = record2.get(0);
                    m1.forEach((key, value) -> {
                        System.out.println(value.toString());
                        if(key.toString().equalsIgnoreCase("amount")){
                            textArea.append("\nBill Amount: "+value);
                        }
                    });

                    JOptionPane.showMessageDialog(null,textArea);
                    mongoClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if (ae.getActionCommand().equalsIgnoreCase("ADD A CUSTOMER")){
                dispose();
                new AddCust();
            }

            else if (ae.getActionCommand().equalsIgnoreCase("CANCEL PROJECT")) {
                try {
                    JTextArea textArea = new JTextArea();
                    MongoClient mongoClient = new MongoClient("localhost", 27017);
                    MongoDatabase database = mongoClient.getDatabase("agency");
                    MongoCollection<Document> collection = database.getCollection("project");

                    String id = JOptionPane.showInputDialog("Enter Project ID");

                    // Delete the document
                    collection.deleteOne(Filters.eq("project_id",Integer.parseInt(id)));

                    JOptionPane.showMessageDialog(null, "done!");
                    mongoClient.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            else if(ae.getActionCommand().equalsIgnoreCase("EXIT")){
                System.exit(0);
            }

        }
    }
}
