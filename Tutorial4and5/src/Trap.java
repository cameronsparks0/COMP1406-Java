public class Trap extends StationaryObject implements Harmful {

    public Trap(Point2D loc) {
        super(loc);
    }

    public int getDamageAmount(){
        return -50;
    }

    public String toString() {
        return generalInformation("Trap");
    }
}