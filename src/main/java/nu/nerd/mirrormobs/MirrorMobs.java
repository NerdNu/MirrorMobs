package nu.nerd.mirrormobs;

import nu.nerd.mirrormobs.mobs.*;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;


public class MirrorMobs extends JavaPlugin {


    public static MirrorMobs instance;
    private Map<String, CustomMob> customMobs;


    public void onEnable() {
        MirrorMobs.instance = this;
        registerCustomMobs();
        new MirrorMobsListener(this);
        new CommandHandler(this);
    }


    private void registerCustomMobs() {
        customMobs = new HashMap<String, CustomMob>();
        customMobs.put("drcuddles", new DrCuddles());
        customMobs.put("giant", new Giant());
        customMobs.put("killerrabbit", new KillerRabbit());
        customMobs.put("lordpuggleston", new LordPuggleston());
        customMobs.put("mrskeltal", new MrSkeltal());
        customMobs.put("sirmeowingtons", new SirMeowingtons());
    }


    public CustomMob getCustomMobClass(String id) {
        return customMobs.get(id);
    }


    public Map<String, CustomMob> getCustomMobs() {
        return customMobs;
    }


    /**
     * Spawn a custom mob
     * @param id the ID of the custom mob
     * @param loc where to spawn the mob
     * @return LivingEntity or null if the desired mob could not be spawned
     */
    public LivingEntity spawnMob(String id, Location loc) {
        if (customMobs.containsKey(id)) {
            CustomMob mob = customMobs.get(id);
            LivingEntity ent = mob.spawn(loc);
            mob.initAI(ent);
            mob.tagEntity(ent);
            return ent;
        }
        return null;
    }


}
