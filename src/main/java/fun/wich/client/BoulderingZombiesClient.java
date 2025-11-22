package fun.wich.client;

import fun.wich.BoulderingZombiesMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BoulderingZombiesClient implements ClientModInitializer {
	public static final EntityModelLayer BOULDERING_ZOMBIE = MakeModelLayer("bouldering_zombie");
	public static final EntityModelLayer BOULDERING_ZOMBIE_BABY = MakeModelLayer("bouldering_zombie_baby");
	private static EntityModelLayer MakeModelLayer(String path) {
		return new EntityModelLayer(Identifier.of(BoulderingZombiesMod.MOD_ID, path), "main");
	}
	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(BOULDERING_ZOMBIE, BoulderingZombieEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BOULDERING_ZOMBIE_BABY, () -> BoulderingZombieEntityModel.getTexturedModelData().transform(BipedEntityModel.BABY_TRANSFORMER));
		EntityRendererFactories.register(BoulderingZombiesMod.BOULDERING_ZOMBIE, BoulderingZombieEntityRenderer::new);
	}
}
