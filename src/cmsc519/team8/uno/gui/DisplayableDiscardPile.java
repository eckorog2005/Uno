package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cmsc519.team8.uno.data.Card;

public class DisplayableDiscardPile {

	DisplayableDiscardPile(Card discard){
		this.setDiscardCard(discard);
	}

	public DisplayableDiscardPile() {
		discard = null;
	}

	private Card discard;


	public void setDiscardCard(Card discard) {
		this.discard = discard;
	}
	
	public Card getDiscardCard() {
		return discard;
	}
	
	public void clearDiscardPile(){
		discard = null;
	}
	
	public void displayDiscardPile(Graphics g, int x, int y){

		if(discard != null){
			String filename = "/images/unoCards/test";
			Image image = 
					new ImageIcon(getClass().getResource(filename)).getImage(discard.getCardImg());
        
			g.drawImage(image, x, y, null);
		}else{
			
		}
	}
}
