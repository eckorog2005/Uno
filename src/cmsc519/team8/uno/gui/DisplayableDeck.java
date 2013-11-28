package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cmsc519.team8.uno.data.Deck;

public class DisplayableDeck extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -3805243899408674285L;

	DisplayableDeck(Deck deck){
		this.deck = deck;
		String filename = "/images/unoCards/UNO BACK.JPG";
		image = 
        		new ImageIcon(getClass().getResource(filename)).getImage();
	}

	private Deck deck;
	private Image image;

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	
	public DisplayableCard drawCard(){
		return new DisplayableCard(deck.drawCard());
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 100, 140, null);
	}
}
