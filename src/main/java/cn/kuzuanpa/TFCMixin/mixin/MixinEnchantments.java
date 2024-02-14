package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentDamage.class)

public class MixinEnchantments extends Enchantment{
    protected MixinEnchantments(int p_i1926_1_, int p_i1926_2_, EnumEnchantmentType p_i1926_3_) {
        super(p_i1926_1_, p_i1926_2_, p_i1926_3_);
    }

    @Inject(method = "func_152376_a",at=@At("RETURN"),cancellable = true)
    public void modifyDamage(int p_152376_1_, EnumCreatureAttribute p_152376_2_, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(cir.getReturnValueF()*50.0F);
    }
}
