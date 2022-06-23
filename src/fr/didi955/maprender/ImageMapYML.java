package fr.didi955.maprender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class ImageMapYML {
	
	private UUID imageMapUUID;
	private File configfile;
	private YamlConfiguration yamlConfig;
	
	public ImageMapYML(UUID imageMapUUID) {
		this.imageMapUUID = imageMapUUID;
		this.configfile = new File(MapRender.IMAGES_MAP_DIR, imageMapUUID.toString() + ".yml");
		this.yamlConfig = YamlConfiguration.loadConfiguration(configfile);
	}
	
	public void write(ImageMap imageMap) {
		
		final ConfigurationSection config = this.yamlConfig.createSection("image");
		
		config.set("uuid", imageMap.getUUID().toString());
		config.set("path", imageMap.getPath());
		config.set("ids", imageMap.getmapIds());
		
		save();
		
	}
	
	public ImageMap read() {
		
		final ConfigurationSection config = this.yamlConfig.getConfigurationSection("image");

		final String uuidstr;
		if (config != null) {
			uuidstr = config.getString("uuid");
			final String path = config.getString("path");
			final ArrayList<Short> ids = (ArrayList<Short>) config.getShortList("ids");

			return new ImageMap(UUID.fromString(uuidstr), path, ids);
		}
		return null;
	}
	
	private void save() {
		
		try {
			
			yamlConfig.save(configfile);
			
		} catch (IOException e) {
			MapRender.getInstance().getLogger().severe("Cannot save config file: " + imageMapUUID.toString() + ".yml");
			
		}
	}

}
