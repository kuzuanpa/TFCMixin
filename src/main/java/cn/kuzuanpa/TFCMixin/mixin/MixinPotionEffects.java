package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.entity.SharedMonsterAttributes;
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
        return constant*50.0F;
    }
    @ModifyArg(method = "affectEntity", at= @At(value = "INVOKE",target = "Lnet/minecraft/entity/EntityLivingBase;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"), index = 1)
    public float modifyInstantDamage(float p_70097_2_){
        return p_70097_2_*50.0F;
    }
    @ModifyArg(method = "affectEntity", at= @At(value = "INVOKE",target = "Lnet/minecraft/entity/EntityLivingBase;heal(F)V"), index = 0)
    public float modifyInstantHealth(float p_70097_2_){
        damageBoost = new PotionAttackDamage(5, false, 9643043,4, 0).setPotionName("potion.damageBoost").func_111184_a(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 150.0D, 2);
        weakness = new PotionAttackDamage(18, true, 4738376,5, 0).setPotionName("potion.weakness").func_111184_a(SharedMonsterAttributes.attackDamage, "22653B89-116E-49DC-9B6B-9971489B5BE5", 100.0D, 0);
        return p_70097_2_*50.0F;
    }
}
