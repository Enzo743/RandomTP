package fr.enzoooo.randomtp.commands;

import fr.enzoooo.randomtp.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CommandRTP implements CommandExecutor {

    private Map<Player, Long> tpCooldown = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(tpCooldown.containsKey(p)){
                long time = (System.currentTimeMillis() - tpCooldown.get(p)) / 1000;

                if(time < Main.getInstance().getConfig().getLong("cooldown")){
                    p.sendMessage("§cMerci de patienter entre chaque téléportement !");
                    return false;
                } else {
                    tpCooldown.remove(p);
                }
            }

            if(p.hasPermission("randomtp.use")){
                int max = Main.getInstance().getConfig().getInt("distance-max");
                int min = Main.getInstance().getConfig().getInt("distance-min");

                int x = new Random().nextInt(max - min) + min;
                int z = new Random().nextInt(max - min) + min;

                Location loc = new Location(p.getWorld(), x, 0, z);

                int y = loc.getWorld().getHighestBlockYAt(loc) + 3;
                loc.setY(y);

                p.teleport(loc);
                p.sendMessage("§aVous avez bien été téléporté aléatoirement en §7" + x + " " + y + " " + z);
                tpCooldown.put(p, System.currentTimeMillis());
            } else {
                p.sendMessage("§cVous n'avez pas le droit d'executer cette commande !");
            }
        } else {
            if (args.length == 1){
                Player p = Bukkit.getPlayer(args[0]);
                if(p != null && p.isOnline()){
                    int max = Main.getInstance().getConfig().getInt("distance-max");
                    int min = Main.getInstance().getConfig().getInt("distance-min");

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
