import java.util.HashMap;

public class Customer implements java.io.Serializable { // Implements Serializable so that customer objects can be written to a file.
    private String name;
    private double totalSpent;
    private HashMap<String,Integer> purchases; // A hashmap to keep track of the different purchases a customer has made (The keys represent the name of the item and the values represent how much of that item)

    public Customer(String name){
        this.name = name;
        totalSpent = 0;
        purchases = new HashMap<>();
    }

    public void printPurchaseHistory(){
        for(String key: purchases.keySet()){ //Grabs all the different keys in the hashmap to display it's values
            System.out.println(purchases.get(key)+" x "+key);
        }
    }

    public void addItem(String item, int amount, double cost){
        if(purchases.containsKey(item)){ // Adds the amount bought to the hashmap and updates the total amount of money the customer has spent.
            purchases.put(item,purchases.get(item)+amount);
            totalSpent = totalSpent + cost;
        }
        else{ // If it's the first time a user is buying an item it adds it to the hashmap along with the initial amount bought. Also updates totalSpent.
            purchases.put(item,amount);
            totalSpent = totalSpent + cost;
        }
    }

    public String toString(){
        return name+" Who has spent $"+totalSpent;
    }

    public double getTotalSpent(){ return totalSpent;}
    public String getName(){return name;}
}
