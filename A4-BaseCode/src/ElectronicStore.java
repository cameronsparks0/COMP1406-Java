//Class representing an electronic store
//Has an array of products that represent the items the store can sell
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class ElectronicStore implements java.io.Serializable{ // Implements Serializable so that ElectronicStore objects can be written to a file.
  private String name;
  private ArrayList<Product> stock; //Using an Arraylist ADT to store the stock and customers of the store
  private double revenue;
  private ArrayList<Customer> customers;
  
  public ElectronicStore(String initName){ // Initializing ADTs and other store variables.
    revenue = 0.0;
    name = initName;
    stock = new ArrayList<Product>();
    customers = new ArrayList<Customer>();
  }

  public <E> boolean genericChecker(E obj, ArrayList<E> objList){ // Using a generic helper method for both addProduct and registerCustomer because they are identical code in terms of functionality.
    for(E o: objList){ // Grabbing every object in objList (Whether products or customers) to check if the same object already exists.
      if(o.toString().equals(obj.toString())){
        return false; // Returns false and doesn't add the product or customer to their respective store list.
      }
    }
    objList.add(obj);
    return true;
  }

  public boolean addProduct(Product newProduct){
    return genericChecker(newProduct, stock);
  }

  public boolean registerCustomer(Customer newCustomer){
    return genericChecker(newCustomer, customers);
  }

  public List<Product> searchProducts(String x){
    ArrayList<Product> searchedProducts = new ArrayList<>();
    for(Product p: stock){ // Grabbing every product and comparing it's name to the String x
      if(p.toString().toLowerCase().contains(x.toLowerCase())){
        searchedProducts.add(p); // If x is contained within one of the product strings then add it to the final returned list.
      }
    }
    return searchedProducts;
  }

  public List<Product> searchProducts(String x, double minPrice, double maxPrice){
    ArrayList<Product> searchedProducts = new ArrayList<>(searchProducts(x)); // Might as well use the other function to narrow down the results and only compare prices instead of names as well.
    ArrayList<Product> priceRangeProducts = new ArrayList<>();
    if(minPrice>=0 && maxPrice>=0){ // Checking all of the different cases
      for(Product p: searchedProducts){ // Grabbing all the products and adding them to the final list if they are within price range.
        if(p.getPrice()>=minPrice && p.getPrice()<=maxPrice){
          priceRangeProducts.add(p);
        }
      }
      return priceRangeProducts;
    }
    else if(minPrice<0 && maxPrice<0){
      return searchedProducts; // Returns the regular search without any price range
    }
    else if(minPrice>=0 && maxPrice<0){
      for(Product p: searchedProducts){
        if(p.getPrice()>=minPrice){
          priceRangeProducts.add(p);
        }
      }
      return priceRangeProducts;
    }
    else {
      for(Product p: searchedProducts){
        if(p.getPrice()<=maxPrice){
          priceRangeProducts.add(p);
        }
      }
      return priceRangeProducts;
    }
  }

  public boolean addStock(Product p, int amount){
    for(Product product: stock){ // Grabbing every product and increasing its amount if it matches the given product.
      if(product.toString().equals(p.toString())){
        product.increaseStock(amount);
        return true;
      }
    }
    return false;
  }

  public boolean sellProduct(Product p, Customer c, int amount){
    if(stock.contains(p) && customers.contains(c) && (p.getStockQuantity()>=amount) && amount>0){ // Selling the product to the customer if all the criteria is met.
      c.addItem(p.toString(),amount,p.getPrice()*amount); // Adds the item to the customer object (Stored in the hashmap and updates totalSpent)
      p.sellUnits(amount);
      return true;
    }
    return false;
  }

  public List<Customer> getTopXCustomers(int x){
    ArrayList<Customer> topCustomers = new ArrayList<Customer>(); // Final list to be returned
    ArrayList<Customer> tempCustomers = new ArrayList<Customer>(customers); // Copy of the customers List used to find the top x customer without altering the original list.
    if(x<=0){
      return topCustomers; // Returns null
    }
    for(int y=0; y<x; y++){ // Iterates x times
      Customer mostMoney = new Customer("");
      for(Customer c: tempCustomers){
        if(c.getTotalSpent()>=mostMoney.getTotalSpent()){
          mostMoney = c; // Finding the user with the most money spent.
        }
      }
      if(!mostMoney.getName().equals("")){//Making sure the default comparable customer is not added to the final List (In case the user inputs an x value larger then the total amount of customers )
        tempCustomers.remove(mostMoney); // Removing the user out of the List so that the loop doesn't add the same customer over and over again.
        topCustomers.add(mostMoney);
      }
    }
    return topCustomers;
  }

  public boolean saveToFile(String filename){
    try{
      ObjectOutputStream out; // Writing the full object out to the file instead of individual values (Much easier)
      out = new ObjectOutputStream(new FileOutputStream(filename));
      out.writeObject(this); // Writing this store object to the file
      out.close();
      return true;
    }
    catch (FileNotFoundException e){
      return false;
    }
    catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static ElectronicStore loadFromFile(String filename){
    try{
      ObjectInputStream in; // Reading in the full object instead of reading individual values at a time.
      in = new ObjectInputStream(new FileInputStream(filename));
      ElectronicStore newStore = (ElectronicStore)in.readObject();
      in.close();
      return newStore;
    }
    catch (ClassNotFoundException e){
      return null;
    }
    catch (FileNotFoundException e){
      return null;
    }
    catch (IOException e){
      return null;
    }
  }

  public List<Customer> getCustomers(){ return customers;}

} 