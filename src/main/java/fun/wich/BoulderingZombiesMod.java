package fun.wich;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class BoulderingZombiesMod implements ModInitializer {
	public static final String MOD_ID = "wich";
	public static final SoundEvent ENTITY_BOULDERING_ZOMBIE_AMBIENT = register("entity.bouldering_zombie.ambient");
	public static final SoundEvent ENTITY_BOULDERING_ZOMBIE_DEATH = register("entity.bouldering_zombie.death");
	public static final SoundEvent ENTITY_BOULDERING_ZOMBIE_HURT = register("entity.bouldering_zombie.hurt");
	public static final SoundEvent ENTITY_BOULDERING_ZOMBIE_STEP = register("entity.bouldering_zombie.step");
	public static final SoundEvent ENTITY_BOULDERING_ZOMBIE_AMBIENTCLIMB = register("entity.bouldering_zombie.ambientclimb");
	public static final SoundEvent ENTITY_BOULDERING_ZOMBIE_CLIMB = register("entity.bouldering_zombie.climb");
	public static final SoundEvent ENTITY_PARROT_IMITATE_BOULDERING_ZOMBIE = register("entity.parrot.imitate.bouldering_zombie");
	private static SoundEvent register(String path) {
		Identifier id = Identifier.of(MOD_ID, path);
		return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
	}
	public static final TagKey<Biome> TAG_SPAWNS_BOULDERING_ZOMBIES = TagKey.of(RegistryKeys.BIOME, Identifier.of(MOD_ID, "spawns_bouldering_zombies"));
	public static final EntityType<BoulderingZombieEntity> BOULDERING_ZOMBIE = register(
			"bouldering_zombie",
			EntityType.Builder.create(BoulderingZombieEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.6F, 1.99F)
					.eyeHeight(1.74F)
					.vehicleAttachment(-0.7F)
					.maxTrackingRange(8)
					.notAllowedInPeaceful()
	);
	private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type) {
		RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MOD_ID, name));
		EntityType<T> entityType = type.build(key);
		Registry.register(Registries.ENTITY_TYPE, key, entityType);
		return entityType;
	}
	public static final Item BOULDERING_ZOMBIE_SPAWN_EGG = register("bouldering_zombie_spawn_egg", SpawnEggItem::new, new Item.Settings().spawnEgg(BOULDERING_ZOMBIE));
	public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
		RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, name));
		Item item = itemFactory.apply(settings.registryKey(key));
		Registry.register(Registries.ITEM, key, item);
		return item;
	}

	@Override
	public void onInitialize() {
		//Attributes
		FabricDefaultAttributeRegistry.register(BOULDERING_ZOMBIE, BoulderingZombieEntity.createZombieAttributes());
		//Spawning
		SpawnRestriction.register(BOULDERING_ZOMBIE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BoulderingZombieEntity::canSpawnInDark);
		BiomeModifications.addSpawn(BiomeSelectors.tag(TAG_SPAWNS_BOULDERING_ZOMBIES),
				SpawnGroup.MONSTER, BOULDERING_ZOMBIE, 20, 1, 4);
		//Items
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(itemGroup -> itemGroup.add(BOULDERING_ZOMBIE_SPAWN_EGG));
	}
}