package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import cmsc519.team8.uno.data.Card;
import cmsc519.team8.uno.data.Hand;

public class DisplayableHand {
	
	private final int HAND_LENGTH = 500;
	private final int CARD_SELECT_HEIGHT = 50; 
	private final int CARD_WIDTH = 100;
	private boolean isUser = false;
	private BufferedImage image = null;

	DisplayableHand(boolean isUser){
		hand = new Hand();
		this.isUser = isUser;
		if(!isUser){
			try {
				image = 
						ImageIO.read(getClass().getResource
								("/images/unoCards/UNO BACK.JPG"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private Hand hand;

	public DisplayableCard removeCard() {
		return new DisplayableCard(hand.getSelectedCard());
	}

	public void addCard(DisplayableCard card) {
		hand.addCard(card.getCard());
	}
	
	public void displayHand(Graphics g, int topX, int topY, boolean inverted, 
			boolean switchX){
		int spacing = 45;
		int x = 0;
		int y = 0;
		int handSize = hand.getCards().size();
		
		do{
			spacing -= 5;
			
			//figure out starting placement
			if(handSize % 2 == 0){
				if(switchX){
					y = (HAND_LENGTH/2)-((handSize/2 * spacing) + 
							((CARD_WIDTH - spacing)/2));
				}else{
					x = (HAND_LENGTH/2)-((handSize/2 * spacing) + 
							((CARD_WIDTH - spacing)/2));
				}
			}else{
				if(switchX){
					y = (HAND_LENGTH/2)-((handSize/2 * spacing) + 
							(CARD_WIDTH/2));
				}else{
					x = (HAND_LENGTH/2)-((handSize/2 * spacing) + 
							(CARD_WIDTH/2));
				}
			}
		}while((x < 0 || y < 0) && spacing > 0);
		
		if(inverted){
			if(switchX){
				y += (spacing * (handSize-1));
			}else{
				x += (spacing * (handSize-1));
			}
		}
		
		for(Card card : hand.getCards()){
			if(isUser){
				DisplayableCard dCard = new DisplayableCard(card);
				if(switchX){
					dCard.displayCard(g, topX + x, topY + y, 90);
				}else{
					dCard.displayCard(g, topX + x, topY + y, 0);
				}
			}else{
				if(switchX){
					if (inverted){
						g.drawImage(ImageUtils.rotate(image,Math.toRadians(-90)),
								topX + x, topY + y, 140, 100, null);
					}else{
						g.drawImage(ImageUtils.rotate(image,Math.toRadians(90)),
								topX + x, topY + y, 140, 100, null);
					}
				}else{
					if (inverted){
						g.drawImage(ImageUtils.rotate(image,Math.toRadians(180)),
								topX + x, topY + y, 100, 140, null);
					}else{
						g.drawImage(image, topX + x, topY + y, 100, 140, null);
					}
				}
			}
			if(inverted){
				if(switchX){
					y -= spacing;
				}else{
					x -= spacing;
				}
			}else{
				if(switchX){
					y += spacing;
				}else{
					x += spacing;
				}
			}
		}
	}
}
