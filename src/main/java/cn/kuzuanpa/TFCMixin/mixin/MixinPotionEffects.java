package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Potion.class)

public class MixinPotionEffects {
    @Mutable @Shadow @Final public static Potion damageBoost;
    @Mutable @Shadow @Final public static Potion weakness;
    @ModifyConstant(method = "performEffect", constant=@Constant(floatValue = 1.0F),slice = @Slice(from = @At(value = "INVOKE",target = "Lnet/minecraft/entity/EntityLivingBase;getMaxHealth()F"),to = @At(value = "INVOKE",target = "Lnet/minecraft/entity/player/EntityPlayer;addExhaustion(F)V")))
    public float modifyRegenAndPoison(float constant) {
        return constant*35.0F;
    }
    @ModifyArg(method = "affectEntity", at= @At(value = "INVOKE",target = "Lnet/minecraft/entity/EntityLivingBase;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"), index = 1)
    public float modifyInstantDamage(float p_70097_2_){
        return p_70097_2_*50.0F;
    }
    @ModifyArg(method = "affectEntity", at= @At(value = "INVOKE",target = "Lnet/minecraft/entity/EntityLivingBase;heal(F)V"), index = 0)
    public float modifyInstantHealth(float p_70097_2_){
        return p_70097_2_*50.0F;
    }
}
