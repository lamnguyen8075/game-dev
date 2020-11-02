
/**
 * @author Bao
 * Every GameObject requires the update method which gets called in GameManager loop
 */
public abstract class GameObject {
	
	public GameObject()
	{
		GameManager.gameObjects.add(this);
	}
	
	/**
	 * Simulates a call once per frame
	 * Called by the internal GameManager loop
	 */
	public abstract void update();
}
