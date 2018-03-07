import java.awt.Color;

/**
 * A bouncy ball, bouncing around the window.
 * Based on BouncyBalls, but now objectified.
 * 
 * @author A. Jacoby (20 Feb 2018)
 */
public class BouncyBall implements StdDrawable {
  // Fields
  /** x coordinate of center. */
  private double x;
  /** y coordinate of center. */
  private double y;
  /** x component of velocity. */
  private double xVel;
  /** y component of velocity. */
  private double yVel;
  /** Radius. */
  private double r;
  /** Color. */
  private Color color;
  /** World. */
  private StdWorld world;
    
  // Force of gravity
  public static final double GRAVITY = -0.0001;
  
  // Constructors
  /** Constructs a random new BouncyBall. */
  public BouncyBall() {
    x = Math.random(); // x between 0 and 1
    y = Math.random(); // y between 0 and 1
    r = 0.01 + 0.06*Math.random(); // radius between 0.01 and 0.07
    // velocities between -0.01 and 0.01
    xVel = -0.01 + 0.02*Math.random();
    yVel = -0.01 + 0.02*Math.random();  
    color = Color.getHSBColor((float) Math.random(), 1, 1);  
  }
  
  // Methods
  
  public void setWorld(StdWorld world) {
    this.world = world;
  }
  
  public void draw() {
    StdDraw.setPenColor(color);
    StdDraw.circle(x, y, r);
  }
  
  /**
   * Moves ball based on x and y velocities. Bounces off
   * "walls" assuming world exists between (0,0) and (1,1).
   */
  public void step() {
    x += xVel;
    y += yVel;
    // bounce off left or right wall
    if (((x - r) <= 0 && xVel < 0) ||
        ((x + r) >= 1 && xVel > 0)) {
      xVel = -xVel;
    }
    // bounce off bottom or top wall
    if (((y - r) <= 0 && yVel < 0) ||
        ((y + r) >= 1 && yVel > 0)) {
      yVel = -yVel;
    } else {
      // Add some gravity
      yVel += GRAVITY;        
    }

  }
  
  public Point getLocation() {
    return new Point(x, y);
  }
  
  // Static methods
  public static void main(String[] args) {
    int numBalls = (args.length > 0)? Integer.parseInt(args[0]) : 10;
    BouncyBall[] ballInfo = initialize(numBalls);
    StdDraw.enableDoubleBuffering();
    while (true) {
      drawBalls(ballInfo);
      updateBalls(ballInfo);
      StdDraw.pause(2);
    }
  }
  
  /**
   * Initializes all balls to random positions, sizes, and velocities.
   */
  public static BouncyBall[] initialize(int numBalls) {
    BouncyBall[] ballInfo = new BouncyBall[numBalls];
    for (int i = 0; i < ballInfo.length; i++) {
      ballInfo[i] = new BouncyBall();
    }
    return ballInfo;
  }
  
  /** Draws each ball in its current position. */
  public static void drawBalls(BouncyBall[] ballInfo) {
    StdDraw.clear();
    for (int i = 0; i < ballInfo.length; i++) {
      ballInfo[i].draw();
    }
    StdDraw.show();
  }
  
  /** Updates position and velocity of each ball. */
  public static void updateBalls(BouncyBall[] ballInfo) {
    for (int i = 0; i < ballInfo.length; i++) {
      ballInfo[i].step();
    }
  }
  
}

