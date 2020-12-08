package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Systems.GameManager;
import Utility.LoadImage;
/**
 * This EndlessLand class inherits GameObject class
 * creating and displaying the Land of the game
 *
 */
public class EndlessLand extends GameObject{
	
	private BufferedImage sprite;
	private int x, y;
	int width;
	
	/**
	 * Constructor of the EndlessLand class
	 * setting the x,y positions of the land, and loading the 
	 * land's animation
	 */
	public EndlessLand()
	{
		x = 0;
		y = GameManager.GROUND_POSITION;
		sprite = LoadImage.loadImage("image/LandLayer.png");
		width = sprite.getWidth();
	}
	
	/**
	 * An override method that get called once per frame
	 * 
	 */
	@Override
	public void update() {
		move();
	}
	
	/**
	 * Method to make the land move to the left of the screen
	 */
	void move() {
		x -= GameManager.getScrollSpeed() * GameManager.getDeltaTime();
		if (x < -width)
		{
			x += width;
		}
	}

	/**
	 * An override method to display the land's animation by using Graphics
	 * drawImage
	 */
	@Override
	public void display(Graphics g) {
		g.drawImage(sprite, x, y, (width*2), sprite.getHeight(), null);
	}

}
