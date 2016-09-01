package com.henrydangprg.triangularmovement.utilities;

import com.henrydangprg.triangularmovement.utilities.MathUtil;

public class Vector {

	private double deltaX, deltaY, deltaZ;
	private double magnitude, angle;
	
	/**
	 * Creates an empty vector.
	 */
	public Vector() {
		this.deltaX = 0;
		this.deltaY = 0;
		this.deltaZ = 0;
		this.magnitude = 0;
		this.angle = 0;
	}
	
	/**
	 * Creates a vector with its x, y, and z components.
	 * 
	 * @param deltaX  the x component.
	 * @param deltaY  the y component.
	 * @param deltaZ  the z component.
	 */
	public Vector(double deltaX, double deltaY, double deltaZ) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaZ = deltaZ;
		this.magnitude = MathUtil.pythagorean(deltaX, deltaY);
		this.angle = Math.atan2(deltaY, deltaX);
	}
	
	/**
	 * Creates a vector from magnitude and angle in degrees.
	 * 
	 * @param magnitude  the magnitude of the vector.
	 * @param angle      the angle of the vector in degrees.
	 */
	public Vector(double magnitude, double angle) {
		this.magnitude = magnitude;
		this.angle = angle;
		this.deltaX = magnitude * Math.cos(Math.toRadians(angle));
		this.deltaY = magnitude * Math.sin(Math.toRadians(angle));
		this.deltaZ = 0;
	}
	
	/**
	 * Calculates the x, y, and z components from the
	 * magnitude and angle
	 */
	public void findVectorComponents() {
		this.deltaX = magnitude * Math.cos(Math.toRadians(angle));
		this.deltaY = magnitude * Math.sin(Math.toRadians(angle));
		this.deltaZ = 0;
	}
	
	/**
	 * Calculates the x, y, and z components from a
	 * given magnitude and angle in degrees.
	 * 
	 * @param magnitude  the magnitude of the vector.
	 * @param angle      the angle of the vector in degrees.
	 */
	public void findVectorComponents(double magnitude, double angle) {
		this.magnitude = magnitude;
		this.angle = angle;
		this.deltaX = magnitude * Math.cos(Math.toRadians(angle));
		this.deltaY = magnitude * Math.sin(Math.toRadians(angle));
		this.deltaZ = 0;
	}
	
	/**
	 * Calculates the vector from 2 points.
	 * 
	 * @param coord1  the first coordinate.
	 * @param coord2  the second coordinate.
	 */
	public void calcVector(Coordinate coord1, Coordinate coord2) {
		deltaX = coord2.getX() - coord1.getX();
		deltaY = coord2.getY() - coord1.getY();
		deltaZ = coord2.getZ() - coord1.getZ();
	}
	
	/**
	 * Returns a coordinate added by a vector.
	 * 
	 * @param coord   the coordinate being added by a vector.
	 * @param vector  the vector being added.
	 * @return        a coordinate added by a vector.
	 */
	public Coordinate addVector(Coordinate coord, Vector vector) {
		return coord = new Coordinate(coord.getX() + vector.getDeltaX(),
				coord.getY() + vector.getDeltaY(),
				coord.getZ() + vector.getDeltaZ());
	}
	
	/**
	 * Returns a coordinate subtracted by a vector.
	 * 
	 * @param coord   the coordinate being subtracted by a vector.
	 * @param vector  the vector being subtracted.
	 * @return        a coordinate subtracted by a vector.
	 */
	public Coordinate subtractVector(Coordinate coord, Vector vector) {
		return coord = new Coordinate(coord.getX() - vector.getDeltaX(),
				coord.getY() - vector.getDeltaY(),
				coord.getZ() - vector.getDeltaZ());
	}
	
	/**
	 * Multiplies this vector by a value.
	 * 
	 * @param amplify  the value amplifying this vector.
	 */
	public void multiplyVector(double amplify) {
		this.setDeltaX(this.getDeltaX() * amplify);
		this.setDeltaY(this.getDeltaY() * amplify);
		this.setDeltaZ(this.getDeltaZ() * amplify);
	}
	
	/**
	 * Registers the x component of this vector.
	 * 
	 * @param deltaX  the change in X of a vector.
	 */
	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}
	
	/**
	 * Registers the y component of this vector.
	 * 
	 * @param deltaY  the change in Y of a vector.
	 */
	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
	/**
	 * Registers the z component of this vector.
	 * 
	 * @param deltaZ  the change in Z of a vector.
	 */
	public void setDeltaZ(double deltaZ) {
		this.deltaZ = deltaZ;
	}
	
	/**
	 * Registers the x, y, and z components of this vector.
	 * 
	 * @param deltaX  the x component of a vector.
	 * @param deltaY  the y component of a vector.
	 * @param deltaZ  the z component of a vector.
	 */
	public void setDeltaXYZ(double deltaX, double deltaY, double deltaZ) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaZ = deltaZ;
	}
	
	/**
	 * Returns the x component of this vector.
	 * 
	 * @return  a double of the x component.
	 */
	public double getDeltaX() {
		return deltaX;
	}
	
	/**
	 * Returns the y component of this vector.
	 * 
	 * @return  a double of the y component.
	 */
	public double getDeltaY() {
		return deltaY;
	}
	
	/**
	 * Returns the z component of this vector.
	 * 
	 * @return  a double of the z component.
	 */
	public double getDeltaZ() {
		return deltaZ;
	}
	
	/**
	 * Returns the x, y, z components as an array.
	 * @return  returns a double[] of the vector as [x, y, z]
	 */
	public double[] getDeltaXYZ() {
		double[] delta = { deltaX, deltaY, deltaZ };
		return delta;
	}
	
	/**
	 * Returns the magnitude of this vector.
	 * 
	 * @return  a double of the magnitude.
	 */
	public double getMagnitude() {
		return magnitude;
    }
	
	/**
	 * Returns the angle of this vector.
	 * 
	 * @return  the angle of this vector in degrees.
	 */
	public double getAngle() {
		return angle;
	}
	
	/**
	 * Resets the vector by setting the x, y, z components,
	 * magnitude, and angle to 0.
	 */
	public void resetVector() {
		this.deltaX = 0;
		this.deltaY = 0;
		this.deltaZ = 0;
		this.magnitude = 0;
		this.angle = 0;
	}
}