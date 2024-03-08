package dev.cryptic.encryptedapi.registry;

import dev.cryptic.encryptedapi.EncryptedAPI;
import dev.cryptic.encryptedapi.vfx.particle.CubeParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
        DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EncryptedAPI.MODID);


    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
