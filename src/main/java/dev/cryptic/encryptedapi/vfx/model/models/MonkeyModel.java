package dev.cryptic.encryptedapi.vfx.model.models;

import dev.cryptic.encryptedapi.EncryptedAPI;
import dev.cryptic.encryptedapi.vfx.model.ObjModel;
import net.minecraft.resources.ResourceLocation;

public class MonkeyModel extends ObjModel {
    public static final MonkeyModel INSTANCE = new MonkeyModel();
    @Override
    public ResourceLocation getModelLocation() {
        return EncryptedAPI.id("monkey");
    }
}
