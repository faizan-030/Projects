import java.io.Serializable;
import java.util.ArrayList;

public class Customer extends Person implements Serializable{
    private Menu menu;
    private ArrayList<String>allOrders;
    private int totalPrice;


    Customer(String n, String c,int a,Menu m){
        super(n, c, a);
        this.menu = m;
        this.allOrders = new ArrayList<>();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void takeOrder(String o) {
        for (int i =0; i<this.menu.getNames().length;i++){
            if (o.equals(this.menu.getNames()[i])){
                this.allOrders.add(o);
                this.totalPrice = this.getTotalPrice()+this.menu.getPrice()[i];
                System.out.println("YOUR ORDER HAS BEEN ADDED TO ORDER LIST");
                return;
            }
        }
        System.out.println("SORRY THE ORDER YOU PLACED IS UNAVAILABLE");
    }
    public void displayTotalBill(){
        System.out.println("ITEMS  -------  PRICE");
        for (int i = 0;i< allOrders.size();i++){
            System.out.println(allOrders.get(i)+"      "+this.menu.getPriceOfItem(allOrders.get(i)));
        }
    }
}
