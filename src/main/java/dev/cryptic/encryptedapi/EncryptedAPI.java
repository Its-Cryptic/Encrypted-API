package dev.cryptic.encryptedapi;

import com.mojang.logging.LogUtils;
import dev.cryptic.encryptedapi.api.registries.ObjModelRegistry;
import dev.cryptic.encryptedapi.setup.ModSetup;
import dev.cryptic.encryptedapi.vfx.model.models.IcoSphereModel;
import dev.cryptic.encryptedapi.vfx.model.models.MonkeyModel;
import dev.cryptic.encryptedapi.vfx.model.models.UVSphereModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(EncryptedAPI.MODID)
public class EncryptedAPI {
    public static final String MODID = "encryptedapi";
    public static final Logger LOGGER = LogUtils.getLogger();

    public EncryptedAPI() {
        registerModels();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        ModSetup.registers(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        //ModMessages.register();
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ObjModelRegistry.loadAllModels();
        }

    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }

    public static void registerModels() {
        ObjModelRegistry.registerModel(MonkeyModel.INSTANCE);
        ObjModelRegistry.registerModel(IcoSphereModel.INSTANCE);
        ObjModelRegistry.registerModel(UVSphereModel.INSTANCE);
    }
}
