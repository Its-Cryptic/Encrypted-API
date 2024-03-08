package dev.cryptic.encryptedapi.vfx.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.cryptic.encryptedapi.vfx.model.models.IcoSphereModel;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.List;
import java.util.Vector;

public class SparkParticle extends Particle {
    protected SparkParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z);
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float partialTick) {
        Vec3 velocity = new Vec3(this.xd, this.yd, this.zd);
        double speed = velocity.length();

        Vec3 cameraPos = camera.getPosition();

        float xl = (float)(Mth.lerp(partialTick, this.xo, this.x) - cameraPos.x());
        float yl = (float)(Mth.lerp(partialTick, this.yo, this.y) - cameraPos.y());
        float zl = (float)(Mth.lerp(partialTick, this.zo, this.z) - cameraPos.z());
        int lightColor = this.getLightColor(partialTick);

    }

    private void renderCube(Matrix4f matrix4f, VertexConsumer buffer) {
        this.renderFace(matrix4f, buffer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F);
        this.renderFace(matrix4f, buffer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.renderFace(matrix4f, buffer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F);
        this.renderFace(matrix4f, buffer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F);
        this.renderFace(matrix4f, buffer, 0.0F, 1.0F, 0, 0, 0.0F, 0.0F, 1.0F, 1.0F);
        this.renderFace(matrix4f, buffer, 0.0F, 1.0F, 0, 0, 1.0F, 1.0F, 0.0F, 0.0F);
    }

    private void renderFace(Matrix4f matrix4f, VertexConsumer buffer, float v1, float v2, float v3, float v4, float v5, float v6, float v7, float v8) {
        buffer.vertex(matrix4f, v1, v3, v5).endVertex();
        buffer.vertex(matrix4f, v2, v3, v6).endVertex();
        buffer.vertex(matrix4f, v2, v4, v7).endVertex();
        buffer.vertex(matrix4f, v1, v4, v8).endVertex();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
}
