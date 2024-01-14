import java.io.Serializable;
import java.util.Objects;

public class Menu implements Serializable {
    private String [] names;
    private int [] price;
    private int itemCount;


    Menu(){
    this.names = new String[50];
    this.price = new int[50];
    this.itemCount = 0;
    }

    public void addToMenu(String n,int p){
        if (this.itemCount >= names.length){
            System.out.println("The Menu is full no further additions can be done : ");
            return;
        }
        for (int i = 0; i<names.length; i++){
            if (names[i] == null){
                this.names[i] = n;
                this.price[i] = p;
                itemCount++;
                break;
            }
        }
    }

    public String[] getNames() {
        return names;
    }

    public int[] getPrice() {
        return price;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void deleteFromMenu(String n) {

        if (itemCount == 0) {
            System.out.println("The Menu is empty. Please add items to the Menu first.");
        }

        for (int i = 0; i < names.length; i++) {
            if (Objects.equals(names[i], n)) {
                for (int j = i; j < names.length - 1; j++) {
                    names[j] = names[j + 1];
                    price[j] = price[j + 1];
                }
                return;


            }

        }
        System.out.println("Sorry please give correct name");
    }
    public void display(){
        for (int i = 0; i< names.length;i++){
            if (names[i] == null){
                break;
            }
            System.out.println("Item : "+names[i]+"----Price : "+price[i]);
        }
    }
    public void updatePriceOfItem(String n,int p){
        if (itemCount == 0) {
            System.out.println("The Menu is empty. Please add items to the Menu first.");
        }
        for (int i = 0;i< names.length;i++){
            if (Objects.equals(names[i], n)){
                price[i] = p;
            }
        }
    }
    public int getPriceOfItem(String n){
        int ret = 0;
        for (int i =0; i<price.length;i++){
            if (Objects.equals(names[i], n)){
                ret = price[i];
            }
        }
        return ret;
    }

}
