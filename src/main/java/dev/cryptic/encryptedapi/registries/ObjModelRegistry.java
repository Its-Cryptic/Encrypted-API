package dev.cryptic.encryptedapi.registries;

import dev.cryptic.encryptedapi.util.model.ObjModel;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjModelRegistry {
    private static List<ObjModel> OBJ_MODELS = new ArrayList<>();

    public static void registerModel(ObjModel objModel) {
        OBJ_MODELS.add(objModel);
    }

    public static void loadAllModels() {
        OBJ_MODELS.forEach(ObjModel::loadModelData);
    }


}
