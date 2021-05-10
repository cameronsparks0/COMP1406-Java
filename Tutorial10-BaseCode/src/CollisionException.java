public class CollisionException extends Exception {
    MovableObject source;
    public CollisionException(MovableObject s) {
        super("Player collided with wall !");
        source = s;
    }
}