package fr.enzoooo.randomtp;

import fr.enzoooo.randomtp.commands.CommandRConfig;
import fr.enzoooo.randomtp.commands.CommandRTP;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    static Main instance;
    public static Main getInstance() { return instance; }

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("La plugin de RandomTP va s'allumer !");

        getConfig().options().copyDefaults(true);
        saveConfig();

        getCommand("rtp").setExecutor(new CommandRTP());
        getCommand("rconfig").setExecutor(new CommandRConfig());
    }
}
