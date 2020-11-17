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
public class JumpingObstacle extends Obstacle{
	private static final float JUMPFORCE = 1650; //yVelocity initial speed upon jump
	public JumpingObstacle(int width, int height) {
		super(width, height);
	}
	
	public void setY(int valueY) {
		y = valueY;
	}

	// TODO implement the move method
	@Override
	void move() {
		x -= GameManager.getScrollSpeed() * GameManager.getDeltaTime();
	}

	// TODO implement the display method
	@Override
	public void display(Graphics g) {
		g.drawRect(x, y, width, height);
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
	
	//TODO implement update to work with the jump method
	@Override
	public void update()
	{
		
	}
	
	// TODO implement a jump method
	void jump()
	{
		if (y == GameManager.GROUND_POSITION) //If obstacle touching the ground 
			setY(1650);  //Instantaneously set Y velocity in jump
	}}
