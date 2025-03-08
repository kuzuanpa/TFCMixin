package cn.kuzuanpa.TFCMixin.mixin.early;

import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(FontRenderer.class)
public class MixinUnicodeFontShadow {
    @ModifyConstant(method = "renderStringAtPos", constant = @Constant(floatValue = 1.0F))
    private float TFCMixin$fixShadowDistance(float constant){
        return 0.5F;
    }
}
