public class Ball extends MovableObject implements Harmful {
    private boolean 	isBeingHeld;

    public Ball(Point2D loc) {
        super(0,0,loc);
        isBeingHeld = false;
    }

    // The get/set methods
    public boolean isBeingHeld() { return isBeingHeld; }
    public void setIsBeingHeld(boolean newHoldStatus) { isBeingHeld = newHoldStatus; }

    public void draw(){
        System.out.println("ball is at "+location+" facing "+direction+" degrees and moving at "+speed+" pixel per second");
    }

    public void update(){
        moveForward();
        draw();
        if(speed!=0){
            speed = speed -1;
        }
    }

    public int getDamageAmount(){
        return -200;
    }

    public String toString() {
        return movementInformation("Ball");
    }
}