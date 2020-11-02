import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author Bao
 * Manages the entire game loop by
   - Generating Obstacles on set intervals
   - Keeping track of every GameObject's update calls
   - sets pace of the game with scrollSpeed & multipliers
   - Score to keep track of progress
   - Increments difficulty by increasing the scroll speed & spawn frequency over time
 */
public class GameManager implements Runnable{
	
	public static ArrayList<GameObject> gameObjects;
	public static float gameSpeedMultiplier = 1.0f; 
	public static float scrollSpeed = 30; //pixels per second
	public int fps = 60; //cap

	private Thread thread;
	
	public static void main(String[] args) {
		gameObjects = new ArrayList<GameObject>(); 
		new GameManager();
		new Player();
	}
	
	public GameManager()
	{
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * The thread running the game loop emulating a call every frame
	 */
	@Override
	public void run() {
		while (true) 
		{
			try 
			{
				Thread.sleep(TimeUnit.SECONDS.toNanos(1/fps));
				for (GameObject g : gameObjects)
					g.update();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//wip
	void spawnObstacles()
	{
		
	}
}
