package cmsc519.team8.uno.gui;

import java.awt.Graphics;

import cmsc519.team8.uno.data.Card;
import cmsc519.team8.uno.data.Hand;

public class DisplayableHand {

	DisplayableHand(){
		hand = new Hand();
	}
	
	private Hand hand;

	public DisplayableCard removeCard() {
		return new DisplayableCard(hand.getSelectedCard());
	}

	public void addCard(DisplayableCard card) {
		hand.addCard(card.getCard());
	}
	
	public void displayHand(Graphics g, int x, int y){
		for(Card card : hand.getCards()){
			DisplayableCard dCard = new DisplayableCard(card);
			dCard.displayCard(g, x, y);
		}
	}
}
