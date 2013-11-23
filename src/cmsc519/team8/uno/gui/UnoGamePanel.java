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
    
    private final DisplayableHand userHand = new DisplayableHand();
    private final DisplayableHand computer1 = new DisplayableHand();
    private final DisplayableHand computer2 = new DisplayableHand();
    private final DisplayableHand computer3 = new DisplayableHand();
 
    public void paint(Graphics g) {
        displayableDeck.displayDeck(g, 200, 139);
        displayableDiscardPile.displayDiscardPile(g, 300, 139);
        userHand.displayHand(g, 150, 400, false, false);
        computer1.displayHand(g, 0, 25, false, true);
        computer2.displayHand(g, 150, 0, true, false);
        computer3.displayHand(g, 650, 25, true, true);
    }
     
	/**
	 * Create the panel. (image test right now)
	 */
	public UnoGamePanel() {
		for(int i = 0; i<7;i++){
			userHand.addCard(displayableDeck.drawCard());
			computer1.addCard(displayableDeck.drawCard());
			computer2.addCard(displayableDeck.drawCard());
			computer3.addCard(displayableDeck.drawCard());
		}
	}

}
