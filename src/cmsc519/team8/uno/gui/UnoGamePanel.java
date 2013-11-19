package cmsc519.team8.uno.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import cmsc519.team8.uno.data.Card;

public class UnoGamePanel extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -8758461344572468847L;
	
	private DisplayableHand theItem;
    private int x, y;
 
    public void paint(Graphics g) {
        if (theItem != null)
            theItem.displayHand(g, x, y);
    }
     
    public void setItem(DisplayableHand item, int x, int y) {
        theItem = item;
        this.x = x;
        this.y = x;
    }
	/**
	 * Create the panel. (image test right now)
	 */
	public UnoGamePanel() {
		DisplayableHand hand = new DisplayableHand();
		hand.addCard(new DisplayableCard(new Card()));
		setItem(hand,100,100);
	}

}
