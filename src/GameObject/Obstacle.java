package GameObject;
import Scenes.MainMenuScene;
import Systems.GameManager;

/**
 * @author Bao
 * Main responsibilities
 */
public abstract class Obstacle extends GameObject
{
	protected int x, y, width, height; 
	abstract void move();

	
	public Obstacle(int width, int height) {
		this.width = width;
		this.height = height;
		x = GameManager.PIXEL_WIDTH + width;
		y = GameManager.GROUND_POSITION - height;
	}
	
	//Gets called once per frame
	//fps is capped at 60 but is variable depending on computer performance
	public void update() {
		move();
		checkPlayerCollision();
		checkExitScreen();
	}
	
	protected void checkExitScreen()
	{
		if (x + width < 0)
			destroy();
	}
	
	
	protected void checkPlayerCollision()
	{
		if (withinXRange() && withinYRange())
		{
			//TODO: switch to the main game panel
			GameManager.resetScene();
			GameManager.setInGameScene(false);
			new MainMenuScene();
//			GameManager.gameIsRunning = false;
		}
	}
	
	private boolean withinXRange()
	{
		float obstacleLeftEdge = x;
		float obstacleRightEdge = x + width;
		
		float playerLeftEdge = Player.getX();
		float playerRightEdge = Player.getX() + Player.WIDTH;
		
		if (obstacleLeftEdge <= playerRightEdge && obstacleRightEdge >= playerLeftEdge)
			return true;
		return false;
	}
	
	private boolean withinYRange()
	{
		float obstacleTopEdge = y;
		float obstacleBotEdge = y + height;
		float playerTopEdge = Player.getY();
		float playerBotEdge = Player.getY() + Player.HEIGHT;
		
		if (obstacleTopEdge < playerBotEdge && obstacleBotEdge > playerTopEdge)
			return true;
		return false;
	}
}
