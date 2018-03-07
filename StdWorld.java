import java.util.ArrayList;
import java.util.List;

/**
 * Represents a world in which StdDrawable objects exist.
 * 
 * @author AP CS A (22 Feb 2018)
 */
public class StdWorld {
  // Fields
  private final List<StdDrawable> objects = new ArrayList<>();
  /** Pause time between frames of animation. */
  private int pauseMs = 20;
  /** Clears StdDraw between steps of animation. */
  private boolean clearBetweenSteps = false;
    
  // Methods
  /** Clears StdDraw between steps of animation. */
  public void setClearBetweenSteps(boolean b) {
    clearBetweenSteps = b;
  }
  
  /** Starts infinite loop running the world. */
  public void runTheWorld() {
    StdDraw.enableDoubleBuffering();
    while (true) {
      if (clearBetweenSteps) { StdDraw.clear(); }
      // Use a traditional for-loop in case objects list
      // is modified during the loop (for-each would crash
      // with ConcurrentModificationException!)
      for (int i = 0; i < objects.size(); i++) {
        StdDrawable o = objects.get(i);
        o.step();
        o.draw();
      }
      StdDraw.show();
      StdDraw.pause(pauseMs);
    }
  }
  
  /** Add new object(s) to this world. */
  public void add(StdDrawable... objectsToAdd) {
    // Note: Putting ... after the type of a parameter means
    // that zero or more instances of that type are expected. It
    // can only be used with the last parameter in the list, and
    // in effect it wraps all of the instances into an array.
    // automatically.  See how this method, add(), is called in
    // main() below. For more details, search for "java varargs".
    // Thanks to Ethan for this suggestion!
    for (StdDrawable o : objectsToAdd) {
      objects.add(o);
      o.setWorld(this);
    }
  }
  
  /**
   * Removes given objects from this world. Returns true if
   * at least one object removed.
   */
  public boolean remove(StdDrawable... objectsToRemove) {
    boolean removed = false;
    for (StdDrawable o : objectsToRemove) {
      removed = objects.remove(o) || removed;
    }
    return removed;
  }
  
  public boolean remove(List<StdDrawable> objectsToRemove) {
    return remove(objectsToRemove.toArray(new StdDrawable[0]));
  }

  /** Gets a copy of the list of objects in the world. */
  public List<StdDrawable> getObjects() {
    // Returns a copy of the list so that the only way to modify the
    // list is via our add() or remove() methods.
    return new ArrayList<StdDrawable>(objects);
  }
  
  /** Returns list of objects at given location. */
  public List<StdDrawable> getObjectsAt(Point p) {
    List<StdDrawable> list = new ArrayList<>();
    for (StdDrawable sd : objects) {
      if (sd.getLocation().equals(p)) {
        list.add(sd);
      }
    }
    return list;
  }
  
  /** Returns list of objects near given location. */
  public List<StdDrawable> getObjectsNear(Point p, double radius) {
    List<StdDrawable> list = new ArrayList<>();
    for (StdDrawable sd : objects) {
      if (sd.getLocation().distanceTo(p) <= radius) {
        list.add(sd);
      }
    }
    return list;
  }
  
  /**
   * Returns list of objects near given object, not including that
   * object.
   */
  public List<StdDrawable> getObjectsNear(StdDrawable target, double radius) {
    List<StdDrawable> list = new ArrayList<>();
    Point targetLoc = target.getLocation();
    for (StdDrawable sd : objects) {
      if (sd != target && sd.getLocation().distanceTo(targetLoc) <= radius) {
        list.add(sd);
      }
    }
    return list;
  }
  
  // Static methods
  /** Runs a test world. */
  public static void main(String[] args) {
    // Initialize the world and the objects in it
    StdWorld world = new StdWorld();
    world.setClearBetweenSteps(true);
    BouncyBall ball = new BouncyBall();
    TurtleFollower follower1 = new TurtleFollower(ball);
    RandomTurtle dazy = new RandomTurtle();
    TurtleFollower follower2 = new TurtleFollower(dazy);
    FollowMouse fm = new FollowMouse();
    TurtleFollower follower3 = new TurtleFollower(fm);
    Steerable player = new Steerable();
    TurtleFollower chaser = new TurtleFollower(player);
    BlackHole bh = new BlackHole(Point.random(0.1, 0.9), 0.1);
    
    // Add the objects to the world
    world.add(ball, follower1, dazy, follower2, fm, follower3, bh,
              player, chaser);
    
    // Run the world
    world.runTheWorld();
  }
}
