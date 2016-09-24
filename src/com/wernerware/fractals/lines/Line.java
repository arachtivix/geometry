package com.wernerware.fractals.lines;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Line {

	private Vector2D from, to;
	
	public Line(Vector2D from, Vector2D to){
		this.from = from;
		this.to = to;
	}
	
	public boolean intersects(Line other) {
		if( other.from.equals(from) || other.to.equals(to) || other.from.equals(to) || other.to.equals(from) ){
			return true;
		}
		return false;
	}
	
}
