package nu.nerd.mirrormobs;


import nu.nerd.mirrormobs.mobs.CustomMob;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Random;


public class MirrorMobsListener implements Listener {


    private MirrorMobs plugin;
    private Random random;


    public MirrorMobsListener(MirrorMobs plugin) {
        this.plugin = plugin;
        this.random = new Random();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        onEnable();
    }


    public void onEnable() {
        for (World world : plugin.getServer().getWorlds()) {
            for (Chunk chunk : world.getLoadedChunks()) {
                reloadAIForChunkEntities(chunk);
            }
        }
    }


    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        reloadAIForChunkEntities(event.getChunk());
    }


    /**
     * Naturally spawn the custom mobs
     */
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL) {
            return;
        }

        if (random.nextFloat() <= plugin.CONFIG.SPAWN_CHANCE) {
            return;
        }

        int index = random.nextInt(plugin.CONFIG.SPAWN_NATURALLY.size());
        String mobId = plugin.CONFIG.SPAWN_NATURALLY.get(index);

        plugin.spawnMob(mobId, event.getLocation());
        event.setCancelled(true);

        plugin.getLogger().info(String.format("Custom mob %s spawned at %s", mobId, event.getLocation().toString()));
    }


    /**
     * Override experience drops
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() == null || !TagUtil.isEntityCustom(event.getEntity())) {
            return;
        }

        String id = TagUtil.getTaggedMobId(event.getEntity());
        CustomMob mob = plugin.getCustomMobClass(id);

        if (mob.getCustomXP() > -1) {
            event.setDroppedExp(mob.getCustomXP());
        }
    }


    /**
     * Reload the custom AI, which doesn't persist across restarts
     */
    private void reloadAIForChunkEntities(Chunk chunk) {
        for (Entity ent : chunk.getEntities()) {
            if (ent instanceof LivingEntity) {
                if (TagUtil.isEntityCustom(ent)) {
                    String id = TagUtil.getTaggedMobId(ent);
                    CustomMob mob = plugin.getCustomMobClass(id);
                    if (mob != null) {
                        mob.initAI((LivingEntity) ent);
                    }
                }
            }
        }
    }


}
