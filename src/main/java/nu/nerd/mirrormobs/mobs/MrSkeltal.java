package nu.nerd.mirrormobs.mobs;


import nu.nerd.mirrormirror.ExtendedEntity;
import nu.nerd.mirrormirror.Pathfinding.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;


public class MrSkeltal extends CustomMob {


    public MrSkeltal() {
        super("mrskeltal");
    }


    public LivingEntity spawn(Location loc) {
        LivingEntity ent = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
        ent.setCustomName("Mr Skeletal");
        ent.setCustomNameVisible(true);
        ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(80D);
        ent.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4F);
        ent.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(3D);
        ent.setHealth(80D);
        addInventory(ent);
        return ent;
    }


    public void initAI(LivingEntity entity) {
        ExtendedEntity e = new ExtendedEntity(entity);
        e.clearGoals();
        e.injectGoal(1, new FloatGoal(entity));
        e.injectGoal(2, new BowShoot(entity, 0.25D, 20, 10.0F));
        e.injectGoal(3, new FleeSun(entity, 1.0d));
        e.injectGoal(4, new LookAtPlayer(entity));
        e.injectGoal(6, new RandomLookAround(entity));
        e.injectGoal(8, new RandomStrollLand(entity, 0.8D));
        e.clearTargets();
        e.injectTarget(1, new NearestAttackableTarget(entity, "EntityHuman"));
    }


    private void addInventory(LivingEntity entity) {
        ItemStack item = new ItemStack(Material.BOW);
        item.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 6);
        item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        entity.getEquipment().setItemInMainHand(item);
        entity.getEquipment().setItemInOffHandDropChance(0f);
    }


}
