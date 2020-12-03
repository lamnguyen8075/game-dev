package GameObject;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import Scenes.GameScene;
import Systems.GameManager;
import Utility.LoadImage;

public class StartButton extends GameObject implements MouseListener{

	private BufferedImage sprite;
	
	public StartButton()
	{
		sprite = LoadImage.loadImage("image/startbutton.png");
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(sprite, GameManager.PIXEL_WIDTH/2 - 630/4, GameManager.PIXEL_HEIGHT/3, 630/2, 240/2, null);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		GameManager.resetScene();
		GameManager.setInGameScene(true);
		new GameScene();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
