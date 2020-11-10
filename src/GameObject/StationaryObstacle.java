package GameObject;

import java.awt.Color;
import java.awt.Graphics;

import Systems.GameManager;

public class StationaryObstacle extends Obstacle {

	public StationaryObstacle(int width, int height) {
		super(width, height);
	}

	@Override
	void move() {
		x -= GameManager.getScrollSpeed() * GameManager.getDeltaTime();
	}

	@Override
	public void display(Graphics g) {
		g.drawRect(x, y, width, height);
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}
