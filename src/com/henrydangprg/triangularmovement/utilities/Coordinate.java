package com.henrydangprg.triangularmovement.utilities;

public class Coordinate {

	private double x;
	private double y;
	private double z;
	
	/**
	 * An empty constructor for Coordinate
	 */
	public Coordinate() {}

	/**
	 * Creates a coordinate in the form x, y, z
	 * 
	 * @param x  the x coordinate.
	 * @param y  the y coordinate.
	 * @param z  the z coordinate.
	 */
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Creates a coordinate from an existing coordinate.
	 * 
	 * @param coord  the existing coordinate.
	 */
	public Coordinate(Coordinate coord) {
		this.x = coord.getX();
		this.y = coord.getY();
		this.z = coord.getZ();
	}
	
	/**
	 * Sets a coordinate from an existing coordinate.
	 * 
	 * @param coord  the existing coordinate.
	 */
	public void setCoordinate(Coordinate coord) {
		this.x = coord.getX();
		this.y = coord.getY();
		this.z = coord.getZ();
	}
	
	/**
	 * Sets the x coordinate.
	 * 
	 * @param x  the x coordinate.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets the y coordinate.
	 * 
	 * @param y  the y coordinate.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Sets the z coordinate.
	 * 
	 * @param z  the z coordinate.
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Returns the x coordinate.
	 * 
	 * @param x  the x coordinate.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the y coordinate.
	 * 
	 * @param y  the y coordinate.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Returns the z coordinate.
	 * 
	 * @param z  the z coordinate.
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Returns the coordinate's x, y, and z as an array.
	 * @return  returns a double[] with the coordinates in the form [x, y, z]
	 */
	public double[] getXYZ(){
		double[] coordinate = {this.x, this.y, this.z};
		return coordinate;
	}
}