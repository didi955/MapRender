package fr.didi955.maprender;

import java.util.ArrayList;
import java.util.UUID;

public class ImageMap {
	
	private UUID uuid;
	
	private String path;
	
	private ArrayList<Short> mapIds;
	
	public ImageMap(UUID uuid, String path, ArrayList<Short> mapIds) {
		this.uuid = uuid;
		this.path = path;
		this.mapIds = mapIds;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	public String getPath() {
		return path;
	}
	public ArrayList<Short> getmapIds() {
		return mapIds;
	}

}
