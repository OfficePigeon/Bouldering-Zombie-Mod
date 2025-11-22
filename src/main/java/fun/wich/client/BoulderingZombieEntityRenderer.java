package fun.wich.client;

import fun.wich.BoulderingZombieEntity;
import fun.wich.BoulderingZombiesMod;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.Identifier;

public class BoulderingZombieEntityRenderer extends ZombieBaseEntityRenderer<BoulderingZombieEntity, ZombieEntityRenderState, ZombieEntityModel<ZombieEntityRenderState>> {
	private static final Identifier TEXTURE = Identifier.of(BoulderingZombiesMod.MOD_ID, "textures/entity/zombie/bouldering.png");
	public BoulderingZombieEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new BoulderingZombieEntityModel(context.getPart(BoulderingZombiesClient.BOULDERING_ZOMBIE)), new BoulderingZombieEntityModel(context.getPart(BoulderingZombiesClient.BOULDERING_ZOMBIE_BABY)), EquipmentModelData.mapToEntityModel(EntityModelLayers.ZOMBIE_EQUIPMENT, context.getEntityModels(), ZombieEntityModel<ZombieEntityRenderState>::new), EquipmentModelData.mapToEntityModel(EntityModelLayers.ZOMBIE_BABY_EQUIPMENT, context.getEntityModels(), ZombieEntityModel<ZombieEntityRenderState>::new));
	}
	public ZombieEntityRenderState createRenderState() { return new ZombieEntityRenderState(); }
	public Identifier getTexture(ZombieEntityRenderState zombieEntityRenderState) { return TEXTURE; }
}