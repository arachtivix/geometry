package com.wernerware.fractals.circles;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class CirclesSpatialSorting {

	private List<Circle> circles;
	
	public CirclesSpatialSorting(List<Circle> in){
		this.circles = new LinkedList<Circle>();
		for( Circle c : in ){
			this.circles.add(c);
		}
	}

	public List<Circle> getContainingBaseline(Vector2D vector2d) {
		List<Circle> retval = new LinkedList<Circle>();
		
		for( Circle c : circles ){
			if( c.contains(vector2d) ){
				retval.add(c);
			}
		}
		
		return retval;
	}
	
	public double[] getBoundingBox(){
		double retval[] = new double[4];
		
		if( circles.size() > 0 ){
			Vector3D vec = circles.get(0).getCenter();
			retval[0] = vec.getX();
			retval[1] = vec.getY();
			retval[2] = vec.getX();
			retval[3] = vec.getY();
		} else {
			retval[0] = 0;
			retval[1] = 0;
			retval[2] = 0;
			retval[3] = 0;
		}
		
		for( Circle c : circles ){
			double left = c.getCenter().getX() - c.getRadius();
			double top = c.getCenter().getY() + c.getRadius();
			double right = c.getCenter().getX() + c.getRadius();
			double bottom = c.getCenter().getY() - c.getRadius();
			
			retval[0] = retval[0] > left ? left : retval[0];
			retval[1] = retval[1] < top ? top : retval[1];
			retval[2] = retval[2] < right ? right : retval[2];
			retval[3] = retval[3] > bottom ? bottom : retval[3];
		}
		
		return retval;
	}
	
	public List<Circle> getLargestNonOverlappingCirclesByTotalAreaBruteForce(List<Circle> in){
		LinkedList<Circle> out = new LinkedList<Circle>();
		
		
		
		return out;
	}
	
}
