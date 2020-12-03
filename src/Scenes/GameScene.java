package Scenes;
import GameObject.EndlessLand;
import GameObject.Player;
import GameObject.ScoreText;
import GameObject.SpawnManager;

public class GameScene  {
	
	public GameScene()
	{
		new EndlessLand();
		new ScoreText();
		new Player();
		new SpawnManager();
	}
	
}
