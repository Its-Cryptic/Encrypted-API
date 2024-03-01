package dev.cryptic.encryptedapi.util.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import dev.cryptic.encryptedapi.EncryptedAPI;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;

import java.io.IOException;
import java.util.List;

public class ObjUtil {
    public static ObjParser getObjModel(String modid, String filename) {
        ResourceLocation resourceLocation = new ResourceLocation(modid, "obj/"+filename+".obj");
        ObjParser objParser = new ObjParser();
        try {
            objParser.parseObjFile(resourceLocation);
            return objParser;
        } catch (IOException e) {
            EncryptedAPI.LOGGER.error("Failed to parse obj file: " + resourceLocation, e);
            e.printStackTrace();
        }
        return null;
    }

    public static void renderObj(ObjParser objParser, PoseStack poseStack, VertexConsumer buffer) {
        objParser.getFaces().forEach(face -> {
            renderTriangle(poseStack, buffer, face, LightTexture.FULL_BRIGHT, false);
        });
    }

    private static void renderTriangle(PoseStack poseStack, VertexConsumer buffer, Face face, int packedLight, boolean invertNormal) {
        Matrix4f matrix4f = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();
        List<Vector3f> vertices = face.vertices();
        Vector3f normal = face.normal();
        if (invertNormal) normal.mul(-1);
        List<Vec2> uvs = face.uvs();

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
}
