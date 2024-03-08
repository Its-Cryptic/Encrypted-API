package dev.cryptic.encryptedapi.vfx.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.util.RandomSource;

public class ParticleBuilder {
    private final RandomSource RANDOM = RandomSource.create();

    public static ParticleBuilder create(ParticleType<?> type) {
        return new ParticleBuilder(type);
    }

    private ParticleBuilder(ParticleType<?> type) {

    }
}
