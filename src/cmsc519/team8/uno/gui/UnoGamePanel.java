package cmsc519.team8.uno.gui;

import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cmsc519.team8.uno.data.CardColorEnum;
import cmsc519.team8.uno.data.CardValueEnum;
import cmsc519.team8.uno.data.Deck;

import javax.swing.JLabel;

public class UnoGamePanel extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -8758461344572468847L;

	private final int HAND_SIZE = 7;

	private final DisplayableDeck displayableDeck = new DisplayableDeck(
			new Deck());

	private final DisplayableDiscardPile displayableDiscardPile = new DisplayableDiscardPile();

	private final DisplayableHand userHand = new DisplayableHand(true, 150,
			380, false, false);
	private final DisplayableHand computer1 = new DisplayableHand(false, 0, 25,
			false, true);
	private final DisplayableHand computer2 = new DisplayableHand(false, 150,
			0, true, false);
	private final DisplayableHand computer3 = new DisplayableHand(false, 650,
			25, true, true);

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	/**
	 * Create the panel. (image test right now)
	 */
	public UnoGamePanel() {
		this.setLayout(null);

		// add labels
		JLabel lblUser = new JLabel("User");
		lblUser.setSize(70, 20);
		lblUser.setLocation(388, 360);
		lblUser.setVisible(true);
		add(lblUser);

		JLabel lblCpu1 = new JLabel("Computer1");
		lblCpu1.setSize(110, 20);
		lblCpu1.setLocation(150, 250);
		lblCpu1.setVisible(true);
		add(lblCpu1);

		JLabel lblCpu2 = new JLabel("Computer2");
		lblCpu2.setSize(110, 20);
		lblCpu2.setLocation(365, 150);
		lblCpu2.setVisible(true);
		add(lblCpu2);

		JLabel lblCpu3 = new JLabel("Computer3");
		lblCpu3.setSize(110, 20);
		lblCpu3.setLocation(540, 250);
		lblCpu3.setVisible(true);
		add(lblCpu3);

		// add hands
		userHand.setSize(500, 160);
		userHand.setLocation(150, 380);
		userHand.setLabel(lblUser);
		add(userHand);

		computer1.setSize(140, 500);
		computer1.setLocation(0, 25);
		computer1.setLabel(lblCpu1);
		add(computer1);

		computer2.setSize(500, 140);
		computer2.setLocation(150, 0);
		computer2.setLabel(lblCpu2);
		add(computer2);

		computer3.setSize(140, 500);
		computer3.setLocation(650, 25);
		computer3.setLabel(lblCpu3);
		add(computer3);

		// add discard and deck
		displayableDeck.setSize(100, 140);
		displayableDeck.setLocation(260, 200);
		add(displayableDeck);

		displayableDiscardPile.setSize(100, 140);
		displayableDiscardPile.setLocation(410, 200);
		add(displayableDiscardPile);

		// testing purposes
		shuffle();
		//preGame();
		deal();
	}

	private void preGame() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 1; i++) {
			userHand.addCard(displayableDeck.drawCard());
			computer1.addCard(displayableDeck.drawCard());
			computer2.addCard(displayableDeck.drawCard());
			computer3.addCard(displayableDeck.drawCard());
		}
		//Get values of each players.
		displayableDiscardPile.setDiscardCard(displayableDeck.drawCard());
		
	
	}
	

	public void shuffle() {
		Deck deck = new Deck();
		deck.shuffle();
		displayableDeck.setDeck(deck);
	}

	public void deal() {
		for (int i = 0; i < HAND_SIZE; i++) {
			userHand.addCard(displayableDeck.drawCard());
			computer1.addCard(displayableDeck.drawCard());
			computer2.addCard(displayableDeck.drawCard());
			computer3.addCard(displayableDeck.drawCard());
		}
		displayableDiscardPile.setDiscardCard(displayableDeck.drawCard());
		//Calculate the value of each cards.
		
		/**
		
		selectCard.getCard().getCardValue()
		DisplayableCard currentCardValue = computer1.getCurrentCard(0); 

		currentCardValue.getCard().getPregameValue();
		
		
		computer1
		**/
		
	}

	// returns true if card can be played
	public boolean playUserCard(DisplayableCard selectCard) {

		if (displayableDiscardPile.getDiscardCard() == null) {
			return false;
		}

		boolean isPlayable = selectCard.getCard().isPlayable(
				displayableDiscardPile.getDiscardCard().getCard());

		if (isPlayable) {
			if (selectCard.getCard().getCardValue().equals(CardValueEnum.WILD)) {
				selectCard.getCard().setWildColor(wildSelector());
			}

			DisplayableCard card = userHand.removeCard();
			displayableDiscardPile.setDiscardCard(card);
			letComputerPlay(computer1);
			letComputerPlay(computer2);
			letComputerPlay(computer3);
			// When computer finishes, user plays again.
			return isPlayable;
		}

		return isPlayable;
	}

	private void letComputerPlay(DisplayableHand computer) {
		// TODO Auto-generated method stub
		if(displayableDiscardPile.getDiscardCard() == null){
			System.out.print("null");
	}
	
	//Linear Search to find matching card to be discarded.
	for(int i=0; i< computer.getTotalNumbCard().size() ; i++){
		
		DisplayableCard currentCardValue = computer.getCurrentCard(i); 
		System.out.println("computer card value " + currentCardValue.getCard().toString());
		
		boolean isPlayable = currentCardValue.getCard().isPlayable(
				displayableDiscardPile.getDiscardCard().getCard());
		
		if(isPlayable){
			if(currentCardValue.getCard().getCardValue().equals(CardValueEnum.WILD)){
				currentCardValue.getCard().setWildColor(wildSelector());
			}
				computer.removeComputerCard(currentCardValue);
				DisplayableCard card = computer.removeCard();
				displayableDiscardPile.setDiscardCard(card);
		}else if (i== computer.getTotalNumbCard().size()-1 && !isPlayable)
		{
			//Gets a card and check for the condition AGAIN!!
			computer.addCard(displayableDeck.drawCard());
			letComputerPlay(computer);			
		}
		//What if there is no more card?
		else if(displayableDiscardPile.getDiscardCard() == null)
		{
			JOptionPane.showMessageDialog(
    	    		   null, 
    	    		   "NO MORE CARD "
    	    		   + "again", "UNO Error", 
    	    		   JOptionPane.ERROR_MESSAGE);
			}
		}//End searching

	}

	public CardColorEnum wildSelector() {
		Object[] possibleValues = { "RED", "BLUE", "YELLOW", "GREEN" };
		String selectedValue = "";
		CardColorEnum returnValue = CardColorEnum.BLACK;
		int flags = 0;

		while (flags < 1) {
			selectedValue = (String) JOptionPane.showInputDialog(null,

			"Select a color", "Wild Card!",

			JOptionPane.INFORMATION_MESSAGE, null,

			possibleValues, possibleValues[0]);
			if (selectedValue != null) {
				returnValue = CardColorEnum.valueOf(selectedValue);
				flags++;
			}
			/*
			 * We don't want them to cancel the color selection, so if they do,
			 * we loop back and ask again
			 */
		}
		return returnValue;
	}

	public void drawUserCard() {
		// TODO check if user turn

		userHand.addCard(displayableDeck.drawCard());
	}
}
