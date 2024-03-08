package dev.cryptic.encryptedapi.vfx.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ObjModel {
    public ArrayList<Face> faces = new ArrayList<>();

    public abstract ResourceLocation getModelLocation();

    public void loadModelData() {
        String modID = this.getModelLocation().getNamespace();
        String fileName = this.getModelLocation().getPath();
        ResourceLocation resourceLocation = new ResourceLocation(modID, "obj/" + fileName + ".obj");
        ObjParser objParser = new ObjParser();
        try {
            objParser.parseObjFile(resourceLocation);
            this.faces = objParser.getFaces();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders the model.
     *
     * @param poseStack     The pose stack.
     * @param buffer        The vertex consumer.
     * @param packedLight   The packed light. 0-255;
     * @param invertNormal  Whether to invert the normal.
     */
    public void renderModel(PoseStack poseStack, VertexConsumer buffer, int packedLight, boolean invertNormal) {
        faces.forEach(face -> {
            if (face.vertices().size() == 3) {
                face.renderTriangle(poseStack, buffer, packedLight, invertNormal);
            } else if (face.vertices().size() == 4) {
                face.renderQuad(poseStack, buffer, packedLight, invertNormal);
            } else {
                throw new RuntimeException("Face has invalid number of vertices. Supported vertex counts are 3 and 4.");
            }
        });
    }

}
