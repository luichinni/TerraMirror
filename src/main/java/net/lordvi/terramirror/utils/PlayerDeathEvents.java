package net.lordvi.terramirror.utils;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Objects;

public class PlayerDeathEvents {
    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone event) {
        event.getOriginal().getPersistentData().getAllKeys().forEach(
                (key)->{
                    event.getEntity().getPersistentData().put(
                            key,
                            Objects.requireNonNull(event.getOriginal().getPersistentData().get(key))
                    );
                }
        );
    }

}
