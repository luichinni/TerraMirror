package dev.lordvi.terramirror.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class MagicMirrorPro extends MagicMirror {
    public MagicMirrorPro(Properties props) {
        super(props);
    }

    @Override
    protected void firstTeleportEffect(Player player, Level level) {
        teleportEffect(player, level);
    }

    @Override
    protected void teleportEffect(Player player, Level level) {
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION,20 * 5));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION,20 * 10));
    }
}
