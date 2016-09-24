package com.wernerware.fractals.circles;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;

public class CirclesSpatialSortingTest {

	@Test
	public void testAllCirclesContainingPoint() {
		
		List<Circle> circles = new LinkedList<Circle>();
		Circle c1 = new Circle(new Vector2D(100,0),200);
		circles.add(c1);
		CirclesSpatialSorting sorting = new CirclesSpatialSorting(circles);
		
		List<Circle> circlesContaining = sorting.getContainingBaseline(new Vector2D(0,0));
		
		assertTrue(circlesContaining.contains(c1));
		
	}
	
	@Test
	public void testAllCirclesContainingPointExcludingThoseThatDont() {
		
		List<Circle> circles = new LinkedList<Circle>();
		Circle c1 = new Circle(new Vector2D(100,0),200);
		Circle c2 = new Circle(new Vector2D(-100,0),25);
		circles.add(c1);
		circles.add(c2);
		CirclesSpatialSorting sorting = new CirclesSpatialSorting(circles);
		
		List<Circle> circlesContaining = sorting.getContainingBaseline(new Vector2D(0,0));
		
		assertTrue(!circlesContaining.contains(c2));
		
	}
	
	@Test
	public void testBoundingBoxForSingleCircle() {
		
		List<Circle> circles = new LinkedList<Circle>();
		Circle c1 = new Circle(new Vector2D(100,0),200);
		circles.add(c1);
		CirclesSpatialSorting sorting = new CirclesSpatialSorting(circles);
		
		double bounding[] = sorting.getBoundingBox();
		
		assertEquals(bounding[0],-100,.01);
		assertEquals(bounding[1],200,.01);
		assertEquals(bounding[2],300,.01);
		assertEquals(bounding[3],-200,.01);
		
	}
	
	@Test
	public void testBoundingBoxForTwoCircles() {
		
		List<Circle> circles = new LinkedList<Circle>();
		Circle c1 = new Circle(new Vector2D(100,0),200);
		Circle c2 = new Circle(new Vector2D(-100,100),300);
		circles.add(c1);
		circles.add(c2);
		CirclesSpatialSorting sorting = new CirclesSpatialSorting(circles);
		
		double bounding[] = sorting.getBoundingBox();
		
		assertEquals(bounding[0],-400,.01);
		assertEquals(bounding[1],400,.01);
		assertEquals(bounding[2],300,.01);
		assertEquals(bounding[3],-200,.01);
		
	}

}
