public class ToasterOven extends Kitchen{
    private int width;
    private boolean convection;

    public ToasterOven(double price, int quantity, int wattage, String color, String brand, int width, boolean convection){
        super(price,quantity,wattage,color,brand);
        this.width = width;
        this.convection = convection;
    }

    public String toString(){
        if(convection){
            return width+" inch "+getBrand()+" Toaster with convection "+kitchenUtilityInformation();
        }
        else{
            return width+" inch "+getBrand()+" Toaster "+kitchenUtilityInformation();
        }
    }
}
