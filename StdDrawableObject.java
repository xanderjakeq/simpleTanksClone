/**
 * Abstract superclass for StdDrawable objects.  Manages
 * location and world.  Concrete subclasses must implement
 * draw() and step() methods.
 * 
 * @author AP CS A (1 March 2018)
 */
public abstract class StdDrawableObject implements StdDrawable {
  private Point loc;
  private StdWorld world;
  /** Direction (radians in standard position, from -pi to pi). */
  private double dir;

  public StdDrawableObject(Point p) { this.loc = p; }
  
  public void setLocation(Point p) { loc = p; }
  public Point getLocation() { return loc; }
  public double x() { return loc.x(); }
  public double y() { return loc.y(); }  
  
  public double getDir() { return dir; }
  
  /** Turns turtle to face given direction (radians in standard position). */
  public void turnTo(double dir) {
    turn(dir - getDir());
  }

  /**
   * Turns given angle in radians counter-clockwise.
   * <p>
   * Post condition: object's direction is between -pi and pi.
   */
  public void turn(double angle) {
    dir += angle;
    // See https://stackoverflow.com/questions/24234609/standard-way-to-normalize-an-angle-to-π-radians-in-java
    final double TWO_PI = 2*Math.PI;
    dir = dir - TWO_PI * Math.floor((dir + Math.PI) / TWO_PI);
    // There's also a cleaner solution there, but I erred on the side
    // of speed, which is never a good idea :P
    if (dir < -Math.PI || dir > Math.PI) {
      throw new IllegalStateException("Angle should be between 0 and 2pi: " + dir);
    }
  }
   
  public void setWorld(StdWorld w) { world = w; }  
  public StdWorld getWorld() { return world; }
  
  public String toString() {
    return getClass().getName() + " at " + loc + " facing " + dir;
    // getClass().getName() will return the class name of the current
    // object, even if it's a subclass.
    // This way all of our many subclasses don't have to override
    // toString() just to change the class name :)
  }
}
