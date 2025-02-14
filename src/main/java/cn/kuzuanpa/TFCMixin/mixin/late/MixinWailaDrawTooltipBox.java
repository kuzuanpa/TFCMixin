package cn.kuzuanpa.TFCMixin.mixin.late;

import cn.kuzuanpa.TFCMixin.TFCMixin;
import mcp.mobius.waila.overlay.OverlayRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OverlayRenderer.class)
public class MixinWailaDrawTooltipBox {
    @Inject(method = "drawTooltipBox", at = @At("HEAD"), cancellable = true, remap = false)
    private static void render(int x, int y, int w, int h, int bg, int grad1, int grad2, CallbackInfo ci) {

        float f = (float)(grad1 >> 24 & 255) / 255.0F;

        GL11.glColor4f(1,1,1,f);
        TFCMixin.RenderWailaBackground.draw(x,y,w,h);
        GL11.glColor4f(1,1,1,1);
        ci.cancel();
    }

}
