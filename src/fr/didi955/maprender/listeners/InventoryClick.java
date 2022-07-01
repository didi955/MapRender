package fr.didi955.maprender.listeners;

import fr.didi955.maprender.tasks.TaskRenderImage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();

        if(!event.getView().getTitle().equals("MapRender") || event.getCurrentItem() == null){
            return;
        }

        if(event.getClick().equals(ClickType.LEFT) && event.getCurrentItem().getType().equals(Material.ARROW) && TaskRenderImage.inventoriesMaps.containsKey(player)){
            ArrayList<Inventory> inventories = TaskRenderImage.inventoriesMaps.get(player);
            int i = 0;
            for(Inventory inv : inventories){
                if(inv.equals(inventory) && i != inventories.size()-1){
                    Inventory next = inventories.get(i+1);
                    player.openInventory(next);
                }
                i++;
            }
        }
    }

}
