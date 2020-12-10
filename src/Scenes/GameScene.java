package Scenes;
import GameObject.EndlessLand;
import GameObject.Player;
import GameObject.ScoreText;
import GameObject.SpawnManager;

/**
 * The Game scene where the player can run & jump past obstacles
 * contains all the initial objects within the game scene
 * Essentially a class purely used to contain the data of that scene
 */
public class GameScene  {
	
	public GameScene()
	{
		new EndlessLand();
		new ScoreText();
		new Player();
		new SpawnManager();
	}
	
}
