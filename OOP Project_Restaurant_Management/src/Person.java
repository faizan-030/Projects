import java.io.Serializable;

public class Person implements Serializable {
    protected String name;
    protected String CNIC;
    protected int age;

    Person(){
        this.name = "Undefined";
        this.CNIC = "XXXXX-XXXXXXX-X";
        this.age = 0;
    }
    Person(String n, String c,int a){
        if (a < 0){
            this.age = 18;
        }
        this.name = n;
        this.CNIC = c;
        this.age = a;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String toString(){
        return ("NAME : "+this.getName()+"\nCNIC : "+this.getCNIC()+"\nAGE : "+this.getAge());
    }
    public void display(){
        System.out.println(this.toString());
    }
}
