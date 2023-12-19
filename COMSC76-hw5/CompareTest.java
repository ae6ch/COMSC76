import java.util.Arrays;

/**
 * Randomly create 100 points and apply the Arrays.sort method to display the
 * points in increasing order of their x-coordinates, and increasing order of
 * their y-coordinates, respectively.
 * 
 * 10/4/2023
 * 
 * @author Steve Rubin 0370442
 */
public class CompareTest {
   public static void main(String[] args) {
      final int NUM_POINTS = 100; // create random points
      Point[] points = new Point[NUM_POINTS];
      for (int i = 0; i < points.length; i++) {
         points[i] = new Point((float) Math.random() * 100, (float) Math.random() * 100);
      }

      System.out.println("Points sorted on x-coordinates");
      Arrays.sort(points);
      for (Point p : points) {
         System.out.println(p);
      }

      System.out.println("Points sorted on y-coordinates");
      Arrays.sort(points, new CompareY());
      for (Point p : points) {
         System.out.println(p);
      }
   }
}