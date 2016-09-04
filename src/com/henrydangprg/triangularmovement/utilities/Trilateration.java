package com.henrydangprg.triangularmovement.utilities;

public class Trilateration {

    Coordinate origin = new Coordinate(0, 0, 0);
    Coordinate pos1, pos2, pos3;
    Coordinate endCoord = new Coordinate();
    Vector translatedVector = new Vector();

    double x, y, z;
    double distance1, distance2, distance3;
    boolean calcBelow = true;

    /**
     * Sets the origin of 3 spheres to be used for trilateration.
     * 
     * <p>
     * 3 Coordinates are set to be the center of 3 spheres. They <b>MUST</b> be
     * in the order of left vertex, right vertex, and top vertex. The z axis
     * must also be the same throughout all 3 vertices. Otherwise, trilateration
     * will not work.
     * 
     * <p>
     * The coordinates will be translated if the left vertex is not at the
     * origin.
     * 
     * @param leftVertex
     *            The coordinate of the left vertex.
     * @param rightVertex
     *            The coordinate of the right vertex.
     * @param topVertex
     *            The coordinate of the top vertex.
     */
    public Trilateration(Coordinate leftVertex, Coordinate rightVertex, Coordinate topVertex) {
	this.pos1 = leftVertex;
	this.pos2 = rightVertex;
	this.pos3 = topVertex;
	this.translate();
    }

    /**
     * Translates the triangle to start at the origin.
     * 
     * <p>
     * Creates a vector to move the trilaterated point to its position in the
     * untranslated triangle.
     */
    public void translate() {
	if (!pos1.equals(origin)) {
	    translatedVector.calcVector(pos1, origin);
	    pos1 = new Coordinate(translatedVector.addVector(pos1, translatedVector));
	    pos2 = new Coordinate(translatedVector.addVector(pos2, translatedVector));
	    pos3 = new Coordinate(translatedVector.addVector(pos3, translatedVector));
	}
    }

    /**
     * Controls whether trilateration will return the intersection above or
     * below the Z axis.
     * 
     * @param calcBelow
     */
    public void calcBelow(boolean calcBelow) {
	if (calcBelow)
	    this.calcBelow = true;
	else
	    this.calcBelow = false;
    }

    /**
     * Calculates the point of intersection of 3 spheres.
     * 
     * <p>
     * The distances from a vertex to a point is treated as a radius of a
     * sphere. This will calculate the intersection of the 3 spheres.
     * 
     * <p>
     * Returns the intersection above the Z axis if calcBelow is true, else it
     * will return the intersection below the Z axis.
     * 
     * @param distance1
     *            distance from left vertex to point.
     * @param distance2
     *            distance from right vertex to point.
     * @param distance3
     *            distance from top vertex to point.
     * @return the coordinate of intersection rounded to the hundred thousandth
     *         place.
     */
    public Coordinate calcPosition(double distance1, double distance2, double distance3) {

	x = (Math.pow(distance1, 2) - Math.pow(distance2, 2) + Math.pow(pos2.getX(), 2)) / (2 * pos2.getX());
	endCoord.setX(Math.round(x * 100000d) / 100000d);

	y = ((Math.pow(distance1, 2) - Math.pow(distance3, 2) + Math.pow(pos3.getX(), 2) + Math.pow(pos3.getY(), 2))
		/ (2 * pos3.getY())) - ((pos3.getX() / pos3.getY()) * x);
	endCoord.setY(Math.round(y * 100000d) / 100000d);

	z = Math.sqrt(Math.pow(distance1, 2) - Math.pow(x, 2) - Math.pow(y, 2));
	if (calcBelow) {
	    endCoord.setZ(-(double) Math.round(z * 100000d) / 100000d);
	} else {
	    endCoord.setZ(Math.round(z * 100000d) / 100000d);
	}

	endCoord.setCoordinate(translatedVector.subtractVector(endCoord, translatedVector));

	return endCoord;
    }
}