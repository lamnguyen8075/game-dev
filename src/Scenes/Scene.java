package Scenes;
import java.awt.Graphics;
import javax.swing.JPanel;
import GameObject.*;
import Systems.GameManager;

public class Scene extends JPanel {
	
	public void paint(Graphics g) {
		//Render all active GameObjects
		for (GameObject gameObject : GameManager.getAllGameObjects())
			gameObject.display(g);
	}
}