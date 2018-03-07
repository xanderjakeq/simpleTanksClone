/**
 * Represents a point in 2D space.
 * 
 * @version Added equals() and dirTo()
 */
public class Point {
  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public static Point random(double min, double max) {
    double range = max - min;
    double x = Math.random()*range + min;
    double y = Math.random()*range + min;
    return new Point(x, y);
  }
  
  public double x() { return x; }
  public double y() { return y; }
  
  public String toString() {
    return "(" + x + "," + y + ")";
  }
  
  public double distanceTo(Point p) {
    double deltaX = this.x - p.x;
    double deltaY = this.y - p.y;
    return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
  }
  
  /**
   * Returns angle in radians in standard position from this
   * point to the given point.  If both points are in the same
   * location, throws an illegal argument exception.
   */
  public double dirTo(Point p) {
    if (this.equals(p)) {
      throw new IllegalArgumentException("Unable to find direction to self: " + this);
    }
    double deltaY = p.y - this.y;
    double deltaX = p.x - this.x;
    return Math.atan2(deltaY, deltaX);
  }
  
  public Point midpoint(Point p) {
    return new Point((x+p.x)/2, (y+p.y)/2);
  }
  
  public Point plus(Point p) {
    return new Point(x+p.x, y+p.y);
  }
  
  public Point translateAngleDist(double theta, double r) {
    return new Point(x + Math.cos(theta)*r, y + Math.sin(theta)*r);
  }
  
  /**
   * Returns true if o is a Point with the same x and y
   * coordinates as this object.
   */
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null) { return false; }
    if (!(o instanceof Point)) { return false; }
    Point p = (Point) o;
    return this.x == p.x && this.y == p.y;
  }
  
  public static void main(String[] args) {
    // Create a point at the origin
    Point origin = new Point(0, 0);
    System.out.println("origin is at " + origin);
    Point p = new Point(1, 1);
    System.out.println("distance from " + origin + " to " +  p +
      " = " + origin.distanceTo(p));
    System.out.println("direction: " + origin.dirTo(p));
    Point p2 = new Point(1, 1);
    System.out.println("Equals? " + p.equals(p2));
  }
}
