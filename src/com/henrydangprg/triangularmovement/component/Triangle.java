package com.henrydangprg.triangularmovement.component;

import com.henrydangprg.triangularmovement.utilities.MathUtil;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Triangle {

	public static final double LEFT_VERTEX_X = 0.0;
	public static final double LEFT_VERTEX_Y = 400.0;
	public static final double LEFT_VERTEX_Z = 0.0;
	public static final double TOP_VERTEX_X = 250.0;
	public static final double TOP_VERTEX_Y = 0.0;
	public static final double TOP_VERTEX_Z = 0.0;
	public static final double RIGHT_VERTEX_X = 500.0;
	public static final double RIGHT_VERTEX_Y = 400.0;
	public static final double RIGHT_VERTEX_Z = 0.0;
	public static final double DEPTH_LIMIT = -10;
	private double leftToTopSlope, topToRightSlope, bottomYIntercept;
	private double topToRightYIntercept;
	
	private Polygon triangle;
	
	public Triangle(){
		triangle = new Polygon();
		triangle.getPoints().addAll(
				new Double[] { LEFT_VERTEX_X, LEFT_VERTEX_Y, TOP_VERTEX_X,
						TOP_VERTEX_Y, RIGHT_VERTEX_X, RIGHT_VERTEX_Y });
		triangle.setFill(Color.WHITE);
		
		leftToTopSlope = MathUtil.findSlope(this.getLeftVertex(), this.getTopVertex());
		topToRightSlope = -leftToTopSlope;
		
		topToRightYIntercept = MathUtil.findYIntercept(topToRightSlope, getTopVertex());
		bottomYIntercept = LEFT_VERTEX_Y;
	}
	
	public boolean isInBounds(Coordinate coord) {
		if (MathUtil.checkInequality(leftToTopSlope, bottomYIntercept, coord, true) &&
            MathUtil.checkInequality(topToRightSlope, topToRightYIntercept, coord, true) &&
            MathUtil.checkInequality(0, bottomYIntercept, coord, false) &&
            coord.getZ() >= DEPTH_LIMIT && coord.getZ() <= 0) {
			return true;
		}
		
		return false;
	}
	
	public Coordinate getTopVertex() {
		Coordinate coord = new Coordinate(TOP_VERTEX_X, TOP_VERTEX_Y, TOP_VERTEX_Z);
		return coord;
	}
	
	public Coordinate getLeftVertex() {
		Coordinate coord = new Coordinate(LEFT_VERTEX_X, LEFT_VERTEX_Y, LEFT_VERTEX_Z);
		return coord;
	}
	
	public Coordinate getRightVertex() {
		Coordinate coord = new Coordinate(RIGHT_VERTEX_X, RIGHT_VERTEX_Y, RIGHT_VERTEX_Z);
		return coord;
	}
	
	public void sendToBack(){
		triangle.toBack();
	}

	public Polygon getTriangle() {
		return triangle;
	}
}

