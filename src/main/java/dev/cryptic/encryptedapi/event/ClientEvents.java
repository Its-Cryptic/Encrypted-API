package dev.cryptic.encryptedapi.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dev.cryptic.encryptedapi.EncryptedAPI;
import dev.cryptic.encryptedapi.vfx.model.models.UVSphereModel;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector3f;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;


public class ClientEvents {
    @Mod.EventBusSubscriber(modid = EncryptedAPI.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        private static final ResourceLocation UV_TEST = EncryptedAPI.id("textures/vfx/misc/uv_grid.png");

//        private static final RenderTypeProvider TEST_RENDER_TYPE = new RenderTypeProvider((texture) ->
//                LodestoneRenderTypeRegistry.createGenericRenderType(
//                        EncryptedAPI.MODID,
//                        "test_texture",
//                        POSITION_COLOR_TEX_LIGHTMAP,
//                        VertexFormat.Mode.QUADS,
//                        LodestoneShaderRegistry.LODESTONE_TEXTURE.getShard(),
//                        EncryptedStateShards.NORMAL_TRANSPARENCY,
//                        new RenderStateShard.TextureStateShard(texture, false, false),
//                        StateShards.CULL
//                ));
//
        @SubscribeEvent
        public static void renderPlayerEvent(RenderPlayerEvent event) {
            float scale = 0.8f;
            Vector3f renderPos = new Vector3f(0.0f, 2.5f, 0.0f);
            PoseStack poseStack = event.getPoseStack();

            VertexConsumer vertexConsumer = event.getMultiBufferSource().getBuffer(LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE.applyAndCache(UV_TEST));
            float time = event.getEntity().level().getGameTime() + event.getPartialTick();
            poseStack.pushPose();
            poseStack.translate(renderPos.x(), renderPos.y(), renderPos.z);
            poseStack.scale(scale, scale, scale);
            poseStack.mulPose(Axis.YP.rotationDegrees(time * 2.5f));
            UVSphereModel.INSTANCE.faces.forEach(face -> {
                Vector3f centroid = face.getCentroid().normalize();
                poseStack.pushPose();
                float faceScale = 0.5f;
                faceScale = faceScale + (float) Math.sin(time * 0.1f) * faceScale;
                //poseStack.translate(centroid.x * faceScale, centroid.y * faceScale, centroid.z * faceScale);
                face.renderFace(poseStack, vertexConsumer, event.getPackedLight(), false);
                poseStack.popPose();
            });
            //UVSphereModel.INSTANCE.renderModel(poseStack, vertexConsumer, event.getPackedLight(), true);
            poseStack.popPose();
        }
    }

    @Mod.EventBusSubscriber(modid = EncryptedAPI.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
//        @SubscribeEvent
//        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
//            event.register(KeyBinding.DRINKING_KEY);
//        }
//
//        @SubscribeEvent
//        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
//            event.registerAboveAll("thirst", ThirstHudOverlay.HUD_THIRST);
//        }

    }
}