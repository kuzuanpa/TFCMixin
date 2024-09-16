package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FurnaceRecipes.class)
public class MixinRemoveSandGlassRecipe {
    @Inject(at = @At("HEAD"), method = "func_151393_a", cancellable = true)
    public void TFCMixin$removeGlassRecipe(Block p_151393_1_, ItemStack p_151393_2_, float p_151393_3_, CallbackInfo ci){
        if(p_151393_1_.equals(Blocks.sand))ci.cancel();
    }
}
