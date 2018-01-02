package nu.nerd.mirrormobs.mobs;


import nu.nerd.mirrormobs.MirrorMobs;
import nu.nerd.mirrormobs.TagUtil;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public abstract class CustomMob {


    private String id;
    private int customXP;


    public CustomMob(String id) {
        this.id = id;
        this.customXP = -1;
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
     * Set the amount of experience that this mob type will drop.
     * This is typically set in the extending class.
     * A value of -1 will mean no override from the default.
     */
    protected void setCustomXP(int xp) {
        this.customXP = xp;
    }


    /**
     * Get the amount of experience that this mob type will drop
     */
    public int getCustomXP() {
        return this.customXP;
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
