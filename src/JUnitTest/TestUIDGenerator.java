package JUnitTest;
import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Utility.UniqueIDGenerator;

class TestUIDGenerator {

   @Test
   void testGenerator() {
      int id1 = 0;
      int id2 = 0;
      int test = 0; 
      for(int i = 0; i <= 10; i++) {
         id1 = UniqueIDGenerator.getID();
         if (id2<id1) {
            test += 1;
         }
         id2 = id1;
      }
      Assert.assertEquals("Your method is working", 10 ,test);
   }


}
