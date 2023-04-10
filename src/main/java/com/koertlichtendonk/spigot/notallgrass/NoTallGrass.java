package main.java.com.koertlichtendonk.spigot.notallgrass;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

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
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
            if (itemInHand.getType() == Material.BONE_MEAL) {
                event.setCancelled(true);
                Block block = event.getClickedBlock();
                if (block != null && block.getType() == Material.GRASS_BLOCK) {
                    bonemealEffect(block);

                    // Remove one bonemeal from the player's hand
                    if (itemInHand.getAmount() > 1) {
                        itemInHand.setAmount(itemInHand.getAmount() - 1);
                    } else {
                        event.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }

    private void bonemealEffect(Block grassBlock) {
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            Block target = grassBlock.getRelative(random.nextInt(6) - 3, random.nextInt(3) - 1, random.nextInt(6) - 3);
            if (target.getType() == Material.GRASS_BLOCK) {
                Block above = target.getRelative(BlockFace.UP);
                if (above.getType() == Material.AIR) {
                    Material[] flowers = {Material.DANDELION, Material.POPPY, Material.BLUE_ORCHID, Material.ALLIUM,
                            Material.AZURE_BLUET, Material.RED_TULIP, Material.ORANGE_TULIP, Material.WHITE_TULIP, Material.PINK_TULIP,
                            Material.OXEYE_DAISY};
                    Material randomFlower = flowers[random.nextInt(flowers.length)];
                    if (random.nextInt(4) == 0) {
                        above.setType(randomFlower);
                    } else {
                        above.setType(Material.GRASS);
                    }
                }
            }
        }
    }
}