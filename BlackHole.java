import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a black hole that absorbs nearby objects.
 *
 * @author AP CS A (March 2, 2018)
 */
public class BlackHole extends StdDrawableObject {
  public static final String IMAGE_FILE = "blackhole.png";
  
  // Fields
  private double radius;
  
  /**
   * If we can't load the image file, set this to true so we don't
   * keep trying each time!
   */
  private boolean imageFailed = false;
    
  // Constructors
  public BlackHole(Point loc, double radius) {
    super(loc);
    this.radius = radius;
  }
  
  // Methods
  /**
   * Get bh radius
   */
  public double getRadius(){
    return radius;
  }
  /**
   * Draws image of blackhole if possible, but falls back to
   * simpler black circle if unable to load image.
   */
  public void draw() {
    if (imageFailed) {
      drawSimple();
    } else {
      try {
        Point loc = getLocation();
        StdDraw.picture(loc.x(), loc.y(), IMAGE_FILE,
                        radius*2, radius*2,
                        Math.toDegrees(getDir()));
      } catch (IllegalArgumentException e) {
        // Unable to load image file :(
        imageFailed = true;
        System.err.println("Unable to draw black hole image!");
        e.printStackTrace(); // Detailed error information
        drawSimple();
      }
    }
  }

  /** Fallback drawing method if we can't load image. */  
  private void drawSimple() {
    Point loc = getLocation();
    StdDraw.setPenColor(Color.BLACK);
    StdDraw.filledCircle(loc.x(), loc.y(), radius);    
  }
  
  /**
   * Rotates, then absorbs nearby objects, removing them from the world.
   * 
   * Pre-condition: getWorld() is not null
   */
  public void step() {
    turn(Math.toRadians(1)); // degrees to rotate per frame
    Point loc = getLocation();
    List<StdDrawable> nearbyObjects =
        getWorld().getObjectsNear(loc, radius);
    for (StdDrawable o : nearbyObjects) {
      if (o != this) { // Don't remove self!
        getWorld().remove(o);
        System.out.println(this + " absorbed " + o);
      }
    }
  }
    
}
