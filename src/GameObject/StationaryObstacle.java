package GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Systems.GameManager;
import Utility.LoadImage;

public class StationaryObstacle extends Obstacle {

	private BufferedImage sprites[] = new BufferedImage[4];
	private int spriteIndex = 0;
	private float spriteSwapInterval = .15f, timer = 0;
	
	public StationaryObstacle(int width, int height) {
		super(width, height);
		for (int i = 0; i < sprites.length; i++)
			sprites[i] = LoadImage.loadImage("image/mushroom" + i + ".png");
	}
	
	@Override
	public void update()
	{
		super.update();
		changeSpriteSheet();
	}
	
	void changeSpriteSheet()
	{
		timer += GameManager.getDeltaTime();
		if (timer > spriteSwapInterval)
		{
			timer -= spriteSwapInterval;
			spriteIndex++;
			if (spriteIndex >= sprites.length)
				spriteIndex -= sprites.length;
		}
	}
 
	@Override
	void move() {
		x -= GameManager.getScrollSpeed() * GameManager.getDeltaTime();
	}

	@Override
	public void display(Graphics g) {
		
		g.drawImage(sprites[spriteIndex], (int)x, (int)y, (int)(width*1.2f), (int)(height*1.2f), null);
//		g.drawRect(x, y, width, height);
//		g.setColor(Color.red);
//		g.fillRect(x, y, width, height);
	}
}
