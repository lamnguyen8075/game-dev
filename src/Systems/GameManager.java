package Systems;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GameObject.GameObject;
import GameObject.Obstacle;
import GameObject.Player;
import GameObject.SpawnManager;
import Scenes.GameScene;
import Scenes.MainMenuScene;
import Scenes.Scene;

/**
 * @author Bao
   The purpose of this class is to manage the entire game loop
   This class sets the main thread for frame checks and holds
   a list of all GameObjects to be updated in the thread
 
   This class is 
   - Is the main() class run to start the game
   - holds a Map of every GameObject for their update() calls & paint()
   - sets pace of the game with scrollSpeed & multipliers
   - has many static method getDeltaTime to return current frame time
   - Variables to adjust the fps Cap & scrollSpeed of the game
   - Variables to set default window size & relative ground height
   - The ability to reset & switch scenes
   
 */
public class GameManager extends JFrame implements Runnable{
	
	public static final int PIXEL_WIDTH = 1280, PIXEL_HEIGHT = 720; //read only
	public static double getDeltaTime() { return deltaTime; } //public static getter read only
	public static double getTime() { return time; } //public static getter read only
	public static int getScrollSpeed() { return scrollSpeed; }
	public static final int GROUND_POSITION = 600; 
	
	private static Map<GameObject, Integer> gameObjects; //list every single GameObject to be updated 
	private static Map<GameObject, Integer> addQueue;    //queue to add after every update frame
	private static Map<GameObject, Integer> removeQueue; //queue to remove after every update frame
	private static double deltaTime; //time elapsed per frame
	private static double time; //time elapsed during entire play duration
	private static int scrollSpeed = 700; //Game moves at a pace of 700 pixels per unit
	private int fps = 60; //cap
	private Thread thread;
	private static boolean inGameScene = false;
	public static GameManager instance; //holds instance of itself for public static calls
	
	public static boolean gameIsRunning = true;
	
	public static void main(String[] args) {
		new GameManager();
	}
	
	public GameManager()
	{	
		super("Endless Runner"); //title
		instance = this;
		setSize(PIXEL_WIDTH, PIXEL_HEIGHT);
		
		gameObjects = new HashMap<GameObject, Integer>(); 
		addQueue = new HashMap<GameObject, Integer>();
		removeQueue = new HashMap<GameObject, Integer>();
		
		//put the window in the center of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int widthLocation = (int)(screenSize.getWidth() - PIXEL_WIDTH)/2;
		int heightLocation = (int)(screenSize.getHeight() - PIXEL_HEIGHT)/2;
		setLocation(widthLocation, heightLocation);
		
		//GameWindow defaults
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); 

		       
		//appending the game scene as the active panel
		add(new Scene());
		new MainMenuScene();
		thread = new Thread(this);
		thread.start();
	   setVisible(true);
	}
	
	/**
	 * Used for resetting current elapsed Play time (survival Time)
	 * @param gameScene toggles the state of whether in game or not
	 */
	public static void setInGameScene(boolean gameScene)
	{
		inGameScene = gameScene;
		if (inGameScene)
			time = 0;
	}
	
	/**
	 * This adds a GameObject to the collection of things to be updated in the thread
	 * @param g The GameObject to append to this map
	 * @param uid the value associated with that key
	 */
	public static void addGameObject(GameObject g, int uid)
	{
		addQueue.put(g, uid);
	}
	
	  /**
    * This removes the specific GameObject from the collection to be removed from the thread's update
    * @param g The key to be removed
    * @param uid the value associated with that GameObject
    */
	public static void removeGameObject(GameObject g, int uid)
	{
		removeQueue.put(g, uid);
	}

   /**
   * Helper method to get all GameObjects in the set for the Scene to render
   */
	public static Set<GameObject> getAllGameObjects() 
	{
		return GameManager.gameObjects.keySet();
	}
	
	/**
	 * Global method used for GameObjects to append themselves to the Window's listener
	 * This allows the GameObject to handle it's own responsibility for user inputs
	 * rather than allocating all input responsibilities to the GameManager Class
	 * @param <T> Generic allows for any type of class to be added
	 * @param k any GameObject that implements any type of Listener will be added here 
	 */
	public static <T> void addListener(T k)
	{
		if (k instanceof KeyListener)
			instance.addKeyListener((KeyListener) k);
		else if (k instanceof MouseListener)
			instance.addMouseListener((MouseListener) k);
	}
	
   /**
    * Global method used for GameObjects to deallocate themselves from the Window's listener
    * @param <T> Generic allows for any type of class to be removed
    * @param k any GameObject that implements any type of Listener will be removed here 
    */
	public static <T> void removeListener(T k)
	{
		if (k instanceof KeyListener)
			instance.removeKeyListener((KeyListener) k);
		else if (k instanceof MouseListener)
			instance.removeMouseListener((MouseListener) k);
	}
	
	/**
	 * Removes every every single GameObject from the scene
	 * used before loading a new scene
	 */
	public static void resetScene()
	{
		for (GameObject g : gameObjects.keySet())
			g.destroy();
	}

	/**
	 * The main thread which runs the game loop emulating a call every frame
	 * This thread calls update() for every GameObject in it's collection 
	   which allows the GameObject to handle it's own responsibilities every frame
	 * This thread also calls repaint() which causes the Scene to rerender itself every frame
	 * deltaTime & time will be calculated here
	 */ 
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
				Thread.sleep(1000/fps); //wait the duration of one frame with targeted fps, sleep has slight inaccuracies
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			updateTime = (System.nanoTime() - initialTime); //time after waiting 
			deltaTime = (updateTime - curTime) /1_000_000_000.00; //elapsed waiting time in seconds
			if (inGameScene)
				time += deltaTime; //total time
		}
	}
	
	/**
	 * Thread fail safe to prevent modification of the GameObjects list during its iteration
	 * The purpose of this method is to make sure the list does not get modified during 
	   it's iteration of the key set which will generate an out of bounds error for add/removals
	 * This ensures that the list will get modified before/after looping through the collection
	 */
	public void modifyGameObjectList()
	{
		for (GameObject g : addQueue.keySet())
			gameObjects.put(g, g.getUID());
		addQueue.clear();
		for (GameObject g : removeQueue.keySet())
			gameObjects.remove(g, g.getUID());
		removeQueue.clear();
	}
}
