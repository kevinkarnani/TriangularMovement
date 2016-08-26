package com.henrydangprg.triangularmovement.utilities;

import com.henrydangprg.triangularmovement.utilities.MathUtil;

public class Vector {

	private double deltaX, deltaY, deltaZ;
	private double magnitude, angle;
	
	public Vector() {
		this.deltaX = 0;
		this.deltaY = 0;
		this.deltaZ = 0;
		this.magnitude = 0;
		this.angle = 0;
	}
	
	public Vector(double deltaX, double deltaY, double deltaZ) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaZ = deltaZ;
		this.magnitude = MathUtil.pythagorean(deltaX, deltaY);
		this.angle = Math.atan2(deltaY, deltaX);
	}
	
	public Vector(double magnitude, double angle) {
		this.magnitude = magnitude;
		this.angle = angle;
		this.deltaX = magnitude * Math.cos(angle);
		this.deltaY = magnitude * Math.sin(angle);
		this.deltaZ = 0;
	}
	
	public void findVectorComponents() {
		this.deltaX = magnitude * Math.cos(angle);
		this.deltaY = magnitude * Math.sin(angle);
		this.deltaZ = 0;
	}
	
	public void findVectorComponents(double magnitude, double angle) {
		this.deltaX = magnitude * Math.cos(Math.toRadians(angle));
		this.deltaY = magnitude * Math.sin(Math.toRadians(angle));
		this.deltaZ = 0;
	}
	
	public void calcVector(Coordinate coord1, Coordinate coord2) {
		deltaX = coord2.getX() - coord1.getX();
		deltaY = coord2.getY() - coord1.getY();
		deltaZ = coord2.getZ() - coord1.getZ();
	}
	
	public Coordinate addVector(Coordinate coord, Vector vector) {
		return coord = new Coordinate(coord.getX() + vector.getDeltaX(),
				coord.getY() + vector.getDeltaY(),
				coord.getZ() + vector.getDeltaZ());
	}
	
	public Coordinate subtractVector(Coordinate coord, Vector vector) {
		return coord = new Coordinate(coord.getX() - vector.getDeltaX(),
				coord.getY() - vector.getDeltaY(),
				coord.getZ() - vector.getDeltaZ());
	}
	
	public void multiplyVector(double amplify) {
		this.setDeltaX(this.getDeltaX() * amplify);
		this.setDeltaY(this.getDeltaY() * amplify);
		this.setDeltaZ(this.getDeltaZ() * amplify);
	}
	
	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}
	
	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}
	
	public void setDeltaZ(double deltaZ) {
		this.deltaZ = deltaZ;
	}
	
	public void setDeltaXYZ(double deltaX, double deltaY, double deltaZ) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.deltaZ = deltaZ;
	}
	
	public double getDeltaX() {
		return deltaX;
	}
	
	public double getDeltaY() {
		return deltaY;
	}
	
	public double getDeltaZ() {
		return deltaZ;
	}
	
	public double[] getDeltaXYZ() {
		double[] delta = { deltaX, deltaY, deltaZ };
		return delta;
	}
	
	public double getMagnitude() {
		return magnitude;
    }
	
	public double getAngle() {
		return angle;
	}
	
	public void resetVector() {
		this.deltaX = 0;
		this.deltaY = 0;
		this.deltaZ = 0;
		this.magnitude = 0;
		this.angle = 0;
	}
}