package cmsc519.team8.uno.data;

/**
 * Represents a single UNO card
 * 
 * @author Team8
 *
 */
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
	
	/**
	 * checks to see if this called can be played on top of other card
	 * @param otherCard - discarded card
	 * @return
	 */
	public boolean isPlayable(Card otherCard){
		if(this.cardValue.equals(CardValueEnum.WILD)){
			return true;
		}else if(this.cardValue.equals(otherCard.cardValue)){
			return true;
		}else if(this.cardColor.equals(otherCard.cardColor)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString(){
		return this.cardValue + " " + this.cardColor;
	}
	
		public String getCardImg(){
		return "UNO" + this.cardColor + " " +this.cardValue;
	}
}

