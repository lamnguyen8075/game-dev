package Systems;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.awt.*;

import javax.swing.JFrame;

import GameObject.GameObject;
import GameObject.Obstacle;
import GameObject.Player;
import GameObject.SpawnManager;
import Scenes.GameScene;

/**
 * @author Bao
 * The purpose of this class is to manage the entire game loop
 * This class sets the main thread for frame checks and holds
   a list of all GameObjects to be updated in the thread
 
 * This class is 
   - Keeping track of every GameObject's update calls
   - sets pace of the game with scrollSpeed & multipliers
   - Score to keep track of progress
   - Increments difficulty by increasing the scroll speed & spawn frequency over time
 */
public class GameManager extends JFrame implements Runnable{
	
	public static final int PIXEL_WIDTH = 1280, PIXEL_HEIGHT = 720; //read only
	public static double getDeltaTime() { return deltaTime; } //public static getter read only
	public static double getTime() { return time; } //public static getter read only
	public static int getScrollSpeed() { return scrollSpeed; }
	public static final int GROUND_POSITION = 600; 
	
	public static Map<GameObject, Integer> gameObjects; //list every single GameObject to be updated 
	private static Map<GameObject, Integer> addQueue;    //queue to add after every update frame
	private static Map<GameObject, Integer> removeQueue; //queue to remove after every update frame
	private static double deltaTime; //time elapsed per frame
	private static double time; //time elapsed during entire play duration
	private static int scrollSpeed = 700; //Game moves at a pace of 700 pixels per unit
	private int fps = 60; //cap
	private Thread thread;
	
	public static boolean gameIsRunning = true;
	
	public static void main(String[] args) {
		new GameManager();
		new SpawnManager();
	}
	
	public GameManager()
	{	
		super("Endless Runner"); //title
		setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
		
		gameObjects = new HashMap<GameObject, Integer>(); 
		addQueue = new HashMap<GameObject, Integer>();
		removeQueue = new HashMap<GameObject, Integer>();
		
		//put the window in the center of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int widthLocation = (int)(screenSize.getWidth() - PIXEL_WIDTH)/2;
		int heightLocation = (int)(screenSize.getHeight() - PIXEL_HEIGHT)/2;
		setLocation(widthLocation, heightLocation);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		GameScene gameScene = new GameScene();
		add(gameScene);
		thread = new Thread(this);
		thread.start();
		
		Player p = new Player();
		addKeyListener(p);
	}
	
	
	public static void addGameObject(GameObject g, int uid)
	{
		addQueue.put(g, uid);
	}
	
	public static void removeGameObject(GameObject g, int uid)
	{
		removeQueue.put(g, uid);
	}

	public static Set<GameObject> getAllGameObjects() 
	{
		return GameManager.gameObjects.keySet();
	}

	// The thread running the game loop emulating a call every frame
	@Override
	public void run() {
		
		long initialTime = System.nanoTime();
		long curTime, updateTime;
	    
		while (gameIsRunning) 
		{
			modifyGameObjectList();
			
			curTime = (System.nanoTime() - initialTime);
			for (GameObject g : gameObjects.keySet())
				g.update(); //update all frame-based computation

			repaint(); //render from active Panel

			try {
				Thread.sleep(1000/fps); //wait for 1 frame, sleep has slight inaccuracies
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			updateTime = (System.nanoTime() - initialTime); //time after waiting 
			deltaTime = (updateTime - curTime) /1_000_000_000.00; //elapsed waiting time in seconds
			time += deltaTime; //total time
		}
	}
	
	// Thread fail safe to prevent modification of the GameObjects list during its iteration
	public void modifyGameObjectList()
	{
		for (GameObject g : addQueue.keySet())
			gameObjects.put(g, g.getUID());
		addQueue.clear();
		for (GameObject g : removeQueue.keySet())
			gameObjects.put(g, g.getUID());
		removeQueue.clear();
	}
}