package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cmsc519.team8.uno.data.Card;

public class DisplayableCard{
	
	DisplayableCard(Card card){
		this.card = card;
	}

	private Card card;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	public void displayCard(Graphics g, int x, int y){
		/**
		 * use commented line once uno cards are found
		 */
		//String filename = "\\images\\unoCards\\" + card.getCardColor()+ 
			//	card.getCardValue()+ ".png";
        String filename = "/images/unoCards/test";
		Image image = 
        		new ImageIcon(getClass().getResource(filename)).getImage();
        
        g.drawImage(image, x, y, null);
	}
}
