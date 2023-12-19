/**
 * Define a class named Point with two data fields x and y to represent a
 * point's x- and y-coordinates. Implement the Comparable interface for the
 * comparing the points on x-coordinates. If two points have the same
 * x-coordinates, compare their y-coordinates.
 * 
 * 10/4/2023
 * 
 * @author Steve Rubin 0370442
 */
public class Point implements Comparable<Point> {
   private final float x;
   private final float y;

   public Point(float x, float y) {
      this.x = x;
      this.y = y;
   }

   /**
    * Return the x-coordinate of the point.
    * 
    * @return the x-coordinate of the point
    */

   public float getX() {
      return x;
   }

   /**
    * Return the y-coordinate of the point.
    * 
    * @return the y-coordinate of the point
    */
   public float getY() {
      return y;
   }

   /**
    * Compare two points on their x-coordinates. If two points have the same
    * x-coordinates, compare their y-coordinates.
    * 
    * @param p the point to be compared
    * @return -1 if this point's x-coordinate is less than p's x-coordinate, 1 if
    *         this point's x-coordinate is greater than p's x-coordinate, and 0 if
    *         this point's x-coordinate is equal to p's x-coordinate
    * 
    */
   public int compareTo(Point p) {
      if (x < p.x)
         return -1;
      if (x > p.x)
         return 1;
      if (y < p.y)
         return -1;
      if (y > p.y)
         return 1;
      return 0; // points are equal
   }

   /**
    * Return a string representation of the point.
    *
    * @return a string representation of the point
    */
   public String toString() {
      return "(" + x + ", " + y + ")";
   }
}