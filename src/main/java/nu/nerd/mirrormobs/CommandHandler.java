package nu.nerd.mirrormobs;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {


    private MirrorMobs plugin;


    public CommandHandler(MirrorMobs plugin) {
        this.plugin = plugin;
        plugin.getCommand("mirrormobs").setExecutor(this);
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mirrormobs")) {
            if (args.length < 1) {
                sender.sendMessage("Available custom mob IDs: ");
                sender.sendMessage(ChatColor.GREEN + String.join(", ", plugin.getCustomMobs().keySet()));
                sender.sendMessage("Available commands:");
                sender.sendMessage(ChatColor.DARK_AQUA + "spawn, reload");
            }
            else if (args[0].equalsIgnoreCase("reload")) {
                plugin.CONFIG.reload();
                sender.sendMessage(ChatColor.DARK_AQUA + "MirrorMirror config reloaded.");
            }
            else if (args[0].equalsIgnoreCase("spawn")) {
                spawnCommand(sender, args);
            }
            return true;
        }
        return false;
    }


    private void spawnCommand(CommandSender sender, String[] args) {
        if (args.length == 2) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                plugin.spawnMob(args[1], player.getLocation());
            } else {
                sender.sendMessage(ChatColor.RED + "Only players can leave out spawn coordinates.");
            }
        } else if (args.length == 6) {
            try {
                String id = args[1];
                World world = plugin.getServer().getWorld(args[2]);
                int x = Integer.parseInt(args[3]);
                int y = Integer.parseInt(args[4]);
                int z = Integer.parseInt(args[5]);
                if (world != null) {
                    Location loc = new Location(world, x, y, z);
                    plugin.spawnMob(id, loc);
                } else {
                    sender.sendMessage(ChatColor.RED + "World not found!");
                }
            } catch (NumberFormatException ex) {
                sender.sendMessage(ChatColor.RED + "Malformed coordinate!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /mirrormobs spawn <id> or /mirrormirror spawn <id> <world> <x> <y> <z>");
        }
    }


}
