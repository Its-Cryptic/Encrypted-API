package dev.cryptic.encryptedapi.setup;

import dev.cryptic.encryptedapi.api.attribute.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModSetup {
    public static void registers(IEventBus modEventBus) {
        Attributes.ATTRIBUTES.register(modEventBus);
    }
}
