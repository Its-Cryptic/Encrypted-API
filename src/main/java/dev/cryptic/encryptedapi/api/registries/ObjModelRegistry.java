package dev.cryptic.encryptedapi.api.registries;

import dev.cryptic.encryptedapi.vfx.model.ObjModel;

import java.util.ArrayList;
import java.util.List;

public class ObjModelRegistry {
    private static List<ObjModel> OBJ_MODELS = new ArrayList<>();

    public static void registerModel(ObjModel objModel) {
        OBJ_MODELS.add(objModel);
    }

    public static void loadAllModels() {
        OBJ_MODELS.forEach(ObjModel::loadModelData);
    }


}
