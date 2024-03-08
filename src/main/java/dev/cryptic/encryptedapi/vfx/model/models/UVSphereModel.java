package dev.cryptic.encryptedapi.vfx.model.models;

import dev.cryptic.encryptedapi.EncryptedAPI;
import dev.cryptic.encryptedapi.vfx.model.ObjModel;
import net.minecraft.resources.ResourceLocation;

/**
 * A model representing a UV sphere.
 * 16 segments, 16 rings.
 */
public class UVSphereModel extends ObjModel {
    public static final UVSphereModel INSTANCE = new UVSphereModel();

    @Override
    public ResourceLocation getModelLocation() {
        return EncryptedAPI.id("sphere_16_16");
    }
}
