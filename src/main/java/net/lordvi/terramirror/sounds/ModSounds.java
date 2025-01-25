package net.lordvi.terramirror.sounds;

import net.lordvi.terramirror.TerraMirror;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> MOD_SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, TerraMirror.MODID);

    public static final RegistryObject<SoundEvent> MIRROR_TELEPORT_SOUND = registerSoundEffects("mirror_teleport_sound");

    private static RegistryObject<SoundEvent> registerSoundEffects(String name) {
        return MOD_SOUNDS.register(name,
                () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TerraMirror.MODID, name))
        );
    }

    public static void register(IEventBus bus) {
        MOD_SOUNDS.register(bus);
    }
}
