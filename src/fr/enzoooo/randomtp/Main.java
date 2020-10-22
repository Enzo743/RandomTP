package fr.enzoooo.randomtp;

import fr.enzoooo.randomtp.commands.CommandRTP;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("La plugin de RandomTP va s'allumer !");
        getCommand("rtp").setExecutor(new CommandRTP());
    }
}
