package net.lordvi.terramirror.init;

import net.lordvi.terramirror.TerraMirror;
import net.lordvi.terramirror.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CreativeTabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TerraMirror.MODID);

    public static final List<Supplier<? extends ItemLike>> MOD_TAB_ITEMS = new ArrayList<>();

    public static final RegistryObject<CreativeModeTab> MAGIC_MIRROR_TAB = TABS.register("magic_mirror_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.magic_mirror_tab"))
                    .icon(ModItems.MAGIC_MIRROR.get()::getDefaultInstance)
                    .displayItems((displayParams, output) ->{
                        MOD_TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()));
                    })
                    .build()
    );

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        MOD_TAB_ITEMS.add(itemLike);
        return itemLike;
    }
}
