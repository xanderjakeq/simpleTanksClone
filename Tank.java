import java.awt.event.KeyEvent;

/**
 * Tank is a steerable object.
 * shoots blackholes
 * and don't move when no button is pushed.
 */
public class Tank extends Steerable{
    //Fields
    private int bullets;

    private int keyCodeForward;

    private int keyCodeBackward;

    //Costructors
    public Tank(Point loc, double radius,int bullets,
                   int keyCodeLeft, int keyCodeRight,  int keyCodeForward, int keyCodeBackward){
        super(loc, radius, keyCodeLeft, keyCodeRight);
        this.bullets = bullets;
        this.keyCodeForward = keyCodeForward;
        this.keyCodeBackward = keyCodeBackward;
    }

    public Tank(Point loc, double radius){
        this(loc, radius, 5, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
    }

    public Tank(){
        this(Point.random(0,1), Math.random()*0.03);
    }

    //Methods
    /**
     * Move only when control keys are pressed
     * 
     * just override moveForward()
     */
    public void moveForward(){

        if(StdDraw.isKeyPressed(keyCodeForward)){

            Point newLoc = getLocation().translateAngleDist(getDir(), getMoveDelta());
            setLocation(newLoc);

        }
         if(StdDraw.isKeyPressed(keyCodeBackward)){

            Point newLoc = getLocation().translateAngleDist(getDir(), -getMoveDelta());
            setLocation(newLoc);

        }
            
            
    } 

    public static void main(String[] args){
        StdWorld arena = new StdWorld();
        arena.setClearBetweenSteps(true);
        Tank one = new Tank();
        Bullet boom = new Bullet(one.getLocation(), one.getDir());

        arena.add(one,boom);
        arena.runTheWorld();
    }
}