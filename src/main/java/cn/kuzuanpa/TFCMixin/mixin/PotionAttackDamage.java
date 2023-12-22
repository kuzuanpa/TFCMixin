package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net.minecraft.potion.PotionAttackDamage")
public class PotionAttackDamage extends Potion {
    public PotionAttackDamage(int p_i1570_1_, boolean p_i1570_2_, int p_i1570_3_,int p_76399_1_, int p_76399_2_)
    {
        super(p_i1570_1_, p_i1570_2_, p_i1570_3_);
        super.setIconIndex(p_76399_1_,p_76399_2_);
    }
}
