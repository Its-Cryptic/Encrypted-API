package dev.cryptic.encryptedapi.vfx.model.models;

import dev.cryptic.encryptedapi.EncryptedAPI;
import dev.cryptic.encryptedapi.vfx.model.ObjModel;
import net.minecraft.resources.ResourceLocation;

public class IcoSphereModel extends ObjModel {
    public static final IcoSphereModel INSTANCE = new IcoSphereModel();
    @Override
    public ResourceLocation getModelLocation() {
        return EncryptedAPI.id("ico_sphere");
    }

}
