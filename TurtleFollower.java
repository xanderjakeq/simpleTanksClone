import java.awt.Color;

/**
 * Represents a Turtle that follows a target.
 * If target is non-null, then this follower will
 * always turn to face its target whenever waddle() is
 * called.
 * 
 * @author AP CS A (20 Feb 2018)
 */
public class TurtleFollower extends Turtle {
  // Fields
  /** Target to follow (can be null). */
  private StdDrawable target;
  
  // Constructors
  public TurtleFollower(String newName, Color newColor, double newX, double newY) {
    super(newName, newColor, newX, newY); // Calls Turtle constructor
  }
  
  public TurtleFollower(StdDrawable target) {
    super();
    setTarget(target);
  }
  
  // Methods
  public void setTarget(StdDrawable t) {
    target = t;
  }
  
  public void waddle(double dist) {
    // Turn to face target (if target != null and not in same location)
    if (target != null && !getLocation().equals(target.getLocation())) {
      turnTo(getLocation().dirTo(target.getLocation()));
    }
    super.waddle(dist); // do the usual Turtle waddle
  }

  /** Waddles forward 0.001. */
  public void step() {
    waddle(0.001);
  }
    
  public static void main(String[] args) {
    // Create a TurtleFollower and something for it to follow.
    StdDrawable leader = new BouncyBall();
    TurtleFollower follower = new TurtleFollower("follower", Color.GREEN, 0, 0);
    follower.setTarget(leader);
    StdDraw.enableDoubleBuffering();

    while (true) {
      // StdDraw.clear();
      leader.step();
      follower.step();
      leader.draw();
      follower.draw();
      StdDraw.show();
      StdDraw.pause(10);
    }
  }

}
