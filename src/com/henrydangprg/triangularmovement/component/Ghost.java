package com.henrydangprg.triangularmovement.component;

import com.henrydangprg.triangularmovement.utilities.Coordinate;
import com.henrydangprg.triangularmovement.utilities.MathUtil;
import com.henrydangprg.triangularmovement.utilities.Ratio;
import com.henrydangprg.triangularmovement.utilities.Trilateration;
import com.henrydangprg.triangularmovement.utilities.Vector;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ghost {
	
	private Ratio motorRatio;

	private Motor leftMotor;
	private Motor topMotor;
	private Motor rightMotor;
	
	private Circle ghostRepresentation;
	
	private Coordinate ghostCoord;
	
	private Trilateration trilateration;
	
	private double depthLimit;
	
	public Ghost(Motor leftMotor, Motor topMotor, Motor rightMotor){
		this.leftMotor = leftMotor;
		this.topMotor = topMotor;
		this.rightMotor = rightMotor;
		motorRatio = new Ratio();
		ghostCoord = new Coordinate();
		ghostRepresentation = new Circle(); 
		ghostRepresentation.setRadius(depthLimit);
		ghostRepresentation.setFill(Color.CHOCOLATE);
		trilateration = new Trilateration(leftMotor.getCoordinate(), 
										  rightMotor.getCoordinate(), 
										  topMotor.getCoordinate());
	}
	
	public void setDepthLimit(double depth) {
		depthLimit = depth;
		ghostRepresentation.setRadius(depthLimit);
	}
	
	public Circle getGhost() {
		return ghostRepresentation;
	}

	public void setPosition(double x, double y, double z) {
		ghostCoord.setX(x);
		ghostCoord.setY(y);
		ghostCoord.setZ(z);
		this.updatePosition();
	}
	
	public void setPosition(Coordinate coord) {
		ghostCoord.setX(coord.getX());
		ghostCoord.setY(coord.getY());
		ghostCoord.setZ(coord.getZ());
		this.updatePosition();
	}
	
	public void moveGhost(Vector vector) {
		this.setPosition(vector.addVector(this.getCoordinate(), vector));
		this.updatePosition();
	}
	
	public Coordinate getNextCoordinate(Vector vector) {
		return vector.addVector(this.getCoordinate(), vector);
	}
	
	public double getDistanceFromTop() {
		return MathUtil.distFromPoints(topMotor.getCoordinate(), this.getCoordinate());
	}
	
	public double getDistanceFromLeft() {
		return MathUtil.distFromPoints(leftMotor.getCoordinate(), this.getCoordinate());
	}

	public double getDistanceFromRight() {
		return MathUtil.distFromPoints(rightMotor.getCoordinate(), this.getCoordinate());
	}
	
	public void calcMotorSpeeds(Vector vector) {
		double topMotorSpeed =  MathUtil.calcSpeed(this.getDistanceFromTop(),
				  				  				   MathUtil.distFromPoints(topMotor.getCoordinate(), this.getNextCoordinate(vector)),
				  				  				   1);
		double leftMotorSpeed = MathUtil.calcSpeed(this.getDistanceFromLeft(),
				  								   MathUtil.distFromPoints(leftMotor.getCoordinate(), this.getNextCoordinate(vector)),
				  								   1);
		double rightMotorSpeed = MathUtil.calcSpeed(this.getDistanceFromRight(),
				  				 					MathUtil.distFromPoints(rightMotor.getCoordinate(), this.getNextCoordinate(vector)),
				  				 					1);
		
		if (topMotorSpeed != 0 && leftMotorSpeed != 0 && rightMotorSpeed != 0) {
			motorRatio.add(topMotorSpeed, leftMotorSpeed, rightMotorSpeed);
			motorRatio.calcRatio();
			
			motorRatio.amplifyRatio(1);
			
			topMotor.set(motorRatio.getRatio()[0]);
			leftMotor.set(motorRatio.getRatio()[1]);
			rightMotor.set(motorRatio.getRatio()[2]);
		} else {
			topMotor.set(0);
			leftMotor.set(0);
			rightMotor.set(0);
		}
	}
	
	public Coordinate getPosition() {
		ghostCoord = trilateration.calcCoordIntersection(leftMotor.getEncoderValue(), 
												   		 rightMotor.getEncoderValue(), 
												 		 topMotor.getEncoderValue());
		return ghostCoord;
	}
	
	public void resetToRight() {
		ghostCoord.setX(rightMotor.getCoordinate().getX());
		ghostCoord.setY(rightMotor.getCoordinate().getY());
		ghostCoord.setZ(rightMotor.getCoordinate().getZ());
		this.updatePosition();
	}
	
	public void updatePosition() {
		ghostRepresentation.setCenterX(ghostCoord.getX());
		ghostRepresentation.setCenterY(ghostCoord.getY());
		ghostRepresentation.setRadius(depthLimit + ghostCoord.getZ());
	}

	public Coordinate getCoordinate(){
		return ghostCoord;
	}
	
	public Motor getTopMotor() {
		return topMotor;
	}
	
	public Motor getLeftMotor() {
		return topMotor;
	}
	
	public Motor getRightMotor() {
		return topMotor;
	}
}