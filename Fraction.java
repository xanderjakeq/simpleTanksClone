import java.util.ArrayList;
import java.util.List;

/**
 * Represents a fraction (rational number) in lowest terms.
 * The numerator can be any integer, and the denominator
 * can be any non-negative integer.
 * 
 * @author AP CS A (1 Mar 2018)
 */
public class Fraction extends Number implements Comparable<Fraction> {
  private final int num;
  private final int denom;
  
  /** Creates a new Fraction with given numerator and denominator. */
  public Fraction(int num, int denom) {
    if (denom == 0) {
      throw new IllegalArgumentException("Denominator cannot be 0!");
    }
    if (denom < 0) { // Flip both signs
      num = -num;
      denom = -denom;
    }
    int gcd = gcd(num, denom);
    this.num = num/gcd;
    this.denom = denom/gcd;
  }
  
  /** Creates a random Fraction. */
  public Fraction() {
    this((int)(Math.random()*20-10), // Random num between -10 and 19
         (int)(Math.random()*10+1)); // Random denom between 1 and 10
  }
  
  /** Returns gcd of a and b. */
  public static int gcd(int a, int b) {
    // Note: Very inefficient implementation!
    int gcd = 1;
    a = Math.abs(a);
    b = Math.abs(b);
    for (int i = 2; i <= a && i <= b; i++) {
      if (a % i == 0 && b % i == 0) { gcd = i; }
    }
    return gcd;
  }
  
  public String toString() {
    if (denom == 1) { return "" + num; }
    if (num == 0)   { return "0"; }
    return num + "/" + denom;
  }
  
  public double doubleValue() {
    return ((double) num) / denom;
  }
  
  public float floatValue() {
    return ((float) num) / denom;
  }
  
  /** Truncates int division. */
  public long longValue() {
    return num / denom;
  }
  
  /** Truncates int division. */
  public int intValue() {
    return num / denom;
  }
  
  public int compareTo(Fraction f) {
    double thisD = this.doubleValue();
    double thatD = f.doubleValue();
    if (thisD > thatD)  { return 1; }
    if (thisD == thatD) { return 0; }
    return -1;
  }
  
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null) { return false; }
    if (!(o instanceof Number)) { return false; }
    Number n = (Number) o;
    return this.doubleValue() == n.doubleValue();
  }
  
  public static void main(String[] args) {
    Fraction f1 = new Fraction(1, 2);
    Fraction f2 = new Fraction(-2, -4);
    Fraction f3 = new Fraction(10, -15);
    System.out.println("Made " + f1 + " and " + f2 + " and " + f3);
    System.out.println(f1.compareTo(f2));
    List<Fraction> nums = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      nums.add(new Fraction());
    }
    System.out.println(nums);
    java.util.Collections.sort(nums);
    System.out.println(nums);
  }
}
