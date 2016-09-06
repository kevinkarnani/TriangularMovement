package com.henrydangprg.triangularmovement.component;

import com.henrydangprg.triangularmovement.utilities.Coordinate;

import javafx.scene.shape.Line;

/**
 * The wire in this simulation is a line drawn
 * from a motor object to the ghost. 
 * For JavaFX, a line is created and attached from the motor to the ghost.
 */
public class Wire {

    Line line;

    /**
     * Creates a visual wire.
     */
    public Wire() {
	line = new Line();
    }

    /**
     * Registers where the line attaches from.
     * 
     * @param startCoord
     *            the start point of the wire.
     */
    public void attachFrom(Coordinate startCoord) {
	line.setStartX(startCoord.getX());
	line.setStartY(startCoord.getY());
    }

    /**
     * Registers where the line attaches to.
     * 
     * @param endCoord
     *            the end point of the wire.
     */
    public void attachTo(Coordinate endCoord) {
	line.setEndX(endCoord.getX());
	line.setEndY(endCoord.getY());
    }

    /**
     * Sets the start and end points of this wire.
     * 
     * @param startCoord
     *            the start point of the wire.
     * @param endCoord
     *            the end point of the wire.
     */
    public void setLine(Coordinate startCoord, Coordinate endCoord) {
	line.setStartX(startCoord.getX());
	line.setStartY(startCoord.getY());
	line.setEndX(endCoord.getX());
	line.setEndY(endCoord.getY());
    }

    /**
     * Returns the wire as an object used for JavaFX.
     * 
     * @return the wire object.
     */
    public Line getLine() {
	return line;
    }
}