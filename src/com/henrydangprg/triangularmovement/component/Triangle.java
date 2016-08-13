package com.henrydangprg.triangularmovement.component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Triangle {

	private final double LEFT_VERTEX_X = 0.0;
	private final double LEFT_VERTEX_Y = 400.0;
	private final double TOP_VERTEX_X = 250.0;
	private final double TOP_VERTEX_Y = 0.0;
	private final double RIGHT_VERTEX_X = 500.0;
	private final double RIGHT_VERTEX_Y = 400.0;
	private Polygon triangle = new Polygon();;

	public Triangle() {
		triangle.getPoints().addAll(
				new Double[] { LEFT_VERTEX_X, LEFT_VERTEX_Y, TOP_VERTEX_X,
						TOP_VERTEX_Y, RIGHT_VERTEX_X, RIGHT_VERTEX_Y });
		triangle.setFill(Color.WHITE);
	}

	public void sendToBack() {
		triangle.toBack();
	}

	public Polygon getTriangle() {
		return triangle;
	}

	/**
	 * @return the lEFT_VERTEX_X
	 */
	public double getLEFT_VERTEX_X() {
		return LEFT_VERTEX_X;
	}

	/**
	 * @return the lEFT_VERTEX_Y
	 */
	public double getLEFT_VERTEX_Y() {
		return LEFT_VERTEX_Y;
	}

	/**
	 * @return the tOP_VERTEX_X
	 */
	public double getTOP_VERTEX_X() {
		return TOP_VERTEX_X;
	}

	/**
	 * @return the tOP_VERTEX_Y
	 */
	public double getTOP_VERTEX_Y() {
		return TOP_VERTEX_Y;
	}

	/**
	 * @return the rIGHT_VERTEX_X
	 */
	public double getRIGHT_VERTEX_X() {
		return RIGHT_VERTEX_X;
	}

	/**
	 * @return the rIGHT_VERTEX_Y
	 */
	public double getRIGHT_VERTEX_Y() {
		return RIGHT_VERTEX_Y;
	}
}