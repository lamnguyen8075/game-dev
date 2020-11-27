package GameObject;
import java.awt.Graphics;

/** This GameObject will be a bouncing obstacle 
 *  that constantly jumps up and down on the ground

 *  TODO add the necessary variables for jump velocity force & gravity
 *  TODO implement the behavior to make this class a bouncing obstacle
 *  Refer to the Player class on how to jump
 */
public class JumpingObstacle extends Obstacle implements KeyListener {

	private static final float JUMPFORCE = 1650; 					//yVelocity initial speed upon jump
	private static final float GRAVITY = -6000; 
	public static final int WIDTH = 40, HEIGHT = 75;
	private static float x = 50, y = GameManager.GROUND_POSITION; 
	private float yVel = 0; 										//player's Y speed	
	
	//0, 0 is top left corner
	//GameManager.PIXEL_WIDTH, GameManager.PIXEL_HEIGHT is bottom right corner
	public static float getX() { 
		return x; 
	}

	public static float getY() { 
		return y; 
	}

	public JumpingObstacle(int width, int height) {
		super(width, height);
	}

	// TODO implement the move method
	@Override
	void move() {	
		x -= GameManager.getScrollSpeed() * GameManager.getDeltaTime();
	}

	// TODO implement the display method
	@Override
	public void display(Graphics g) {

	}
	
	//TODO implement update to work with the jump method
	@Override
	public void update() {
		move();
		jump();
		checkPlayerCollision();
		checkExitScreen();
	}
	
	//gravity constantly increments y coordinate based on yVelocity
	void gravity() {
		y -= yVel * GameManager.getDeltaTime(); 
		
		if (y < GameManager.GROUND_POSITION - HEIGHT) //airborne decrement yVelocity
			yVel += GRAVITY * GameManager.getDeltaTime();
		else //grounded, set yVelocity to 0
		{
			yVel = 0;  //obstacle is grounded, no y velocity
			y = GameManager.GROUND_POSITION - HEIGHT;
		}
	}

	void jump() {
		if (y >= GameManager.GROUND_POSITION - HEIGHT) //obstacle is grounded 
			yVel = JUMPFORCE;  //Instantaneously set Y velocity in jump
	}
}
