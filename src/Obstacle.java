/**
 * @author Bao
 * Main responsibilities
 */
public class Obstacle extends GameObject{
	
	private int x, y, width, height; 
	
	public Obstacle(int x, int y, int width, int height)
	{
		
	}
	
	public void update()
	{
		if (playerCollision()) //wip
			System.out.println("GAME OVER");
	}
	
	public boolean playerCollision()
	{
		if (x == Player.getX() && y == Player.getY()) //wip not taking into pixel height & width
			return true;
		return false;
	}
}
