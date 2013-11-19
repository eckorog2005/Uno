package cmsc519.team8.uno.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import cmsc519.team8.uno.data.Card;
import cmsc519.team8.uno.data.Deck;

public class UnoGamePanel extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -8758461344572468847L;
	
	private DisplayableHand theItem;
    private int x, y;

    private final DisplayableDeck displayableDeck = 
    		new DisplayableDeck(new Deck());

    private final DisplayableDiscardPile displayableDiscardPile = 
    		new DisplayableDiscardPile();
 
    public void paint(Graphics g) {
        displayableDeck.displayDeck(g, 200, 139);
        displayableDiscardPile.displayDiscardPile(g, 300, 139);
    }
     
	/**
	 * Create the panel. (image test right now)
	 */
	public UnoGamePanel() {
		DisplayableHand hand = new DisplayableHand();
		hand.addCard(new DisplayableCard(new Card()));
		//setItem(hand,100,100);
	}

}
