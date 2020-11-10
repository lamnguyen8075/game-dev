package Scenes;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import GameObject.GameObject;
import Systems.GameManager;

public class GameScene extends JPanel {

	private String backgroundColor = "#000000"; //black hexadecimal
	
	/** Everything in the scene gets rendered here 
	    GameManager constantly repaints the active panel's scene 
	    every frame after calling update
	 */
	public void paint(Graphics g) {
		//Render all active GameObjects
		for (GameObject gameObject : GameManager.getAllGameObjects())
			gameObject.display(g);
	}
}
