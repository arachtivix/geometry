package com.wernerware.fractals.lines;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;
import static org.junit.Assert.*;

public class LineTest {

	@Test
	public void aGivenEndpointMatches() {

		Vector2D p1 = new Vector2D(0,0);
		Vector2D p2 = new Vector2D(1,1);
		Line l1 = new Line(p1,p2);
		
		Vector2D p3 = new Vector2D(1,0);
		Line l2 = new Line(p2,p3);
		
		assertTrue(l1.intersects(l2));
		
	}

	@Test
	public void noIntersection() {

		Vector2D p1 = new Vector2D(0,0);
		Vector2D p2 = new Vector2D(0,1);
		Line l1 = new Line(p1,p2);

		Vector2D p3 = new Vector2D(1,1);
		Vector2D p4 = new Vector2D(1,0);
		Line l2 = new Line(p3,p4);
		
		assertFalse(l1.intersects(l2));
		
	}

	@Test
	public void interstitialIntersection() {

		Vector2D p1 = new Vector2D(1,0);
		Vector2D p2 = new Vector2D(0,1);
		Line l1 = new Line(p1,p2);

		Vector2D p3 = new Vector2D(1,1);
		Vector2D p4 = new Vector2D(0,0);
		Line l2 = new Line(p3,p4);
		
		assertTrue(l1.intersects(l2));
		
	}

}
