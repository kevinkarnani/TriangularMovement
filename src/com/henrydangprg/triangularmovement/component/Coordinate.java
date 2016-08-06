package com.henrydangprg.triangularmovement.component;

public class Coordinate {

	private double x;
	private double y;

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Return the coordinate's x and y pair as an array.
	 * @return Returns a double[] with the coordinates in the form [x, y]
	 */
	public double[] getXY(){
		double[] coordinates = {this.x, this.y};
		return coordinates;
	}
}
