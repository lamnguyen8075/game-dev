package GameObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Systems.GameManager;
/**
 * The ScoreText class inherits GameObject, and used to get the score
 * of the running player and display it.
 */
public class ScoreText extends GameObject {
	
	int score;
	String scoreText;
	/**
	 * An override method to update the score of the player
	 */
	@Override
	public void update() {
		score = (int)(GameManager.getTime() * 10);
		scoreText = "Score: " + score;
		// TODO Auto-generated method stub
	}
	/**
	 * An override method to display the score of the player
	 */
	@Override
	public void display(Graphics g) {
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.setColor(Color.black);
		g.drawString(scoreText, GameManager.PIXEL_WIDTH/2 - 50, 55);
	}

}
