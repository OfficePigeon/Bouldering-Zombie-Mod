package fun.wich.client;

import fun.wich.BoulderingZombieEntity;
import fun.wich.BoulderingZombiesMod;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.util.Identifier;

public class BoulderingZombieEntityRenderer extends ZombieBaseEntityRenderer<BoulderingZombieEntity, ZombieEntityModel<BoulderingZombieEntity>> {
	private static final Identifier TEXTURE = Identifier.of(BoulderingZombiesMod.MOD_ID, "textures/entity/zombie/bouldering.png");
	public BoulderingZombieEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new BoulderingZombieEntityModel(context.getPart(BoulderingZombiesClient.BOULDERING_ZOMBIE)), new ZombieEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE_INNER_ARMOR)), new ZombieEntityModel<>(context.getPart(EntityModelLayers.ZOMBIE_OUTER_ARMOR)));
	}
	public Identifier getTexture(BoulderingZombieEntity state) { return TEXTURE; }
}