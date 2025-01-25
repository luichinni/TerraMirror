package net.lordvi.terramirror.effects;

import net.lordvi.terramirror.TerraMirror;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TerraMirror.MODID);

    public static final RegistryObject<MobEffect> HOME_SWEET_HOME_EFFECT = MOD_EFFECTS.register(
            "home_sweet_home_effect",
            () -> new HomeSweetHomeEffect(MobEffectCategory.NEUTRAL, 0xf88379)
    );

    public static void register(IEventBus bus) {
        MOD_EFFECTS.register(bus);
    }
}
