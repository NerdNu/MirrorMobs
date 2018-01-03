package nu.nerd.mirrormobs;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;


public class Configuration {


    private MirrorMobs plugin;

    public double SPAWN_CHANCE;
    public List<String> SPAWN_NATURALLY;


    public Configuration(MirrorMobs plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        reload();
    }


    public void reload() {
        plugin.reloadConfig();
        SPAWN_CHANCE = plugin.getConfig().getDouble("spawn_chance", 0.05d);
        SPAWN_NATURALLY = plugin.getConfig().getStringList("spawn_naturally");
    }


}
