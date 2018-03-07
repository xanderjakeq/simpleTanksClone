import java.awt.event.KeyEvent;

/**
 * Object that can be steered (turned) using the keyboard.
 * 
 * @author A. Jacoby (2 March 2018)
 */
public class Steerable extends StdDrawableObject {
  // TODO: Add support for colors
  // Fields
  /** Direction object facing (radians in standard position). */
  private double dir;
  
  /** Radius of object. */
  private double radius;
  
  /** Key code for turning left. See java.awt.event.KeyEvent. */
  private int keyCodeLeft;
  
  /** Key code for turning right. See java.awt.event.KeyEvent. */
  private int keyCodeRight;
  
  /** Amount to turn (in radians) each step when turning. */
  private double turnDelta = 0.1;
  
  /** Amount to move each step. */
  private double moveDelta = 0.01;
  
  // Constructors
  /**
   * Creates new Steerable with given location, radius, and
   * keycodes for turning left and right.
   */
  public Steerable(Point loc, double radius,
                   int keyCodeLeft, int keyCodeRight) {
    super(loc);
    this.radius = radius;
    this.keyCodeLeft = keyCodeLeft;
    this.keyCodeRight = keyCodeRight;
  }
  
  /**
   * Creates new Steerable with given location and radius.
   * Uses left and right arrow keys for steering.
   */
  public Steerable(Point loc, double radius) {
    this(loc, radius, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
  }
  
  /**
   * Creates new Steerable with random location and radius.
   * Uses left and right arrow keys for steering.
   */
  public Steerable() {
    this(Point.random(0, 1), Math.random()*0.03);
  }
  
  // Methods
  public double getDir() { return dir; }
  
  public void draw() {
    // TODO: Use an image (see BlackHole for example)
    final double T_LEN = radius*2;
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.setPenRadius(StdDraw.getPenRadius()*2);
    double x = getLocation().x();
    double y = getLocation().y();
    StdDraw.line(x, y, x + Math.cos(dir)*T_LEN, y + Math.sin(dir)*T_LEN);
    StdDraw.line(x+Math.cos(dir+Math.PI/2)*T_LEN/3, y+Math.sin(dir+Math.PI/2)*T_LEN/3,
                x+Math.cos(dir-Math.PI/2)*T_LEN/3, y+Math.sin(dir-Math.PI/2)*T_LEN/3);
    StdDraw.setPenRadius();
  }
  
  /** Checks for turning, then moves, then handles hitting walls. */
  public void step() {
    checkForTurn();
    moveForward();
    handleWall();
  }
  
  /** Turns if relevant key is pressed on keyboard. */
  private void checkForTurn() {
    if (StdDraw.isKeyPressed(keyCodeLeft))  { dir += turnDelta; }
    if (StdDraw.isKeyPressed(keyCodeRight)) { dir -= turnDelta; }
    // Note: Not an else-if so if both keys are down, they cancel
    // each other out :)
  }
  
  /** Moves forward in current direction. */
  private void moveForward() {
    Point newLoc = getLocation().translateAngleDist(dir, moveDelta);
    setLocation(newLoc);
  }
  
  /** Wraps around to other side of world if hits a wall. */
  private void handleWall() {
    Point loc = getLocation();
    // Wrap around to other side
    if (loc.x() < 0) { setLocation(new Point(1, loc.y())); }
    if (loc.x() > 1) { setLocation(new Point(0, loc.y())); }
    if (loc.y() < 0) { setLocation(new Point(loc.x(), 1)); }
    if (loc.y() > 1) { setLocation(new Point(loc.x(), 0)); }
    // If you'd rather bounce, see Turtle.bounce() for sample code
  }
  
  /**
   * Creates new world with two steerable objects: one uses arrow keys
   * to steer, the other uses the A and D keys.
   */
  public static void main(String... args) {
    StdWorld world = new StdWorld();
    world.add(new Steerable());
    // world.add(new Steerable(Point.random(0,1), 0.01, KeyEvent.VK_A, KeyEvent.VK_D));
    world.runTheWorld();
  }
}
