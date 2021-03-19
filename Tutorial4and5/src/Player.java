import java.awt.Color;
public class Player extends MovableObject {
    private String		name;
    private Color       color;
    private boolean 	hasBall;
    private int	 		score;

    public Player(String n, Color c, Point2D loc, int dir) {
        super(0,dir,loc);
        name = n;
        color = c;
        hasBall = false;
        score = 0;
    }

    // The get/set methods
    public String getName() { return name; }
    public Color getColor() { return color; }
    public boolean hasBall() { return hasBall; }
    public int getScore() { return score; }
    public void setHasBall(boolean newHasBall) { hasBall = newHasBall; }
    public void setScore(int newScore) { score = newScore; }

    public void draw(){
        System.out.println("Player "+name+ " is at " +location+" facing "+direction+" degrees and moving at "+speed+" pixel per second");
    }

    public String toString() {
        String  s =  movementInformation(name);
        if (hasBall)
            s += " with the ball";
        return s;
    }
}