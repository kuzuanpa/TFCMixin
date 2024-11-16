package cn.kuzuanpa.TFCMixin.mixin;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderBlocks.class)
public abstract class MixinRenderBlocks {
    @Shadow
    public abstract IIcon getIconSafe(IIcon p_147758_1_);

    @Inject(method = "getBlockIcon(Lnet/minecraft/block/Block;)Lnet/minecraft/util/IIcon;",at = @At(value = "HEAD"), cancellable = true)
    public void tryCatchExceptions(Block p_147745_1_, CallbackInfoReturnable<IIcon> cir){
        try {
            cir.setReturnValue(this.getIconSafe(p_147745_1_.getBlockTextureFromSide(1)));
        }catch (Exception e){
            FMLLog.log(Level.ERROR,"Exception when getting Block Icon: "+p_147745_1_.getUnlocalizedName()+"method: getBlockTextureFromSide(1);");
            cir.setReturnValue(this.getIconSafe(null));
        }
    }
    @Inject(method = "getBlockIcon(Lnet/minecraft/block/Block;Lnet/minecraft/world/IBlockAccess;IIII)Lnet/minecraft/util/IIcon;",at = @At(value = "HEAD"), cancellable = true)
    public void tryCatchExceptions1(Block p_147793_1_, IBlockAccess p_147793_2_, int p_147793_3_, int p_147793_4_, int p_147793_5_, int p_147793_6_, CallbackInfoReturnable<IIcon> cir){
        try {
            cir.setReturnValue(this.getIconSafe(p_147793_1_.getIcon(p_147793_2_, p_147793_3_, p_147793_4_, p_147793_5_, p_147793_6_)));
        }catch (Exception e){
            FMLLog.log(Level.ERROR,"Exception when getting Block Icon: "+p_147793_1_.getUnlocalizedName()+"method: getIcon("+p_147793_2_+", "+p_147793_3_+", "+p_147793_4_+", "+p_147793_5_+", "+p_147793_6_+");");
            cir.setReturnValue(this.getIconSafe(null));
        }
    }
    @Inject(method = "getBlockIconFromSide",at = @At(value = "HEAD"), cancellable = true)
    public void tryCatchExceptions2(Block p_147777_1_, int p_147777_2_, CallbackInfoReturnable<IIcon> cir){
        try {
            cir.setReturnValue(this.getIconSafe(p_147777_1_.getBlockTextureFromSide(p_147777_2_)));
        }catch (Exception e){
            FMLLog.log(Level.ERROR,"Exception when getting Block Icon: "+p_147777_1_.getUnlocalizedName()+"method: getBlockTextureFromSide("+p_147777_2_+");");
            cir.setReturnValue(this.getIconSafe(null));
        }
    }
    @Inject(method = "getBlockIconFromSideAndMetadata",at = @At(value = "HEAD"), cancellable = true)
    public void tryCatchExceptions3(Block p_147787_1_, int p_147787_2_, int p_147787_3_, CallbackInfoReturnable<IIcon> cir){
        try {
            cir.setReturnValue(this.getIconSafe(p_147787_1_.getIcon(p_147787_2_, p_147787_3_)));
        }catch (Exception e){
            FMLLog.log(Level.ERROR,"Exception when getting Block Icon: "+p_147787_1_.getUnlocalizedName()+"method: getIcon("+p_147787_2_+", "+ p_147787_3_+");");
            cir.setReturnValue(this.getIconSafe(null));
        }
    }
}
