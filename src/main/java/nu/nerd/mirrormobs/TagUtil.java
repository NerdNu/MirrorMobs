package nu.nerd.mirrormobs;


import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class TagUtil {


    private static final String ISCUSTOM = "mirrormob.iscustom:true";
    private static final String IDPREFIX = "mirrormob.id:";


    public static void tagCustomMob(LivingEntity entity, String id) {
        entity.addScoreboardTag(ISCUSTOM);
        entity.addScoreboardTag(TagUtil.getMobTagString(id));
    }


    public static boolean isEntityCustom(Entity entity) {
        return entity.getScoreboardTags().contains(ISCUSTOM);
    }


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
