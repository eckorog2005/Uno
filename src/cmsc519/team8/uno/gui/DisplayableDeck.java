package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import cmsc519.team8.uno.data.Deck;

public class DisplayableDeck {

	DisplayableDeck(Deck deck){
		this.deck = deck;
	}

	private Deck deck;

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public DisplayableCard drawCard(){
		return new DisplayableCard(deck.drawCard());
	}
	
	public void displayDeck(Graphics g, int x, int y){
        String filename = "/images/unoCards/test";
		Image image = 
        		new ImageIcon(getClass().getResource(filename)).getImage("UNO BACK");
        
		g.drawImage(image, x, y, null);
	}
}
