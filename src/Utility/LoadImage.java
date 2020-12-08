package Utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * The LoadImage program is used to handle and operate directly 
 * with image data. 
 *
 */
public class LoadImage {
	/**
	 * This is the method is used to get the image from file
	 * with a provided path.
	 * 
	 * @param path a String parameter providing the path of the image
	 * @return the desired image from the file
	 * @exception IOException on input error
	 */
	public static BufferedImage loadImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch( IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
