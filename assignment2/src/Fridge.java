public class Fridge extends Kitchen{
    private double cubicFeet;
    private boolean hasFreezer;

    public Fridge(double price, int quantity, int wattage, String color, String brand, double cubicFeet, boolean freezer){
        super(price,quantity,wattage,color,brand);
        this.cubicFeet = cubicFeet;
        this.hasFreezer = freezer;
    }

    public String toString(){
        if(hasFreezer){
            return cubicFeet+" cu. ft. "+getBrand()+" Fridge with Freezer "+kitchenUtilityInformation();
        }
        else{
            return cubicFeet+" cu. ft. "+getBrand()+" Fridge "+kitchenUtilityInformation();
        }
    }
}
