package fr.enzoooo.randomtp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class CommandRTP implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("randomtp.use")){
                int max = 2500;
                int min = 300;

                int x = new Random().nextInt(max - min) + min;
                int z = new Random().nextInt(max - min) + min;

                Location loc = new Location(p.getWorld(), x, 0, z);

                int y = loc.getWorld().getHighestBlockYAt(loc) + 3;
                loc.setY(y);

                p.teleport(loc);
                p.sendMessage("§aVous avez bien été téléporté aléatoirement en §7" + x + " " + y + " " + z);
            } else {
                p.sendMessage("§cVous n'avez pas le droit d'executer cette commande !");
            }
        } else {
            if (args.length == 1){
                Player p = Bukkit.getPlayer(args[0]);
                if(p != null && p.isOnline()){
                    int max = 2500;
                    int min = 300;

                    int x = new Random().nextInt(max - min) + min;
                    int z = new Random().nextInt(max - min) + min;

                    Location loc = new Location(p.getWorld(), x, 0, z);

                    int y = loc.getWorld().getHighestBlockYAt(loc) + 3;
                    loc.setY(y);

                    p.teleport(loc);
                    p.sendMessage("§aVous avez été téléporté aléatoirement en §7" + x + " " + y + " " + z);
                } else {
                    sender.sendMessage("§cLe joueur specifie n'existe pas ou n'est pas connecter !");
                }
            } else {
                sender.sendMessage("§cMerci de specifier un joueur a teleporter !");
            }
        }
        return false;
    }
}
