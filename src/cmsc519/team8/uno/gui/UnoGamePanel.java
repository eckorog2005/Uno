package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Arrays;

import javax.swing.JButton;
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
	
	private JButton controlButton;

	private boolean preGame;
	
	private String dealer;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	/**
	 * Create the panel.
	 */
	public UnoGamePanel() {
		this.setLayout(null);

		// add labels and status
		JLabel lblUser = new JLabel("User");
		lblUser.setSize(70, 20);
		lblUser.setLocation(388, 340);
		lblUser.setVisible(true);
		add(lblUser);
		JLabel userStatus = new JLabel("");
		userStatus.setSize(70, 20);
		userStatus.setLocation(381, 360);
		userStatus.setVisible(true);
		add(userStatus);

		JLabel lblCpu1 = new JLabel("Computer1");
		lblCpu1.setSize(110, 20);
		lblCpu1.setLocation(150, 250);
		lblCpu1.setVisible(true);
		add(lblCpu1);
		JLabel cpu1Status = new JLabel("");
		cpu1Status.setSize(110, 20);
		cpu1Status.setLocation(164, 270);
		cpu1Status.setVisible(true);
		add(cpu1Status);

		JLabel lblCpu2 = new JLabel("Computer2");
		lblCpu2.setSize(110, 20);
		lblCpu2.setLocation(365, 150);
		lblCpu2.setVisible(true);
		add(lblCpu2);
		JLabel cpu2Status = new JLabel("");
		cpu2Status.setSize(110, 20);
		cpu2Status.setLocation(379, 170);
		cpu2Status.setVisible(true);
		add(cpu2Status);

		JLabel lblCpu3 = new JLabel("Computer3");
		lblCpu3.setSize(110, 20);
		lblCpu3.setLocation(540, 250);
		lblCpu3.setVisible(true);
		add(lblCpu3);
		JLabel cpu3Status = new JLabel("");
		cpu3Status.setSize(110, 20);
		cpu3Status.setLocation(554, 270);
		cpu3Status.setVisible(true);
		add(cpu3Status);
		
		//add controlButton
		controlButton = new JButton("StartGame");
		controlButton.setSize(100,25);
		controlButton.setLocation(525, 340);
		controlButton.setVisible(true);
		controlButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkState();
			}
		});
		add(controlButton);

		// add hands
		userHand.setSize(500, 160);
		userHand.setLocation(150, 380);
		userHand.setLabel(lblUser);
		userHand.setStatus(userStatus);
		add(userHand);

		computer1.setSize(140, 500);
		computer1.setLocation(0, 25);
		computer1.setLabel(lblCpu1);
		computer1.setStatus(cpu1Status);
		add(computer1);

		computer2.setSize(500, 140);
		computer2.setLocation(150, 0);
		computer2.setLabel(lblCpu2);
		computer2.setStatus(cpu2Status);
		add(computer2);

		computer3.setSize(140, 500);
		computer3.setLocation(645, 25);
		computer3.setLabel(lblCpu3);
		computer3.setStatus(cpu3Status);
		add(computer3);

		// add discard and deck
		displayableDeck.setSize(100, 140);
		displayableDeck.setLocation(260, 200);
		add(displayableDeck);

		displayableDiscardPile.setSize(100, 140);
		displayableDiscardPile.setLocation(410, 200);
		add(displayableDiscardPile);

		preGame = true;
		preGame();
	}
	
	private void preGame() {
		// Pre game: Determines dealer and player turn.
		// Get one cards each. just one.
		shuffle();
		computer1.setVisibility(true);
		computer2.setVisibility(true);
		computer3.setVisibility(true);
		userHand.addCard(displayableDeck.drawCard());
		computer1.addCard(displayableDeck.drawCard());
		computer2.addCard(displayableDeck.drawCard());
		computer3.addCard(displayableDeck.drawCard());
	}

	private void determineDealer() {
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
			dealer = "user";
			return; // 0 Turn Implies : Com1 -> Com2 -> Com3 -> User.
		} else if (data[3] == comp1Val) {

			userHand.clearHand();
			computer1.clearHand();
			computer2.clearHand();
			computer3.clearHand();
			dealer = "computer1";
			return; // 1 implies: Com2 -> Com3 -> User -> Comp1.
		} else if (data[3] == comp2Val) {
			userHand.clearHand();
			computer1.clearHand();
			computer2.clearHand();
			computer3.clearHand();
			dealer = "computer2";
			return;
			// Turn 2 : computer 3 -> user -> comp1 -> comp2 and loop.
		} else if (data[3] == comp3Val) {
			userHand.clearHand();
			computer1.clearHand();
			computer2.clearHand();
			computer3.clearHand();
			dealer = "computer3";
			return;
			// Turn : user -> computer1 - > computer 2-> computer 3 and iterate.
		} else
			userHand.clearHand();
		computer1.clearHand();
		computer2.clearHand();
		computer3.clearHand();
		dealer = "";
		return;
	}
	
	private void gameSetup() {
		// Displays the dealer
		if (dealer.equals("user")) {
			userHand.getLabel().setText("DEALER");
			JOptionPane.showMessageDialog(null, "You are the dealer" + "", "",
					JOptionPane.PLAIN_MESSAGE);
		} else if (dealer.equals("computer1")) {
			computer1.getLabel().setText("DEALER");
			JOptionPane.showMessageDialog(null,
					"Computer 1 is the dealer" + "", "",
					JOptionPane.PLAIN_MESSAGE);
		} else if (dealer.equals("computer2")) {
			computer2.getLabel().setText("DEALER");
			JOptionPane.showMessageDialog(null,
					"Computer 2 is the dealer" + "", "",
					JOptionPane.PLAIN_MESSAGE);
		} else if (dealer.equals("computer3")) {
			computer3.getLabel().setText("DEALER");
			JOptionPane.showMessageDialog(null, "Computer 3 is a dealer" + "",
					"", JOptionPane.PLAIN_MESSAGE);
		}

		computer1.setVisibility(false);
		computer2.setVisibility(false);
		computer3.setVisibility(false);
		
		shuffle();
		// The game should be played in a proper term based on dealer.
		deal(dealer);
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
		} else if (userHandValue.getCard().getCardValue().toString() == "THREE") {
			return 3;
		} else if (userHandValue.getCard().getCardValue().toString() == "FOUR") {
			return 4;
		} else if (userHandValue.getCard().getCardValue().toString() == "FIVE") {
			return 5;
		} else if (userHandValue.getCard().getCardValue().toString() == "SIX") {
			return 6;
		} else if (userHandValue.getCard().getCardValue().toString() == "SEVEN") {
			return 7;
		} else if (userHandValue.getCard().getCardValue().toString() == "EIGHT") {
			return 8;
		} else if (userHandValue.getCard().getCardValue().toString() == "NINE") {
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

		if (selectCard == null) {
			userHand.setHasDrawnCardAlready(false);
			letComputerPlay(computer1);
			computer1.setHasDrawnCardAlready(false);
			letComputerPlay(computer2);
			computer2.setHasDrawnCardAlready(false);
			letComputerPlay(computer3);
			computer3.setHasDrawnCardAlready(false);
			return true; // just lost turn, no error needed to be shown
		}

		boolean isPlayable = selectCard.getCard().isPlayable(
				displayableDiscardPile.getDiscardCard().getCard());

		if (isPlayable) {
			if (selectCard.getCard().getCardValue().equals(CardValueEnum.WILD)) {
				selectCard.getCard().setWildColor(wildSelector());
			}

			DisplayableCard card = userHand.removeCard(false);
			displayableDiscardPile.setDiscardCard(card);
			userHand.setHasDrawnCardAlready(false);
			controlButton.setVisible(false);
			letComputerPlay(computer1);
			computer1.setHasDrawnCardAlready(false);
			letComputerPlay(computer2);
			computer2.setHasDrawnCardAlready(false);
			letComputerPlay(computer3);
			computer3.setHasDrawnCardAlready(false);
			// When computer finishes, user plays again.
			return isPlayable;
		}

		return isPlayable;
	}

	private void letComputerPlay(DisplayableHand computer) {

		if (displayableDiscardPile.getDiscardCard() == null) {
			System.out.print("null");
		}

		// Linear Search to find matching card to be discarded.
		for (int i = 0; i < computer.getTotalNumbCard(); i++) {

			DisplayableCard currentCardValue = computer.getCurrentCard(i);
			System.out.println("computer card value "
					+ currentCardValue.getCard().toString());

			boolean isPlayable = currentCardValue.getCard().isPlayable(
					displayableDiscardPile.getDiscardCard().getCard());

			if (isPlayable) {
				if (currentCardValue.getCard().getCardValue()
						.equals(CardValueEnum.WILD)) {
					computer.setWildCardValue(currentCardValue);
				}
				computer.removeComputerCard(currentCardValue);
				DisplayableCard card = computer.removeCard(false);
				displayableDiscardPile.setDiscardCard(card);
				break;
			} else if (i == computer.getTotalNumbCard() - 1 && !isPlayable
					&& !computer.hasDrawnCardAlready()) {
				// Gets a card and check for the condition AGAIN!!
				computer.addCard(displayableDeck.drawCard());
				computer.setHasDrawnCardAlready(true);
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

	public void checkState() {
		if (preGame) {
			determineDealer();
			gameSetup();
			preGame = false;
			controlButton.setVisible(false);
			controlButton.setText("Skip Turn");
		} else {
			if (userHand.hasDrawnCardAlready()) {
				playUserCard(null);
				controlButton.setVisible(false);
			} else {
				userHand.setHasDrawnCardAlready(true);
				userHand.addCard(displayableDeck.drawCard());
				controlButton.setVisible(true);
			}
		}
	}

	public void deckEmpty() {
		Object[] possibleValues = { "Restart", "Quit" };

		int selectedValue = JOptionPane.showOptionDialog(this,
				"Deck is empty, the winner of the game is: "
						+ "\n<HTML><font size=12>"+ findWinner() + 
						"</font></HTML>\n", "GAME OVER",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
						possibleValues[1]);
		if (selectedValue == 1) {
			System.exit(0);
		} else {
			try {
				restart();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void handEmpty(DisplayableHand winner) {
		Object[] possibleValues = { "Restart", "Quit" };

		int selectedValue = JOptionPane.showOptionDialog(this,
				"Congratulations on winning UNO : \n" +
						"<HTML><font size=12>"+ findWinner() + 
						"</font></HTML>\n", "GAME OVER",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
						possibleValues[1]);
		if (selectedValue == 1) {
			System.exit(0);
		} else {
			try {
				restart();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void restart() throws IOException, InterruptedException {
		StringBuilder cmd = new StringBuilder();
		cmd.append(System.getProperty("java.home") + File.separator + "bin"
				+ File.separator + "java ");
		for (String jvmArg : ManagementFactory.getRuntimeMXBean()
				.getInputArguments()) {
			cmd.append(jvmArg + " ");
		}
		cmd.append("-cp ")
				.append(ManagementFactory.getRuntimeMXBean().getClassPath())
				.append(" ");
		cmd.append(MainFrame.class.getName()).append(" ");
		Runtime.getRuntime().exec(cmd.toString());
		System.exit(0);
	}

	private String findWinner() {
		String winner = null;
		int maxPoints = Integer.MAX_VALUE;
		if (userHand.getTotalNumbCard() < maxPoints) {
			winner = userHand.getName();
			maxPoints = userHand.getTotalNumbCard();
		}
		if (computer1.getTotalNumbCard() < maxPoints) {
			winner = computer1.getName();
			maxPoints = computer1.getTotalNumbCard();
		}
		if (computer2.getTotalNumbCard() < maxPoints) {
			winner = computer2.getName();
			maxPoints = computer2.getTotalNumbCard();
		}
		if (computer3.getTotalNumbCard() < maxPoints) {
			winner = computer3.getName();
			maxPoints = computer3.getTotalNumbCard();
		}
		return winner;
	}

}