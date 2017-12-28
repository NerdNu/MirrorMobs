package nu.nerd.mirrormobs.mobs;


import nu.nerd.mirrormirror.ExtendedEntity;
import nu.nerd.mirrormirror.Pathfinding.*;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class Giant extends CustomMob {


    public Giant() {
        super("giant");
    }


    public LivingEntity spawn(Location loc) {
        LivingEntity ent = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.GIANT);
        ent.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3F);
        ent.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(80); //twice default for zombies
        return ent;
    }


    public void initAI(LivingEntity entity) {
        ExtendedEntity e = new ExtendedEntity(entity);
        e.injectGoal(0, new FloatGoal(entity));
        e.injectGoal(2, new MeleeAttack(entity, 1.0d, false));
        e.injectGoal(5, new MoveTowardsRestriction(entity, 1.0d));
        e.injectGoal(7, new RandomStrollLand(entity, 1.0d));
        e.injectGoal(8, new LookAtPlayer(entity));
        e.injectGoal(8, new RandomLookAround(entity));
        e.injectGoal(6, new MoveThroughVillage(entity, 1.0d, false));
        e.injectTarget(1, new HurtByTarget(entity, true, "EntityPigZombie"));
        e.injectTarget(2, new NearestAttackableTarget(entity, "EntityHuman"));
        e.injectTarget(3, new NearestAttackableTarget(entity, "EntityVillager"));
        e.injectTarget(3, new NearestAttackableTarget(entity, "EntityIronGolem"));
    }


}
