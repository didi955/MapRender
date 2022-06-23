package fr.didi955.maprender.tasks;

import java.awt.image.BufferedImage;

import java.util.*;

import fr.didi955.maprender.ImageMap;
import fr.didi955.maprender.ImageMapRenderer;
import fr.didi955.maprender.ImageMapYML;
import fr.didi955.maprender.MapRender;
import fr.didi955.maprender.callbacks.ImageCallback;
import fr.didi955.maprender.helpers.ImageHelper;
import fr.didi955.maprender.helpers.RenderHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class TaskRenderImage {

	public static HashMap<Player, ArrayList<Inventory>> inventoriesMaps = new HashMap<>();
	
	private Player player ;
	private String path;
	
	public TaskRenderImage(Player player,String path) {
		this.player = player;
		this.path = path;
	}

	private void retrieveImage(final ImageCallback callback) {
		Bukkit.getScheduler().runTaskAsynchronously(MapRender.getInstance(), () -> {
			final BufferedImage image = ImageHelper.getImage(path);
			Bukkit.getScheduler().runTask(MapRender.getInstance(), () -> {
				// call the callback with the result
				callback.onImageRetrieveDone(image);
			});
		});
	}

	public void run(){
		// retrieve the image
		retrieveImage(image -> {
			final ArrayList<ItemStack> items = new ArrayList<>();
			final ArrayList<Short> mapsIds = new ArrayList<>();

			final int rows = image.getHeight() / 128;
			final int cols = image.getWidth() / 128;

			MapView map;
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) {
					map = Bukkit.createMap(player.getWorld());
					map = RenderHelper.resetRenderers(map);
					map.setScale(MapView.Scale.FARTHEST);
					map.setUnlimitedTracking(false);
					map.addRenderer(new ImageMapRenderer(image.getSubimage(j * 128, i * 128, 128, 128)));
					ItemStack mapstack = new ItemStack(Material.FILLED_MAP, 1, (short) map.getId());
					MapMeta meta = (MapMeta) mapstack.getItemMeta();
					if (meta != null) {
						meta.setMapView(map);
					}
					mapstack.setDurability((short) map.getId());
					mapstack.setItemMeta(meta);

					mapsIds.add((short) map.getId());
					items.add(mapstack);
				}
			}
			Inventory inv = Bukkit.createInventory(player, 54, "MapRender");
			if(inventoriesMaps.containsKey(player)){
				inventoriesMaps.get(player).add(inv);
			}
			else
			{
				inventoriesMaps.put(player, new ArrayList<>(List.of(inv)));
			}
			int i=1;
			for(ItemStack item : items) {
				if(i == 54){
					inv.addItem(new ItemStack(Material.ARROW));
					inv = Bukkit.createInventory(player, 54, "MapRender");
					inventoriesMaps.get(player).add(inv);
					i=1;
				}
				inv.addItem(item);
				i++;
			}

			final ImageMap imageMap = new ImageMap(UUID.randomUUID(), path, mapsIds);

			final ImageMapYML imageMapYML = new ImageMapYML(imageMap.getUUID());

			imageMapYML.write(imageMap);

			MapRender.IMAGE_MAP_MANAGER.addImageMap(imageMap);

			player.openInventory(inventoriesMaps.get(player).get(0));

			player.sendMessage("§aRendu terminé !");
		});
	}

}

