public class Store {
    public static final int MAX_CUSTOMERS = 500;
    private String name;
    private Customer[] customers;
    private static int LATEST_ID = 100000;
    private int customerCount;
    public Store(String n) {
        name = n;
        customers = new Customer[MAX_CUSTOMERS];
        customerCount = 0;
    }

    public Customer[] getCustomers(){
        return customers;
    }
    public int getCustomerCount(){
        return customerCount;
    }

    public void addCustomer(Customer c) {
        if (customerCount < MAX_CUSTOMERS)
            customers[customerCount] = c;
            customers[customerCount].setRewardId(LATEST_ID);
            LATEST_ID = LATEST_ID + 1;
            customerCount = customerCount + 1;
    }
    public void listCustomers() {
        for (int i=0; i<customerCount; i++)
            System.out.println(customers[i]);
    }
    public int averageCustomerAge(){
        int totalAge=0;
        for (int i=0;i<customerCount;i++){
            totalAge= totalAge + customers[i].getAge();
        }
        return totalAge/customerCount;
    }

    public Customer richestCustomer(){
        Customer richestPerson = new Customer("default",0,20);
        float maxMoney=0;
        for (int i=0;i<customerCount;i++){
            if(!richestPerson.hasMoreMoneyThan((customers[i]))){
                richestPerson = customers[i];
            }
        }
        return richestPerson;
    }

}
