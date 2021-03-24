//Base class for all products the store will sell
public class Product{
 private double price;
 private int stockQuantity;
 private int soldQuantity;
 
 public Product(double initPrice, int initQuantity){
   price = initPrice;
   stockQuantity = initQuantity;
   soldQuantity = 0;
 }
 
 public int getStockQuantity(){
   return stockQuantity;
 }
 
 public int getSoldQuantity(){
   return soldQuantity;
 }
 
 public double getPrice(){
   return price;
 }

 public void eraseSoldQuantity(){soldQuantity = 0;} // When a purchase is complete to get whichever item it is out of the cart.

 public void buyUnits(){ //When the user removes something from the cart
     stockQuantity = stockQuantity +1;
     soldQuantity = soldQuantity -1;
 }
 
 //Returns the total revenue (price * amount) if there are at least amount items in stock
 //Return 0 otherwise (i.e., there is no sale completed)
 public double sellUnits(int amount){
   if(amount > 0 && stockQuantity >= amount){
     stockQuantity -= amount;
     soldQuantity += amount;
     return price * amount;
   }
   return 0.0;
 }
}