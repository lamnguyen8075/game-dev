package GameObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Systems.GameManager;
import Utility.LoadImage;

/** This GameObject will be a bouncing obstacle 
 *  that constantly jumps up and down on the ground
 *  implement the behavior to make this class a bouncing obstacle
 *  Refer to the Player class on how to jump
 */
public class JumpingObstacle extends Obstacle {

	private static final float JUMPFORCE = 500; 					//yVelocity initial speed upon jump
	private static final float GRAVITY = -2500; 
	public static final int WIDTH = 60, HEIGHT = 85;
	private float yVel = 0; 										//player's Y speed	
	float speed = GameManager.getScrollSpeed();
	private float spriteSwapInterval = .05f, timer = 0;
	BufferedImage sprites[] = new BufferedImage[8];
	private int spriteIndex = 0;
	
	/**
	 * Constructor of the JumpingObstacle class
	 * loading the Obstacle's animation
	 * @param width - width of the jumping obstacle
	 * @param height - height of the jumping obstacle
	 */
	public JumpingObstacle(int width, int height) {
		super(width, height);
		for (int i = 0; i < sprites.length; i++)
			sprites[i] = LoadImage.loadImage("image/bat" + i + ".png");
	}
	
	
	/**
	 * An override method that get called once per frame,
	 * update to make the jumping obstacles move,jump up and down
	 */
	@Override
	public void update() {
		super.update();;
		gravity();
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
 
	
	/**
	 * An override method to change the x coordinate to get the
	 * jumping obstacle to move the the left of the scene
	 */
	@Override
	void move() {
		x -= speed * GameManager.getDeltaTime();
	}
	
	//gravity constantly increments y coordinate based on yVelocity
	/**
	 * Method to apply gravity to the jumping obstacle so it will hit
	 * the ground after every jump. 
	 */
	void gravity() {
		y -= yVel * GameManager.getDeltaTime(); 

		if (y < GameManager.GROUND_POSITION - HEIGHT) //airborne decrement yVelocity
			yVel += GRAVITY * GameManager.getDeltaTime();
		else 
		{
			y = GameManager.GROUND_POSITION - HEIGHT;
			yVel = JUMPFORCE;
		}
	}
	
	/**
	 * An override method to display the jumping obstacles animation by using
	 * Graphic drawImage.
	 */
	@Override
	public void display(Graphics g) {
		g.drawImage(sprites[spriteIndex], (int)x, (int)y, (int)(width*1.5f), (int)(height*1.5f), null);
//		g.drawRect((int)x, (int)y, WIDTH, HEIGHT);
//		g.setColor(Color.green);
//		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
}