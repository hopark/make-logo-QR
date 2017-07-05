package com.LogoQR;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Logo {
	private BufferedImage image;
	private int size;

	public Logo(String filePath) {
		File myFile = new File(filePath);
		try {
			image = ImageIO.read(myFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		size = image.getWidth();
		if (size != image.getHeight()) {
			return;
		}
	}
	
	public int getSize() {
		return this.size;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
}
