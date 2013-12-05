package cmsc519.team8.uno.data;

import java.util.ArrayList;
import java.util.Random;

/**
 * This deck represents an Uno deck, not a generic one
 * 
 * @author Team8
 * 
 */
public class Deck {

	public Deck() {
		setupDeck();
	}

	private void setupDeck() {
		cards = new ArrayList<Card>();
		addCardsByColor(CardColorEnum.BLUE);
		addCardsByColor(CardColorEnum.GREEN);
		addCardsByColor(CardColorEnum.RED);
		addCardsByColor(CardColorEnum.YELLOW);
		cards.add(new Card(CardValueEnum.WILD, CardColorEnum.BLACK));
		cards.add(new Card(CardValueEnum.WILD, CardColorEnum.BLACK));
		cards.add(new Card(CardValueEnum.WILD, CardColorEnum.BLACK));
		cards.add(new Card(CardValueEnum.WILD, CardColorEnum.BLACK));
	}

	private void addCardsByColor(CardColorEnum color) {
		cards.add(new Card(CardValueEnum.ZERO, color));
		cards.add(new Card(CardValueEnum.ONE, color));
		cards.add(new Card(CardValueEnum.TWO, color));
		cards.add(new Card(CardValueEnum.THREE, color));
		cards.add(new Card(CardValueEnum.FOUR, color));
		cards.add(new Card(CardValueEnum.FIVE, color));
		cards.add(new Card(CardValueEnum.SIX, color));
		cards.add(new Card(CardValueEnum.SEVEN, color));
		cards.add(new Card(CardValueEnum.EIGHT, color));
		cards.add(new Card(CardValueEnum.NINE, color));
		cards.add(new Card(CardValueEnum.ONE, color));
		cards.add(new Card(CardValueEnum.TWO, color));
		cards.add(new Card(CardValueEnum.THREE, color));
		cards.add(new Card(CardValueEnum.FOUR, color));
		cards.add(new Card(CardValueEnum.FIVE, color));
		cards.add(new Card(CardValueEnum.SIX, color));
		cards.add(new Card(CardValueEnum.SEVEN, color));
		cards.add(new Card(CardValueEnum.EIGHT, color));
		cards.add(new Card(CardValueEnum.NINE, color));
	}

	private ArrayList<Card> cards;
	public final int DECK_SIZE = 82;

	public Card drawCard() {
		if (!cards.isEmpty()) {
			return cards.remove(0);
		} else {
			return null;
		}
	}

	public void shuffle() {
		if (cards.size() < DECK_SIZE) {
			setupDeck();
		}

		Card temp;
		Random rand = new Random();
		for (int i = cards.size() - 1; i >= 1; i--) {
			temp = cards.get(i);
			int randInt = Math.abs(rand.nextInt()) % i;
			cards.set(i, cards.get(randInt));
			cards.set(randInt, temp);
		}
	}

	public void shuffle(Card card) {
		cards.add(card);

		Card temp;
		Random rand = new Random();
		for (int i = cards.size() - 1; i >= 1; i--) {
			temp = cards.get(i);
			int randInt = Math.abs(rand.nextInt()) % i;
			cards.set(i, cards.get(randInt));
			cards.set(randInt, temp);
		}
	}

	public void printDeck() {
		for (int i = 0; i < cards.size(); i++) {
			System.out.println(cards.get(i).toString());
		}
	}

	public int getSize() {
		return cards.size();
	}

}