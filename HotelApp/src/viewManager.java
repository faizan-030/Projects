import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewManager extends JFrame {

    Container c;
    Font font1 = new Font("Nirmala UI", Font.BOLD, 14);
    Font font2 = new Font("Nirmala UI", Font.BOLD, 25);
    Font font3 = new Font("Nirmala UI", Font.ITALIC, 12);

    JLabel l1;
    JTextArea t1;
    JScrollPane scroll;
    JButton b1;

    public static void main(String[] args) {
        new viewManager();
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

    public viewManager(){

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(48, 48, 48));
        setTitle("digital services agency");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(450,0,400,680);

        ManagerViewAllBookingsAL mvabal = new ManagerViewAllBookingsAL();

        l1 = new JLabel("ALL MANAGERS");
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


        try {
            JTextArea textArea = new JTextArea();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("agency");
            MongoCollection<Document> collection2 = database.getCollection("manager");

            ArrayList<Object> valuesList2 = new ArrayList<>();
            FindIterable<Document> man = collection2.find(new Document("id", new Document("$exists", true)));
            for (Document document : man) {
                Object value = document.get("id");
                valuesList2.add(value);
            }

            for (int i = 0; i < valuesList2.size(); i++) {
                t1.append("\n----------------------------------\n");
                String id = String.valueOf(valuesList2.get(i));
                ArrayList<Map> record  = retrieveRecordsFromTable(new Document("member_id",Integer.parseInt(id)),"member");
                Map m = record.get(0);
                m.forEach((key, value) -> {
                    String mid = null;
                    System.out.println(value.toString());
                    if(key.toString().equalsIgnoreCase("member_id")){
                        t1.append("Member ID: "+value.toString());
                        mid = value.toString();
                    }
                    else if(key.toString().equalsIgnoreCase("first_name")){
                        t1.append("\nFirst Name: "+value.toString());
                    }
                    else if(key.toString().equalsIgnoreCase("last_name")){
                        t1.append("\nLast Name: "+value.toString());
                    }
                    else if(key.toString().equalsIgnoreCase("email")){
                        t1.append("\nEmail: "+value.toString());
                    }
                    if (mid!=null) {
                        ArrayList<Map> record1 = retrieveRecordsFromTable(new Document("id", Integer.parseInt(mid)), "manager");
                        Map m1 = record1.get(0);
                        m1.forEach((key1, value1) -> {
                            System.out.println(value1.toString());
                            if (key1.toString().equalsIgnoreCase("salary")) {
                                t1.append("\nSalary: " + value1.toString());
                            } else if (key1.toString().equalsIgnoreCase("hire_date")) {
                                t1.append("\nHire Date: " + value1.toString());
                            }
                        });
                    }
                });
                t1.append("\n----------------------------------\n");
            }
            textArea.setEditable(false);

            mongoClient.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        c.add(scroll);

        b1 = new JButton("<- BACK");
        b1.setFont(font1);
        b1.setForeground(Color.DARK_GRAY);
        b1.setBackground(Color.GRAY);
        b1.setFocusable(false);
        b1.setBounds(30,580,90,25);
        b1.addActionListener( mvabal);
        c.add(b1);
        setVisible(true);
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
