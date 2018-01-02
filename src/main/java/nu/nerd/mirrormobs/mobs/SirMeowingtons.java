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

public class SirMeowingtons extends CustomMob {


    public SirMeowingtons() {
        super("sirmeowingtons");
    }


    public LivingEntity spawn(Location loc) {
        LivingEntity ent = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
        ent.setCustomName("Sir Meowingtons");
        ent.setCustomNameVisible(true);
        ent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200D);
        ent.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4F);
        ent.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4.0D);
        ent.setHealth(200D);
        setCustomXP(1000);
        addInventory(ent);
        return ent;
    }


    public void initAI(LivingEntity entity) {
        entity.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
        ExtendedEntity e = new ExtendedEntity(entity);
        e.clearGoals();
        e.injectGoal(0, new FloatGoal(entity));
        e.injectGoal(2, new ZombieAttack(entity, 1.0d, false));
        e.injectGoal(5, new MoveTowardsRestriction(entity, 1.0d));
        e.injectGoal(7, new RandomStrollLand(entity, 1.0d));
        e.injectGoal(8, new LookAtPlayer(entity));
        e.injectGoal(8, new RandomLookAround(entity));
        e.clearTargets();
        e.injectTarget(1, new NearestAttackableTarget(entity, "EntityHuman"));
        e.injectTarget(2, new NearestAttackableTarget(entity, "EntityIronGolem"));
    }


    private void addInventory(LivingEntity entity) {
        ItemStack stick = new ItemStack(Material.STICK);
        stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 6);
        entity.getEquipment().setItemInMainHand(stick);
        entity.getEquipment().setItemInOffHandDropChance(0.5f);
        entity.getEquipment().setHelmet(new ItemStack(Material.PUMPKIN));
        entity.getEquipment().setHelmetDropChance(0f);
    }


}
