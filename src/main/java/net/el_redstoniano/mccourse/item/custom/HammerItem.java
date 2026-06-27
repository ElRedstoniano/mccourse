package net.el_redstoniano.mccourse.item.custom;

import com.jcraft.jorbis.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HammerItem extends Item {
    private static final Set<BlockPos> HARVESTED_BLOCKS =  new HashSet<>();

    public HammerItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState state, BlockPos pos, LivingEntity owner) {
        if (itemStack.getItem() instanceof HammerItem hammerItem && owner instanceof ServerPlayer serverPlayer) {
            if (HARVESTED_BLOCKS.contains(pos)) {
                return true; // Broken block
            }

            Tool tool = itemStack.get(DataComponents.TOOL);
            if (tool == null) {
                return false;
            } else {
                for (BlockPos position : getBlocksToBeDestroyed(1, pos, serverPlayer)) {
                    // This was required to be added for damaging the item
                    if (!level.isClientSide() && state.getDestroySpeed(level, pos) != 0.0F && tool.damagePerBlock() > 0) {
                        itemStack.hurtAndBreak(tool.damagePerBlock(), owner, EquipmentSlot.MAINHAND);
                    }

                    if (pos == position || !hammerItem.isCorrectToolForDrops(itemStack, level.getBlockState(position))) {
                        // Si la posición actual es la principal que se está destruyendo actualmente o si la posición que se está intentando
                        // destruir no es la correcta
                        continue;
                    }

                    // Es necesario tener una lista temporal de bloques a destruir, ya que al destruir otro bloque el mismo evento
                    // se vuelve a llamar y se volvería a empezar desde el principio ejecutando el mismo método_ cambiando la
                    // posición inicial, y al comprobar si el bloque destruido ya estaba añadido a la lista se puede evitar
                    // volver a ejecutar el mismo evento para otros bloques que no sean el de la posición inicial.
                    HARVESTED_BLOCKS.add(position);
                    serverPlayer.gameMode.destroyBlock(position);
                    HARVESTED_BLOCKS.remove(position);
                }
            }
        }
        return true; // Block not broken

    }

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    public List<BlockPos> getBlocksToBeDestroyed(int range, BlockPos initalBlockPos, ServerPlayer player) {
        List<BlockPos> positions = new ArrayList<>();
        BlockHitResult traceResult = player.level().clip(new ClipContext(player.getEyePosition(1f),
                (player.getEyePosition(1f).add(player.getViewVector(1f).scale(6f))),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        if (traceResult.getDirection() == Direction.DOWN || traceResult.getDirection() == Direction.UP) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY(), initalBlockPos.getZ() + y));
                }
            }
        }

        if (traceResult.getDirection() == Direction.NORTH || traceResult.getDirection() == Direction.SOUTH) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX() + x, initalBlockPos.getY() + y, initalBlockPos.getZ()));
                }
            }
        }

        if (traceResult.getDirection() == Direction.EAST || traceResult.getDirection() == Direction.WEST) {
            for (int x = -range; x <= range; x++) {
                for (int y = -range; y <= range; y++) {
                    positions.add(new BlockPos(initalBlockPos.getX(), initalBlockPos.getY() + y, initalBlockPos.getZ() + x));
                }
            }
        }

        return positions;
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity mob, LivingEntity attacker) {
        mob.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 300), attacker);
        super.postHurtEnemy(itemStack, mob, attacker);
    }
}
