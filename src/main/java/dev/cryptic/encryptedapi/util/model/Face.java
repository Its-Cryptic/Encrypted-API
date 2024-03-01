package dev.cryptic.encryptedapi.util.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.util.List;

public record Face(List<Vector3f> vertices, Vector3f normal, List<Vec2> uvs) {
    public void renderTriangle(PoseStack poseStack, VertexConsumer buffer, int packedLight, boolean invertNormal) {
        Matrix4f matrix4f = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();
        List<Vector3f> vertices = this.vertices();
        Vector3f normal = this.normal();
        if (invertNormal) normal.mul(-1);
        List<Vec2> uvs = this.uvs();

        for (int i = 0; i < 3; i++) {
            buffer.vertex(matrix4f, vertices.get(i).x(), vertices.get(i).y(), vertices.get(i).z())
                    .color(255, 255, 255, 255)
                    .uv(uvs.get(i).x, -uvs.get(i).y)
                    .overlayCoords(OverlayTexture.NO_OVERLAY)
                    .normal(normalMatrix, normal.x(), normal.y(), normal.z())
                    .uv2(packedLight)
                    .endVertex();
        }
        buffer.vertex(matrix4f, vertices.get(0).x(), vertices.get(0).y(), vertices.get(0).z())
                .color(255, 255, 255, 255)
                .uv(uvs.get(0).x, uvs.get(0).y)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .normal(normalMatrix, normal.x(), normal.y(), normal.z())
                .uv2(packedLight)
                .endVertex();
    }

    public void translate(float x, float y, float z) {
        for (Vector3f vertex : vertices) {
            vertex.set(vertex.x() + x, vertex.y() + y, vertex.z() + z);
        }
    }

    public void translate(Vector3f translation) {
        translate(translation.x(), translation.y(), translation.z());
    }

    public void translate(Vec3 translation) {
        translate((float) translation.x, (float) translation.y, (float) translation.z);
    }





}
