/*
Problem Statement (from [topcoder]TM ARENA)
In aviation, a "near miss" occurs if the distance between two aircraft at some point in time is less than or equal to some threshold distance R. At a moment in time, the positions and velocities of two aircraft are known exactly. Assuming that each aircraft continues to fly with constant speed and direction, you need to work out if the aircraft will undergo a near miss now or at some point in the future.

You will be given the threshold distance R and the positions and velocities of the aircraft in 4 s: p1, v1, p2 and v2. The position of the first aircraft in 3-D cartesian space is given by (p1[0] , p1[1] , p1[2]) and its velocity vector by (v1[0] , v1[1] , v1[2]) and similarly the second aircraft's position and velocity are given by (p2[0] , p2[1] , p2[2]) and (v2[0] , v2[1] , v2[2]). So, if an aircraft's initial position is the vector p and its velocity vector is v, the position of this aircraft at some future time t will be p + v * t. You should return a String containing "YES" if the aircraft will undergo a near miss now or in the future or "NO" if they won't (quotes for clarity).
Definition
Class: Aircraft
Method: nearMiss
Parameters: int[], int[], int[], int[], int
Returns: String
Method signature: String nearMiss(int[] p1, int[] v1, int[] p2, int[] v2, int R)
(be sure your method is public)
Limits
Time limit (s): 840.000
Memory limit (MB): 64
Notes
- The distance between two points (x1, y1, z1) and (x2, y2, z2) is given by sqrt((x1-x2)2 + (y1-y2)2 + (z1-z2)2).
Constraints
- p1, v1, p2 and v2 will contain exactly 3 elements.
- Each element of p1, v1, p2 and v2 will be between -10000 and 10000, inclusive.
- R will be between 0 and 10000, inclusive.
Examples
0)
{15,50,5}
{25,1,0}
{161,102,9}
{-10,-10,-1}
10
Returns: "YES"
At time 4, the first aircraft will be at position {115,54,5} and the second at {121,62,5}, which is the first point in time at which they are exactly 10 distance units apart.
1)
{0,0,0}
{2,2,0}
{9,0,5}
{-2,2,0}
5
Returns: "YES"
At time 2.25, aircraft 1 will be at {4.5, 4.5, 0} and aircraft 2 at {4.5, 4.5, 5}, exactly a distance of 5 units apart.
2)
{0,0,0}
{-2,2,0}
{9,0,5}
{2,2,0}
5
Returns: "NO"
The aircraft are flying away from each other here, so the distance always increases with time.
3)
{-2838,-7940,-2936}
{1,1,-2}
{532,3850,9590}
{1,0,-3}
3410
Returns: "YES"
The closest approach of the aircraft occurs at time 12,158
4)
{-8509,9560,345}
{-89,-33,62}
{-5185,-1417,2846}
{-58,24,26}
8344
Returns: "YES"
A near miss occurs between times 111 and 112
5)
{-7163,-371,-2459}
{-59,-41,-14}
{-2398,-426,-5487}
{-43,27,67}
5410
Returns: "NO"
The aircraft almost have a near miss between times 15 and 16, but stay just outside the threshold distance.
6)
{1774,-4491,7810}
{-12,19,-24}
{2322,3793,9897}
{-12,19,-24}
10000
Returns: "YES"
The aircraft are here flying with identical velocities, so the distance between them never changes. The distance at time 0 is lower than the threshold, so a near miss is already underway.
7)
{3731,8537,5661}
{-70,71,32}
{8701,-1886,-5115}
{28,-13,7}
9766
Returns: "NO"
This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 */

/**
 *
 * @author Elizabeth
 */
public class Aircraft {

    //solution doesn't work. need to brush up on physics before revisiting this problem.

    public String nearMiss(int[] p1, int[] v1, int[] p2, int[] v2, int R) {
        Point point1 = new Point(p1);
        Point point2 = new Point(p2);
        Vector vector1 = new Vector(v1);
        Vector vector2 = new Vector(v2);

        Vector nVector = vector1.unitVector(vector2);
        Point difference = point1.subtract(point2);
        double shortestDist = dotProduct(nVector, difference);
        
        System.out.println(shortestDist);

        if (shortestDist <= R) {
            return "YES";
        }
        return "NO";
    }

    private double dotProduct(Vector v, Point p) {
        return v.x * p.x + v.y * p.y + v.z * p.z;
    }

    private static class Vector {

        private final double x;
        private final double y;
        private final double z;

        public Vector(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Vector(int[] v1) {
            this(v1[0], v1[1], v1[2]);
        }

        private Vector unitVector(Vector v2) {
            Vector cross = this.crossProduct(v2);
            return cross.multiply(1.0 / cross.magnitude());
        }

        private double magnitude() {
            return squared(x) + squared(y) + squared(z);
        }

        private double squared(double num) {
            return Math.pow(num, 2);
        }

        private Vector multiply(double n) {
            return new Vector(x * n, y * n, z * n);
        }

        private Vector crossProduct(Vector v2) {
            double iComponent = determinant(y, z, v2.y, v2.z);
            double jComponent = determinant(x, z, v2.x, v2.z);
            double kComponent = determinant(x, y, v2.x, v2.y);
            return new Vector(iComponent, -jComponent, kComponent);
        }

        private double determinant(double a, double b, double c, double d) {
            return a * d - b * c;
        }
    }

    private static class Point {

        private final double x;
        private final double y;
        private final double z;

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Point(int[] p1) {
            this(p1[0], p1[1], p1[2]);
        }

        private double getDistance(Point p2) {
            double deltaXSquared = getDeltaSquared(x, p2.x);
            double deltaYSquared = getDeltaSquared(y, p2.y);
            double deltaZSquared = getDeltaSquared(z, p2.z);
            double distance = Math.sqrt(deltaXSquared + deltaYSquared + deltaZSquared);
            return distance;
        }

        private double getDeltaSquared(double n1, double n2) {
            return Math.pow(n1 - n2, 2);
        }

        private Point subtract(Point p2) {
            return new Point(x - p2.x, y - p2.y, z - p2.z);
        }

    }
}
