package com.henrydangprg.triangularmovement.component;

public class Coordinate {

	private double x;
	private double y;
	private double z;
	
	public Coordinate() {}

	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	/**
	 * Return the coordinate's x, y, and z as an array.
	 * @return Returns a double[] with the coordinates in the form [x, y, z]
	 */
	public double[] getXYZ(){
		double[] coordinates = {this.x, this.y, this.z};
		return coordinates;
	}
}
