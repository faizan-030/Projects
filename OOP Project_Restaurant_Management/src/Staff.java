import java.io.Serializable;

public class Staff extends Person implements Serializable {
    private int ID;
    private String designation;

    Staff(){
        super();
        this.ID = 000;
        this.designation = "Undefined";
    }
    Staff(String n,String c,int a,int id,String d){
        super(n, c, a);
        this.ID = id;
        this.designation = d;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String toString(){
        return (super.toString()+"\nID : "+this.getID()+"\nDESIGNATION : "+this.getDesignation());
    }
    public void display(){
        System.out.println(this.toString());
    }
}
