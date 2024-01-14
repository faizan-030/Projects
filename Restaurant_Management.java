import org.junit.jupiter.api.Test;

import javax.swing.*;            //this class is used to create GUI
import java.sql.*;              //This class is used for connection of database
import java.util.Scanner;      //This class create input object
public class Restaurant_Management {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        getConnection(); //calling getConnection to link database


        System.out.println("|-------------------------------------------------------|");
        System.out.println("|                                                       |");
        System.out.println("|        M I L L E N N I U M   C A F E   A P P          |");
        System.out.println("|                                                       |");
        System.out.println("|-------------------------------------------------------|");

       GUI_interface();      // Calling GUI interface function


    }

    //This function display menu of restaurant
    public static void menu() {
        System.out.println("-----THE PAKISTANI FOOD AVAILABLE ARE-----");
        System.out.println("1 - BIRYANI----RS 250\n2 - CHICKEN KARAHI-----RS 1500\n3 - MUTTON KARAHI----RS 2200\n4 - NIHARI----RS 700\n5 - C-HICKEN TIKKA----RS 200\n6 - BAR-B-Q----RS 250\n7 - DAAL CHAWAL----RS 150\n8 - SUJI HALWA----RS 300\n9 - GAAJAR HALWA----RS 500\n10 - GULAB JAMUN----RS 200");
        System.out.println();
        System.out.println("-----THE CHINESE FOOD AVAILABLE ARE-----");
        System.out.println("1 - CHOW MEIN----RS 600\n2 - TOFU----RS 800\n3 - SPRING ROLLS----RS 500\n4 - MOMOS----RS 250\n5 - CHICKEN FRIED RICE----RS 350");
        System.out.println();
        System.out.println("-----THE ITALIAN FOOD AVAILABLE ARE-----");
        System.out.println("1 - PIZZA----RS 1800\n2 - PASTA----RS 900\n3 - LASAGNA----RS 1000\n4 - RISOTTO----RS 2500\n5 - RATATOUILLE----RS 3000");
        System.out.println();
        System.out.println("-----THE SPANISH FOOD AVAILABLE ARE-----");
        System.out.println("1 - GAZPACHO----RS 1500\n2 - PATATA----RS 2000\n3 - PAELLA----RS 3000\n4 - TORTILA ESPANOLA----RS 3500\n5 - CHURROS CON CHOCOLATE----RS 1500");
        System.out.println();
        System.out.println("-----THE TURKISH FOOD AVAILABLE ARE-----");
        System.out.println("1 - INEGOL KOFTE----RS 850\n2 - ISKENDER KEBAB----RS 1600\n3 - CAG KEBAB----RS 2000\n4 - GOZLEME ----RS 600\n5 - DONDURMA----RS 1500");

    }

    //This method takes order from user
    public static String[] place_Order() {

        Scanner input = new Scanner(System.in);
        String[] arr;
        System.out.println("Enter the order number: ");
        int order_no = input.nextInt();  //getting order number

        int t1 = (int) (Math.random() * (1 +1)-0);
        int t2 = (int) (Math.random() * (9 +1)-1);
        int t3 = (int) (Math.random() * (5)-0);
        int t4 = (int) (Math.random() * (9)-0);
        String time =(t1+""+t2+":"+t3+""+t4);    //randomly generating time of table order

        int table = (int) (Math.random() * (20 - 1+1)-1);    //randomly generating table number
        int number_of_order;
        System.out.println("enter number of orders you want to place : ");
        number_of_order = input.nextInt();
        arr = new String[number_of_order];
        int a = arr.length;

        //Entering the order from user

        System.out.println("KINDLY PLACE YOUR ORDER: ");
        for (int i = 0; i < number_of_order; i++) {
            System.out.println("enter the food");
            arr[i] = input.next();
        }

        //Displaying order of current table

        System.out.println("the order of table number " + table + " is:");
        for (int i = 0; i < number_of_order; i++) {
            System.out.println(arr[i]);
        }
        int member;
        System.out.println("enter 1 if you are member");
        member = input.nextInt();
        if (member==1) {
            member_billing(arr, table, time, order_no); // member billing() is called if user is member of restaurant
        }
        else {
            billing(arr,table,time,order_no);  //billing () is called if user is not a member
        }
        return arr;
    }


    //This method generate bill and display amount return to user and stores it in database
    static  void billing(String[] Array, int table, String time, int order_no) {
        Scanner input = new Scanner(System.in);
        try {
            int[] bill = new int[Array.length];

            for (int i = 0; i < Array.length; i++) {
                System.out.println("price of " + Array[i] + "is ");
                bill[i] = input.nextInt();
            }
            int total = 0;
            for (int k = 0; k < Array.length; k++) {
                total += bill[k];
            }
            System.out.println("your total bill is " + total);


            System.out.println("how do you want the bill\noption-1 Credit card\noption-2 cash");
            int option;
            option = input.nextInt();
            int return_money = 0;
            String x;
            if (option == 1) {      // valid credit card number checking
                System.out.println("PLEASE enter a valid credit card number");
                x = input.next();
                System.out.println(x.length());
                boolean isCheck = (x.length() == 14 &&
                        (Character.isDigit(x.charAt(0))) &&
                        (Character.isDigit(x.charAt(1))) &&
                        (Character.isDigit(x.charAt(2))) &&
                        (Character.isDigit(x.charAt(3))) &&
                        (x.charAt(4) == '-') &&
                        (Character.isDigit(x.charAt(5))) &&
                        (Character.isDigit(x.charAt(6))) &&
                        (Character.isDigit(x.charAt(7))) &&
                        (Character.isDigit(x.charAt(8))) &&
                        (x.charAt(9) == '-') &&
                        (Character.isDigit(x.charAt(10))) &&
                        (Character.isDigit(x.charAt(11)) &&
                                (Character.isDigit(x.charAt(12))) &&
                                (Character.isDigit(x.charAt(13)))));

                if (isCheck) {   //getting 4-digit pin of user
                    System.out.println(x + "is a valid credit card number   ");
                    System.out.println("enter your 4-Digit pin: ");
                    int[] pin = new int[4];
                    for (int i = 0; i < pin.length; i++) {
                        pin[i] = input.nextInt();
                    }

                    System.out.println("the amount deducted from your account is: RS " + total);
                } else{
                    System.out.println("it is not a valid credit card number\nyour number should in the form of DDDD-DDDD-DDDD\nplease enter again");
                check(x,total);}
            } else if (option == 2) {   //getting bill in form of cash

                int cash;
                System.out.println("Please pay your bill:");
                cash = input.nextInt();
                System.out.println("cash you payed is" + cash);
                return_money = cash - total;
                System.out.println("your return money is: " + return_money);
            }
            Connection conn = getConnection();
            //inserting query to enter parameters in database
            PreparedStatement insert = conn.prepareStatement("INSERT INTO order_history(Table_No, Orders_No, Total_Price, Time)" + "VALUES('" + table + "','" + order_no + "','" + total + "','" + time + "')");
            insert.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
app();

    }
     //this method creates our connection to our database
    public static Connection getConnection() {  // for connecting database
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseurl = "jdbc:mysql://localhost:3306/restaurent_management_app"; //Url link at local host 3306
            String username = "root";
            String password = "";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, password);  //creating connection
            System.out.println("DATABASE connected");
            return conn;
        } catch (Exception e) {
            System.out.println("ERROR database not connected");
        }

        return null;
    }

    //This method is for getting membership of restaurant
    public static void member() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter your first name: ");
            String name = input.next();
            System.out.println("Enter your last name: ");
            String last = input.next();
            System.out.println("enter your phone number : ");
            long phone = input.nextLong();
            System.out.println("enter your E-mail address: ");
            String email = input.next();
            int Id = (int) (Math.random() * (10000 - 1000 + 1) + 1000);
            System.out.println("your membership ID is: " + Id);
            Connection conn = getConnection();
            //using inserting query to insert the information of member into database
            PreparedStatement insert = conn.prepareStatement("INSERT INTO members(First_Name, Last_Name, Phone_No, E_mail, Member_ID)" + "VALUES('" + name + "','" + last + "','" + phone + "','" + email + "','" + Id + "')");
            insert.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
app();
    }

    //This function runs for billing of members only
    static void member_billing(String[] Array, int table, String time, int order_no) {
        Scanner input = new Scanner(System.in);
        try {
            int[] bill = new int[Array.length];

            for (int i = 0; i < Array.length; i++) {
                System.out.println("price of " + Array[i] + "is ");
                bill[i] = input.nextInt();
            }
            int total = 0;
            for (int k = 0; k < Array.length; k++) {
                total += bill[k];
            }
            System.out.println("your total bill is " + total);
            int grand_total;
            boolean member;
            try {
                System.out.println("enter your membership ID if you are member");
                int ID = input.nextInt();
                Connection conn = getConnection();
                assert conn != null;
                Statement stmt = conn.createStatement();

                //select query to compare the input ID and ID stored in database and retreiving it

                String SQl = "SELECT * FROM members WHERE Member_ID='" + ID + "' ";
                ResultSet rs = stmt.executeQuery(SQl);
                if (rs.next()) {
                    System.out.println("You are member of this restaurant:");
                    grand_total = total - (total * 20 / 100);
                    System.out.println("how do you want the bill\noption-1 Credit card\noption-2 cash");
                    int option;
                    option = input.nextInt();
                    int return_money = 0;
                    String x;
                    if (option == 1) {
                        System.out.println("PLEASE enter a valid credit card number");
                        x = input.next();
                        System.out.println(x.length());
                        boolean isCheck = (x.length() == 14 &&
                                (Character.isDigit(x.charAt(0))) &&
                                (Character.isDigit(x.charAt(1))) &&
                                (Character.isDigit(x.charAt(2))) &&
                                (Character.isDigit(x.charAt(3))) &&
                                (x.charAt(4) == '-') &&
                                (Character.isDigit(x.charAt(5))) &&
                                (Character.isDigit(x.charAt(6))) &&
                                (Character.isDigit(x.charAt(7))) &&
                                (Character.isDigit(x.charAt(8))) &&
                                (x.charAt(9) == '-') &&
                                (Character.isDigit(x.charAt(10))) &&
                                (Character.isDigit(x.charAt(11)) &&
                                        (Character.isDigit(x.charAt(12))) &&
                                        (Character.isDigit(x.charAt(13)))));
                        if (isCheck) {
                            System.out.println(x + "is a valid social security number   ");
                            System.out.println("enter your 4-Digit pin: ");
                            int[] pin = new int[4];
                            for (int i = 0; i < pin.length; i++) {
                                pin[i] = input.nextInt();
                            }

                            System.out.println("the amount deducted from your account is: RS " + grand_total);
                        } else{
                            System.out.println("it is not a valid credit card number\nyour number should in the form of DDD-DD-DDDD\nplease enter again");
                        check(x,grand_total);
                        }

                    } else if (option == 2) {

                        int cash;
                        System.out.println("Please pay your bill:");
                        cash = input.nextInt();
                        System.out.println("cash you payed is" + cash);
                        return_money = cash - grand_total;
                        System.out.println("your return money is: " + return_money);
                    }

                } else {
                    System.out.println("Sorry! You don't have membership");
                }
                PreparedStatement insert = conn.prepareStatement("INSERT INTO order_history(Table_No, Orders_No, Total_Price, Time)" + "VALUES('" + table + "','" + order_no + "','" + total + "','" + time + "')");
                insert.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        } finally {

        }
app();
    }

    //this is the main method of our app. In this method other methods are called according to our choice
    static void app(){
        Scanner input = new Scanner(System.in);
    int choice;
    System.out.println("What would you like to do\nChoice-1--Display Menu\nChoice-2--Take Order & Billing\nChoice-3--Membership Form\nChoice-4--Exit The Program ");
    System.out.println();
    choice = input.nextInt();
    if (choice == 1) {
        GUI_interface();
    } else if (choice == 2) {
        place_Order();
    } else if (choice == 3) {
        member();
    } else if (choice == 4) {
        System.exit(0); //Exiting the program
    }
    }

    //creating GUI interface for displaying the menu and start the program
static void GUI_interface(){
    JFrame frame = inter(); //Calling inter() method for creating a JFrame file
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11; //Declaring labels for displaying output
    l1 = new JLabel("|    W E L C O M E  T O  O U R  R E S T A U R A N T     |");

    l1.setBounds(550, 50, 1000, 30);   //Setting the location of entered data
    l2 = new JLabel("THE PAKISTANI FOOD AVAILABLE ARE");
    l2.setBounds(550, 120, 1000, 30);
    l3 = new JLabel("1 - BIRYANI----RS 250  2 - CHICKEN KARAHI-----RS 1500  3 - MUTTON KARAHI----RS 22004  4- NIHARI----RS 700  5 - C-HICKEN TIKKA----RS 200  6 - BAR-B-Q----RS 250  7 - DAAL CHAWAL----RS 150");
    l3.setBounds(50, 150, 10000, 30);
    l4 = new JLabel("THE CHINESE FOOD AVAILABLE ARE");
    l4.setBounds(550, 200, 1000, 30);
    l5 = new JLabel("1 - CHOW MEIN----RS 600  2 - TOFU----RS 800  3 - SPRING ROLLS----RS 500  4 - MOMOS----RS 250  5 - CHICKEN FRIED RICE----RS 350");
    l5.setBounds(50, 230, 1000, 30);
    l6 = new JLabel("THE ITALIAN FOOD AVAILABLE ARE");
    l6.setBounds(550, 270, 1000, 30);
    l7 = new JLabel("1 - PIZZA----RS 1800  2 - PASTA----RS 900  3 - LASAGNA----RS 1000  4 - RISOTTO----RS 2500  5 - RATATOUILLE----RS 3000");
    l7.setBounds(50, 300, 1000, 30);
    l8 = new JLabel("THE SPANISH FOOD AVAILABLE ARE");
    l8.setBounds(550, 350, 1000, 30);
    l9 = new JLabel("1 - GAZPACHO----RS 1500  2 - PATATA----RS 2000  3 - PAELLA----RS 3000  4 - TORTILA ESPANOLA----RS 3500  5 - CHURROS CON CHOCOLATE----RS 1500");
    l9.setBounds(50, 380, 1000, 30);
    l10 = new JLabel("THE TURKISH FOOD AVAILABLE ARE");
    l10.setBounds(550, 420, 1000, 30);
    l11 = new JLabel("1 - INEGOL KOFTE----RS 850  2 - ISKENDER KEBAB----RS 1600  3 - CAG KEBAB----RS 2000  4 - GOZLEME ----RS 600  5 - DONDURMA----RS 1500");
    l11.setBounds(50, 450, 1000, 30);
    frame.add(l1);
    frame.add(l2);
    frame.add(l3);
    frame.add(l4);
    frame.add(l5);
    frame.add(l6);
    frame.add(l7);
    frame.add(l8);
    frame.add(l9);
    frame.add(l10);
    frame.add(l11);
    JButton btn = new JButton("PLACE ORDER"); //creating button for Starting program
    btn.setBounds(550,550,300,30);
    frame.add(btn);
    btn.addActionListener(e -> app());  //when button is pressed our main method is called


    }

// this creating JFrame interface
    static JFrame inter(){
        JFrame frame = new JFrame();
        frame.setVisible(true);  //making sure that frame is visible
        frame.setTitle("MILLENNIUM RESTAURANT APP"); //file name
        frame.setLayout(null);
        frame.setBounds(100, 200, 350, 300); // setting bounds for the JFRAME file
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // this will exit the JFRAME file when clicked on cross icon
        return frame;
    }

    static boolean check (String x,int total){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your credit card number: ");
        x = input.next();
        boolean isCheck = (x.length() == 14 &&
                (Character.isDigit(x.charAt(0))) &&
                (Character.isDigit(x.charAt(1))) &&
                (Character.isDigit(x.charAt(2))) &&
                (Character.isDigit(x.charAt(3))) &&
                (x.charAt(4) == '-') &&
                (Character.isDigit(x.charAt(5))) &&
                (Character.isDigit(x.charAt(6))) &&
                (Character.isDigit(x.charAt(7))) &&
                (Character.isDigit(x.charAt(8))) &&
                (x.charAt(9) == '-') &&
                (Character.isDigit(x.charAt(10))) &&
                (Character.isDigit(x.charAt(11)) &&
                        (Character.isDigit(x.charAt(12))) &&
                        (Character.isDigit(x.charAt(13)))));
        if (isCheck){
            System.out.println(x + "is a valid credit card number   ");
            System.out.println("enter your 4-Digit pin: ");
            int[] pin = new int[4];
            for (int i = 0; i < pin.length; i++) {
                pin[i] = input.nextInt();
            }
            System.out.println("The amount deducted from your account is: "+total);
        }
        else{
            System.out.println("it is not a valid credit card number\nyour number should in the form of DDDD-DDDD-DDDD\nplease enter again");
check(x, total);}
        return isCheck;
    }


}







