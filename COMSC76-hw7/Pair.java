import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Pair contains 2 points, p1 and p2 and various methods (static and not) for calculating the between points. It
 * also contains a static method for finding the closest pair of points in an array of points.
 * 
 * 10/24/2023
 * @author Steve Rubin 0370442
 */
public class Pair {
   Point p1;
   Point p2;

   public Pair(Point p1, Point p2) { // constructor
      this.p1 = p1;
      this.p2 = p2;
   }

   /**
    * Generate an array of n random points
    * 
    * @param n number of points
    * @return an array of random points
    */
public static double[][] randomPoints(int n) {
    return IntStream.range(0, n)
        .mapToObj(i -> new double[] { Math.random() * 100, Math.random() * 100 })
        .toArray(double[][]::new);
}


   /**
    * Print the points in the array
    * 
    * @param points
    */
   public static void printPoints(double[][] points) {
      Arrays.stream(points)
      .map(point -> String.format("(%s, %s)\n", point[0], point[1]))
      .forEach(System.out::print);
   }

   public static double distance(Point p1, Point p2) {
      return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
   }

   public static double distance(double x1, double y1, double x2, double y2) {
      return Math.sqrt(
            (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
   }

   /**
    * Return the distance between the two points in the instance variables p1 and
    * p2
    * 
    * @return
    */
   public double getDistance() {
      return distance(p1, p2);
   }

   /**
    * Return the distance of the closest pair of points
    * 
    * @param points 2d array of points
    * @return the distance of the closest pair of points
    */
   public static Pair getClosestPair(double[][] points) {
      return getClosestPair(Arrays.stream(points).map(point -> new Point(point[0], point[1])).toArray(Point[]::new));
   }

   /**
    * Return the distance of the closest pair of points
    * 
    * @param points array of Points
    * @return
    */
   public static Pair getClosestPair(Point[] points) {
      /*
       * Sort the points in increasing order of x-coordinates. For the points with the
       * same x-coordinates, sort on y-coordinates. The result should be a sorted
       * collection of points denoted by S.
       */
      Point[] pointsOrderedOnX = points.clone();
      Arrays.sort(pointsOrderedOnX);
      Point[] pointsOrderedOnY = pointsOrderedOnX.clone();
      Arrays.sort(pointsOrderedOnY, new CompareY());

      return distance(pointsOrderedOnX, 0, pointsOrderedOnX.length - 1, pointsOrderedOnY);
   }

   /**
    * Return the distance of the closest pair of points in pointsOrderedOnX[low,
    * high]. This is a recursive method. pointsOrderedOnX and pointsOrderedOnY are
    * not changed in the subsequent recursive calls.
    */

   public static Pair distance(Point[] pointsOrderedOnX, int low, int high, Point[] pointsOrderedOnY) {
      // If there are zero or one points, return null or the pair.
      if (low >= high)
         return null;
      else if (low + 1 == high)
         return new Pair(pointsOrderedOnX[low], pointsOrderedOnX[high]);

      // Calculate the midpoint of the array and recursively divide the problem.
      int middle = (low + high) / 2;
      Pair leftSide = distance(pointsOrderedOnX, low, middle, pointsOrderedOnY);
      Pair rightSide = distance(pointsOrderedOnX, 1 + middle, high, pointsOrderedOnY);

      Pair p = Stream.of(
            // If both p1 and p2 are not null, choose the pair with the minimum distance.
            // the nested ternary operator is used to choose the pair with the minimum
            // distance, but its not pretty
            (leftSide != null && rightSide != null) ? (distance(leftSide.p1, leftSide.p2) < distance(rightSide.p1, rightSide.p2) ? leftSide : rightSide) : null,
            new Pair(pointsOrderedOnX[middle], pointsOrderedOnX[middle + 1])) // Pair representing the two closest points at
                                                                        // the merge point.
            .filter(Objects::nonNull) // Filter out any null pairs.
            .min(Comparator.comparingDouble(pair -> distance(pair.p1, pair.p2))) // Find the minimum distance pair.
            .orElse(null);

      // Divide the set of points into two parts: stripL and stripR based on the
      // midpoint and minimum distance pair.
      List<Point> stripL = Stream.of(pointsOrderedOnY)
            .filter(point -> point.getX() <= pointsOrderedOnX[middle].getX()
                  && point.getX() >= pointsOrderedOnX[middle].getX() - distance(p.p1, p.p2))
            .collect(Collectors.toList());

      List<Point> stripR = Stream.of(pointsOrderedOnY)
            .filter(point -> point.getX() > pointsOrderedOnX[middle].getX()
                  && point.getX() <= pointsOrderedOnX[middle].getX() + distance(p.p1, p.p2))
            .collect(Collectors.toList());

      // Combine points from stripL and stripR to find the minimum distance pair in
      // the strip.
      Pair minPair = stripL.stream()
            .flatMap(l -> stripR.stream()
                  .filter(
                        r -> l.getY() <= r.getY() + distance(p.p1, p.p2) && r.getY() <= l.getY() + distance(p.p1, p.p2))
                  .map(r -> new Pair(l, r)))
            .min(Comparator.comparingDouble(pair -> distance(pair.p1, pair.p2)))
            .orElse(null);

      // Compare the minimum distance pair in the strip with the overall minimum
      // distance pair.
      if (p == null)
         return minPair;
      if (minPair != null && distance(minPair.p1, minPair.p2) < distance(p.p1, p.p2)) {
         return minPair;
      }

      // Return the overall minimum distance pair.
      return p;
   }
}