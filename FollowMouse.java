/**
 * StdDrawbable that returns the mouse's current location.
 * 
 * @author Aviv (21 Feb 2018)
 */
public class FollowMouse implements StdDrawable {
  private StdWorld world;
  
  /** Does nothing since mouse pointer drawn by operating system. */
  public void draw() { }
  
  private boolean handledClick = false;
  /** Removes nearby objects if mouse clicked. */
  public void step() {
    if (world == null) { return; }
    if (StdDraw.isMousePressed()) {
      if (!handledClick) {
        world.remove(world.getObjectsNear(this, 0.1));
        handledClick = true;
      }
    } else { // Mouse not pressed
      handledClick = false;
    }
  }
  
  /** Returns mouse pointer's location. */
  public Point getLocation() {
    double x = StdDraw.mouseX();
    double y = StdDraw.mouseY();
    return new Point(x, y);
  }
  
  public void setWorld(StdWorld world) {
    this.world = world;
  }
  
}
