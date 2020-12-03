package Scenes;
import GameObject.ScoreText;
import GameObject.StartButton;

//TODO implement the panel's paint behaviour 
//TODO implement the start button
//TODO implement the quit button
public class MainMenuScene{
	
	public MainMenuScene()
	{
		new StartButton();
		new ScoreText();
	}
}