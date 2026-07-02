package net.el_redstoniano.mccourse.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import net.el_redstoniano.mccourse.item.ModItems;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    public AbstractClientPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

//    @Inject(method = "getFieldOfViewModifier", at = @At(value = "TAIL"), cancellable = true)
//    private void getFieldOfViewModifierMixin(boolean b, float f, CallbackInfoReturnable<Float> info) {
//        float modifier = 1f;
//        if (this.isUsingItem() && this.getActiveItem().is(ModItems.KAUPEN_BOW)) {
//            float scale = Math.min(this.getTicksUsingItem() / 20.0F, 1.0F);
//            modifier *= 1.0F - Mth.square(scale) * 0.15F;
//            info.setReturnValue(Mth.lerp(Minecraft.getInstance().options.fovEffectScale().get().floatValue(), 1.0F, modifier));
//        }
//    }

    // Another advanced way to do the same thing targeting a more concrete piece of code:
    @WrapOperation(method = "getFieldOfViewModifier",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean getFieldOfViewModifierMixin(ItemStack instance, Object o, Operation<Boolean> original) {
        return original.call(instance, ModItems.KAUPEN_BOW) || original.call(instance, o);
    }
}
