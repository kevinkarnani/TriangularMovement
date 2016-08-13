package com.henrydangprg.triangularmovement.component;

import javafx.scene.shape.Line;

public class Wire {

	Ghost ghost;
	Line line;

	public Wire(Ghost ghost) {
		this.ghost = ghost;
		line = new Line();
	}

	public Line getLine() {
		return line;
	}
	
	public void setLine(double startX, double startY, Coordinate coordinate){
		line.setStartX(startX);
		line.setStartY(startY);
		line.setEndX(coordinate.getX());
		line.setEndY(coordinate.getY());
	}
}
