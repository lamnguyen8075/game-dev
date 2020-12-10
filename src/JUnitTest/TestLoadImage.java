package JUnitTest;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import Utility.LoadImage;
import javax.imageio.ImageIO;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;
//import org.junit.Test;
//import java.lang.reflect.*;

class TestLoadImage {

      //testing to see if image exist in directory
      @Test
      void test() {
         String path = "image/bat0.png";
         try {
            BufferedImage expectedResult = ImageIO.read(new File(path)); 
            BufferedImage result = null;
         
               
            result = LoadImage.loadImage(path);
            Assert.assertNotEquals("Your file exists", expectedResult, result);
         } catch( IOException e) {
            e.printStackTrace();
         }
      }
}