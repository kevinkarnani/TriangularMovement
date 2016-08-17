package com.henrydangprg.triangularmovement.component;

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
	}
}
