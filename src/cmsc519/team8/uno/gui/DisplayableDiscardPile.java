package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DisplayableDiscardPile extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -6588966950766522126L;

	DisplayableDiscardPile(DisplayableCard discard) {
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
		repaint();
	}

	public DisplayableCard getDiscardCard() {
		return discard;
	}

	public void clearDiscardPile() {
		discard = null;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (discard != null) {
			discard.displayCard(g, 0, 0, 0);
		} else {
			g.drawImage(noCards, 0, 0, 100, 140, null);
		}
	}

}