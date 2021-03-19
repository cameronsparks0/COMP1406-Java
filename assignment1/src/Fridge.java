public class Fridge {
    double size;
    boolean freezer;
    String color;

    public Fridge(double size, boolean freezer, String color){
        this.size = size;
        this.freezer = freezer;
        this.color = color;
    }

    public String toString(){
        if(freezer){
            return (size +" cubic foot Fridge with Freezer ("+color+")");
        }
        else{
            return (size +" cubic foot Fridge ("+color+")");
        }
    }

}
