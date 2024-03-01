package dev.cryptic.encryptedapi.event;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.cryptic.encryptedapi.EncryptedAPI;
import dev.cryptic.encryptedapi.util.model.ObjParser;
import dev.cryptic.encryptedapi.util.model.ObjUtil;
import dev.cryptic.encryptedapi.util.model.SinModel;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import team.lodestar.lodestone.handlers.RenderHandler;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = EncryptedAPI.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

//        private static final ResourceLocation UV_TEST = new ResourceLocation(EncryptedAPI.MODID, "textures/vfx/uv_test.png");
//        @SubscribeEvent
//        public static void renderLevelGameStageEvent(RenderLevelStageEvent event) {
//            if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_SOLID_BLOCKS) return;
//            VertexConsumer vertexConsumer = RenderHandler.DELAYED_RENDER.getBuffer(LodestoneRenderTypeRegistry.TEXTURE.applyAndCache(UV_TEST));
//
//            Vec3 renderPos = new Vec3(0,0,0);
//            Vec3 cameraPos = event.getCamera().getPosition();
//            PoseStack poseStack = event.getPoseStack();
//            Vec3 relativePos = renderPos.subtract(cameraPos);
//            poseStack.pushPose();
//            poseStack.translate(relativePos.x, relativePos.y, relativePos.z);
//            SinModel.INSTANCE.renderModel(poseStack, vertexConsumer);
//
//            poseStack.popPose();
//        }


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