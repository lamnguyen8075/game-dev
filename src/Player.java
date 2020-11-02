/**
 * @author Bao
Main Responsibilities
1. Jump method for detecting input & shifting player's y position
2. Position of player coordinates which Obstacles use to detect collision
3. Player player Sprite animation
 */

public class Player extends GameObject {

	private static int x, y; //player coordinates
	
	public static int getX()
	{
		return x;
	}
	
	public static int getY() 
	{
		return y;
	}
	
	public void update()
	{
		System.out.println("Player testing");
	}
}
