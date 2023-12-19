import java.util.Comparator;

/**
 * Define a class named CompareY that implements Comparator<Point>. Implement
 * the compare method to compare two points on their y-coordinates. If two
 * points have the same y-coordinates, compare their x-coordinates.
 *
 * 10/4/2023
 *
 * @author Steve Rubin 0370442
 */
public class CompareY implements Comparator<Point> {
   /**
    * Compare two points on their y-coordinates.
    *
    * @param p1 the first point
    * @param p2 the second point
    * @return -1 if p1's y-coordinate is less than p2's y-coordinate, 1 if p1's
    *         y-coordinate is greater than p2's y-coordinate, and 0 if p1's
    *         y-coordinate is equal to p2's y-coordinate
    */
   public int compare(Point p1, Point p2) {
      if (p1.getY() < p2.getY())
         return -1;
      if (p1.getY() > p2.getY())
         return 1;
      return 0; // Y is equal
   }
}