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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LordPuggleston extends CustomMob {


    public LordPuggleston() {
        super("lordpuggleston");
    }


    public LivingEntity spawn(Location loc) {
        LivingEntity ent = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.CAVE_SPIDER);
        ent.setCustomName("Lord Puggleston");
        ent.setCustomNameVisible(true);
        ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20D);
        ent.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.7F);
        ent.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(2D);
        addInventory(ent);
        return ent;
    }


    public void initAI(LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
        ExtendedEntity e = new ExtendedEntity(entity);
        e.clearGoals();
        e.injectGoal(0, new MeleeAttack(entity, 1.0d, false));
        e.injectGoal(1, new FloatGoal(entity));
        e.injectGoal(2, new LeapAtTarget(entity, 0.2f));
        e.injectGoal(3, new RandomStrollLand(entity, 0.3d));
        e.injectGoal(4, new LookAtPlayer(entity));
        e.injectGoal(6, new RandomLookAround(entity));
        e.clearTargets();
        e.injectTarget(1, new NearestAttackableTarget(entity, "EntityHuman"));
    }


    private void addInventory(LivingEntity entity) {
        ItemStack item = new ItemStack(Material.RED_ROSE);
        item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
        entity.getEquipment().setItemInMainHand(item);
        entity.getEquipment().setItemInOffHandDropChance(0f);
    }


}
