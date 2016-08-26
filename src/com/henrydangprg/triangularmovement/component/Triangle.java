package com.henrydangprg.triangularmovement.component;

import com.henrydangprg.triangularmovement.utilities.Coordinate;
import com.henrydangprg.triangularmovement.utilities.MathUtil;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Triangle {

	private Coordinate leftVertex;
	private Coordinate topVertex;
	private Coordinate rightVertex;
	private double depth;
	private double leftToTopSlope, topToRightSlope, bottomYIntercept;
	private double leftToTopYIntercept, topToRightYIntercept;
	
	private Polygon triangle;
	
	public Triangle(double length, double width, double depth){
		leftVertex = new Coordinate(0, width, 0);
		topVertex = new Coordinate(length/2, 0, 0);
		rightVertex = new Coordinate(length, width, 0);
		this.depth = -depth;
		
		triangle = new Polygon();
		
		this.setInequalities();
		
		triangle.getPoints().addAll(
				new Double[] { leftVertex.getX(), leftVertex.getY(), topVertex.getX(),
						topVertex.getY(), rightVertex.getX(), rightVertex.getY() });
		triangle.setFill(Color.WHITE);
	}
	
	public void shiftTriangle(double shiftX, double shiftY) {
		leftVertex = new Coordinate(leftVertex.getX() + shiftX, leftVertex.getY() + shiftY, 0);
		rightVertex = new Coordinate(rightVertex.getX() + shiftX, rightVertex.getY() + shiftY, 0);
		topVertex = new Coordinate(topVertex.getX() + shiftX, topVertex.getY() + shiftY, 0);

		triangle.relocate(shiftX, shiftY);
		this.setInequalities();
	}
	
	public void setInequalities() {
		leftToTopSlope = MathUtil.findSlope(this.getLeftVertex(), this.getTopVertex());
		leftToTopYIntercept = MathUtil.findYIntercept(leftToTopSlope, this.getLeftVertex());
		topToRightSlope = -leftToTopSlope;
	
		topToRightYIntercept = MathUtil.findYIntercept(topToRightSlope, this.getTopVertex());
		bottomYIntercept = leftVertex.getY();
	}
	
	public boolean isInBounds(Coordinate coord) {
		if (MathUtil.checkInequality(leftToTopSlope, leftToTopYIntercept, coord, true) &&
            MathUtil.checkInequality(topToRightSlope, topToRightYIntercept, coord, true) &&
            MathUtil.checkInequality(0, bottomYIntercept, coord, false) &&
            coord.getZ() >= depth && coord.getZ() <= 0) {
			return true;
		}
		
		return false;
	}
	
	public Coordinate getTopVertex() {
		return topVertex;
	}
	
	public Coordinate getLeftVertex() {
		return leftVertex;
	}
	
	public Coordinate getRightVertex() {
		return rightVertex;
	}
	
	public double getDepth() {
		return -depth;
	}
	
	public void sendToBack(){
		triangle.toBack();
	}

	public Polygon getTriangle() {
		return triangle;
	}
}