package com.wernerware.fractals.circles.programs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.wernerware.fractals.circles.Circle;

public class NonOverlappingSubCircles {

	public static void main(String[] args) {

		try {
			int width = 5000, height = 5000;

			// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
			// into integer pixels
			BufferedImage bi = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);

			Graphics2D ig2 = bi.createGraphics();

			ig2.setColor(Color.RED);
			
			ig2.drawRect(0, 0, 4999, 4999);
			
			Circle master = new Circle( 2500, 2500, 2450 );
			master.draw(ig2);
			
			List<Circle> subs = getNonOverlappingSubCircles(master, 3, 350, .99, 100000000);
			for( Circle c : subs ){
				c.draw(ig2);
			}

			ImageIO.write(bi, "PNG", new File("NonOverlappingSubCircles.PNG"));

		} catch (IOException ie) {
			ie.printStackTrace();
		}	

	}
	
	public static List<Circle> getNonOverlappingSubCircles(Circle c, double minSubRadius, double maxSubRadius, double targetCoverage, int maxTries){
		LinkedList<Circle> retval = new LinkedList<Circle>();
		
		double surfaceArea = 0;
		double parentSurfaceArea = Math.PI * c.getRadius() * c.getRadius();
		
		for( int i = 0; i < maxTries; i++ ){
			Circle candidate = c.generateRandomSubCircle(minSubRadius, maxSubRadius);
			boolean isFit = true;
			for( Circle other : retval ){
				if( candidate.overlaps(other) ){
					isFit = false;
					break;
				}
			}
			if( isFit ) {
				retval.add(candidate);
				surfaceArea += Math.PI * candidate.getRadius() * candidate.getRadius();
			}
			if( surfaceArea/parentSurfaceArea > targetCoverage ){
				break;
			}
		}
		
		return retval;
	}

}
