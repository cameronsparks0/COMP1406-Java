import java.io.*;
public class Store {
  public static final int MAX_CUSTOMERS = 500;
  private static int LATEST_ID = 100000;
  private String      name;
  private Customer[]  customers;
  private int         customerCount;
  
  public Store(String n) {
    name = n;
    customers = new Customer[MAX_CUSTOMERS];
    customerCount = 0;
  }
  
  public Customer[] getCustomers() {
    return customers;
  }
  
  public int getCustomerCount() {
    return customerCount;
  }
  
  
  public void addCustomer(Customer c) {
    if (customerCount < MAX_CUSTOMERS)
      customers[customerCount++] = c;
    c .setID(LATEST_ID);
    LATEST_ID++;
  }
  
  public void listCustomers() {
    for (int i=0; i<customerCount; i++)
      System.out.println(customers[i]);
    
  }
  
  public int averageCustomerAge(){
    int total = 0;
    for(int i = 0; i < customerCount; i++){
      total += customers[i].getAge(); 
    }
    System.out.println(total + " / " + customerCount);
    return total / customerCount;
  }
  
  public Customer richestCustomer(){
    Customer richest = null;
    for(int i = 0; i < customerCount; i++){
      if(richest == null || customers[i].hasMoreMoneyThan(richest)){
        richest = customers[i];
      }
    }
    return richest;
  }
  
  public Customer[] friendsFor(Customer c){
    int friendCount = 0;
    
    for(int i =0; i < customerCount; i++){
      if(Math.abs(customers[i].getAge() - c.getAge()) <= 3 && c != customers[i]){
        friendCount++;
      }
    }
    Customer[] friends = new Customer[friendCount];
    friendCount = 0;
    for(int i = 0; i < customerCount; i++){
      if(Math.abs(customers[i].getAge() - c.getAge()) <= 3 && c != customers[i]){
        friends[friendCount] = customers[i];
        friendCount++;
      }
    }
    return friends;
  }

  public void saveTo(PrintWriter aFile) throws IOException {
    for(Customer c: customers){
      try {
        c.saveTo(aFile);
      } catch (Exception e){
        //Do nothing
      }
    }
  }

  public static Store readFrom(BufferedReader aFile) throws IOException
  {
    Store s = new Store("?");
    while (aFile.ready()) {
      s.customers[s.customerCount]= Customer.readFrom(aFile);
      s.customerCount = s.customerCount + 1;
    }
    return s;
  }


}
