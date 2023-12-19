(Closest pair of points) Section 22.8 introduced the following algorithm for finding the closest pair of points using a divide-and-conquer approach:

Step 1: Sort the points in increasing order of x-coordinates. For the points with the same x-coordinates, sort on y-coordinates. The result should be a sorted collection of points denoted by S.

Step 2: Divide S into two subsets, S1 and S2, of equal size about the midpoint of the sorted list S. Include the midpoint in S1. Recursively find the closest pair in S1 and S2. Let d1 and d2 denote the distance of closest pairs in the two subsets, respectively. 

Step 3: Find the closest pair between a point in S1 and a point in S2 and denote the distance between them as d3. The closest pair is the one with distance equal to the minimum of {d1, d2, d3}.

The Algorithm for Obtaining stripL and stripR is:

for each point p in pointsOrderedOnY

  if (p is in S1 and mid.x - p.x  <= d)

     append p to stripL;

  else if (p is in S2 and p.x - mid.x <= d)

     append p to stripR;

 

Algorithm for Finding the Closest Pair in Step 3

d = min(d1, d2);

r = 0;  // r is the index of a point in stripR

for (each point p in stripL) {

    // Skip the points in stripR below p.y - d

    while (r < stripR.length && q[r].y <= p.y - d) 

        r++;

    let r1 = r;

    while (r1 < stripR.length && |q[r1].y  - p.y| <=  d) {

        // Check if (p, q[r1] is a possible closest pair   

        if (distance(p, q[r1]) < d) {

             d = distance(p, q[r1]);

              (p, q[r1]) is now the current closest pair;

       }

   r1 = r1 + 1;

 }

}

 

Implement the algorithm to meet the following requirements:

Define the classes Point and CompareY in the same way as your program from Chapter 20 for sorting points on both the x and y coordinates.
Define  a class named Pair with data fields p1 and p2 to represent two points and a method named getDistance() that returns the distance between the two points.
Implement the following methods:
/** Return the distance of the closest pair of points */
/* Return the method call to method 2*/ 
1. public static Pair getClosestPair(double [ ] [ ]  points)

 

/** Return the distance of the closest pair of points */
/* Returns the method call to method 3 */ 

2. public static Pair getClosestPair(Point [ ]  points)

 

/** Return the distance of the closest pair of points in pointsOrderedOnX[low, high]. This is a recursive method. pointsOrderedOnX and pointsOrderedOnY are not changed in the subsequent recursive calls.*/
/ * This method implements the algorithm detailed above */
3. public static Pair distance(Point [ ] pointsOrderedOnX, int low, int high, Point [ ] pointsOrderedOnY)

 

/** Compute the distance between two points p1 and p2 */

4. public static double distance(Point p1, Point p2)

 

/** Compute the distance between points (x1, y1) and (x2, y2) */

5. public static double distance(double x1, double y1, double x2, double y2)

The following is an example of the minimum expected output from your program: 

The shortest distance is 0.09201132727109172 between the points 
(61.29956987796068, 46.22889070417251) and (61.30991147912446, 46.13746239547352)

Time spent on the divide-and-conquer algorithm is 148 milliseconds

If you want, you can also list the original 100 random points that were randomly generated at the beginning of the program before printing this summary data if you want. I also ran and timed the brute-force algorithm from Chapter 8 of the course textbook and got an execution time of 17 milliseconds. A question for you to consider: why did the divide-and-conquer approach require more time than the less complicated brute-force method (which is O(n^2) in this case?
