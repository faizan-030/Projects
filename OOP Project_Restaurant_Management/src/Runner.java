public class Runner {
    public static void main(String[] args) {
        Menu m = new Menu();
        m.addToMenu("Biryani",200);
        m.addToMenu("Bar b Que",350);
        m.addToMenu("pasta",250);
        m.updatePriceOfItem("Tikka",300);
        m.deleteFromMenu("Biryani");
        m.display();
//

    }
}
