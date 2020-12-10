package Scenes;
import java.awt.Graphics;
import javax.swing.JPanel;
import GameObject.*;
import Systems.GameManager;

/**
 * Used for rendering all active GameObjects in the game
 */
public class Scene extends JPanel {
	
	public void paint(Graphics g) {
		//Render all active GameObjects
		for (GameObject gameObject : GameManager.getAllGameObjects())
			gameObject.display(g);
	}
}
