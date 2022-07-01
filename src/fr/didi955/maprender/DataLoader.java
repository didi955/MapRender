package fr.didi955.maprender;

import fr.didi955.maprender.tasks.TaskUpdateImage;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DataLoader {
	
	public static void loadMaps() throws IOException{
		
		final File imagesDir = MapRender.IMAGES_DIR;
		final File imagesMapDir = MapRender.IMAGES_MAP_DIR;
		
		if(!imagesDir.exists() && !imagesDir.mkdirs()) {
			throw new IOException("Cannot create image directory");
		}
		if(!imagesMapDir.exists() && !imagesDir.mkdirs()) {
			throw new IOException("Cannot create images maps directory");
		}
		
		final File[] files = imagesMapDir.listFiles();
		if(files != null) {
			
			ImageMap imageMap;
			ImageMapYML imageMapYML;
			
			for(File file : files) {
				if(file.getName().endsWith(".yml")) {
					
					imageMapYML = new ImageMapYML(UUID.fromString(file.getName().replaceAll(".yml", "")));
					imageMap = imageMapYML.read();

					MapRender.IMAGE_MAP_MANAGER.addImageMap(imageMap);
					new TaskUpdateImage(imageMap).run();
					
				}
			}
		}
		
	}

}
