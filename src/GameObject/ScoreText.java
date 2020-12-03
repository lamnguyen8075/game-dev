package GameObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Systems.GameManager;

public class ScoreText extends GameObject {
	
	int score;
	String scoreText;

	@Override
	public void update() {
		score = (int)(GameManager.getTime() * 10);
		scoreText = "Score: " + score;
		// TODO Auto-generated method stub
	}

	@Override
	public void display(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.setColor(Color.black);
		g.drawString(scoreText, GameManager.PIXEL_WIDTH/2 - 50, 55);
	}

}
