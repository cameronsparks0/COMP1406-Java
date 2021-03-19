public abstract class GameObject {
    protected Point2D location;

    public GameObject(Point2D initLocation){
        location = initLocation;
    }

    public abstract void update();

    public Point2D getLocation() { return location; }
    public void setLocation(Point2D newLocation) { location = newLocation; }

    public String generalInformation(String className) {
        return className+" at ("+(int)location.getX()+","+(int)location.getY()+ ") ";
    }
}
