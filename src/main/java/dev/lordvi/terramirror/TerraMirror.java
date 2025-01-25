package dev.lordvi.terramirror;

import dev.lordvi.terramirror.effects.ModEffects;
import dev.lordvi.terramirror.init.CreativeTabInit;
import dev.lordvi.terramirror.items.ModItems;
import dev.lordvi.terramirror.sounds.ModSounds;
import dev.lordvi.terramirror.utils.PlayerDeathEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TerraMirror.MODID)
public class TerraMirror {
    public static final String MODID = "terra_mirror";

    public TerraMirror(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();

        CreativeTabInit.TABS.register(bus);
        ModItems.ITEMS.register(bus);
        ModEffects.MOD_EFFECTS.register(bus);
        ModSounds.MOD_SOUNDS.register(bus);

        PlayerDeathEvents events = new PlayerDeathEvents();
        MinecraftForge.EVENT_BUS.register(events);
    }
}
