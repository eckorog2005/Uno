package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cmsc519.team8.uno.data.Card;
import cmsc519.team8.uno.data.Hand;

public class DisplayableHand extends JPanel {
	
	/**
	 * uuid
	 */
	private static final long serialVersionUID = -7808675344590554340L;
	
	private final int HAND_LENGTH = 500;
	private final int CARD_WIDTH = 100;
	private final int CARD_SELECT_HEIGHT = 20; 
	private boolean isUser = false;
	private boolean inverted = false;
	private boolean switchX = false;
	private int topX = 0;
	private int topY = 0;
	private BufferedImage image = null;
	private ArrayList<DisplayableCard> cards;

	DisplayableHand(boolean isUser, int topX, int topY, boolean inverted, 
			boolean switchX){
		hand = new Hand();
		this.isUser = isUser;
		this.inverted = inverted;
		this.switchX = switchX;
		this.topX = topX;
		this.topY = topY;
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
		
		addMouseListener(new MouseController());
	}
	
	private Hand hand;

	public DisplayableCard removeCard() {
		DisplayableCard card = new DisplayableCard(hand.getSelectedCard());
		hand.removeCard(card.getCard());
		repaint();
		return card;
	}

	public void addCard(DisplayableCard card) {
		hand.addCard(card.getCard());
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
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
		
		cards = null;
		cards = new ArrayList<DisplayableCard>();
		
		for(Card card : hand.getCards()){
			if(isUser){
				DisplayableCard dCard = new DisplayableCard(card);
				cards.add(dCard);
				if(switchX){
					dCard.displayCard(g, topX + x, topY + y, 90);
				}else{
					int actualY = y + CARD_SELECT_HEIGHT;
					
					
					if(card.equals(hand.getSelectedCard())){
						actualY -= CARD_SELECT_HEIGHT;
					}
					
					dCard.displayCard(g, x, actualY, 0);
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
	
	
	//needs to detect if its the users turn somehow
	private class MouseController extends MouseAdapter
    {
        public void mouseClicked(MouseEvent me)
        {           
            Point point = me.getPoint();
            DisplayableCard prev = null;

            for(DisplayableCard card : cards){
            	if(card.getUserCardRectangle().contains(point)){
            		prev = card;
            	}else if(prev != null && 
            			!card.getUserCardRectangle().contains(point)){
            		if(prev.getCard().equals(hand.getSelectedCard())){
            			if(!((UnoGamePanel)getParent()).playUserCard(prev)){
            				//display warning
            				JOptionPane.showMessageDialog(
         	    	    		   null, 
         	    	    		   "Unable to play selected card, Please try "
         	    	    		   + "again", "UNO Error", 
         	    	    		   JOptionPane.ERROR_MESSAGE);
            				return;
            			}
            		}else{
            			hand.setSelectedCard(prev.getCard());
            			repaint();
            			return;
            		}
            	}
            }
            
            if(prev != null){
            	if(prev.getCard().equals(hand.getSelectedCard())){
        			if(!((UnoGamePanel)getParent()).playUserCard(prev)){
        				//display warning
        				JOptionPane.showMessageDialog(
     	    	    		   null, 
     	    	    		   "Unable to play selected card, Please try "
     	    	    		   + "again", "UNO Error", 
     	    	    		   JOptionPane.ERROR_MESSAGE);
        				return;
        			}
            	}else{
            		hand.setSelectedCard(prev.getCard());
        			repaint();
            	}
        	}
        }
    }
}
