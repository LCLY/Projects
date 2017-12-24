/*************************************************************************
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point>{
    // compare points by slope
    public final Comparator<Point> BY_SLOPE_ORDER = new SlopeOrder();    // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private class SlopeOrder implements Comparator<Point>{
        public int compare(Point a, Point b){
            double slope_A = slopeTo(a);
            double slope_B = slopeTo(b);

            if(slope_A > slope_B){

                return 1;

            }else if(slope_B > slope_A){

                return -1;

            }else{

                return 0;

            }
        }
    }
    public double slopeTo(Point that){
        double changeOfY = that.y - this.y;
        double changeOfX = that.x - this.x;

        if(changeOfX == 0 && changeOfY == 0){

            return Double.NEGATIVE_INFINITY;

        }else if (changeOfX == 0){

            return Double.POSITIVE_INFINITY;

        }else if (changeOfY == 0){

            return 0.0;

        }

        return changeOfY/changeOfX;

    }


    // are the 3 points p, q, and r collinear?
    public static boolean areCollinear(Point p, Point q, Point r) {
        /* YOUR CODE HERE */
        /*if((p.y - q.y)*(p.x - q.x) == (p.y - r.y)*(p.x - r.x)){

            return true;

        }else {

            return false;

        }*/

        return p.slopeTo(q) == p.slopeTo(r);
    }

    // are the 4 points p, q, r, and s collinear?
    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        /* YOUR CODE HERE */
      /*  double deltaY1 = p.y-q.y;
        double deltaX1 = p.x-q.x;
        double slope1 = deltaY1/deltaX1;
        double deltaY2 = p.y-r.y;
        double deltaX2 = p.x-r.x;
        double slope2 = deltaY2/deltaX2;
        double deltaY3 = p.y-s.y;
        double deltaX3 = p.x-s.x;
        double slope3 = deltaY3/deltaX3;

        if(slope1 == slope2 && slope1 == slope3 && slope2 == slope3){

            return true;

        }else {

            return false;

        }*/
        return p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s);

    }


    public void draw(){
        StdDraw.point(x,y);
    }

    public void drawTo(Point that){
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    // is this point lexicographically smaller than that one?

    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        int deltaX = this.x - that.x;
        if(deltaX == 0){
            int deltaY = this.y - that.y;
            deltaX = deltaY;
        }

        return deltaX;
    }

}
