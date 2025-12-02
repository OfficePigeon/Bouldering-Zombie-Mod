package fun.wich.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;

@Environment(EnvType.CLIENT)
public class BoulderingZombieEntityModel extends ZombieEntityModel<ZombieEntityRenderState> {
	public BoulderingZombieEntityModel(ModelPart modelPart) { super(modelPart); }
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0);
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4, -8, -4, 8, 8, 8), ModelTransform.NONE);
		modelPartData.addChild(EntityModelPartNames.HAT, ModelPartBuilder.create().uv(32, 0).cuboid(-4, -8, -4, 8, 8, 8, new Dilation(0.5f)), ModelTransform.NONE);
		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(16, 16).cuboid(-4, 0, -2, 8, 12, 4), ModelTransform.NONE);
		modelPartData.addChild(EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(34, 32).cuboid(-1, -2, -2, 5, 14, 4), ModelTransform.origin(5, 2, 0));
		modelPartData.addChild(EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(16, 32).cuboid(-4, -2, -2, 5, 14, 4), ModelTransform.origin(-5, 2, 0));
		modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(0, 34).cuboid(-1.9f, 0, -2, 4, 12, 4), ModelTransform.origin(1.9f, 12, 0));
		modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(0, 16).cuboid(-2.1f, 0, -2, 4, 12, 4), ModelTransform.origin(-1.9f, 12, 0));
		return TexturedModelData.of(modelData, 64, 64);
	}
}
