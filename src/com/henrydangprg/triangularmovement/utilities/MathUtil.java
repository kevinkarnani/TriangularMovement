package com.henrydangprg.triangularmovement.utilities;

import com.henrydangprg.triangularmovement.component.Coordinate;

public class MathUtil {
	public static double calcSpeed(double start, double end, double time) {
        return (end - start) / time;
    }
	
	public static double pythagorean(double a, double b) {
		return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}

    public static double distanceBetweenPoints(Coordinate coord1, Coordinate coord2) {
        return Math.sqrt(Math.pow(coord2.getX() - coord1.getX(), 2) + Math.pow(coord2.getY() - coord1.getY(), 2));
    }

    public static double findSlope(Coordinate coord1, Coordinate coord2) {
        return (coord2.getY() - coord1.getY()) / (coord2.getX() - coord1.getX());
    }

    public static double findYIntercept(double slope, Coordinate coord) {
        return -slope * coord.getX() + coord.getY();
    }

    public static double[] findVectorComponents(double magnitude, double angle) {
        double[] vectorComponents = { magnitude * Math.cos(angle),
                                      magnitude * Math.sin(angle) };
        return vectorComponents;
    }

    public static boolean checkInequality(double slope, double yintercept, Coordinate coord, boolean isMore) {
        if (isMore) {
            if (coord.getY() >= slope * coord.getX() + yintercept) return true;
        } else {
            if (coord.getY() <= slope * coord.getX() + yintercept) return true;
        }

        return false;
    }
}
