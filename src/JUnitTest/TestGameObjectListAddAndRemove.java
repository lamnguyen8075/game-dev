package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;

import org.junit.jupiter.api.Test;

import GameObject.Clouds;
import GameObject.GameObject;
import GameObject.JumpingObstacle;
import GameObject.Player;
import GameObject.StationaryObstacle;
import Systems.GameManager;

class TestGameObjectListAddAndRemove {

   @Test
   //the purpose of this thread is to test the modification of the GameObject list
   void test() {

      new GameManager();
      assertEquals(0, GameManager.getAllGameObjects().size()); 
      
      try {
         Robot r = new Robot();
         GameObject[] gameObjects = new GameObject[4];
         gameObjects[0] = new Player();
         gameObjects[1] = new Clouds(50, 50);
         gameObjects[2] = new StationaryObstacle(50,50);
         gameObjects[3] = new JumpingObstacle(50,50);
         
         //we must wait for at least 1 frame before the modification gets added
         //using robot class to delay 
         r.delay(250); //wait quarter second 
         assertEquals(4, GameManager.getAllGameObjects().size());
         
         //testing the destroy method works
         for(int i = 0; i < gameObjects.length; i ++)
            gameObjects[i].destroy();
         
         //we must wait for at least 1 frame before the modification gets added
         //using robot class to delay 
         r.delay(250); //wait quarter second 
         assertEquals(0, GameManager.getAllGameObjects().size());
      } catch (AWTException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
