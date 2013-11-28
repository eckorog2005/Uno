package cmsc519.team8.uno.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import cmsc519.team8.uno.data.Deck;

import javax.swing.JLabel;

public class UnoGamePanel extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -8758461344572468847L;

    private final DisplayableDeck displayableDeck = 
    		new DisplayableDeck(new Deck());

    private final DisplayableDiscardPile displayableDiscardPile = 
    		new DisplayableDiscardPile();
    
    private final DisplayableHand userHand = new DisplayableHand(true, 150, 380, false, false);
    private final DisplayableHand computer1 = new DisplayableHand(false, 0, 25, false, true);
    private final DisplayableHand computer2 = new DisplayableHand(false, 150, 0, true, false);
    private final DisplayableHand computer3 = new DisplayableHand(false, 650, 25, true, true);
 
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
     
	/**
	 * Create the panel. (image test right now)
	 */
	public UnoGamePanel() {
		this.setLayout(null);
		
		//add labels
		JLabel lblUser = new JLabel("User");
		lblUser.setSize(30, 20);
		lblUser.setLocation(388, 360);
		lblUser.setVisible(true);
		add(lblUser);
		
		JLabel lblCpu1 = new JLabel("Computer1");
		lblCpu1.setSize(70, 20);
		lblCpu1.setLocation(150, 250);
		lblCpu1.setVisible(true);
		add(lblCpu1);
		
		JLabel lblCpu2 = new JLabel("Computer2");
		lblCpu2.setSize(70, 20);
		lblCpu2.setLocation(365, 150);
		lblCpu2.setVisible(true);
		add(lblCpu2);
		
		JLabel lblCpu3 = new JLabel("Computer3");
		lblCpu3.setSize(70, 20);
		lblCpu3.setLocation(570, 250);
		lblCpu3.setVisible(true);
		add(lblCpu3);
		
		//add hands
		userHand.setSize(500, 160);
		userHand.setLocation(150, 380);
		add(userHand);
		
		computer1.setSize(140, 500);
		computer1.setLocation(0, 25);
		add(computer1);
		
		computer2.setSize(500, 140);
		computer2.setLocation(150, 0);
		add(computer2);
		
		computer3.setSize(140, 500);
		computer3.setLocation(650, 25);
		add(computer3);
		
		
		//add discard and deck
		displayableDeck.setSize(100, 140);
		displayableDeck.setLocation(260, 200);
		add(displayableDeck);
		
		displayableDiscardPile.setSize(100, 140);
		displayableDiscardPile.setLocation(430, 200);
		add(displayableDiscardPile);
		
		
		//testing purposes
		for(int i = 0; i<7;i++){
			userHand.addCard(displayableDeck.drawCard());
			computer1.addCard(displayableDeck.drawCard());
			computer2.addCard(displayableDeck.drawCard());
			computer3.addCard(displayableDeck.drawCard());
		}
		displayableDiscardPile.setDiscardCard(displayableDeck.drawCard());
	}
	
	//returns true if card can be played
	public boolean playUserCard(DisplayableCard selectCard){
		
		if(displayableDiscardPile.getDiscardCard() == null){
			return false;
		}
		
		boolean isPlayable = selectCard.getCard().isPlayable(
				displayableDiscardPile.getDiscardCard().getCard());
		
		if(isPlayable){
			DisplayableCard card = userHand.removeCard();
			displayableDiscardPile.setDiscardCard(card);
		}
		return isPlayable;
	}
	
	public void drawUserCard(){
		//TODO check if user turn
		
		userHand.addCard(displayableDeck.drawCard());
	}
}
