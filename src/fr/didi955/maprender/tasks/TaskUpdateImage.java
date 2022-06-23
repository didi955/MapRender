package fr.didi955.maprender.tasks;

import java.awt.image.BufferedImage;

import fr.didi955.maprender.ImageMap;
import fr.didi955.maprender.ImageMapRenderer;
import fr.didi955.maprender.MapRender;
import fr.didi955.maprender.callbacks.ImageCallback;
import fr.didi955.maprender.helpers.ImageHelper;
import fr.didi955.maprender.helpers.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.map.MapView;

public class TaskUpdateImage {
	
	private final ImageMap imageMap;
	
	public TaskUpdateImage(ImageMap imageMap) {
		this.imageMap = imageMap;
	}

	private void retrieveImage(final ImageCallback callback) {
		Bukkit.getScheduler().runTaskAsynchronously(MapRender.getInstance(), new Runnable() {
			@Override
			public void run() {
				final BufferedImage image = ImageHelper.getImage(imageMap.getPath());
				Bukkit.getScheduler().runTask(MapRender.getInstance(), () -> {
					// call the callback with the result
					callback.onImageRetrieveDone(image);
				});
			}
		});
	}

	public void run() {
		retrieveImage(image -> {

			final int rows = image.getHeight() / 128;
			final int cols = image.getWidth() / 128;

			MapView map;
			int index = 0;

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					map = Bukkit.getMap(imageMap.getmapIds().get(index));
					map = RenderHelper.resetRenderers(map);
					map.setScale(MapView.Scale.FARTHEST);
					map.setUnlimitedTracking(false);
					map.addRenderer(new ImageMapRenderer(image.getSubimage(j * 128, i * 128, 128, 128)));

					index++;
				}
			}

		});
	}

}
