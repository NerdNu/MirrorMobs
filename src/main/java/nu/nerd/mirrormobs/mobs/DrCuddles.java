package nu.nerd.mirrormobs.mobs;

import nu.nerd.mirrormirror.ExtendedEntity;
import nu.nerd.mirrormirror.Pathfinding.*;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;


public class DrCuddles extends CustomMob {


    public DrCuddles() {
        super("drcuddles");
    }


    public LivingEntity spawn(Location loc) {
        LivingEntity ent = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
        ent.setCustomName("Dr Cuddles");
        ent.setCustomNameVisible(true);
        ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60D);
        ent.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4F);
        ent.setHealth(60D);
        return ent;
    }


    public void initAI(LivingEntity entity) {
        ExtendedEntity e = new ExtendedEntity(entity);
        e.setCreeperExplosionRadius(12);
        e.setCreeperMaxFuseTicks(8);
        e.clearGoals();
        e.injectGoal(1, new FloatGoal(entity));
        e.injectGoal(2, new Swell(entity));
        e.injectGoal(4, new LookAtPlayer(entity));
        e.injectGoal(6, new RandomLookAround(entity));
        e.clearTargets();
        e.injectTarget(1, new NearestAttackableTarget(entity, "EntityHuman"));
    }


}
