package ual.eda2.DyV;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairDyV {

	// A structure to represent a Point in 2D plane
	private static class Point {
		public int x;
		public int y;
	 
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class PointXComparator implements Comparator<Point> {
		// Needed to sort array of points according to X coordinate
		@Override
		public int compare(Point pointA, Point pointB) {
			return Integer.compare(pointA.x, pointB.x);
		}
	}

	static class PointYComparator implements Comparator<Point> {	 
		// Needed to sort array of points according to Y coordinate
		@Override
		public int compare(Point pointA, Point pointB) {
			return Integer.compare(pointA.y, pointB.y);
		} 
	}
	
	// A utility function to find the distance between two points
	public static double distance(Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
						(p1.y - p2.y) * (p1.y - p2.y));
	  }
	
	// A Brute Force method to return the smallest distance between two points
	// in P[] of size n
	public static double bruteForce(Point[] P, int n) {
		double min = Double.MAX_VALUE;
		double currMin = 0;
	 
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				currMin = distance(P[i], P[j]);
				if (currMin < min) {
					min = currMin;
				}
			}
		}
		return min;
	}
	
	// A utility function to find the distance between the closest points of
	// strip of given size. All points in strip[] are sorted according to y coordinate.
	// They all have an upper bound on minimum distance as delta.
	// Note that this method seems to be a O(n^2) method, but it's a O(n)
	// method as the inner loop runs at most 7 times
	public static double stripClosest(Point[] strip, int size, double delta) {
		double min = delta; // Initialize the minimum distance as d
	 
	    Arrays.sort(strip, 0, size, new PointYComparator());
	 
	    // Pick all points one by one and try the next points till the difference
	    // between y coordinates is smaller than d.
	    // This is a proven fact that this loop runs at most 7 times
	    for (int i = 0; i < size; ++i) {
	    	for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
	    		if (distance(strip[i], strip[j]) < min) {
	    			min = distance(strip[i], strip[j]);
	    		}
	    	}
	    }
	    return min;
	}

	// A recursive function to find the smallest distance. The array P contains
	// all points sorted according to x coordinate
	public static double closestPair(Point[] P, int startIndex, int endIndex) {
		// If there are 2 or 3 points, then use brute force
		if ((endIndex - startIndex) <= 3) {
			return bruteForce(P, endIndex);
		}
	 
		// Find the middle point
		int mid = startIndex + (endIndex - startIndex) / 2;
		Point midPoint = P[mid];
	 
		// Consider the vertical line passing through the middle point calculate
	    // the smallest distance dl on left of middle point and dr on right side
		double dl = closestPair(P, startIndex, mid);
		double dr = closestPair(P, mid, endIndex);
	 
		// Find the smaller of two distances
		double delta = Math.min(dl, dr);
	 
		// Build an array strip[] that contains points close (closer than delta)
		// to the line passing through the middle point
		Point[] strip = new Point[endIndex];
		int j = 0;
		for (int i = 0; i < endIndex; i++) {
			if (Math.abs(P[i].x - midPoint.x) < delta) {
				strip[j] = P[i];
				j++;
			}
		}
	 
		// Find the closest points in strip.
		// Return the minimum of delta and closest distance is strip[]
	    return Math.min(delta, stripClosest(strip, j, delta));
	}

	// The main function that finds the smallest distance
	// This method mainly uses closestUtil()
	public static double closestPair(Point[] P, int n) {
		Arrays.sort(P, 0, n, new PointXComparator());
	 
		// Use recursive function closestUtil() to find the smallest distance
		return closestPair(P, 0, n);
	}
	
	public static void main(String[] args) {
		Point[] P = new Point[]{
			new Point(2, 3),
			new Point(12, 30),
			new Point(40, 50),
			new Point(5, 1),
			new Point(12, 10),
			new Point(3, 4)
			};
	    	 
		System.out.println("The smallest distance is " + closestPair(P, P.length));
	}

}
