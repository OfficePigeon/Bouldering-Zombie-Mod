package fun.wich.client;

import fun.wich.BoulderingZombiesMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BoulderingZombiesClient implements ClientModInitializer {
	public static final EntityModelLayer BOULDERING_ZOMBIE = new EntityModelLayer(Identifier.of(BoulderingZombiesMod.MOD_ID, "bouldering_zombie"), "main");
	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(BOULDERING_ZOMBIE, BoulderingZombieEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(BoulderingZombiesMod.BOULDERING_ZOMBIE, BoulderingZombieEntityRenderer::new);
	}
}
