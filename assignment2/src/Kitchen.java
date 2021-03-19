public abstract class Kitchen extends Product {
    private int wattage;
    private String color;
    private String brand;

    public String getBrand(){return brand;}

    public Kitchen(double price, int quantity, int wattage, String color, String brand){
        super(price,quantity);
        this.wattage = wattage;
        this.color = color;
        this.brand = brand;
    }

    public String kitchenUtilityInformation(){
        return "("+color+", "+wattage+" watts)"+sellInformation();
    }
}
