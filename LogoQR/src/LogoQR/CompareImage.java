package LogoQR;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class CompareImage {
   BufferedImage imageQR, imageIcon, imageNew;
   int width;
   int height;
   int unit;
   
   public CompareImage() {
      try {
         File inputQR = new File("C:\\Users\\PARK\\Desktop\\a\\3.png");
         File inputIcon = new File("C:\\Users\\PARK\\Desktop\\a\\b1.png");
         
         imageQR = ImageIO.read(inputQR);
         imageIcon = ImageIO.read(inputIcon);
         width = imageQR.getWidth();
         height = imageQR.getHeight();
         imageNew = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         
         unit = width*height/100;
         
         int count = 0;
         
         for(int i=0; i<height; i++){
         
            for(int j=0; j<width; j++){
               count++;
               Color cq = new Color(imageQR.getRGB(j, i));
               Color ci = new Color(imageIcon.getRGB(j, i));
               float luminanceQ = (cq.getRed() * 0.2126f + cq.getGreen() * 0.7152f + cq.getBlue() * 0.0722f);
               
               int red = ci.getRed();
               int green = ci.getGreen();
               int blue = ci.getBlue();
               
               if(luminanceQ < 255/2) {
            	   int min = (red < green) ? red : green;
                   min = (min < blue) ? min : blue;
                   
                   if(min == 0) {
                	   min = 1;
                   }
                   
                   int lumiR = red/min;
                   int lumiG = green/min;
                   int lumiB = blue/min;
                   
	               while((red * 0.2126f + green * 0.7152f + blue * 0.0722f) > 100) {
	            	   red -= lumiR;
	            	   green -= lumiG;
	            	   blue -= lumiB;
	            	   
	            	   if(red <= 0 || green <= 0 || blue <= 0) break; 
	               }
               } else {
            	   int min = (255-red < 255-green) ? 255-red : 255-green;
                   min = (min < 255-blue) ? min : 255-blue;
                   
                   if(min == 0) {
                	   min = 1;
                   }
            	   
            	   int lumiR = (255-red)/min;
                   int lumiG = (255-green)/min;
                   int lumiB = (255-blue)/min;
                   
	               while((red * 0.2126f + green * 0.7152f + blue * 0.0722f) < 155) {
	            	   red += lumiR;
	            	   green += lumiG;
	            	   blue += lumiB;
	            	   
	            	   if(red >= 255 || green >= 255 || blue >= 255) break; 
	               }
               }
           
               Color cn = new Color(red, green, blue);
               imageNew.setRGB(j,  i, cn.getRGB());
               
               if(count % unit == 0) {
            	   System.out.println("Progress: " + count/unit + ".0%!");
               }
            }
         }         
      } catch (Exception e) {
    	  e.printStackTrace();
      }     
      
      File outputNew = new File("C:\\Users\\PARK\\Desktop\\a\\5b1.png");
      try {
		ImageIO.write(imageNew, "png", outputNew);
      } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
   }
   
   static public void main(String args[]) throws Exception 
   {
      CompareImage obj = new CompareImage();
   }
}