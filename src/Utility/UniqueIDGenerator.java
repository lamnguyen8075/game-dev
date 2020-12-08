package Utility;
/**
 * The program is used for creating the unique ID for 
 * each GameObject.
 */
public class UniqueIDGenerator {
	
	private static int id = 0;
	
	//WIP
	/** Method to get the unique ID for the object
	 * 
	 * @return the ID of the object
	 */
	public static int getID() {
		return id++;
	}
}
