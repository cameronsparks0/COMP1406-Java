public abstract class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;

    public Product(double price, int quantity){
        this.price = price;
        this.stockQuantity = quantity;
        this.soldQuantity = 0;
    }

    public double sellUnits(int amount){
        if(amount>=0){
            if((stockQuantity-amount)>=0){
                stockQuantity = stockQuantity-amount;
                soldQuantity = soldQuantity + amount;
                return (amount*price);
            }
        }
        return -1.0;
    }

    public String sellInformation(){
        return "\n("+price+" dollars each, "+stockQuantity+" in stock, "+soldQuantity+" sold)";
    }
}
