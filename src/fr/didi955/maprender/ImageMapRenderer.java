package fr.didi955.maprender;

import java.awt.image.BufferedImage;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class ImageMapRenderer extends MapRenderer {
	
	private boolean shouldRender;
	private BufferedImage image;
	
	public ImageMapRenderer(BufferedImage image) {
		this.image = image;
		this.shouldRender = true;
	}

	@Override
	public void render(MapView mapview, MapCanvas mapCanvas, Player player) {
		
		if(shouldRender) {
			mapCanvas.drawImage(0, 0, image);
			shouldRender = false;
		}
		
	}

}
