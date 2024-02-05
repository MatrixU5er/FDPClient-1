/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/SkidderMC/FDPClient/
 */
package net.ccbluex.liquidbounce.injection.forge.mixins.performance;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(targets = "net.minecraft.world.GameRules$Value")
public class MixinGameRulesValue {
    @Shadow
    private String valueString;

    @Inject(method = "setValue(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    private void cancelIfUnchanged(String value, CallbackInfo ci) {
        if (Objects.equals(this.valueString, value)) {
            ci.cancel();
        }
    }
}