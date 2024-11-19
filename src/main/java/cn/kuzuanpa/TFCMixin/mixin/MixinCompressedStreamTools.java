package cn.kuzuanpa.TFCMixin.mixin;

import cn.kuzuanpa.TFCMixin.TFCMixin;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.DataOutput;

@Mixin(CompressedStreamTools.class)
public abstract class MixinCompressedStreamTools {

    @Redirect(method = "write(Lnet/minecraft/nbt/NBTTagCompound;Ljava/io/DataOutput;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompressedStreamTools;func_150663_a(Lnet/minecraft/nbt/NBTBase;Ljava/io/DataOutput;)V"))
    private static void outputDataWhenFailed(NBTBase p_150663_0_, DataOutput p_150663_1_){
        try {
            CompressedStreamToolsInvoker.Invoke_func_150663_a(p_150663_0_, p_150663_1_);
        }catch (Exception e){
            FMLLog.log(Level.FATAL, e, "Failed to write NBT data: ");
            if(TFCMixin.outputDataWhenChunkSavingFailed){
                FMLLog.log(Level.FATAL, p_150663_0_.toString());
                //To avoid spamming, quit the game instance.
                throw new RuntimeException("Failed to write NBT data");
            }
        }
    }
}
