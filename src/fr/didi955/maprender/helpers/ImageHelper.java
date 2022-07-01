package fr.didi955.maprender.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageHelper {
	
	public static boolean isURL(String path) {
		
		return path.startsWith("http://") || path.startsWith("https://");
		
	}
	
	public static BufferedImage getImage(String path) {

		try {
			if (isURL(path)) {
				final URL url = new URL(path);

				return ImageIO.read(url);
			} else {
				final File imageFile = new File(path);

				if (imageFile.exists()) {
					return ImageIO.read(imageFile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
