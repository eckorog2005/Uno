package cmsc519.team8.uno;

public class Card {

	public Card(){
		this.cardValue = CardValueEnum.WILD;
		this.cardColor = CardColorEnum.BLACK;
	}
	
	public Card(CardValueEnum value, CardColorEnum color){
		this.cardValue = value;
		this.cardColor = color;
	}
	
	private CardValueEnum cardValue;
	private CardColorEnum cardColor;
	
	public CardValueEnum getCardValue() {
		return cardValue;
	}

	public void setCardValue(CardValueEnum cardValue) {
		this.cardValue = cardValue;
	}

	public CardColorEnum getCardColor() {
		return cardColor;
	}

	public void setCardColor(CardColorEnum cardColor) {
		this.cardColor = cardColor;
	}
	
	
}
