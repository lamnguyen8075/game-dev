package GameObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Systems.GameManager;
/**
 * @author Bao
Main Responsibilities
1. Jump method for detecting input & shifting player's y position
2. Position of player coordinates which Obstacles use to detect collision
3. Player player Sprite animation
 */
public class Player extends GameObject implements KeyListener {

	//0, 0 is top left corner
	//GameManager.PIXEL_WIDTH, GameManager.PIXEL_HEIGHT is bottom right corner
	public static float getX() { return x; }
	public static float getY() { return y; }
	private static final float JUMPFORCE = 1650; //yVelocity initial speed upon jump
	private static final float GRAVITY = -6000; 
	
	public static final int WIDTH = 40, HEIGHT = 75;
	private static float x = 50, y = GameManager.GROUND_POSITION; //player coordinates
	private float yVel = 0; //player's Y speed
	
	@Override //do nothing
	public void keyReleased(KeyEvent arg0) {}

	@Override //do nothing
	public void keyTyped(KeyEvent arg0) {}
	   
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			jump();
	}
	
	//update gets called every frame
	public void update() {
		gravity(); 
	}
	
	void jump()
	{
		if (y >= GameManager.GROUND_POSITION - HEIGHT) //player is grounded 
			yVel = JUMPFORCE;  //Instantaneously set Y velocity in jump
	}
	 
	//gravity constantly increments y coordinate based on yVelocity
	void gravity() 
	{
		y -= yVel * GameManager.getDeltaTime(); 
		
		if (y < GameManager.GROUND_POSITION - HEIGHT) //airborne decrement yVelocity
			yVel += GRAVITY * GameManager.getDeltaTime();
		else //grounded, set yVelocity to 0
		{
			yVel = 0;  //player is grounded, no y velocity
			y = GameManager.GROUND_POSITION - HEIGHT;
		}
	}
	
	//render player 
	public void display(Graphics g) {
		g.drawRect((int)x, (int)y, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
}
