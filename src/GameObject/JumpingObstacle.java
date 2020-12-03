package GameObject;
import java.awt.Color;
import java.awt.Graphics;
import Systems.GameManager;

/** This GameObject will be a bouncing obstacle 
 *  that constantly jumps up and down on the ground
 *  TODO add the necessary variables for jump velocity force & gravity
 *  TODO implement the behavior to make this class a bouncing obstacle
 *  Refer to the Player class on how to jump
 */
public class JumpingObstacle extends Obstacle {

	private static final float JUMPFORCE = 1000; 					//yVelocity initial speed upon jump
	private static final float GRAVITY = -6000; 
	public static final int WIDTH = 60, HEIGHT = 85;
	private static float x = GameManager.PIXEL_WIDTH, y = GameManager.GROUND_POSITION; 
	private float yVel = 0; 										//player's Y speed	
	float speed = GameManager.getScrollSpeed()/8;

	public JumpingObstacle(int width, int height) {
		super(width, height);
	}
	
	//TODO implement update to work with the jump method
	@Override
	public void update() {
		move();
		gravity();
		
	}
	
	// TODO implement the move method
	@Override
	void move() {
		x -= speed * GameManager.getDeltaTime();
	}
	
	//gravity constantly increments y coordinate based on yVelocity
	void gravity() {
		y -= yVel * GameManager.getDeltaTime(); 
//		yVel = 0;
		if (y < GameManager.GROUND_POSITION - HEIGHT) //airborne decrement yVelocity
			yVel += GRAVITY * GameManager.getDeltaTime();
		else //grounded, set yVelocity to 0
		{
			y = GameManager.GROUND_POSITION - HEIGHT;
			yVel = JUMPFORCE;
			System.out.println(yVel);
		}
	}
//
//	void jump() {
//		if (y >= GameManager.GROUND_POSITION - HEIGHT) //obstacle is grounded 
//			yVel = JUMPFORCE;  //Instantaneously set Y velocity in jump
//	}
	
	// TODO implement the display method 
	@Override
	public void display(Graphics g) {
		g.drawRect((int)x, (int)y, WIDTH, HEIGHT);
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}
}