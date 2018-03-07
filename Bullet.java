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
        this(loc, .01, dir, .02 );
    }

    //Methodos
    public void moveForward(){
        Point newLoc = getLocation().translateAngleDist(dir, speed);
        setLocation(newLoc);
    }
    public void step(){
        moveForward();

    }

}