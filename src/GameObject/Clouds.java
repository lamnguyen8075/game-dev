package GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import Systems.GameManager;
import Utility.LoadImage;
/**
 * This Clouds class inherits the Obstacles class to create and 
 * display the cloud obstacle
 */
public class Clouds extends Obstacle{

	private int yOffset = 500; //y position from 0
	private float fluctuationTime = .8f;  //time to reach min/max scale
	private float minScale = 1, maxScale = 1.12f; 
	float speed = GameManager.getScrollSpeed()/2; 
	
	private float currentScale;
	private boolean scaleUp = true;
	private BufferedImage cloudImage;
	
	/**
	 * Constructor of the Clouds class
	 * loading the cloud animation
	 * @param width - width of the cloud
	 * @param height - height of the cloud
	 */
	public Clouds(int width, int height) {
		super(width, height);
		cloudImage = LoadImage.loadImage("image/cloud.png");
		currentScale = minScale;
	}
	
	/**
	 * An override method that get called once per frame
	 * update to make the cloud moves to the left of the screen
	 */
	@Override
	public void update() {
		move();
	}
	
	/**
	 * An override method that display the cloud obstacle by using Graphics 
	 * drawImage and add the cloud changing size effect
	 */
	@Override
	public void display(Graphics g) {
		float scaling = maxScale - minScale;
		if (scaleUp)
		{
			currentScale += scaling/fluctuationTime * GameManager.getDeltaTime();
			if (currentScale > maxScale)
				scaleUp = false;
		}
		else
		{
			currentScale -= scaling/fluctuationTime * GameManager.getDeltaTime();
			if (currentScale < minScale)
				scaleUp = true;
		}
		int width = (int)(cloudImage.getWidth() * currentScale);
		int height = (int)(cloudImage.getHeight() * currentScale);
		g.drawImage(cloudImage, x + cloudImage.getWidth(), y - yOffset, width, height, null);
	}
	
	/**
	 *  Method to make the cloud moves with half of the speed of other objects
	 */
	@Override
	public void move() {
		//x -= (GameManager.getScrollSpeed()/2)*(GameManager.getDeltaTime()/2);;
		x -= speed * GameManager.getDeltaTime();
	}
}
