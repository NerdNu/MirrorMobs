package nu.nerd.mirrormobs.mobs;


import nu.nerd.mirrormobs.MirrorMobs;
import nu.nerd.mirrormobs.TagUtil;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public abstract class CustomMob {


    private String id;


    public CustomMob(String id) {
        this.id = id;
    }


    /**
     * The internal ID of this custom mob type
     */
    public String getId() {
        return id;
    }


    public MirrorMobs plugin() {
        return MirrorMobs.instance;
    }


    public void tagEntity(LivingEntity entity) {
        TagUtil.tagCustomMob(entity, getId());
    }


    /**
     * Set up the transient AI characteristics on spawn or reload
     */
    public abstract void initAI(LivingEntity entity);


    /**
     * Create the initial mob
     */
    public abstract LivingEntity spawn(Location loc);


}
