package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import cmsc519.team8.uno.data.Card;

public class DisplayableCard{
	
	DisplayableCard(Card card){
		this.card = card;
		
		String filename = "/images/unoCards/UNO" + card.getCardColor() +
				" " + card.getCardValue() + ".png";
		image = 
        		new ImageIcon(getClass().getResource(filename)).getImage();
	}

	private Card card;
	private Image image;

	public Card getCard() {
		return card;
	}
	
	public void displayCard(Graphics g, int x, int y, double rotation){
		/**
		 * use commented line once uno cards are found
		 */
		AffineTransform at = new AffineTransform();
		at.rotate(Math.toRadians(rotation));
        g.drawImage(image, x, y, 100, 140, null);
	}
}
