package com.LogoQR;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class CompareImage {
	BufferedImage imageQR, imageIcon, imageNew, imageNew2;
	int width;
	int height;
	int unit;

	public CompareImage() {
		try {
			File inputQR = new File("C:\\Users\\PARK\\Desktop\\a\\3.png");
			File inputIcon = new File("C:\\Users\\PARK\\Desktop\\a\\b.png");

			imageQR = ImageIO.read(inputQR);
			imageIcon = ImageIO.read(inputIcon);
			width = imageQR.getWidth();
			height = imageQR.getHeight();
			imageNew = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imageNew2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			unit = width * height / 100;

			int count = 0;

			for (int i = 0; i < height; i++) {

				for (int j = 0; j < width; j++) {
					count++;
					Color cq = new Color(imageQR.getRGB(j, i));
					Color ci = new Color(imageIcon.getRGB(j, i));
					float luminanceQ = (cq.getRed() * 0.2126f + cq.getGreen() * 0.7152f + cq.getBlue() * 0.0722f);

					int red = ci.getRed();
					int green = ci.getGreen();
					int blue = ci.getBlue();

					int red2 = ci.getRed();
					int green2 = ci.getGreen();
					int blue2 = ci.getBlue();

					int min = (red < green) ? red : green;
					min = (min < blue) ? min : blue;

					if (min == 0) {
						min = 1;
					}

					int lumiR = red / min;
					int lumiG = green / min;
					int lumiB = blue / min;

					while ((red * 0.2126f + green * 0.7152f + blue * 0.0722f) > 100) {
						red -= lumiR;
						green -= lumiG;
						blue -= lumiB;

						if (red <= 0 || green <= 0 || blue <= 0)
							break;
					}

					int min2 = (255 - red2 < 255 - green2) ? 255 - red2 : 255 - green2;
					min2 = (min2 < 255 - blue2) ? min2 : 255 - blue2;

					if (min2 == 0) {
						min2 = 1;
					}

					int lumiR2 = (255 - red2) / min2;
					int lumiG2 = (255 - green2) / min2;
					int lumiB2 = (255 - blue2) / min2;

					while ((red2 * 0.2126f + green2 * 0.7152f + blue2 * 0.0722f) < 155) {
						red2 += lumiR2;
						green2 += lumiG2;
						blue2 += lumiB2;

						if (red2 >= 255 || green2 >= 255 || blue2 >= 255)
							break;
					}

					Color cn = new Color(red, green, blue);
					Color cn2 = new Color(red2, green2, blue2);
					if (luminanceQ < 255 / 2) {

						imageNew.setRGB(j, i, cn.getRGB());
						imageNew2.setRGB(j, i, cn2.getRGB());
					} else {
						imageNew2.setRGB(j, i, cn.getRGB());
						imageNew.setRGB(j, i, cn2.getRGB());
					}

					if (count % unit == 0) {
						System.out.println("Progress: " + count / unit + ".0%!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		File outputNew = new File("C:\\Users\\PARK\\Desktop\\a\\5b1777.png");
		File outputNew2 = new File("C:\\Users\\PARK\\Desktop\\a\\5b27777.png");
		try {
			ImageIO.write(imageNew, "png", outputNew);
			ImageIO.write(imageNew2, "png", outputNew2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public void main(String args[]) throws Exception {
		CompareImage obj = new CompareImage();
	}
}