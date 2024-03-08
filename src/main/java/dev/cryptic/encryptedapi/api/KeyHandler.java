package dev.cryptic.encryptedapi.api;

import dev.cryptic.encryptedapi.EncryptedAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = EncryptedAPI.MODID, value = Dist.CLIENT)
public class KeyHandler {
    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final Options options = minecraft.options;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (true) return;
        boolean isKeyDown = options.keyDown.isDown();
        boolean isKeyPressed = options.keyDown.consumeClick();
        long ticks = minecraft.level.getGameTime();
        EncryptedAPI.LOGGER.info("" + isKeyDown + ", " + isKeyPressed + ", " + ticks);
//        if (options.keyDown.consumeClick()) {
//            EncryptedAPI.LOGGER.info("Key down!");
//        }
    }

    static boolean lastDown = false;
    static boolean lastPress = false;
    static boolean tapDetected = false;
    static boolean tapStart = false;

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;
        if (minecraft.level == null) return;

        boolean down = options.keyDown.isDown();
        boolean press = options.keyDown.consumeClick();
        long ticks = minecraft.level.getGameTime();

        //EncryptedAPI.LOGGER.info("{} {} {}", down, press, ticks);

        // Start of a tap or direct single tap without "true false" state
        if (!tapStart && down && press) {
            tapDetected = true;
            tapStart = true;
        } else if (tapStart && tapDetected && !down) {
            // Confirming single tap
            //EncryptedAPI.LOGGER.info("Single tap detected at tick {}", ticks);
            tapDetected = false;
            tapStart = false;
        } else if (!down) {
            // Reset on key release
            tapDetected = false;
            tapStart = false;
        }

        // Update last states
        lastDown = down;
        lastPress = press;
    }

    static long startTime = System.currentTimeMillis();
    static boolean moveKeyPressed = false;
    static boolean keyEnabled = false;

    @SubscribeEvent
    public static void movement(MovementInputUpdateEvent event) {
        EncryptedAPI.LOGGER.info("Logged movement event!");
        boolean keyPressed = event.getInput().down;

        if(!keyEnabled){
            if(!moveKeyPressed && keyPressed){
                long now = System.currentTimeMillis();

                if(now > startTime + 300){
                    startTime = now;
                }else{
                    keyEnabled = true;
                    EncryptedAPI.LOGGER.info("Double tap detected!");
                    // do stuff on double tap...
                }

                moveKeyPressed = true;
            }else{ // optional
                if(!moveKeyPressed && keyPressed && System.currentTimeMillis() > startTime + 300){
                    keyEnabled = false;
                    // do stuff to cancel double tap action if key is pressed again...
                    // useful if you need to allow the player to override the keypress and cancel the double tap action
                }
            }

            // detect key release
            if(!keyPressed){
                moveKeyPressed = false;
            }
        }
    }

}
