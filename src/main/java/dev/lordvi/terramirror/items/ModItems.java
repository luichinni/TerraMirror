package dev.lordvi.terramirror.items;

import dev.lordvi.terramirror.TerraMirror;
import dev.lordvi.terramirror.init.CreativeTabInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TerraMirror.MODID);

    public static final RegistryObject<Item> MAGIC_MIRROR = CreativeTabInit.addToTab(
            ITEMS.register(
                    "magic_mirror",
                    () -> new MagicMirror(new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.UNCOMMON)
                    )
            )
    );

    public static final RegistryObject<Item> MAGIC_MIRROR_PRO = CreativeTabInit.addToTab(
            ITEMS.register(
                    "magic_mirror_pro",
                    () -> new MagicMirrorPro(new Item.Properties()
                            .stacksTo(1)
                            .rarity(Rarity.EPIC)
                    )
            )
    );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
