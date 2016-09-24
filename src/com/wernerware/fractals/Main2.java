package com.wernerware.fractals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import com.wernerware.fractals.tree.ReductionRatioGenerator;
import com.wernerware.fractals.tree.Tree;
import com.wernerware.fractals.tree.TreeBranchAngleGenerator;

public class Main2 {

	public static void main(String[] args) {

		try {
			int width = 6000, height = 6000;
			
			int iterations = 12;
			double angle = Math.PI/2.0;
			Vector3D bottom = new Vector3D(3000.0,3300.0,0.0);
			Vector3D top = new Vector3D(3000.0,3000.0,0.0);
			Color colors[] = new Color[iterations+1];
			for( int i = 0; i < colors.length; i++ ){
				if( i != 0 ){
					colors[i] = Color.blue;
				} else {
					colors[i] = Color.white;
				}
			}
			
			for( int i = 0; i < 100; i++ ){
				// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
				// into integer pixels
				BufferedImage bi = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_ARGB);
				Graphics2D ig2 = bi.createGraphics();
				ig2.setColor(Color.black);
				ig2.fillRect(0, 0, bi.getWidth(), bi.getHeight());
				int emptyColor = bi.getRGB(0, 0);
				TreeBranchAngleGenerator anGen = new TreeBranchAngleGenerator(angle*Math.random(),0.8+Math.random()/5.0);
				ReductionRatioGenerator reductionRatio = new ReductionRatioGenerator(0.2 + 0.6*Math.random(),0.2*Math.random());
				Tree bc = new Tree(bottom,top,reductionRatio,anGen,colors,iterations);
				bc.draw(ig2);
				
				ImageIO.write(trim(bi,emptyColor,20), "PNG", new File("out" + i + ".PNG"));
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		}	
	}
	
	public static BufferedImage trim(BufferedImage in, int emptyColor, int buffer){
		BufferedImage out = null;
		
		int xmax = 0, ymax = 0, xmin = in.getWidth(), ymin = in.getWidth();
		for( int i = 0; i < in.getWidth(); i++ ){
			for( int j = 0; j < in.getHeight(); j++ ){
				if( in.getRGB(i, j) != emptyColor ){
					if( i < xmin ) xmin = i;
					if( j < ymin ) ymin = j;
					if( i > xmax ) xmax = i;
					if( j > ymax ) ymax = j;
				}
			}
		}
		
		out = new BufferedImage(xmax - xmin + buffer, ymax - ymin + buffer, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = out.createGraphics();
		g.drawImage(in, -xmin + buffer/2, -ymin + buffer/2, null);
		
		g.setColor(Color.red);
		g.drawLine(0, 0, out.getWidth(), 0);
		g.drawLine(out.getWidth() - 1, 0, out.getWidth() - 1, out.getHeight() - 1);
		g.drawLine(out.getWidth() - 1, out.getHeight() - 1, 0, out.getHeight() - 1);
		g.drawLine(0, out.getHeight() - 1, 0, 0);
		
		return out;
	}

}
