package nl.koertlichtendonk.spigotplugin.notallgrass;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class NoTallGrass extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("NoTallGrass has been enabled!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable(){

    }

    @EventHandler
    public void onFertilize(BlockFertilizeEvent event)
    {
        if(event.getBlock().getType() == Material.GRASS_BLOCK) {
            new BukkitRunnable() {
                public void run() {
                    for (BlockState block : event.getBlocks()) {
                        getLogger().info(block.getBlock().getType() + " has been created by the bonemeal?");
                        if (block.getBlock().getType() == Material.TALL_GRASS) {
                            block.getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
                            block.getBlock().getRelative(BlockFace.UP).getState().update(true);

                            block.getBlock().setType(Material.GRASS);
                            block.getBlock().getState().update(true);
                        }
                    }
                }
            }.runTaskLater(this, 1);
        }
    }
}
