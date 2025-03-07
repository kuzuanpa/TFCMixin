package cn.kuzuanpa.TFCMixin.mixin.unicodeFont;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FontRenderer.class)
public class MixinUnicodeFontShadow {
    @ModifyConstant(method = "renderStringAtPos", constant = @Constant(floatValue = 1.0F))
    private float TFCMixin$fixShadowDistance(float constant, @Local(ordinal = 1) int j){
        //decide shadow distance not only by Game Setting, but also by the char itself.
        return j == -1? 0.5F:1.0F;
    }
}
