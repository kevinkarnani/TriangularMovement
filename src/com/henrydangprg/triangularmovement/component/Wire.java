package com.henrydangprg.triangularmovement.component;

import javafx.scene.shape.Line;

public class Wire {

	Line line;

	public Wire(Coordinate startCoord) {
		line = new Line();
		line.setEndX(startCoord.getX());
		line.setEndY(startCoord.getY());
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
