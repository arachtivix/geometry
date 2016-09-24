package com.wernerware.fractals;

import javax.imageio.ImageIO;

public class R {

	public static void main(String[] args) {
		
		String[] writerFormatNames = ImageIO.getWriterFormatNames();
		for( int i = 0; i < writerFormatNames.length; i++ ){
			System.out.println(writerFormatNames[i]);
		}
		
	}

}
