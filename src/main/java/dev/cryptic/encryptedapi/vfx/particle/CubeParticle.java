package dev.cryptic.encryptedapi.vfx.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.cryptic.encryptedapi.vfx.model.models.IcoSphereModel;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class CubeParticle extends Particle {
    protected CubeParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
    }
    protected CubeParticle(ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(level, x, y, z, motionX, motionY, motionZ);
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        float x = (float)(Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x());
        float y = (float)(Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y());
        float z = (float)(Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z());

        PoseStack poseStack = RenderSystem.getModelViewStack();
        Matrix4f matrix = poseStack.last().pose();

        // Adjust rendering logic to render cube faces
        IcoSphereModel.INSTANCE.renderModel(poseStack, vertexConsumer, 255, false);
        //renderCube(matrix, vertexConsumer);
    }

    private void renderCube(Matrix4f matrix, VertexConsumer vertexConsumer) {

    }

    private void renderFace() {

    }



    @Override
    public ParticleRenderType getRenderType() {
        return null;
    }
}
