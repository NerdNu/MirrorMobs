package nu.nerd.mirrormobs;


import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

/**
 * Uses Scoreboard tags for persistent storage of metadata on entities.
 */
public class TagUtil {


    private static final String ISCUSTOM = "mirrormob.iscustom:true";
    private static final String IDPREFIX = "mirrormob.id:";


    /**
     * Mark an entity as a custom mob
     * @param entity the entity to mark
     * @param id a valid ID of a custom mob
     */
    public static void tagCustomMob(LivingEntity entity, String id) {
        entity.addScoreboardTag(ISCUSTOM);
        entity.addScoreboardTag(TagUtil.getMobTagString(id));
    }


    /**
     * Determine if a mob is custom or not.
     * This is faster than getTaggedMobId.
     */
    public static boolean isEntityCustom(Entity entity) {
        return entity.getScoreboardTags().contains(ISCUSTOM);
    }


    /**
     * Determine the custom mob ID of a given entity.
     * For performance, always check isEntityCustom() first.
     */
    public static String getTaggedMobId(Entity entity) {
        for (String tag : entity.getScoreboardTags()) {
            if (tag.startsWith(IDPREFIX)) {
                return tag.substring(IDPREFIX.length());
            }
        }
        return null;
    }


    public static String getMobTagString(String id) {
        return IDPREFIX + id;
    }


}
