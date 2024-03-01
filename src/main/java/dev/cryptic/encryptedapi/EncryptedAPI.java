package dev.cryptic.encryptedapi;

import com.mojang.logging.LogUtils;
import dev.cryptic.encryptedapi.registries.ObjModelRegistry;
import dev.cryptic.encryptedapi.setup.ModSetup;
import dev.cryptic.encryptedapi.util.model.ObjModel;
import dev.cryptic.encryptedapi.util.model.ObjParser;
import dev.cryptic.encryptedapi.util.model.SinModel;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.io.IOException;

@Mod(EncryptedAPI.MODID)
public class EncryptedAPI {
    public static final String MODID = "encryptedapi";
    public static final Logger LOGGER = LogUtils.getLogger();

    public EncryptedAPI() {
        ObjModelRegistry.registerModel(SinModel.INSTANCE);

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
        public static void onClientSetup(FMLClientSetupEvent event){
            ObjModelRegistry.loadAllModels();
        }

    }
}
