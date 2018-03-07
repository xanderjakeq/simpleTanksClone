import java.awt.Color;
import java.util.ArrayList;

/**
 * A turtle that maintains a list of followers that copy everything
 * this turtle does.
 * 
 * @author APCS A (Feb 15 2018)
 */
public class TurtleLeader extends Turtle {
  // Fields
  private ArrayList<Turtle> followers;
  
  // Constructors
  public TurtleLeader(String newName, Color newColor, double newX, double newY) {
    super(newName, newColor, newX, newY); // Calls Turtle constructor
    // superclass constructor must be first line of your constructor.
    // (Your parent must be born before you!)
    followers = new ArrayList<>();
  }
  
  // Methods
  public void addFollower(Turtle t) {
    followers.add(t);
  }
  
  public void waddle(double dist) {
    super.waddle(dist); // do the usual Turtle waddle
    for (Turtle t : followers) { t.waddle(dist); }
  }
  
  public void turn(double angle) {
    super.turn(angle); // do the usual Turtle turn
    for (Turtle t : followers) { t.turn(angle); }
  }
  
  public void setPenDown(boolean isPenDown) {
    super.setPenDown(isPenDown);
    for (Turtle t : followers) { t.setPenDown(isPenDown); }
  }

  public String toString() {
    return "Leader" + super.toString() + " with " + followers.size() + " followers";
  }
  
  public static void main(String[] args) {
    TurtleLeader daBoss = new TurtleLeader("Squirtle", Color.GREEN, 0.5, 0.5);
    for (int i = 0; i < 5; i++) {
      Turtle t = new Turtle("Peon " + i, Color.RED, 0.1*i, 0.1*i);
      daBoss.addFollower(t);
    }
    daBoss.waddle(0.3);
    daBoss.turn(0.2);
    daBoss.waddle(0.3);
    System.out.println(daBoss);
  }
}
