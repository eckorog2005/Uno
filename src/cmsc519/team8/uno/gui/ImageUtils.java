package cmsc519.team8.uno.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class ImageUtils {

	public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
	
	public static BufferedImage rotate(BufferedImage image, double angle) {
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth(), h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
	    GraphicsConfiguration gc = getDefaultConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(angle, w/2, h/2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}
	
	public static BufferedImage colorCard(BufferedImage loadImg, 
			Color color) {
	    BufferedImage img = new BufferedImage(loadImg.getWidth(), 
	    		loadImg.getHeight(), BufferedImage.TRANSLUCENT);

	    BufferedImage mask = generateMask(loadImg, 
				color, 0.5f);
	    
	    Graphics2D graphics = img.createGraphics(); 
	    graphics.drawImage(loadImg, 0, 0, null);
	    graphics.drawImage(mask, 0, 0, null);
	    graphics.dispose();
	    return img;
	}
	
	private static BufferedImage generateMask(BufferedImage imgSource, 
			Color color, float alpha) {
        BufferedImage imgMask = new BufferedImage(imgSource.getWidth(), 
        		imgSource.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D g2 = imgMask.createGraphics();

        g2.drawImage(imgSource, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(
        		AlphaComposite.SRC_IN, alpha));
        g2.setColor(color);

        g2.fillRect(0, 0, imgSource.getWidth(), imgSource.getHeight());
        g2.dispose();

        return imgMask;
    }
}
