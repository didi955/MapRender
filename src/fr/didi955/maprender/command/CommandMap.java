package fr.didi955.maprender.command;

import fr.didi955.maprender.tasks.TaskRenderImage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMap implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("map")) {
			if(sender instanceof Player) {
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("render")) {
						
						final Player player = (Player) sender;
						final String path = args[1];
						
						new TaskRenderImage(player, path).run();
					}
				}
				else
				{
					sender.sendMessage("§cNombre arguments incorrect");
				}
			}
			return true;
		}
		return false;
	}
}
