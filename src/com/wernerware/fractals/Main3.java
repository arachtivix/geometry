package com.wernerware.fractals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Main3 {

	public static void main(String[] args) {

		try {
			int width = 200, height = 200;

			// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
			// into integer pixels
			BufferedImage bi = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_ARGB);

			Graphics2D ig2 = bi.createGraphics();

			ig2.setColor(Color.RED);
			
			BufferedImage test1 = null;
			try {
			    test1 = ImageIO.read(new File("test1.jpg"));
			} catch (IOException e) {
			}
			
			ig2.drawImage(test1, 0, 0, null);

			ImageIO.write(bi, "PNG", new File("out.PNG"));

		} catch (IOException ie) {
			ie.printStackTrace();
		}	

	}

}
