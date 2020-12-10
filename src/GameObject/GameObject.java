package GameObject;
import java.awt.Graphics;

import Systems.GameManager;
import Utility.UniqueIDGenerator;

/**
 * @author Bao
 * The purpose to inherit from this class is to use to the update() & display method
 * GameObjects are objects that are added to the GameManager's loop 
   to be constantly updated and displayed every frame 
 */
public abstract class GameObject {
	
	private int uid;
	
	/**
	 * Constructs a uniqueID to be set in the GameManager's collection 
	 * that gets constantly frame checked in the update loop
	 * During creation append the GameObject to the GameManager's listener if it exists
	 */
	public GameObject() {
		uid = UniqueIDGenerator.getID();
		GameManager.addGameObject(this, uid);
		GameManager.addListener(this);
	}
	 
	/**
	 * gets called once per frame by GameManager loop
	 */
	public abstract void update();
	
	// Rendered in the active scenes
	
	/**
	 * Graphics listener to allocate the rendering responsibility to the GameObject's class 
	 * Every GameObject must implement this method
	 * @param g the graphics supplied in the base display method
	 */
	public abstract void display(Graphics g);
	
	/**
	 * removes current GameObject from the update thread for garbage collection to take over
	 * Removes the listener from this GameObject, if it exists
	 */
	public void destroy() {
		GameManager.removeGameObject(this, uid);
		GameManager.removeListener(this);
	}
	
	/**
	 * Used for debugging purposes to make sure everyObject has a unique ID 
	   and spawns in the correct order
	 * @return returns the uniqueID for this gameObject 
	 */
	public int getUID()
	{
		return uid;
	}
}
