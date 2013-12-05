package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cmsc519.team8.uno.data.Deck;

public class DisplayableDeck extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -3805243899408674285L;

	DisplayableDeck(Deck deck) {
		this.deck = deck;
		String filename = "/images/unoCards/UNO BACK.JPG";
		image = new ImageIcon(getClass().getResource(filename)).getImage();
		addMouseListener(new MouseController());
	}

	private Deck deck;
	private Image image;

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public DisplayableCard drawCard() {
		if (deck.getSize() == 1) {
			((UnoGamePanel) getParent()).deckEmpty();
		}
		return new DisplayableCard(deck.drawCard());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, 100, 140, null);
	}

	private class MouseController extends MouseAdapter {
		public void mouseClicked(MouseEvent me) {
			//handle various action in gamepanel
			((UnoGamePanel) getParent()).checkState();
		}
	}

}