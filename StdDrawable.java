/**
 * An object that can be drawn to StdDraw for use in
 * a virtual world.
 * 
 * @author AP CS A (20 Feb 2018)
 */
public interface StdDrawable {
  /** Draws this object to StdDraw. */
  public void draw();
  
  /** Updates object for one frame of animation. */
  public void step();
  
  /** Returns object's (x,y) center. */
  public Point getLocation();
  
  /** Tells object which world it's in. */
  public void setWorld(StdWorld world);
}

