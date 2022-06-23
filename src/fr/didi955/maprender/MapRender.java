package fr.didi955.maprender;

import fr.didi955.maprender.command.CommandMap;
import fr.didi955.maprender.listeners.InventoryClick;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class MapRender extends JavaPlugin {

    private static MapRender instance;

    public static File IMAGES_DIR;
    public static File IMAGES_MAP_DIR;
    public static ImageMapManager IMAGE_MAP_MANAGER;

    public void onStartup() {
        IMAGES_DIR = new File(getDataFolder(), "images");
        IMAGES_MAP_DIR = new File(getDataFolder(), "maps");
        IMAGE_MAP_MANAGER = new ImageMapManager();

        try {

            DataLoader.loadMaps();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        initCommand();
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        onStartup();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        instance = null;
    }

    private void initCommand(){
        getCommand("map").setExecutor(new CommandMap());
    }

    public static MapRender getInstance() {
        return instance;
    }
}
