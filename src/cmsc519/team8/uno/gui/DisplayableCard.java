package cmsc519.team8.uno.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import cmsc519.team8.uno.data.Card;

public class DisplayableCard{

	DisplayableCard(Card card){
		this.card = card;

		String filename = "/images/unoCards/UNO" + card.getCardColor() +
				" " + card.getCardValue() + ".png";
		try {
			image = 
					ImageIO.read(getClass().getResource(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Card card;
	private BufferedImage image;
	private int x;
	private int y;

	public BufferedImage getImage(){
		return image;
	}

	public Card getCard() {
		return card;
	}

	public Rectangle getUserCardRectangle(){
		return new Rectangle(x,y,100,140);
	}

	public void displayCard(Graphics g, int x, int y, double rotation){		
		this.x = x;
		this.y = y;
		if(rotation == 90){
			g.drawImage(ImageUtils.rotate(image,Math.toRadians(rotation)),
					x, y, 140, 100, null);
		}else{
			g.drawImage(image, x, y, 100, 140, null);
		}
	}
}
