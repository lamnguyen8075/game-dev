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

class TestResetScene {

   //tests to see if the GameManager's Reset scene clears all GameObjects
   @Test
   void test() {
      
      Robot r;
      try {
         r = new Robot();
         new GameManager(); //we must always make a new GameManager so the thread & list can exist
         
         GameObject[] gameObjects = new GameObject[4];
         gameObjects[0] = new Player();
         gameObjects[1] = new Clouds(50, 50);
         gameObjects[2] = new StationaryObstacle(50,50);
         gameObjects[3] = new JumpingObstacle(50,50);
         r.delay(250); //we use robot to wait as the list has to be updated the next frame 
         assertEquals(4, GameManager.getAllGameObjects().size());
         
         //testing that resetting the scene clears all existing GameObjects
         GameManager.resetScene(); 
         r.delay(250); //wait quarter second 
         assertEquals(0, GameManager.getAllGameObjects().size());
         
      } catch (AWTException e) {

         e.printStackTrace();
      }
      
   }

}
