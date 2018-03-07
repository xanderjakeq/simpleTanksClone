import java.awt.Color;

/**
 * Simple Turtle graphics.
 * Screen scale for Turtle world is [0,1] x [0,1] by default.
 * 
 * @author APCS class (Jan 30, 2017)
 * @version Added drawInitials and drawRegularPolygon
 * @version Added StdDrawable interface
 * @version Refactored to extend StdDrawableObject (removed some cruft)
 */
public class Turtle extends StdDrawableObject {
  // Fields
  /** Color of turtle. */
  private Color color;
  
  /** Turtle's name. */
  private String name;
  
  /** Do we draw as we move? */
  private boolean isPenDown = true;
  
  // Static fields
  /**
   * Number of anonymous turtles created so far - keep
   * track so that each gets a unique name.
   */
  private static int numAnonTurtles = 0;
  
  // Constructors
  /** Creates new turtle in given location facing up. */
  public Turtle(String newName, Color newColor, double newX, double newY) {
    super(new Point(newX, newY));
    turnTo(Math.PI / 2); // facing up
    color = newColor;
    name = newName;
  }
  
  /** Creates new turtle in center facing up with given color and name. */
  public Turtle(String newName, Color newColor) {
    this(newName, newColor, 0.5, 0.5); // Calls other constructor
    // NOTE: This special syntax only works as first line of constructor
  }
  
  /** Creates new turtle in center with random color facing up. */
  public Turtle() {
    this("Shelldon #" + numAnonTurtles,
         Color.getHSBColor((float) Math.random(), 1, 1),
         Math.random(), Math.random());
    numAnonTurtles++;
  }
  
  /** Creates new turtle as clone of given one. */
  public Turtle(Turtle orig) {
    this(orig.name + "'", orig.color, orig.getLocation().x(), orig.getLocation().y());
    turnTo(orig.getDir());
    isPenDown = orig.isPenDown;
  }
  
  // Methods

  /** Sets pen up/down for turtle. */
  public void setPenDown(boolean isPenDown) {
    this.isPenDown = isPenDown; // "this": keyword referring to current object
  }
   
  /**
   * Moves given distance in current direction.  If isPenDown() is true,
   * draws line as it moves and sets pen color to turtle's color.
   */
  public void waddle(double dist) {
    Point oldLoc = getLocation();
    Point newLoc = oldLoc.translateAngleDist(getDir(), dist);
    setLocation(newLoc);
    if (isPenDown) {
      StdDraw.setPenColor(color);
      StdDraw.line(oldLoc.x(), oldLoc.y(), newLoc.x(), newLoc.y());
    }
    bounce();
  }
  
  /** Bounce off wall if necessary. */
  private void bounce() {
    double x = x(); // used so many times it seems worth a temp var :P
    double y = y();
    double dir = getDir();
    // Left wall
    if (x <= 0 && (dir > Math.PI/2 || dir < -Math.PI/2)) {
      x = 0;
      double deltaDir = dir - Math.PI/2;
      turnTo(Math.PI/2 - deltaDir);
    }
    // Right wall
    if (x >= 1 && (dir > -Math.PI/2 && dir < Math.PI/2)) {
      x = 1;
      double deltaDir = Math.PI/2 - dir;
      turnTo(Math.PI/2 + deltaDir);
    }
    // Top wall
    if (y >= 1 && dir > 0) {
      y = 1;
      double deltaDir = Math.PI - dir;
      turnTo(Math.PI + deltaDir);
    }
    // Bottom wall
    if (y <= 0 && dir < 0) {
      y = 0;
      double deltaDir = -dir;
      turnTo(deltaDir);
    }
    setLocation(new Point(x, y));
  }
  
  /** Waddles forward 0.01. */
  public void step() {
    waddle(0.01);
  }
  
  /** Draws upside down T pointing in direction we're facing. */
  public void draw() {
    final double T_LEN = 0.03;
    StdDraw.setPenColor(color);
    double x = x(); // used so many times it seems worth a temp var :P
    double y = y();
    double dir = getDir();
    StdDraw.line(x, y, x + Math.cos(dir)*T_LEN, y + Math.sin(dir)*T_LEN);
    StdDraw.line(x+Math.cos(dir+Math.PI/2)*T_LEN/3, y+Math.sin(dir+Math.PI/2)*T_LEN/3,
                x+Math.cos(dir-Math.PI/2)*T_LEN/3, y+Math.sin(dir-Math.PI/2)*T_LEN/3);
  }
  
  public void drawRegularPolygon(int numSides, double sideLen) {
    if (numSides < 3) {
      throw new IllegalArgumentException("numSides must be at least 3: " + numSides);
    }
    setPenDown(true);
    for (int s = 0; s < numSides; s++) {
      waddle(sideLen);
      turn(2*Math.PI/numSides);
    }
  }
      
  // Static methods
  
  /** Draws sample Turtle image. */
  public static void main (String[] args) {
    StdDrawable t = new Turtle();
    for (int i = 0; i < 10; i++) {
      t.step();
      if (t instanceof Turtle) {
        Turtle t2 = (Turtle) t;
        // Note: Can cast to anything and it will compile!
        // But it will crash at runtime if not a legal cast
        t2.turn(Math.PI/20);
      }
      t.draw();
    }
  }
}
