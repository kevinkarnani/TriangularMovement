package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Triangle {

	public static final double LEFT_VERTEX_X = 0.0;
	public static final double LEFT_VERTEX_Y = 400.0;
	public static final double TOP_VERTEX_X = 250.0;
	public static final double TOP_VERTEX_Y = 0.0;
	public static final double RIGHT_VERTEX_X = 500.0;
	public static final double RIGHT_VERTEX_Y = 400.0;
	private Polygon triangle;
	
	public Triangle(){
		triangle = new Polygon();
		triangle.getPoints().addAll(
				new Double[] { LEFT_VERTEX_X, LEFT_VERTEX_Y, TOP_VERTEX_X,
						TOP_VERTEX_Y, RIGHT_VERTEX_X, RIGHT_VERTEX_Y });
		triangle.setFill(Color.WHITE);
	}
	
	public Coordinate getTopVertex() {
		Coordinate coord = new Coordinate(TOP_VERTEX_X, TOP_VERTEX_Y);
		return coord;
	}
	
	public Coordinate getLeftVertex() {
		Coordinate coord = new Coordinate(LEFT_VERTEX_X, LEFT_VERTEX_Y);
		return coord;
	}
	
	public Coordinate getRightVertex() {
		Coordinate coord = new Coordinate(RIGHT_VERTEX_X, RIGHT_VERTEX_Y);
		return coord;
	}
	
	public void sendToBack(){
		triangle.toBack();
	}

	public Polygon getTriangle() {
		return triangle;
	}
}

