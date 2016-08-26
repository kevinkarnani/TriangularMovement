package com.henrydangprg.triangularmovement.utilities;

public class Trilateration {
	
	Coordinate origin = new Coordinate(0, 0, 0);
	Coordinate pos1, pos2, pos3;
	Coordinate endCoord = new Coordinate();
	Vector translatedVector = new Vector();
	
	double x, y, z;
	double distance1, distance2, distance3;
	
	public Trilateration(Coordinate position1, Coordinate position2, Coordinate position3) {
		pos1 = position1;
		pos2 = position2;
		pos3 = position3;
		this.translation();
	}
	
	public void translation() {
		if (!pos1.equals(origin)) {
			translatedVector.calcVector(pos1, origin);
			pos1 = new Coordinate(translatedVector.addVector(pos1, translatedVector));
			pos2 = new Coordinate(translatedVector.addVector(pos2, translatedVector));
			pos3 = new Coordinate(translatedVector.addVector(pos3, translatedVector));
		}
	}
	
	public Coordinate calcCoordIntersection(double distance1, double distance2, double distance3) {
		
		x = (Math.pow(distance1, 2) - Math.pow(distance2, 2) + Math.pow(pos2.getX(), 2))/(2*pos2.getX());
		endCoord.setX((double)Math.round(x * 1000d) / 1000d);
		
		y = ((Math.pow(distance1, 2) - Math.pow(distance3, 2) + Math.pow(pos3.getX(), 2) + Math.pow(pos3.getY(), 2))/(2*pos3.getY())) - ((pos3.getX()/pos3.getY()) * x);
		endCoord.setY((double)Math.round(y * 1000d) / 1000d);
		
		z = Math.sqrt(Math.pow(distance1, 2) - Math.pow(x, 2) - Math.pow(y, 2));
		endCoord.setZ(-(double)Math.round(z * 1000d) / 1000d);
		
		endCoord.setCoordinate(translatedVector.subtractVector(endCoord, translatedVector));
		
		return endCoord;
	}
}