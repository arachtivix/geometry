package com.wernerware.fractals;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class BezierCurve {
	
	private Vector2D vecs[];

	public BezierCurve(Vector2D vecs[]){
		if( vecs.length > 2 ){
			this.vecs = vecs;
		} else {
			throw new RuntimeException("Not enough points");
		}
	}
	
	public void draw(Graphics2D gfx, int segments) {
		List<Vector2D> bezierProgression = getProgression(segments,vecs[0],vecs[1]);
		
		for( int i = 1; i + 1 < vecs.length; i++ ){
			List<Vector2D> next = getProgression(segments,vecs[i],vecs[i+1]);
			bezierProgression = getBezierProgression(segments, bezierProgression, next);
		}
		
		drawProgression(bezierProgression,gfx);
	}

	private List<Vector2D> getBezierProgression(int segments,
			List<Vector2D> startToMiddle, List<Vector2D> middleToEnd) {
		Iterator<Vector2D> s2mIter = startToMiddle.iterator();
		Iterator<Vector2D> m2eIter = middleToEnd.iterator();
		
		List<Vector2D> bezierProgression = new LinkedList<Vector2D>();
		double timeIncrements = 0;
		double time = 0;
		while( s2mIter.hasNext() && m2eIter.hasNext() ){
			Vector2D i1 = s2mIter.next();
			Vector2D i2 = m2eIter.next();
			Vector2D i1i2 = i2.subtract(i1);
			Vector2D bezier = i1.add(i1i2.scalarMultiply(time));
			time = timeIncrements++/segments;
			bezierProgression.add(bezier);
		}
		return bezierProgression;
	}
	
	private void drawProgression(List<Vector2D> toDraw, Graphics2D gfx){
		Iterator<Vector2D> iter = toDraw.iterator();
		if( iter.hasNext() ){
			Vector2D p1 = iter.next();
			while( iter.hasNext() ){
				Vector2D p2 = iter.next();
				gfx.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
				p1 = p2;
			}
		}
	}

	private List<Vector2D> getProgression(int segments, Vector2D pointA, Vector2D pointB) {
		List<Vector2D> startToMiddleProgression = new LinkedList<Vector2D>();
		startToMiddleProgression.add(pointA);
		
		Vector2D direction = pointB.subtract(pointA).normalize();
		double distance = pointA.distance(pointB);
		
		for( int i = 1; i < segments; i++ ){
			double time = ((double)(i))/((double)segments);
			Vector2D onTheWay = pointA.add(direction.scalarMultiply(distance*time));
			startToMiddleProgression.add(onTheWay);
		}
		
		startToMiddleProgression.add(pointB);
		
		return startToMiddleProgression;
	}
	
	private List<Vector2D> getProgression(List<Vector2D> others, Vector2D newPoint){
		List<Vector2D> retval = new LinkedList<Vector2D>();
		
		int segments = others.size() - 1;
		double timeIncrements = 0;
		double time = 0.0;
		
		for( Vector2D other: others) {
			Vector2D direction = newPoint.subtract(other);
			Vector2D retvalSub = other.add(direction.scalarMultiply(time));
			time = timeIncrements++ / segments;
		}
		
		return retval;
	}
}
