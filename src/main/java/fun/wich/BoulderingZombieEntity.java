package fun.wich;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SpiderNavigation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

public class BoulderingZombieEntity extends ZombieEntity {
	private static final TrackedData<Boolean> CLIMBING = DataTracker.registerData(BoulderingZombieEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	public BoulderingZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) { super(entityType, world); }
	@Override
	protected void initDataTracker(DataTracker.Builder builder) {
		super.initDataTracker(builder);
		builder.add(CLIMBING, false);
	}
	@Override protected EntityNavigation createNavigation(World world) { return new SpiderNavigation(this, world); }
	@Override
	public void tick() {
		super.tick();
		if (!this.getEntityWorld().isClient()) this.setClimbingWall(this.horizontalCollision);
	}
	@Override public boolean isClimbing() { return this.isClimbingWall(); }
	public boolean isClimbingWall() { return this.dataTracker.get(CLIMBING); }
	public void setClimbingWall(boolean climbing) { this.dataTracker.set(CLIMBING, climbing); }
	@Override
	protected void initEquipment(Random random, LocalDifficulty difficulty) {
		super.initEquipment(random, difficulty);
		this.equipStack(EquipmentSlot.CHEST, ItemStack.EMPTY);
	}
	@Override protected SoundEvent getAmbientSound() {
		return isClimbing() ? BoulderingZombiesMod.ENTITY_BOULDERING_ZOMBIE_AMBIENTCLIMB : BoulderingZombiesMod.ENTITY_BOULDERING_ZOMBIE_AMBIENT;
	}
	@Override protected SoundEvent getHurtSound(DamageSource source) { return BoulderingZombiesMod.ENTITY_BOULDERING_ZOMBIE_HURT; }
	@Override protected SoundEvent getDeathSound() { return BoulderingZombiesMod.ENTITY_BOULDERING_ZOMBIE_DEATH; }
	@Override protected SoundEvent getStepSound() {
		return isClimbing() ? BoulderingZombiesMod.ENTITY_BOULDERING_ZOMBIE_CLIMB : BoulderingZombiesMod.ENTITY_BOULDERING_ZOMBIE_STEP;
	}
}
