package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import cmsc519.team8.uno.data.Card;

public class DisplayableCard{
	
	DisplayableCard(Card card){
		this.card = card;
		
		String filename = "/images/unoCards/UNO" + card.getCardColor() +
				" " + card.getCardValue() + ".png";
		try {
			image = 
					ImageIO.read(getClass().getResource(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Card card;
	private BufferedImage image;

	public Card getCard() {
		return card;
	}
	
	public void displayCard(Graphics g, int x, int y, double rotation){
		/**
		 * use commented line once uno cards are found
		 */
		if(rotation == 90){
			g.drawImage(ImageUtils.rotate(image,Math.toRadians(rotation)),
					x, y, 140, 100, null);
		}else{
			g.drawImage(image, x, y, 100, 140, null);
		}
	}
}
