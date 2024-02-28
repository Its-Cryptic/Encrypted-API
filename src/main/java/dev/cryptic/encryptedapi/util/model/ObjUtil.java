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
    public static ObjParser getObjModel(ResourceLocation resourceLocation) {
        resourceLocation.getPath();
        ResourceLocation resourceL = new ResourceLocation(resourceLocation.getNamespace(), "obj/"+resourceLocation.getPath()+"obj");
        ObjParser objParser = new ObjParser();
        try {
            objParser.parseObjFile(resourceLocation);
            return objParser;
        } catch (IOException e) {
            EncryptedAPI.LOGGER.error("Failed to parse obj file: " + resourceL, e);
            e.printStackTrace();
        }
        return null;
    }

    public static void renderObj(ObjParser objParser, PoseStack poseStack, VertexConsumer buffer) {
        objParser.getFaces().forEach(face -> {
            renderTriangle(poseStack, buffer, face, 0);
        });
    }

//    private void renderTriangle(ObjParser objParser, PoseStack poseStack, VertexConsumer buffer, ObjParser.Face face, int packedLight) {
//        Vector3f[] points = new Vector3f[3];
//        Vec2[] uvs = new Vec2[3];
//        Vector3f[] normals = new Vector3f[3];
//        for (int i = 0; i < 3; i++) {
//            int vertexIndex = face.vertexIndices[i];
//            int uvIndex = face.textureIndices[i];
//            int normalIndex = face.normalIndices[i];
//            Vector3f vertex = objParser.vertices.get(vertexIndex);
//            Vec2 uv = objParser.textures.get(uvIndex);
//            Vector3f normal = objParser.normals.get(normalIndex);
//            points[i] = vertex;
//            uvs[i] = uv;
//            normals[i] = normal;
//        }
//        renderTriangle(poseStack, buffer, points, uvs, normals, packedLight);
//    }

    private void renderTriangle(PoseStack poseStack, VertexConsumer buffer, Vector3f[] points, Vec2[] uvs, Vector3f[] normals, int packedLight) {
        Matrix4f matrix = poseStack.last().pose();
        Matrix3f normalMatrix = poseStack.last().normal();

        for (int i = 0; i < 3; i++) {
            buffer.vertex(matrix, points[i].x(), points[i].y(), points[i].z())
                    .color(255, 255, 255, 255)
                    .uv(uvs[i].x, -uvs[i].y)
                    .overlayCoords(OverlayTexture.NO_OVERLAY)
                    .normal(normalMatrix, normals[i].x(), normals[i].y(), normals[i].z())
                    .uv2(packedLight)
                    .endVertex();
        }
        buffer.vertex(matrix, points[0].x(), points[0].y(), points[0].z())
                .color(255, 255, 255, 255)
                .uv(uvs[0].x, uvs[0].y)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .normal(normalMatrix, normals[0].x(), normals[0].y(), normals[0].z())
                .uv2(packedLight)
                .endVertex();
    }

    private void renderTriangle(PoseStack poseStack, VertexConsumer buffer, Face face, int packedLight, boolean invertNormal) {
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
                .uv(uvs[0].x, uvs[0].y)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .normal(normalMatrix, normal.x(), normal.y(), normal.z())
                .uv2(packedLight)
                .endVertex();
    }
}
