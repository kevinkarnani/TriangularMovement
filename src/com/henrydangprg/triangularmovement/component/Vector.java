package com.henrydangprg.triangularmovement.component;

import java.util.ArrayList;
import java.util.Collection;

public class Vector {

	private ArrayList<Double> vector;
	private final double lengthOfVector;

	public Vector(double lengthOfVector) {
		this.lengthOfVector = lengthOfVector;
		this.vector = new ArrayList<Double>();
	}

	public double length() {
		return lengthOfVector;
	}

	public double dot(Vector that) {
		if (this.length() != that.length()) {
			throw new IllegalArgumentException("Component does not match");
		}
		double sum = 0.0;
		for (int i = 0; i < lengthOfVector; i++) {
			sum = sum + this.vector.get(i) * that.vector.get(i);
		}
		return sum;
	}

	public Vector times(double factor) {
		Vector c = new Vector(lengthOfVector);
		for (int i = 0; i < lengthOfVector; i++) {
			c.vector.add((double) i);
		}
		return c;
	}

	public double magnitude() {
		return Math.sqrt(this.dot(this));
	}

	public Vector direction() {
		if (this.magnitude() == 0.0) {
			throw new RuntimeException("You just created a zero vector.");
		}
		return this.times(1.0 / this.magnitude());
	}

	public void loopThroughElements() {
		Collection<? extends Double> numberOfIndeces = null;
		vector.addAll(numberOfIndeces);
		int indeces = vector.size();
		for (int i = 0; i < indeces; i++) {
			System.out.println(vector.get(indeces));
		}
	}
}
