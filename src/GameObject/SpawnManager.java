//package GameObject;
//import java.awt.Graphics;
//import java.util.Random;
//
//import Systems.GameManager;
//
///**
// * @author Bao
// * An invisible GameObject which manages the spawning behavior of Obstacles
// * Spawns obstacles randomly within a minimum and maximum time range
// * Randomizes types of obstacles spawned
// */
//public class SpawnManager extends GameObject {
//	
//	float obstacleMinTime = .7f,obstacleMaxTime = 1; //interval of obstacle spawn
//	float obstacleSpawnTime, obstacleTimer = 0;
//	
//	float cloudSpawnTime = 4.5f; //spawn cloud every 4.5 seconds
//	float cloudTimer = cloudSpawnTime;
//	private Random rand;
//	
//	private static final int SMALL_OBSTACLE_WIDTH = 50, SMALL_OBSTACLE_HEIGHT = 50;
//	private static final int LARGE_OBSTACLE_WIDTH = 50, LARGE_OBSTACLE_HEIGHT = 100;
//	
//	private static final int JUMPING_OBSTACLE_WIDTH = 25, JUMPING_OBSTACLE_HEIGHT = 50;
//	private static final int CLOUD_WIDTH = 20, CLOUD_HEIGHT = 30;
//	public SpawnManager()
//	{ 
//		rand = new Random();
//	}
//	
//	@Override
//	public void update() {
//		spawnObstacle();
//		spawnCloud(); //just added for 
//	}
//	
//	void spawnObstacle() {
//		obstacleTimer += GameManager.getDeltaTime(); //add time elapsed per frame to obstacleTimer
//		if (obstacleTimer >= obstacleSpawnTime) {
//			
//			//TODO make it randomize coin flip between spawning a stationary vs jumping
//			spawnStationaryObstacle();
//			 
//			int min = (int)(obstacleMinTime * 100);
//			int max = (int)(obstacleMaxTime * 100);
//			obstacleSpawnTime = rand.nextInt(max + 1 - min) + min;
//			obstacleSpawnTime /= 100; 
//			
//			obstacleTimer -= obstacleSpawnTime;
//		}
//	}
//	
//	void spawnStationaryObstacle()
//	{
//		if (rand.nextFloat() > 0.5f)
//			new StationaryObstacle(SMALL_OBSTACLE_WIDTH, SMALL_OBSTACLE_HEIGHT);
//		else
//			new StationaryObstacle(LARGE_OBSTACLE_WIDTH, LARGE_OBSTACLE_HEIGHT);
//	}
//	
//	void spawnJumpingObstacle()
//	{
//		new JumpingObstacle(JUMPING_OBSTACLE_WIDTH, JUMPING_OBSTACLE_HEIGHT);
//	}
//
//	void spawnCloud() {
//		//spawnCloud() to spawn every 5 seconds new Cloud()
//		cloudTimer += GameManager.getDeltaTime();
//		if (cloudTimer > cloudSpawnTime)
//		{
//			cloudTimer -= cloudSpawnTime;
//			new Clouds(CLOUD_WIDTH,CLOUD_HEIGHT);
//		}
// 	}
//
//	@Override //displays nothing, invisible GameObject
//	public void display(Graphics g) {}
//}

package GameObject;
import java.awt.Graphics;
import java.util.Random;
import java.sql.Timestamp;


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
			// using timeStamp to generate random value
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());	
			float randVal = timestamp.getTime();

//			spawnJumpingObstacle();
//			if(randVal % 2 == 0){
//				spawnStationaryObstacle();
//			}
//			else{
//				System.out.println("spawning jump");
//				
//			}



			
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
