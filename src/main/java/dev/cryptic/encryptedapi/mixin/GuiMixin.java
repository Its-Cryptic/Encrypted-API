package dev.cryptic.encryptedapi.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.cryptic.encryptedapi.vfx.crosshair.CrosshairHandler;
import dev.cryptic.encryptedapi.vfx.sprite.VFXSpriteLibrary;
import net.minecraft.client.AttackIndicatorStatus;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Gui.class)
public abstract class GuiMixin {


    @Shadow @Final protected static ResourceLocation GUI_ICONS_LOCATION;
    @Shadow @Final protected Minecraft minecraft;
    @Shadow protected int screenWidth;
    @Shadow protected int screenHeight;

    @Redirect(method = "renderCrosshair", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"))
    public void renderCrosshair(GuiGraphics instance, ResourceLocation p_283377_, int p_281970_, int p_282111_, int p_283134_, int p_282778_, int p_281478_, int p_281821_) {
        if (!CrosshairHandler.shouldRenderCustomCrosshair()) {
            encryptedAPI$renderOriginalCrosshair(instance);
        } else {
            encryptedAPI$renderNewCrosshair(instance);
        }
    }

    // This is the original method
    @Unique
    public void encryptedAPI$renderOriginalCrosshair(GuiGraphics guiGraphics) {
        guiGraphics.blit(GUI_ICONS_LOCATION,
                (this.screenWidth - 15) / 2,
                (this.screenHeight - 15) / 2,
                0,
                0,
                15,
                15
        );
    }

    @Unique
    public void encryptedAPI$renderNewCrosshair(GuiGraphics guiGraphics) {
        PoseStack posestack = guiGraphics.pose();
        float scaleFactor = 1.0f;
        int imageWidth = 256;

        posestack.pushPose();
        posestack.scale(scaleFactor, scaleFactor, scaleFactor);
        guiGraphics.blit(VFXSpriteLibrary.Misc.UV_GRID,
                (int) ((this.screenWidth/scaleFactor - imageWidth)/2),
                (int) ((this.screenHeight/scaleFactor - imageWidth)/2),
                0,
                0,
                imageWidth,
                imageWidth
        );
        posestack.popPose();
    }


}
