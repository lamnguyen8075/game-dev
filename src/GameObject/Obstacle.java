package GameObject;
import Scenes.MainMenuScene;
import Systems.GameManager;

/**
 * The Obstacle program inherits from GameObject and it's the 
 * parent class for all Obstacles. 
 * Main responsibilities:
 * - draw it self in the panel and move the coordinates in update()
 * - check if there is collision and allow to call the main menu panel if it does
 */
public abstract class Obstacle extends GameObject
{
	protected int x, y, width, height; 
	abstract void move();

	/**
	 * Constructor of Obstacle class
	 * @param width - width of the Obstacle
	 * @param height - height of the Obstacle
	 */
	public Obstacle(int width, int height) {
		this.width = width;
		this.height = height;
		x = GameManager.PIXEL_WIDTH + width;
		y = GameManager.GROUND_POSITION - height;
	}
	
	//Gets called once per frame
	//fps is capped at 60 but is variable depending on computer performance
	/**
	 * Method that get called once per frame
	 * call the move() method, checkPalyerCollision(), and checkExitScene()
	 */
	public void update() {
		move();
		checkPlayerCollision();
		checkExitScreen();
	}
	
	/**
	 * Method to call the destroy() method to check when the obstacle is 
	 * out of screen
	 */
	protected void checkExitScreen()
	{
		if (x + width < 0)
			destroy();
	}
	
	/**
	 * Method to check if the player hits the obstacle and 
	 * switch to the main menu scene panel if there is collision
	 */
	protected void checkPlayerCollision()
	{
		if (withinXRange() && withinYRange())
		{
			//TODO: switch to the main game panel
			GameManager.resetScene();
			GameManager.setInGameScene(false);
			new MainMenuScene();
			//GameManager.gameIsRunning = false;
		}
	}
	 
	/**
	 * 
	 * @return
	 */
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
	
	/**
	 * 
	 * @return
	 */
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
