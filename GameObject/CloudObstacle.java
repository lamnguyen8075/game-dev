package GameObject;

import java.awt.Color;
import java.awt.Graphics;

import Systems.GameManager;

public class CloudObstacle extends Obstacle {

	public CloudObstacle(int width, int height) {
		super(width,height);
		
	}
	void setHeight(int height) {
		this.height = height;
	}
	
	@Override 
	public void update() {
		move();
	}
	
	@Override
	void move() {
		x -= (GameManager.getScrollSpeed()/2) * (GameManager.getDeltaTime()/2);
	}

	@Override
	public void display(Graphics g) {
		g.drawRect(x, y, width, height);
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}
