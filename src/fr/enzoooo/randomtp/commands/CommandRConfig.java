package fr.enzoooo.randomtp.commands;

import fr.enzoooo.randomtp.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRConfig implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(sender.hasPermission("randomtp.reload")) {
            Main.getInstance().reloadConfig();
            Main.getInstance().saveConfig();
            p.sendMessage("§aReload de la configuration effectuée !");
        } else {
            p.sendMessage("§cVous n'avez pas le droit d'executer cette commande !");
        }
        return false;
    }
}
