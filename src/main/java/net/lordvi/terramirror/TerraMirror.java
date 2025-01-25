package net.lordvi.terramirror;

import net.lordvi.terramirror.effects.ModEffects;
import net.lordvi.terramirror.init.CreativeTabInit;
import net.lordvi.terramirror.items.ModItems;
import net.lordvi.terramirror.sounds.ModSounds;
import net.lordvi.terramirror.utils.PlayerDeathEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TerraMirror.MODID)
public class TerraMirror {
    public static final String MODID = "terramirror";

    public TerraMirror() {
        // deprecado en 1.21+, lo dejo por si arranca para 1.20.1
        this(FMLJavaModLoadingContext.get());
    }

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
