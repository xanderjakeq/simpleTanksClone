import java.util.List;
/**
 * be shot by the Tank
 */
public class Bullet extends BlackHole{
    //Fields
    private double dir;
    private double speed;

    //Constructor
    public Bullet(Point loc, double radius, double dir, double speed){
        super(loc, radius);
        this.dir = dir;
        this.speed = speed;
    }

    public Bullet(Point loc, double dir){
        this(loc, .009, dir, .02 );
    }

    //Methodos
    public void moveForward(){
        Point newLoc = getLocation().translateAngleDist(dir, speed);
        setLocation(newLoc);
    }
    public void step(){
        moveForward();
        turn(Math.toRadians(1)); // degrees to rotate per frame
            Point loc = getLocation();
            List<StdDrawable> nearbyObjects =
                getWorld().getObjectsNear(loc, this.getRadius());
            for (StdDrawable o : nearbyObjects) {
            if (o != this) { // Don't remove self!
                getWorld().remove(o);
                System.out.println(this + " absorbed " + o);
            }
            }

    }

}