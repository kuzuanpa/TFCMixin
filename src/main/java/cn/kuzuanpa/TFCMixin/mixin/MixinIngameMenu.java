package cn.kuzuanpa.TFCMixin.mixin;

import cn.kuzuanpa.TFCMixin.TFCMixin;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cn.kuzuanpa.TFCMixin.TFCMixin.loadScreenTips;

@Mixin(GuiIngameMenu.class)
public abstract class MixinIngameMenu extends GuiScreen {
    @Inject(method = "drawScreen", at = @At("TAIL"))
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_, CallbackInfo ci) {
        if(!TFCMixin.tipsLoaded)loadScreenTips();
        int pointer = (int) ((System.currentTimeMillis()/5000) % TFCMixin.tips.size());
        drawCenteredString(fontRendererObj, TFCMixin.tips.get(pointer), width / 2, height - 10, 16777215);
    }
}
