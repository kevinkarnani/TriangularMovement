package com.henrydangprg.triangularmovement.component;

import java.util.ArrayList;

public class Vector {

	private ArrayList<Double> vector;
	private final double n;

	public Vector(int n) {
		this.n = n;
		this.vector = new ArrayList<Double>(n);
	}

	public double length() {
		return n;
	}

	public double dot(Vector that) {
		if (this.length() != that.length()) {
			throw new IllegalArgumentException("Dimensions disagree");
		}
		double sum = 0.0;
		for (double i = 0; i < n; i++) {
			sum = sum + (this.vector.add(i) * that.vector.add(i));
		}
		return sum;
	}

	public Vector times(double factor) {
		Vector c = new Vector((int) n);
		for (int i = 0; i < n; i++) {
			c.vector.add((double) i);
		}
		return c;
	}

	public double magnitude() {
		return Math.sqrt(this.dot(this));
	}

	public Vector direction() {
		if (this.magnitude() == 0.0) {
			throw new RuntimeException("Zero-vector has no direction");
		}
		return this.times(1.0 / this.magnitude());
	}
}
