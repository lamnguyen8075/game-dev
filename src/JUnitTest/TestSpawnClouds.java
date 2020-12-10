package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import GameObject.Clouds;
import GameObject.GameObject;
import GameObject.SpawnManager;
import Systems.GameManager;

class TestSpawnClouds {

   @Test
   void test() {
      
      
      new GameManager();
      assertEquals(0, GameManager.getAllGameObjects().size()); 
      
      
      Robot r;
      try {
         r = new Robot();
         new SpawnManager();
         r.delay(250);

         //wait 6 seconds, 2 clouds should have spawned 
         //clouds spawn on a 5 second interval with the first spawning right away
         r.delay(6000);
         
         //get all the GameObjects in the scene and search for clouds
         ArrayList<GameObject> clouds = new ArrayList<GameObject>();
         
         for (GameObject gameObject : GameManager.getAllGameObjects())
            if(gameObject instanceof Clouds)
               clouds.add(gameObject);
         
         assertEquals(2, clouds.size()); 
      } catch (AWTException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
