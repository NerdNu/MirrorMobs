package nu.nerd.mirrormobs.mobs;


import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Rabbit;


public class KillerRabbit extends CustomMob {


    public KillerRabbit() {
        super("killerrabbit");
    }


    public LivingEntity spawn(Location loc) {
        LivingEntity ent = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.RABBIT);
        ((Rabbit) ent).setRabbitType(Rabbit.Type.THE_KILLER_BUNNY);
        ent.setCustomName("The Killer Rabbit of Caerbannog");
        ent.setCustomNameVisible(true);
        ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20D);
        ent.setHealth(20D);
        return ent;
    }


    public void initAI(LivingEntity entity) {}


}
