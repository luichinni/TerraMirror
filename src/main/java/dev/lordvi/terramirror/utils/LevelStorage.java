package dev.lordvi.terramirror.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class LevelStorage {
    public static final String DIMENSION_KEY = "last_dimension";

    public static final String POS_X_KEY = "last_pos_x";
    public static final String POS_Y_KEY = "last_pos_y";
    public static final String POS_Z_KEY = "last_pos_z";

    public static void saveLevelData(@NotNull Player player, @NotNull BlockPos pos) {
        CompoundTag persistentData = player.getPersistentData();

        ResourceKey<Level> dimensionKey = player.level().dimension();

        persistentData.putString(DIMENSION_KEY, dimensionKey.location().toString());

        persistentData.putInt(POS_X_KEY, pos.getX());
        persistentData.putInt(POS_Y_KEY, pos.getY()+1);
        persistentData.putInt(POS_Z_KEY, pos.getZ());
    }

    public static Level getSavedLevel(@NotNull Player player) {

        CompoundTag data = player.getPersistentData();
        if (!data.contains(DIMENSION_KEY)) return null;

        AtomicReference<ServerLevel> lv_ret = new AtomicReference<>();

        Objects.requireNonNull(player.getServer()).getAllLevels().forEach((l) -> {
            if (l.dimension().location().toString().equals(data.getString(DIMENSION_KEY))){
                lv_ret.set(l);
            }
        });

        return lv_ret.get();
    }

    public static BlockPos getSavedPosition(@NotNull Player player) {
        if (player.getServer() == null) return null;

        CompoundTag data = player.getPersistentData();
        if (!data.contains(POS_X_KEY)) return null;

        int x = data.getInt(POS_X_KEY);
        int y = data.getInt(POS_Y_KEY);
        int z = data.getInt(POS_Z_KEY);

        return new BlockPos(x, y, z);
    }
}
