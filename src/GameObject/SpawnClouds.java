package GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import Systems.GameManager;
import Utility.LoadImage;

public class SpawnClouds extends Obstacle{
	
	private BufferedImage cloudImage1;
	private BufferedImage cloudImage2;
	
	public SpawnClouds(int width, int height) {
		super(width, height);
		cloudImage1 = LoadImage.loadImage("image/cloud1.jpg");
		cloudImage2 = LoadImage.loadImage("image/cloud2.jpg");
		
	}

	@Override
	public void update() {
		//call move() method 	
		move();
	}

	@Override
	public void display(Graphics g) {
		/*
		g.setColor(Color.WHITE);
		g.drawOval(10, 10, 20 ,30);
		for(int i = 0; i < 30; i++)
		{
			g.fillOval(pickRandom(0, 80),pickRandom(0, 10),pickRandom(1,3), pickRandom(1, 5));
		}
		*/
		g.drawImage(cloudImage1, x, y, null);
		g.drawImage(cloudImage2, x + 200, y + 10, null);
	}
	
	/*
	 * Move method to make it move
	 */
	@Override
	public void move() {
		x -= (GameManager.getScrollSpeed()/2)*(GameManager.getDeltaTime()/2);;
	}
	
	/*
	public int pickRandom(int min, int max) {
		return (int)Math.random()*(min-max+1) + min;
	}
	*/

	
}
