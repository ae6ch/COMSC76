/**
 * This is the demo code for the getClosestPair method in the Pair class.
 * 
 * 10/24/2023
 * 
 * @author Steve Rubin 0370442
 */
public class ClosestPair {
   public static void main(String[] args) {

      // some fixed points for testing
      /*
       * double[][] points = { { 61.29956987796068, 46.22889070417251 }, {
       * 61.30991147912446, 46.13746239547352 }, { -1, -1 }, { 2, 0.5 }, { 2, -1 },
       * { 3, 3 }, { 4, 2 }, { 9, 87 } };
       */
      double[][] points = Pair.randomPoints(100);
      Pair.printPoints(points); // uncomment to print the points
      long startTime = System.currentTimeMillis(); // Start the clock after setup is done
      Pair pair = Pair.getClosestPair(points);
      long endTime = System.currentTimeMillis(); // Stop the clock after the algorithm is done
      
      if (pair == null) {
         System.out.println("getClosestPair returned null");
         System.exit(1);
      }
      // String.valueOf() is used to convert a double to a String regardless of the precision/number of decimal places
      System.out.printf("The shortest distance is %s between the points %s and %s\n",
            String.valueOf(pair.getDistance()),
            pair.p1, pair.p2);

      System.out.printf("Time spent on the divide-and-conquer algorithm is %d milliseconds\n",
            endTime - startTime);

            System.out.println("static method for distance (both overloaded methods) test between (0,0) and (1,0), should be 1.0: " + Pair.distance(new Point(0, 0), new Point(1, 0)));
   }
}