package com.wernerware.fractals.circles;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircleTest {

	@Test
	public void testOverlapFalseWhenNoOverlap() {
		
		Circle c1 = new Circle(0,0,100);
		Circle c2 = new Circle(500,500,200);
		
		assertFalse(c1.overlaps(c2));
		
	}

	@Test
	public void testOverlapTrueWhenOneIsCompletelyInTheOther() {
		
		Circle c1 = new Circle(0,0,100);
		Circle c2 = new Circle(0,0,200);
		
		assertTrue(c1.overlaps(c2));
		
	}

	@Test
	public void testOverlapTrueWhenThereIsOnlyPartialOverlap() {
		
		Circle c1 = new Circle(0,0,100);
		Circle c2 = new Circle(150,0,100);
		
		assertTrue(c1.overlaps(c2));
		
	}

}
