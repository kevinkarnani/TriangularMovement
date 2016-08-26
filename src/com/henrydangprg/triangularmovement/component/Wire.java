package com.henrydangprg.triangularmovement.component;

import com.henrydangprg.triangularmovement.utilities.Coordinate;

import javafx.scene.shape.Line;

public class Wire {

	Line line;

	public Wire() {
		line = new Line();
	}

	public Line getLine() {
		return line;
	}
	
	public void attachFrom(Coordinate startCoordinate) {
		line.setStartX(startCoordinate.getX());
		line.setStartY(startCoordinate.getY());
	}
	
	public void attachTo(Coordinate endCoordinate) {
		line.setEndX(endCoordinate.getX());
		line.setEndY(endCoordinate.getY());
	}
	
	public void setLine(Coordinate startCoordinate, Coordinate endCoordinate){
		line.setStartX(startCoordinate.getX());
		line.setStartY(startCoordinate.getY());
		line.setEndX(endCoordinate.getX());
		line.setEndY(endCoordinate.getY());
	}
}