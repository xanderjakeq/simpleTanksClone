/**
    Follow and shoot a player
 */
public class EnemyAi extends Tank{
    //Fields    
    private Tank target;
    private double dir;
    private double speed = .001;
    private double anglediff;

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
        //TODO: figure out why this does not work
         turnTo(dir);
        // System.out.println(direction);
        //System.out.println(getDir());
        anglediff = Math.abs(this.getDir()-dir);
 
    }    
    public void moveForward(){
       Point newLoc = getLocation().translateAngleDist(getDir(), speed);
        setLocation(newLoc);
  
    }

    public void shoot() {
        if(this.getWorld().getObjects().contains(target)){
            Bullet bullet = new Bullet(this.getLocation(),this.getDir());
            this.getWorld().add(bullet);
        }
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
        if( one && two && three && anglediff < .01){
            one = !one;
            two = !two;
            three = !three;
            shoot();
        }
        handleWall();
    }

}