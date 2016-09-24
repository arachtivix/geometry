package com.wernerware.fractals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Main {

	public static void main(String[] args) {

		try {
			int width = 200, height = 200;

			// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
			// into integer pixels
			BufferedImage bi = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);

			Graphics2D ig2 = bi.createGraphics();

			ig2.setColor(Color.RED);
			
			int n = 5;
			Vector2D vecs[] = new Vector2D[n];
			for( int i = 0; i < n; i++ ){
				vecs[i] = new Vector2D(Math.random()*200.0,Math.random()*200.0);
			}
			
			BezierCurve bc = new BezierCurve(vecs);
			bc.draw(ig2, 30);
			

			ImageIO.write(bi, "PNG", new File("out.PNG"));

		} catch (IOException ie) {
			ie.printStackTrace();
		}	

	}

}
