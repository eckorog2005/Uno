package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cmsc519.team8.uno.data.Card;

public class DisplayableDiscardPile {

	DisplayableDiscardPile(DisplayableCard discard){
		this.setDiscardCard(discard);
	}

	public DisplayableDiscardPile() {
		discard = null;
		String filename = "/images/unoCards/BLANK PILE.gif";
		noCards = new ImageIcon(getClass().getResource(filename)).getImage();
	}

	private DisplayableCard discard;
	private Image noCards;


	public void setDiscardCard(DisplayableCard discard) {
		this.discard = discard;
	}
	
	public DisplayableCard getDiscardCard() {
		return discard;
	}
	
	public void clearDiscardPile(){
		discard = null;
	}
	
	public void displayDiscardPile(Graphics g, int x, int y){

		if(discard != null){
			discard.displayCard(g, x, y, 0);
		}else{        
			g.drawImage(noCards, x, y, 100, 140, null);
		}
	}
}
