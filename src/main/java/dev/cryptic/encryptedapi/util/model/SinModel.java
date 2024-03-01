package dev.cryptic.encryptedapi.util.model;

import dev.cryptic.encryptedapi.EncryptedAPI;
import net.minecraft.resources.ResourceLocation;

public class SinModel extends ObjModel {
    public static SinModel INSTANCE = new SinModel();

    @Override
    public ResourceLocation getModelLocation() {
        return new ResourceLocation(EncryptedAPI.MODID, "sin");
    }
}
