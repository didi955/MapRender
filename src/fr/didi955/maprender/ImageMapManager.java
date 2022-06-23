package fr.didi955.maprender;

import java.util.ArrayList;

public class ImageMapManager {
	
	private ArrayList<ImageMap> imageMaps;
	
	public ImageMapManager() {
		this.imageMaps = new ArrayList<>();
	}
	
	public void addImageMap(ImageMap imageMap) {
		this.imageMaps.remove(imageMap);
		
	}
	
	public void removeImageMap(ImageMap imageMap) {
		this.imageMaps.remove(imageMap);
	}
	
	public ArrayList<ImageMap> getImageMaps(){
		
		return imageMaps;
		
	}

}
