/**
    Follow and shoot a player
 */
public class EnemyAi extends Tank{
    //Fields    
    private Tank target;
    private double dir;
    private double speed = .001;

    private boolean one = true;
    private boolean two = true;
    private boolean three = true;

    private int shootCounter;

    public EnemyAi(Point loc, double radius, Tank target){
        super(loc,radius);
        this.target = target;
    }

    public EnemyAi(Tank target){
        this(Point.random(0,1), Math.random()*0.03, target);

    }

    //Methods
    public double getDir(){
        return dir;
    }

    /**
     * Set dir facing target
     */
    public void turnToTarget(){
        dir = getLocation().dirTo(target.getLocation());
         turnTo(dir);
        // System.out.println(direction);
        System.out.println(getDir());
 
    }    
    public void moveForward(){
       Point newLoc = getLocation().translateAngleDist(getDir(), speed);
        setLocation(newLoc);
  
    }

    public void shoot() {
            Bullet bullet = new Bullet(this.getLocation(),this.getDir());
            this.getWorld().add(bullet);

    }

    public void step(){
        turnToTarget();
        moveForward();

        //add space between shots
        if(!one){
            one = !one;
        }else if(!two){
            two = !two;
        }else{
            three = !three;
        }
        if( one && two && three && target != null){
            one = !one;
            two = !two;
            three = !three;
            shoot();
        }
        handleWall();
    }

}