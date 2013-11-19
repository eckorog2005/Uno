package cmsc519.team8.uno.test;

import cmsc519.team8.uno.data.Deck;
import cmsc519.team8.uno.data.Hand;


public class TestUno {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck deck = new Deck();
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();
		
		System.out.println("normal deck ============");
		deck.printDeck();
		deck.shuffle();
		System.out.println("shuffled deck ============");
		deck.printDeck();
		
		for(int i = 0; i < 7; i++){
			hand1.addCard(deck.drawCard());
			hand2.addCard(deck.drawCard());
		}
		
		System.out.println("hand 1 ============");
		hand1.printHand();
		System.out.println("hand 2 ============");
		hand2.printHand();
	}

}
