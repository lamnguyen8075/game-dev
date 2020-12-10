package GameObject;
import java.awt.Graphics;
import java.util.Random;

import Systems.GameManager;

/**
 * An invisible GameObject which manages the spawning behavior of Obstacles
 * Spawns obstacles randomly within a minimum and maximum time range
 * Randomizes types of obstacles spawned
 */
public class SpawnManager extends GameObject {
	
	float obstacleMinTime = .7f,obstacleMaxTime = 1; //interval of obstacle spawn
	float obstacleSpawnTime, obstacleTimer = 0;
	
	float cloudSpawnTime = 4.5f; //spawn cloud every 4.5 seconds
	float cloudTimer = cloudSpawnTime;
	int obstacleCounter = 0;
	private Random rand;
	
	private static final int SMALL_OBSTACLE_WIDTH = 50, SMALL_OBSTACLE_HEIGHT = 50;
	private static final int LARGE_OBSTACLE_WIDTH = 50, LARGE_OBSTACLE_HEIGHT = 100;
	
	private static final int JUMPING_OBSTACLE_WIDTH = 68, JUMPING_OBSTACLE_HEIGHT = 53;
	private static final int CLOUD_WIDTH = 20, CLOUD_HEIGHT = 30;
	public SpawnManager()
	{ 
		rand = new Random();
	}
	
	/**
	 * gets called every frame
	 */
	@Override
	public void update() {
		spawnObstacle();
		spawnCloud(); //just added for 
	}
	
	/**
	 * Picks a random interval between two values to spawn an obstacle
	 * Swaps between spawning a stationary/jumping obstacle
	 */
	void spawnObstacle() {
		obstacleTimer += GameManager.getDeltaTime(); //add time elapsed per frame to obstacleTimer
		if (obstacleTimer >= obstacleSpawnTime) {
			
			//swaps between spawning a stationary & jumping obstacle
			if (obstacleCounter++ % 2 == 0) 
				spawnStationaryObstacle();
			else 
				spawnJumpingObstacle();
			

			 
			int min = (int)(obstacleMinTime * 100);
			int max = (int)(obstacleMaxTime * 100);
			obstacleSpawnTime = rand.nextInt(max + 1 - min) + min;
			obstacleSpawnTime /= 100; 
			
			obstacleTimer -= obstacleSpawnTime;
		}
	}
	
	/**
	 * Flips a coin and spawns either a bigger/smaller stationary obstacle
	 */
	void spawnStationaryObstacle()
	{
		if (rand.nextFloat() > 0.5f)
			new StationaryObstacle(SMALL_OBSTACLE_WIDTH, SMALL_OBSTACLE_HEIGHT);
		else
			new StationaryObstacle(LARGE_OBSTACLE_WIDTH, LARGE_OBSTACLE_HEIGHT);
	}
	
	
	void spawnJumpingObstacle()
	{
		new JumpingObstacle(JUMPING_OBSTACLE_WIDTH, JUMPING_OBSTACLE_HEIGHT);
	}

	/**
	 * Spawns a cloud after every 5 seconds
	 */
	void spawnCloud() {
		//spawnCloud() to spawn every 5 seconds new Cloud()
		cloudTimer += GameManager.getDeltaTime();
		if (cloudTimer > cloudSpawnTime)
		{
			cloudTimer -= cloudSpawnTime;
			new Clouds(CLOUD_WIDTH,CLOUD_HEIGHT);
		}
 	}

	/**
	 * Method to render this GameObject
	 * does not display anything, hence an invisible GameObject that exists in the scene
	 */
	@Override 
	public void display(Graphics g) {}
}
