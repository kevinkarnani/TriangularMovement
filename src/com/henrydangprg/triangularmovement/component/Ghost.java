package com.henrydangprg.triangularmovement.component;

import com.henrydangprg.triangularmovement.utilities.Coordinate;
import com.henrydangprg.triangularmovement.utilities.MathUtil;
import com.henrydangprg.triangularmovement.utilities.Ratio;
import com.henrydangprg.triangularmovement.utilities.Trilateration;
import com.henrydangprg.triangularmovement.utilities.Vector;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The ghost is an object in the Christmas play is attached to 
 * three motors, which are positioned as an isosceles triangle.
 * 
 * <p>
 * The ghost is moved by moving the three motors at the same time.
 * It does so by finding the desired coordinate to move to and calculates
 * the speed for each motor to spin for. It calculates its actual coordinate
 * with {@link Trilateration} to find its actual coordinate, and the cycle repeats.
 */
public class Ghost {

    private Ratio motorRatio;

    private Motor leftMotor;
    private Motor topMotor;
    private Motor rightMotor;

    private Circle ghostRepresentation;

    private Coordinate ghostCoord;
    private Coordinate nextGhostCoord;

    private Trilateration trilateration;

    private double depthLimit;

    /**
     * Creates a ghost object for the simulation. Defines the 3 motors the ghost
     * is attached to. Instantiates objects used to calculate the ghost
     * position. Creates a visual representation of the ghost.
     * 
     * @param leftMotor
     *            the left motor which the ghost is hanging from.
     * @param topMotor
     *            the top motor which the ghost is hanging from.
     * @param rightMotor
     *            the right motor which the ghost is hanging from.
     */
    public Ghost(Motor leftMotor, Motor topMotor, Motor rightMotor) {
	this.leftMotor = leftMotor;
	this.topMotor = topMotor;
	this.rightMotor = rightMotor;

	motorRatio = new Ratio();
	ghostCoord = new Coordinate();
	nextGhostCoord = new Coordinate();
	trilateration = new Trilateration(leftMotor.getCoordinate(), rightMotor.getCoordinate(),
		topMotor.getCoordinate());

	ghostRepresentation = new Circle();
	ghostRepresentation.setRadius(depthLimit);
	ghostRepresentation.setFill(Color.CHOCOLATE);
    }

    /**
     * Sets the depth limit of the ghost representation in the simulation.
     * 
     * @param depth
     *            the depth limit of the ghost.
     */
    public void setDepthLimit(double depth) {
	this.depthLimit = depth;
	ghostRepresentation.setRadius(depthLimit);
    }

    /**
     * Returns the ghost as a circle object.
     * 
     * @return the circle object representing the ghost.
     */
    public Circle getGhost() {
	return this.ghostRepresentation;
    }

    /**
     * Manually sets the ghost position by the x, y, and z coordinates.
     * 
     * @param x
     *            the x coordinate of the ghost.
     * @param y
     *            the y coordinate of the ghost.
     * @param z
     *            the z coordinate of the ghost.
     */
    public void setPosition(double x, double y, double z) {
	ghostCoord.setX(x);
	ghostCoord.setY(y);
	ghostCoord.setZ(z);
	this.updatePosition();
    }

    /**
     * Calculates and stores the next proposed coordinate of the ghost from a
     * vector.
     * 
     * @param vector
     *            the proposed vector which the ghost moves in.
     */
    public void calcNextCoordinate(Vector vector) {
	this.nextGhostCoord = vector.addVector(this.getCoordinate(), vector);
    }

    /**
     * Returns the next calculated coordinate of the ghost.
     * 
     * @return the next {@link Coordinate} of the ghost.
     */
    public Coordinate getNextCoordinate() {
	return nextGhostCoord;
    }

    /**
     * Calculates the motor speeds for the 3 motors which controls the ghost.
     * 
     * <p>
     * Calculates the speed from the distance from a coordinate to one motor and
     * the distance of the next coordinate to the same motor. Process is
     * repeated for each motor.
     * 
     * <p>
     * Amplifies the ratio of the motor speeds to 1, which is the motor max
     * speed. Sets the motor speeds to the motor.
     * 
     * <p>
     * Note: in the Christmas play: the motor ratio will be defined by the
     * distance of the joystick from the origin of the joystick.
     * 
     * @param vector
     *            the desired direction of the ghost.
     */
    public void calcMotorSpeeds(Vector vector) {
	this.calcNextCoordinate(vector);

	double topMotorSpeed = MathUtil.calcSpeed(this.getDistanceFromTop(),
		MathUtil.distFromPoints(topMotor.getCoordinate(), this.getNextCoordinate()), 1);
	double leftMotorSpeed = MathUtil.calcSpeed(this.getDistanceFromLeft(),
		MathUtil.distFromPoints(leftMotor.getCoordinate(), this.getNextCoordinate()), 1);
	double rightMotorSpeed = MathUtil.calcSpeed(this.getDistanceFromRight(),
		MathUtil.distFromPoints(rightMotor.getCoordinate(), this.getNextCoordinate()), 1);
	motorRatio.add(topMotorSpeed, leftMotorSpeed, rightMotorSpeed);
	motorRatio.amplifyRatio(1);
	
	double newTopMotorSpeed = motorRatio.getRatio()[0];
	double newLeftMotorSpeed = motorRatio.getRatio()[1];
	double newRightMotorSpeed = motorRatio.getRatio()[2];

	topMotor.set(newTopMotorSpeed);
	leftMotor.set(newLeftMotorSpeed);
	rightMotor.set(newRightMotorSpeed);
    }

    /**
     * Finds the coordinates of the ghost based off the encoder values.
     */
    public void calibrate() {
	this.ghostCoord = trilateration.calcPosition(leftMotor.getEncoderValue(), rightMotor.getEncoderValue(), 
		topMotor.getEncoderValue());

	this.updatePosition();
    }

    /**
     * Resets the ghost {@link Coordinate} to the right motor.
     */
    public void resetToRight() {
	ghostCoord.setX(rightMotor.getCoordinate().getX());
	ghostCoord.setY(rightMotor.getCoordinate().getY());
	ghostCoord.setZ(rightMotor.getCoordinate().getZ());
	this.updatePosition();
    }

    /**
     * Updates the ghost representation position in the simulation.
     */
    public void updatePosition() {
	ghostRepresentation.setCenterX(ghostCoord.getX());
	ghostRepresentation.setCenterY(ghostCoord.getY());
	ghostRepresentation.setRadius(depthLimit + ghostCoord.getZ());
    }

    /**
     * Returns the distance from the the coordinate of the ghost to the
     * coordinate of the top motor.
     * 
     * @return a double of the distance from the ghost to the top motor.
     */
    public double getDistanceFromTop() {
	return MathUtil.distFromPoints(topMotor.getCoordinate(), this.getCoordinate());
    }

    /**
     * Returns the distance from the the coordinate of the ghost to the
     * coordinate of the left motor.
     * 
     * @return a double of the distance from the ghost to the left motor.
     */
    public double getDistanceFromLeft() {
	return MathUtil.distFromPoints(leftMotor.getCoordinate(), this.getCoordinate());
    }

    /**
     * Returns the distance from the the coordinate of the ghost to the
     * coordinate of the right motor.
     * 
     * @return a double of the distance from the ghost to the right motor.
     */
    public double getDistanceFromRight() {
	return MathUtil.distFromPoints(rightMotor.getCoordinate(), this.getCoordinate());
    }

    /**
     * Returns the coordinate of the ghost.
     * 
     * @return the {@link Coordinate} of the ghost.
     */
    public Coordinate getCoordinate() {
	return this.ghostCoord;
    }

    /**
     * Returns the Motor object of the top motor.
     * 
     * @return the top {@link Motor} object.
     */
    public Motor getTopMotor() {
	return this.topMotor;
    }

    /**
     * Returns the Motor object of the left motor.
     * 
     * @return the left {@link Motor} object.
     */
    public Motor getLeftMotor() {
	return this.leftMotor;
    }

    /**
     * Returns the Motor object of the right motor.
     * 
     * @return the right {@link Motor} object.
     */
    public Motor getRightMotor() {
	return this.rightMotor;
    }
}