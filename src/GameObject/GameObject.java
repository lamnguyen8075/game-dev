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
	
	public GameObject() {
		uid = UniqueIDGenerator.getID();
		GameManager.addGameObject(this, uid);
	}
	 
	// Called once per frame by internal GameManager loop
	public abstract void update();
	
	// Rendered in the active scenes
	public abstract void display(Graphics g);
	
	//removes current GameObject from the update thread for garbage collection to take over
	public void destroy() {
		GameManager.removeGameObject(this, uid);
	}
	
	public int getUID()
	{
		return uid;
	}
}
