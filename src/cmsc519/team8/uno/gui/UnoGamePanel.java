package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.util.Arrays;

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

		JLabel lblUser = new JLabel("User");
		JLabel lblCpu1 = new JLabel("Computer1");
		JLabel lblCpu2 = new JLabel("Computer2");
		JLabel lblCpu3 = new JLabel("Computer3");

		// Pre game: Determines dealer and player turn.
		String dealer = preGame(userHand, computer1, computer2, computer3);

		// Determines the dealer
		if (dealer.equals("user")) {
			lblUser.setText("DEALER");
			JOptionPane.showMessageDialog(null, "You are the dealer" + "", "",
					JOptionPane.PLAIN_MESSAGE);
		} else if (dealer.equals("computer1")) {
			lblCpu1.setText("DEALER");
			JOptionPane.showMessageDialog(null,
					"Computer 1 is the dealer" + "", "",
					JOptionPane.PLAIN_MESSAGE);
		} else if (dealer.equals("computer2")) {
			lblCpu2.setText("DEALER");
			JOptionPane.showMessageDialog(null,
					"Computer 2 is the dealer" + "", "",
					JOptionPane.PLAIN_MESSAGE);
		} else if (dealer.equals("computer3")) {
			lblCpu3.setText("DEALER");
			JOptionPane.showMessageDialog(null, "Computer 3 is a dealer" + "",
					"", JOptionPane.PLAIN_MESSAGE);
		}

		// add labels
		lblUser.setSize(70, 20);
		lblUser.setLocation(388, 360);
		lblUser.setVisible(true);
		add(lblUser);

		lblCpu1.setSize(110, 20);
		lblCpu1.setLocation(150, 250);
		lblCpu1.setVisible(true);
		add(lblCpu1);

		lblCpu2.setSize(110, 20);
		lblCpu2.setLocation(365, 150);
		lblCpu2.setVisible(true);
		add(lblCpu2);

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

		shuffle();
		// The game should be played in a proper term based on dealer.
		deal(dealer);

	}

	private String preGame(DisplayableHand userHand, DisplayableHand computer1,
			DisplayableHand computer2, DisplayableHand computer3) {

		// Get one cards each. just one.
		shuffle();
		userHand.addCard(displayableDeck.drawCard());
		computer1.addCard(displayableDeck.drawCard());
		computer2.addCard(displayableDeck.drawCard());
		computer3.addCard(displayableDeck.drawCard());

		// get values of each card. (in a integer form so it can be compared).
		int userVal = getIntCardVal(userHand);
		int comp1Val = getIntCardVal(computer1);
		int comp2Val = getIntCardVal(computer2);
		int comp3Val = getIntCardVal(computer3);

		// Testing to see what Values each players has.
		System.out.println(userVal + "" + comp1Val);
		int[] data = new int[4];
		data[0] = userVal;
		data[1] = comp1Val;
		data[2] = comp2Val;
		data[3] = comp3Val;

		Arrays.sort(data);

		// Therefore, it is possible to find dealer and find the right "turn"
		if (data[3] == userVal) {

			userHand.clearHand();
			computer1.clearHand();
			computer2.clearHand();
			computer3.clearHand();
			return "user"; // 0 Turn Implies : Com1 -> Com2 -> Com3 -> User.
		} else if (data[3] == comp1Val) {

			userHand.clearHand();
			computer1.clearHand();
			computer2.clearHand();
			computer3.clearHand();

			return "computer1"; // 1 implies: Com2 -> Com3 -> User -> Comp1.
		} else if (data[3] == comp2Val) {
			userHand.clearHand();
			computer1.clearHand();
			computer2.clearHand();
			computer3.clearHand();
			return "computer2";
			// Turn 2 : computer 3 -> user -> comp1 -> comp2 and loop.
		} else if (data[3] == comp3Val) {
			userHand.clearHand();
			computer1.clearHand();
			computer2.clearHand();
			computer3.clearHand();
			return "computer3";
			// Turn : user -> computer1 - > computer 2-> computer 3 and iterate.
		} else
			userHand.clearHand();
		computer1.clearHand();
		computer2.clearHand();
		computer3.clearHand();
		return "";
	}

	private int getIntCardVal(DisplayableHand player) {
		// It will return integer value of the cards.
		DisplayableCard userHandValue = player.getCurrentCard(0);

		if (userHandValue.getCard().getCardValue().toString() == "ZERO") {
			return 0;
		} else if (userHandValue.getCard().getCardValue().toString() == "ONE") {
			return 1;
		} else if (userHandValue.getCard().getCardValue().toString() == "TWO") {
			return 2;
		}
		if (userHandValue.getCard().getCardValue().toString() == "THREE") {
			return 3;
		}
		if (userHandValue.getCard().getCardValue().toString() == "FOUR") {
			return 4;
		}
		if (userHandValue.getCard().getCardValue().toString() == "FIVE") {
			return 5;
		}
		if (userHandValue.getCard().getCardValue().toString() == "SIX") {
			return 6;
		}
		if (userHandValue.getCard().getCardValue().toString() == "SEVEN") {
			return 7;
		}
		if (userHandValue.getCard().getCardValue().toString() == "EIGHT") {
			return 8;
		}
		if (userHandValue.getCard().getCardValue().toString() == "NINE") {
			return 9;
		} else
			return 0; // Wild card is 0.
	}

	public void shuffle() {
		Deck deck = new Deck();
		deck.shuffle();
		displayableDeck.setDeck(deck);
	}

	public void deal(String dealer) {
		for (int i = 0; i < HAND_SIZE; i++) {
			userHand.addCard(displayableDeck.drawCard());
			computer1.addCard(displayableDeck.drawCard());
			computer2.addCard(displayableDeck.drawCard());
			computer3.addCard(displayableDeck.drawCard());
		}
		displayableDiscardPile.setDiscardCard(displayableDeck.drawCard());
		if (dealer.equals("user")) {
			System.out.println("computer 1 plays first");
			letComputerPlay(computer1);
			letComputerPlay(computer2);
			letComputerPlay(computer3);
		} else if (dealer.equals("computer1")) {
			System.out.println("computer 2 plays first");
			letComputerPlay(computer2);
			letComputerPlay(computer3);
		} else if (dealer.equals("computer2")) {
			System.out.println("computer 3 plays first");
			letComputerPlay(computer3);
		} else if (dealer.equals("computer3")) {
			System.out.println("user plays first");
		}

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
		if (displayableDiscardPile.getDiscardCard() == null) {
			System.out.print("null");
		}

		// Linear Search to find matching card to be discarded.
		for (int i = 0; i < computer.getTotalNumbCard().size(); i++) {

			DisplayableCard currentCardValue = computer.getCurrentCard(i);
			System.out.println("computer card value "
					+ currentCardValue.getCard().toString());

			boolean isPlayable = currentCardValue.getCard().isPlayable(
					displayableDiscardPile.getDiscardCard().getCard());

			if (isPlayable) {
				if (currentCardValue.getCard().getCardValue()
						.equals(CardValueEnum.WILD)) {
					currentCardValue.getCard().setWildColor(wildSelector());
				}
				computer.removeComputerCard(currentCardValue);
				DisplayableCard card = computer.removeCard();
				displayableDiscardPile.setDiscardCard(card);
			} else if (i == computer.getTotalNumbCard().size() - 1
					&& !isPlayable) {
				// Gets a card and check for the condition AGAIN!!
				computer.addCard(displayableDeck.drawCard());
				letComputerPlay(computer);
			}
			// What if there is no more card?
			else if (displayableDiscardPile.getDiscardCard() == null) {
				JOptionPane.showMessageDialog(null, "NO MORE CARD " + "again",
						"UNO Error", JOptionPane.ERROR_MESSAGE);
			}
		}// End searching

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
		userHand.addCard(displayableDeck.drawCard());
	}
}
