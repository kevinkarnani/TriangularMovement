package com.henrydangprg.triangularmovement.component;

import com.henrydangprg.triangularmovement.utilities.Coordinate;
import com.henrydangprg.triangularmovement.utilities.MathUtil;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Creates a triangle to be shown in JavaFX.
 * 
 * <p>
 * In this simulation, the triangle represents the area that
 * the ghost is allowed to move in. The three motors in the
 * vertices of the triangle move a ghost inside the triangle.
 * 
 * <p>
 * Note: The triangle creates an isosceles triangle.
 */
public class Triangle {

    private Coordinate leftVertex;
    private Coordinate topVertex;
    private Coordinate rightVertex;
    private double floor, roof;
    private double leftToTopSlope, topToRightSlope, leftToRightSlope;
    private double leftToTopYIntercept, topToRightYIntercept, leftToRightYIntercept;

    private Polygon triangle;

    /**
     * Creates an isosceles triangle given the length, width, and depth.
     * 
     * <p>
     * Calculates the borders of the triangle and creates inequalities for the
     * triangle.
     * 
     * <p>
     * Creates a visual representation of the triangle for JavaFX.
     * 
     * @param length
     *            the length of the triangle.
     * @param width
     *            the width of the triangle.
     * @param depth
     *            the depth of the triangle.
     */
    public Triangle(double length, double width, double depth) {
	leftVertex = new Coordinate(0, width, 0);
	topVertex = new Coordinate(length / 2, 0, 0);
	rightVertex = new Coordinate(length, width, 0);
	floor = -depth;
	roof = 0;

	this.setInequalities();

	triangle = new Polygon();
	triangle.getPoints().addAll(new Double[] { leftVertex.getX(), leftVertex.getY(), topVertex.getX(),
		topVertex.getY(), rightVertex.getX(), rightVertex.getY() });
	triangle.setFill(Color.WHITE);
    }

    /**
     * Calculates the slope and y-intercept from left to top, top to right, and
     * left to right.
     */
    public void setInequalities() {
	leftToTopSlope = MathUtil.findSlope(this.getLeftVertex(), this.getTopVertex());
	leftToTopYIntercept = MathUtil.findYIntercept(leftToTopSlope, this.getLeftVertex());

	topToRightSlope = MathUtil.findSlope(this.getTopVertex(), this.getRightVertex());
	topToRightYIntercept = MathUtil.findYIntercept(topToRightSlope, this.getTopVertex());

	leftToRightSlope = MathUtil.findSlope(this.getLeftVertex(), this.getRightVertex());
	leftToRightYIntercept = MathUtil.findYIntercept(leftToRightSlope, this.getLeftVertex());
    }

    /**
     * Checks if a coordinate is within a triangle's dimensions through 5
     * inequalities. 3 inequalities are used for the sides of the triangle, and
     * 2 inequalities are used to check the height of the triangle.
     * 
     * @param coord
     *            the coordinate of an object.
     * @return <tt>true</tt> if the object is within the triangle.
     */
    public boolean isInBounds(Coordinate coord) {
	if (MathUtil.checkInequality(leftToTopSlope, leftToTopYIntercept, coord, true)
		&& MathUtil.checkInequality(topToRightSlope, topToRightYIntercept, coord, true)
		&& MathUtil.checkInequality(leftToRightSlope, leftToRightYIntercept, coord, false)
		&& coord.getZ() >= floor && coord.getZ() <= roof) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * Returns the top vertex as a Coordinate.
     * 
     * @return the top vertex {@link Coordinate}.
     */
    public Coordinate getTopVertex() {
	return topVertex;
    }

    /**
     * Returns the left vertex as a Coordinate.
     * 
     * @return the left vertex {@link Coordinate}.
     */
    public Coordinate getLeftVertex() {
	return leftVertex;
    }

    /**
     * Returns the right vertex as a Coordinate.
     * 
     * @return the right vertex {@link Coordinate}.
     */
    public Coordinate getRightVertex() {
	return rightVertex;
    }

    /**
     * Returns the depth of the triangle.
     * 
     * @return the depth as a double.
     */
    public double getDepth() {
	return -floor;
    }

    /**
     * Returns the triangle as a polygon for JavaFX.
     * 
     * @return the triangle Polygon.
     */
    public Polygon getTriangle() {
	return triangle;
    }
}