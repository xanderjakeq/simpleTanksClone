/**
 * A turtle that turns a random amount before each step.
 */
public class RandomTurtle extends Turtle {
  public void step() {
    turn(Math.random() - 0.5);
    super.step();
  }
  
  /** Test with three random turtles. */
  public static void main(String[] args) {
    StdWorld world = new StdWorld();
    world.setClearBetweenSteps(false);
    world.add(new RandomTurtle());
    world.add(new RandomTurtle());
    world.add(new RandomTurtle());
    world.runTheWorld();
  }
}
