package GameObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import Systems.GameManager;
import Utility.LoadImage;
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
	     
	public static final int WIDTH = 96, HEIGHT = 112;
	private static float x = 50, y = GameManager.GROUND_POSITION; //player coordinates
	private float yVel = 0; //player's Y speed
	float timer = 0, spriteUpdateTime = 0.1f;
	
	private BufferedImage sprites[] = new BufferedImage[6];
	private int spriteIndex = 0;
	
	/**
	 * Constructor of the Player class that uses LoadImage to create animation effect 
	 */
	public Player()
	{
		for (int i = 0; i < sprites.length; i++)
			sprites[i] = LoadImage.loadImage("image/player" + i + ".png");
	}
	
	@Override //do nothing
	public void keyReleased(KeyEvent arg0) {}

	@Override //do nothing
	public void keyTyped(KeyEvent arg0) {}
	
	/**
	 * An override method to get the player jumps when pressing the key
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			jump();		
	}

	/**
	 * Method that get called every frame 
	 * applying gravity on the player
	 */
	public void update() {
		gravity(); 
		timer += GameManager.getDeltaTime();
		
		if (timer > spriteUpdateTime)
		{
			timer -= spriteUpdateTime;
			spriteIndex++;
			if (spriteIndex >= sprites.length)
				spriteIndex -= sprites.length;
		}
	}
	
	/**
	 * Method to make the player jump
	 */
	void jump()
	{
		if (y >= GameManager.GROUND_POSITION - HEIGHT) //player is grounded 
			yVel = JUMPFORCE;  //Instantaneously set Y velocity in jump
	}
	 
	//
	/**
	 * method to apply gravity to the player when player is grounded and airborne
	 * gravity constantly increments y coordinate based on yVelocity
	 */
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
	
	/**
	 * Method to display the player by using Graphics drawImage
	 */
	public void display(Graphics g) {
		g.drawImage(sprites[spriteIndex], (int)x, (int)y, WIDTH, HEIGHT, null);
	}
}
