package cmsc519.team8.uno.data;

import java.util.ArrayList;

public class Hand {

	public Hand() {
		cards = new ArrayList<Card>();
	}

	private ArrayList<Card> cards;
	private Card selectedCard;

	public Card getSelectedCard() {
		return selectedCard;
	}

	public void setSelectedCard(Card selectedCard) {
		this.selectedCard = selectedCard;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void removeCard(Card card) {
		cards.remove(card);
	}

	/**
	 * returns true and changes selected card if a card in hand can be played,
	 * else false and selected card is null if no cards can be played.
	 * 
	 * @param playableCard
	 * @return
	 */
	public boolean findBestCard(Card playableCard) {
		for (Card card : cards) {
			if (card.isPlayable(playableCard)) {
				selectedCard = card;
				return true;
			}
		}
		selectedCard = null;
		return false;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void printHand() {
		for (int i = 0; i < cards.size(); i++) {
			System.out.println(cards.get(i).toString());
		}
	}

}