package fr.enzoooo.randomtp;

import fr.enzoooo.randomtp.commands.CommandRConfig;
import fr.enzoooo.randomtp.commands.CommandRTP;
import fr.enzoooo.randomtp.utils.UpdateChecker;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    static Main instance;

    Logger logger = this.getLogger();

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("");

        new UpdateChecker(this, 85077).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("Le plugin de RandomTP va s'allumer !");
            } else {
                logger.info("Une nouvelle version est disponible !");
            }
        });

        getConfig().options().copyDefaults(true);
        saveConfig();

        getCommand("rtp").setExecutor(new CommandRTP());
        getCommand("rconfig").setExecutor(new CommandRConfig());
    }

    public static Main getInstance() { return instance; }
}
