//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.util.*;

public class ElectronicStore{
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int curProducts;
  private String name;
  private Product[] stock; //Array to hold all products
  private int numSales; // Keeps track of the number of complete sales
  private double revenue;
  private ArrayList<Product> cartItems = new ArrayList<Product>(); // Keeps track of all the items added to the cart.
  private HashMap<String,Integer> soldItems = new HashMap<String,Integer>(); //Keeps track of all the sold items and the number of them sold.
  private boolean noPurchases; // Used to verify whether any purchases have been made yet (For the most popular list view)

  public ElectronicStore(String initName){
    revenue = 0.0;
    name = initName;
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
    numSales=0;
    noPurchases=true;
  }

  //Getters
  public String getName(){ return name;}
  public Product[] getStock(){return stock;}
  public ArrayList<Product> getCartItems(){return cartItems;}
  public int getNumSales(){return numSales;}
  public double getRevenue(){ return revenue; }

  //Methods For The Store
  public void completeTransaction(){ // Method to clear the items that still remain in the cart and add them to the soldItems HashMap
    for(Product p: cartItems){
      if(p.getSoldQuantity()>=1){ //Making sure to sell only the items that are actually within the cart.
        if(soldItems.containsKey(p.toString())){ //If the item has already been sold before add the new soldQuantity to its value in the soldItems hashmap.
          soldItems.put(p.toString(),soldItems.get(p.toString())+p.getSoldQuantity());
        }
        else{ //If the item has not been sold before then put it in the hashmap along with the initial amount being sold.
          soldItems.put(p.toString(),p.getSoldQuantity());
        }
        p.eraseSoldQuantity(); //Taking the item(s) out of the cart.
      }
    }
    noPurchases=false;
  }

  public ArrayList<String> getMostPopular(){ // Returns an arrayList of 1-3 strings that represent the most popular items (Most bought)
    ArrayList<String> mostPopular;
    if(noPurchases) { // If nothing has been purchased use the default filler items
      mostPopular = getMostPopularNoPurchase();
    }
    else{ //If something has been purchased, fill it with the actual most popular items
      mostPopular = getMostPopularPurchase();
    }
    return mostPopular;
  }

  public ArrayList<String> getMostPopularNoPurchase(){ // Helper function for finding the default popular items
    ArrayList<String> mostPopular = new ArrayList<String>();
    int minNum;

    if (curProducts >= 3) { //Checking all item amount cases
      minNum = 3;
    } else if (curProducts == 2) {
      minNum = 2;
    } else {
      minNum = 1;
    }
    for (int x = 0; x < minNum; x++) {
      mostPopular.add(stock[x].toString());
    }
    return mostPopular;
  }

  public ArrayList<String> getMostPopularPurchase(){ //Helper function for finding the actual most popular items. - !!! ONLY RETURNS ITEMS THAT HAVE ACTUALLY BEEN BOUGHT LIKE THE ASSIGNMENT MENTIONS !!!
    ArrayList<String> mostPopular = new ArrayList<String>();
    ArrayList<Integer> mostPopularValues = new ArrayList<Integer>(); // For storing the amount of purchases of every sold item.
    int maxCount=3;
    for(Product p: cartItems){
      if(soldItems.containsKey(p.toString())){ //Adding the amount of each sold item to mostPopularValues.
        mostPopularValues.add(soldItems.get(p.toString()));
      }
    }
    Collections.sort(mostPopularValues); //Sorting mostPopularValues so that its largest sales are at the end.
    for(int x=mostPopularValues.size();x>mostPopularValues.size()-4;x--){ //Iterating through from the largest index position of mostPopularItems until three down (To get the most popular three items)
      try{ //Because it is not known how many items have actually been sold (Could be one or two) the index position could be out of bounds.
        for(Product p: cartItems){ //Finding the three items with the largest amount bought
          if(soldItems.containsKey(p.toString()) && soldItems.get(p.toString()).equals(mostPopularValues.get(x)) && maxCount>0 && !mostPopular.contains(p.toString())){
            mostPopular.add(p.toString());
            maxCount = maxCount - 1; //Keeping track of how many have been added (Only three is allowed) - Because some items can sell the same amount
          }
        }
      }
      catch (Exception e){ //Could have just wrote multiple if statements based on the size of the amount sold, however this would take up more space.
        ;
      }
    }
    return mostPopular;
  }

  public ArrayList<String> getProductNames(){ // Returns a string ArrayList of all the product names within the stock (If they have a stock quantity >= 1 )
    ArrayList<String> productNames = new ArrayList<String>();
    for(int x=0; x<curProducts;x++){
      if(stock[x].getStockQuantity()>=1 && stock[x]!=null) {
        productNames.add(stock[x].toString());
      }
    }
    return productNames;
  }

  public ArrayList<String> getCartNames(){ // Returns a string ArrayList of all product names within the cart (If they have a soldQuantity >= 1)
    ArrayList<String> itemNames = new ArrayList<String>();
    for(Product p: cartItems){
      if(p.getSoldQuantity()>=1){
      itemNames.add(p.getSoldQuantity() + " x "+p.toString()); // Making sure to add the amount being sold beside the name of the item.
      }
    }
    return itemNames;
  }

  public float getCartPrice() { // Returns the total price of all the items within the cart.
    float total = 0;
    for (Product p : cartItems) {
      total = total + p.getSoldQuantity()* (float)p.getPrice();
    }
    return total;
  }
  
  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
     stock[curProducts] = newProduct;
     curProducts++;
     return true;
    }
    return false;
  }
  
  //Sells 'amount' of the product at 'index' in the stock array
  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modified
  //If the index is invalid, the revenue is not modified
  public void sellProducts(int index, int amount){
    if(index >= 0 && index < curProducts){
      stock[index].sellUnits(amount);
    }
  }

  public void incrementNumSales(){numSales=numSales+1;} // Increases the total amount of sales that have occurred.

  public void calculateRevenue(){ //Sets the variable revenue as the original revenue plus the cost of the new items being bought.
    revenue = revenue + getCartPrice();
  }
  
  //Prints the stock of the store
  public void printStock(){
    for(int i = 0; i < curProducts; i++){
      System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
    }
  }
  
  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }
} 