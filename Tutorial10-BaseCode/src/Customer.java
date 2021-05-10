import java.io.*;
public class Customer {
    private String      name;
    private int         age; 
    private float       money;
    private int id;

    public Customer(String n, int a, float m) {
        name = n;
        age = a; 
        money = m;
        id = -1;
    }
    
    public void setID(int newID){
      id = newID;
    }

    public String toString() {
        return "Customer " + name + ": a " + age + " year old with $" + money;
    }
    
    public String getName() { return name; }
    public int getAge(){return age;}
    public float getMoney(){return money;}
    public int getId(){return id;}

    public void saveTo(PrintWriter aFile) throws IOException {
        aFile.print(id+",");
        aFile.print(name+",");
        aFile.print(age+",");
        aFile.print(""+money);
        aFile.println("");
    }

    public static Customer readFrom(BufferedReader aFile) throws IOException {
        Customer readCustomer = new Customer("",0,0);
        String[] customerInfo = aFile.readLine().split(",");
        readCustomer.id = Integer.parseInt(customerInfo[0]);
        readCustomer.name = customerInfo[1];
        readCustomer.age = Integer.parseInt(customerInfo[2]);
        readCustomer.money = Float.parseFloat(customerInfo[3]);
        return readCustomer;
    }


    public boolean hasMoreMoneyThan(Customer c) {
      return money > c.money;
    }
}
