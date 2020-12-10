package Scenes;
import GameObject.ScoreText;
import GameObject.StartButton;

/**
 * The Main Menu scene where the player can restart the game
 * contains only a text for the score and a start button
 * Essentially a class purely used to contain the data of that scene
 */
public class MainMenuScene{
	
	public MainMenuScene()
	{
		new StartButton();
		new ScoreText();
	}
}