package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import GameObject.Clouds;
import GameObject.GameObject;
import GameObject.Obstacle;
import GameObject.SpawnManager;
import Systems.GameManager;

class TestObstacleSpawn {

   @Test
   void test() {
      new GameManager();
      assertEquals(0, GameManager.getAllGameObjects().size()); 
      
      
      Robot r;
      try {
         r = new Robot();
         new SpawnManager();
         r.delay(250); //some buffering time 
         
         

         int secondsToMilli = 1000;
         int spawnCount = 5;
         //this behavior is testing that the amount of obstacles spawned is >= the minInterval
         //we will wait 5 * duration of Obstacle's max time
         r.delay((int) (SpawnManager.obstacleMaxTime * spawnCount * secondsToMilli));
         
         
         //we expect a minimum of 5 Obstacles to be spawned
         
         
         //get all the GameObjects in the scene and search for clouds
         ArrayList<GameObject> obstacles = new ArrayList<GameObject>();
         
         for (GameObject gameObject : GameManager.getAllGameObjects())
            if(gameObject instanceof Obstacle)
               obstacles.add(gameObject);
         
         boolean greaterOrEqualToSpawnCount = (obstacles.size() >= spawnCount);
         assertTrue(greaterOrEqualToSpawnCount);
         

      } catch (AWTException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
