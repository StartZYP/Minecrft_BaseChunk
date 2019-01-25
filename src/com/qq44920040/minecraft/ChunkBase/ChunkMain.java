package com.qq44920040.minecraft.ChunkBase;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Random;


public class ChunkMain extends JavaPlugin implements Listener {
    private String tempworld;
    private static int BaseHight;
    private static int BlockId;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!(file.exists())){
            saveDefaultConfig();
        }
        tempworld = getConfig().getString("World");
        BaseHight = getConfig().getInt("BaseHight");
        BlockId = getConfig().getInt("BlockId");
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }

    @EventHandler
    public void onInit(WorldInitEvent event) {

        if (tempworld.equals(event.getWorld().getName())) {
            event.getWorld().getPopulators().add(new PumpkinPopulator());
        }
    }


    private static class PumpkinPopulator extends BlockPopulator {

        @Override
        public void populate(World world, Random random, Chunk chunk) {



            for(int x = 0; x < 16; x++) {
                for(int z = 0; z < 16; z++)
                {
                    chunk.getBlock(x,BaseHight,z).setType(Material.getMaterial(BlockId));
                }
            }
        }
    }
}
