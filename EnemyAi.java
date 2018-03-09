/**
    Follow and shoot a player
 */
public class EnemyAi extends Tank{
    //Fields    
    private Tank target;

    private int shootCounter;

    public EnemyAi(Point loc, double radius, Tank target){
        super(loc,radius);
        this.target = target;
    }

    public EnemyAi(Tank target){
        this(Point.random(0,1), Math.random()*0.03, target);

    }

    //Methods
    public void moveForward(){
        turnTo(getLocation().dirTo(target.getLocation()));
        Point newLoc = getLocation().translateAngleDist(getDir(), getMoveDelta());
        setLocation(newLoc);
  
    }

    public void shoot() {
            Bullet shoot = new Bullet(this.getLocation(),this.getDir());
            this.getWorld().add(shoot);

    }

    public void step(){

        moveForward();
        shoot();
        handleWall();
    }

}