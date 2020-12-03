package GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Systems.GameManager;
import Utility.LoadImage;

public class EndlessLand extends GameObject{
	
	private BufferedImage sprite;
	private int x, y;
	int width;
	
	public EndlessLand()
	{
		x = 0;
		y = GameManager.GROUND_POSITION;
		sprite = LoadImage.loadImage("image/LandLayer.png");
		width = sprite.getWidth();
	}

	@Override
	public void update() {
		move();
	}
	
	void move() {
		x -= GameManager.getScrollSpeed() * GameManager.getDeltaTime();
		if (x < -width)
		{
			x += width;
		}
	}


	@Override
	public void display(Graphics g) {
		g.drawImage(sprite, x, y, (width*2), sprite.getHeight(), null);
	}

}
