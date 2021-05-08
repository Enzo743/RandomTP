package fr.enzoooo.randomtp.commands;

import fr.enzoooo.randomtp.managers.RTPManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRTP implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 0) {
                RTPManager.cooldown(p);

                if(p.hasPermission("randomtp.use")){
                    RTPManager.randomTP(p);
                } else {
                    p.sendMessage("§cVous n'avez pas le droit d'executer cette commande !");
                }
            } else if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player != null && player.isOnline()){
                    RTPManager.cooldown(p);

                    if(p.hasPermission("randomtp.others")){
                        RTPManager.randomTP(player);
                        p.sendMessage("§aLe joueur §5" + p + "§aà bien été téléporté aléatoirement !");
                    } else {
                        p.sendMessage("§cVous n'avez pas la permission de téléporter un autre joueur !");
                    }
                }
            }

        } else {
            if (args.length == 1) {
                Player p = Bukkit.getPlayer(args[0]);
                if(p != null && p.isOnline()) {
                    RTPManager.randomTP(p);
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
