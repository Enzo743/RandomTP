package fr.enzoooo.randomtp.managers;

import fr.enzoooo.randomtp.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RTPManager {

    private static Map<Player, Long> tpCooldown = new HashMap<>();

    public static boolean cooldown(Player player) {
        if(tpCooldown.containsKey(player)){
            long time = (System.currentTimeMillis() - tpCooldown.get(player)) / 1000;

            if(time < Main.getInstance().getConfig().getLong("cooldown")){
                player.sendMessage("§cMerci de patienter entre chaque téléportement !");
                return false;
            } else {
                tpCooldown.remove(player);
            }
        }
        return false;
    }

    public static void randomTP(Player player) {
        int max = Main.getInstance().getConfig().getInt("distance-max");
        int min = Main.getInstance().getConfig().getInt("distance-min");

        int x = new Random().nextInt(max - min) + min;
        int z = new Random().nextInt(max - min) + min;

        Location loc = new Location(player.getWorld(), x, 0, z);

        int y = loc.getWorld().getHighestBlockYAt(loc) + 3;
        loc.setY(y);

        player.teleport(loc);
        player.sendMessage("§aVous avez bien été téléporté aléatoirement en §7" + x + " " + y + " " + z);
        tpCooldown.put(player, System.currentTimeMillis());
    }
}
