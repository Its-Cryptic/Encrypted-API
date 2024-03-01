package dev.cryptic.encryptedapi.util.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public abstract class ObjModel {
    public Vector3f position = new Vector3f(0, 0, 0);
    private final Deque<ObjModel> model = new ArrayDeque<>();
    public ArrayList<Face> faces = new ArrayList<>();
    public float explosionPower = 0.0F;

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

    public ObjModel last() {
        return this.model.getLast();
    }

    public void push() {
        ObjModel model = this.model.getLast();
        this.model.addLast(model);
    }

    public void pop() {
        this.model.removeLast();
    }

    public void renderModel(PoseStack poseStack, VertexConsumer buffer, int packedLight, boolean invertNormal) {
        faces.forEach(face -> {
            face.renderTriangle(poseStack, buffer, packedLight, invertNormal);
        });
    }

    public void translate(float x, float y, float z) {
        for (Face face : faces) {
            face.translate(x, y, z);
        }
    }

    public void translate(Vector3f translation) {
        translate(translation.x(), translation.y(), translation.z());
    }

    public void translate(Vec3 translation) {
        translate((float) translation.x, (float) translation.y, (float) translation.z);
    }

    public void explodeModel(float power) {
        this.explosionPower = power;
    }
}
