package com.wernerware.fractals.circles;

import java.awt.Color;
import java.awt.Graphics2D;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Circle implements Comparable<Circle> {

	private final Vector3D center;
	private final double radius;
	private double boundingBox[]; // Top, right, bottom, left
	
	public Circle( double x, double y, double radius ){
		this.center = new Vector3D(x,y,0);
		this.radius = radius;
		initBoundingBox();
	}
	
	public Circle(Vector2D center, double radius){
		this.center = new Vector3D(center.getX(),center.getY(),0);
		this.radius = radius;
	}
	
	public Circle(Vector3D center, double radius){
		this.center = center;
		this.radius = radius;
	}
	
	private void initBoundingBox(){
		boundingBox = new double[4];
		boundingBox[0] = center.getY() - radius;
		boundingBox[1] = center.getX() + radius;
		boundingBox[2] = center.getY() + radius;
		boundingBox[3] = center.getX() - radius;
	}

	public void draw(Graphics2D ig2) {
		Color c;
		if( radius < 10 ){
			c = Color.red;
		} else if ( radius < 70 ){
			c = Color.blue;
		} else {
			c = Color.green;
		}
		ig2.setColor(c);
		
		ig2.drawOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)radius*2, (int)radius*2);
		
	}
	
	public boolean overlaps(Circle other){
		return other.center.distance(center) < other.radius + radius;
	}
	
	public boolean isCompletelyWithin(Circle other){
		return other.center.distance(center) < other.radius - radius;
	}
	
	public Circle generateRandomSubCircle(double maxRadius, double minRadius){
		Vector2D newCenter;
		Circle retval;
		
		do {
			double x = boundingBox[3] + 2.0 * radius * Math.random();
			double y = boundingBox[0] + 2.0 * radius * Math.random();
			double deltaRad = maxRadius - minRadius;
			double rad = minRadius + deltaRad * Math.random();
			newCenter = new Vector2D(x,y);
			retval = new Circle(newCenter,rad);
		} while(!retval.isCompletelyWithin(this));
		
		return retval;
	}

	public Vector3D getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public boolean contains(Vector2D vector2d) {
		Vector3D vec = new Vector3D(vector2d.getX(),vector2d.getY(),0);
		if( vec.distance(center) <= radius ){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Circle arg0) {
		int retval;
		
		Circle other = (Circle)arg0;
		double diff = this.radius - other.radius;
		if( diff > 1 || diff < -1 ){
			retval = (int)diff;
		} else if( diff > 0 ) {
			retval = 1;
		} else {
			retval = -1;
		}
		
		return retval;
	}
}
