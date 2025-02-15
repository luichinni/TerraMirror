package net.lordvi.terramirror.items;

import net.lordvi.terramirror.effects.ModEffects;
import net.lordvi.terramirror.sounds.ModSounds;
import net.lordvi.terramirror.utils.LevelStorage;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MagicMirror extends Item {

    public MagicMirror(Properties props) {
        super(props);
    }

    protected void GoToBed(Player player, Level level) {
        if (player.isLocalPlayer()) {
            player.playSound(ModSounds.MIRROR_TELEPORT_SOUND.get());
            return;
        }

        BlockPos pos = LevelStorage.getSavedPosition(player);

        if (pos != null) {
            player.teleportTo((ServerLevel) Objects.requireNonNull(LevelStorage.getSavedLevel(player)), pos.getX(), pos.getY(), pos.getZ(), new HashSet<RelativeMovement>(), 0, 0);
            player.setInvulnerable(true);

            String message = "§eBienvenido a casa!";
            player.sendSystemMessage(Component.literal(message));

            // para quitar la invulnerabilidad luego de 2 segs
            scheduler.schedule(() -> {
                player.setInvulnerable(false);
            }, 2, TimeUnit.SECONDS);

            if (!player.hasEffect(ModEffects.HOME_SWEET_HOME_EFFECT.get())){
                player.addEffect(new MobEffectInstance(ModEffects.HOME_SWEET_HOME_EFFECT.get(),20 * 15));
                this.firstTeleportEffect(player, level);
            }else{
                this.teleportEffect(player, level);
            }
        }
    }

    protected void firstTeleportEffect(Player player, Level level) {
    }

    protected void teleportEffect(Player player, Level level) {
        player.setHealth(player.getHealth()-1f);
        player.animateHurt(0.1f);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getLevel().isClientSide) return InteractionResult.CONSUME;

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState bloque = level.getBlockState(pos);

        if (bloque.getBlock().getDescriptionId().contains("_bed") && level.dimensionType().bedWorks()) {
            LevelStorage.saveLevelData(Objects.requireNonNull(context.getPlayer()), context.getClickedPos());
            String message = "§bPunto de retorno establecido!";
            context.getPlayer().sendSystemMessage(Component.literal(message));
        }else{
            this.GoToBed(context.getPlayer(), level);
        }

        return InteractionResult.SUCCESS;
    }

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, InteractionHand interactHand) {
        this.GoToBed(player,level);

        return super.use(level, player, interactHand);
    }
}
