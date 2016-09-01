package com.henrydangprg.triangularmovement.utilities;

public class MathUtil {
	/**
	 * Clips a number so it never goes above its max 
	 * number or below its minimum number.
	 * 
	 * @param val  the number being checked.
	 * @param min  the minimum value the number can be.
	 * @param max  the maximum value the number can be.
	 * @return     itself if it does not exceed its range, else it 
	 * 			   will return its minimum or maximum number.
	 */
	public static <T extends Comparable<T>> T range(T val, T min, T max){
		if (val.compareTo(min) < 0) return min;
		else if(val.compareTo(max) > 0) return max;
		else return val;
	}
	
	/**
	 * Returns the speed of an object.
	 * 
	 * <p>Subtracts the end distance from the start 
	 * distance, and divides by the time elapsed to
	 * get the speed.
	 * 
	 * @param start  the start distance.
	 * @param end    the end distance.
	 * @param time   the elapsed time.
	 * @return       the speed of the object.
	 */
	public static double calcSpeed(double start, double end, double time) {
        return (end - start) / time;
    }
	
	/**
	 * Returns the length of the hypotenuse given 
	 * the length of two legs of a right triangle.
	 * 
	 * @param a  the first leg.
	 * @param b  the second leg.
	 * @return   the length of the hypotenuse.
	 */
	public static double pythagorean(double a, double b) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

	/**
	 * Returns the distance from 2 coordinates.
	 * 
	 * @param coord1  the first coordinate.
	 * @param coord2  the second coordinate.
	 * @return        the distance from the 2 coordinates.
	 */
    public static double distFromPoints(Coordinate coord1, Coordinate coord2) {
        return Math.sqrt(Math.pow(coord2.getX() - coord1.getX(), 2) + 
        				 Math.pow(coord2.getY() - coord1.getY(), 2) +
        				 Math.pow(coord2.getZ() - coord1.getZ(), 2));
    }

    /**
     * Returns the graphed slope of two coordinates.
     * 
     * @param coord1  the first coordinate.
	 * @param coord2  the second coordinate.
	 * @return        the slope of the 2 coordinates.
     */
    public static double findSlope(Coordinate coord1, Coordinate coord2) {
        return (coord2.getY() - coord1.getY()) / (coord2.getX() - coord1.getX());
    }

    /**
     * Returns the graphed y-intercept from a point and a slope.
     * 
     * @param slope  the slope of the line.
     * @param coord  a point on the line.
     * @return       the y-intercept of the line.
     */
    public static double findYIntercept(double slope, Coordinate coord) {
        return -slope * coord.getX() + coord.getY();
    }

    /**
     * Returns a boolean if a point satisfies a line inequality.
     * 
     * <p>Given a slope, a y-intercept, and a boolean, it will create an
     * inequality. It will check if a coordinate satisfies this inequality.
     * 
     * @param slope       the slope of the inequality.
     * @param yintercept  the y-intercept of the inequality.
     * @param coord       the coordinate checked in this inequality.
     * @param isMore      determines whether the point needs to be more or less
     * 					  than the inequality.
     * @return            <tt>true</tt> if the coordinate satisfies the inequality.
     */
    public static boolean checkInequality(double slope, double yintercept, Coordinate coord, boolean isMore) {
        if (isMore) {
            if (coord.getY() >= slope * coord.getX() + yintercept) return true;
        } else {
            if (coord.getY() <= slope * coord.getX() + yintercept) return true;
        }

        return false;
    }
}