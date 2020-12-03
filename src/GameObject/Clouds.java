package GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import Systems.GameManager;
import Utility.LoadImage;

public class Clouds extends Obstacle{

	private int yOffset = 500; //y position from 0
	private float fluctuationTime = .8f;  //time to reach min/max scale
	private float minScale = 1, maxScale = 1.12f; 
	float speed = GameManager.getScrollSpeed()/2; 
	
	private float currentScale;
	private boolean scaleUp = true;
	private BufferedImage cloudImage;

	public Clouds(int width, int height) {
		super(width, height);
		cloudImage = LoadImage.loadImage("image/cloud.png");
		currentScale = minScale;
	}
		
	@Override
	public void update() {
		move();
	}

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
	
	/*
	 * Move method to make it move
	 */
	@Override
	public void move() {
		//x -= (GameManager.getScrollSpeed()/2)*(GameManager.getDeltaTime()/2);;
		x -= speed * GameManager.getDeltaTime();
	}
}
