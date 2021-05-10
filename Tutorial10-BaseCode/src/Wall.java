import java.awt.Color;
public class Wall extends StationaryObject {
    private int      width;
    private int      height;

    public Wall(Point2D loc, int w, int h) {
        super(loc);
        width = w;
        height = h;
    }

    // The get/set methods
    public Point2D getLocation() { return location; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public void setLocation(Point2D newLocation) { location = newLocation; }

    public boolean contains(Point2D p) {
        if(p.getX()>=this.location.getX() && p.getX()<=(this.location.getX()+this.width-1) && p.getY()>=this.location.getY() && p.getY()<=(this.location.getY()+this.height-1)){
            return true;
        }
        else{
            return false;
        }

    }

    public String toString() {
        return "Wall" + " at (" + (int)location.getX() + "," + (int)location.getY() + ") with width " +
                width + " and height " + height;
    }

    public char appearance() { return '#'; }
}