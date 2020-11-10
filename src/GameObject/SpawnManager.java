package GameObject;
import java.awt.Graphics;
import java.util.Random;

import Systems.GameManager;

/**
 * @author Bao
 * An invisible GameObject which manages the spawning behavior of Obstacles
 * Spawns obstacles randomly within a minimum and maximum time range
 * Randomizes types of obstacles spawned
 */
public class SpawnManager extends GameObject {
	
	float spawnMinTime = .7f,spawnMaxTime = 1; //interval of obstacle spawn
	float spawnTime = 0, timer = 0;
	private Random rand;
	
	private static final int SMALL_OBSTACLE_WIDTH = 50, SMALL_OBSTACLE_HEIGHT = 50;
	private static final int LARGE_OBSTACLE_WIDTH = 50, LARGE_OBSTACLE_HEIGHT = 100;
	
	private static final int JUMPING_OBSTACLE_WIDTH = 25, JUMPING_OBSTACLE_HEIGHT = 50;
	
	public SpawnManager()
	{
		rand = new Random();
	}
	
	@Override
	public void update() {
		spawnObstacle();
	}
	
	void spawnObstacle() {
		timer += GameManager.getDeltaTime(); //add time elapsed per frame to timer
		if (timer >= spawnTime) {
			
			//TODO make it randomize coin flip between spawning a stationary vs jumping
			spawnStationaryObstacle();
			
			int min = (int)(spawnMinTime * 100);
			int max = (int)(spawnMaxTime * 100);
			spawnTime = rand.nextInt(max + 1 - min) + min;
			spawnTime /= 100; 
			
			timer -= spawnTime;
		}
	}
	
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

	@Override //displays nothing, invisible GameObject
	public void display(Graphics g) {}
}
