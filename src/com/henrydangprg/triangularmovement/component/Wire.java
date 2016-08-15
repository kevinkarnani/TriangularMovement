package com.henrydangprg.triangularmovement.component;

import javafx.scene.shape.Line;

public class Wire {

	Ghost ghost;
	Line line;

	public Wire(Ghost ghost) {
		this.ghost = ghost;
		line = new Line();
		line.setEndX(ghost.getCoordinate().getX());
		line.setEndY(ghost.getCoordinate().getY());
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
