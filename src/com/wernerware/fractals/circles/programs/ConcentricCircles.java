package com.wernerware.fractals.circles.programs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import com.wernerware.fractals.circles.Circle;

public class ConcentricCircles {

	public static void main(String[] args) {

		try {
			int width = 200, height = 200;

			// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
			// into integer pixels
			BufferedImage bi = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);

			Graphics2D ig2 = bi.createGraphics();

			ig2.setColor(Color.RED);
			
			for( double r = 75.0; r > 4.0; r -= 2 ){
				Circle c = new Circle(new Vector2D(100.0,100.0),r);
				c.draw(ig2);
			}
			
			ig2.drawRect(0, 0, 199, 199);
			

			ImageIO.write(bi, "PNG", new File("ConcentricCircles.PNG"));

		} catch (IOException ie) {
			ie.printStackTrace();
		}	

	}

}
