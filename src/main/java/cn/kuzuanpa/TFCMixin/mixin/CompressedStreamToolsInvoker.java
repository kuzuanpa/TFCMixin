package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.io.DataOutput;

@Mixin(CompressedStreamTools.class)
public interface CompressedStreamToolsInvoker {

    @Invoker("func_150663_a")
    public static void Invoke_func_150663_a(NBTBase p_150663_0_, DataOutput p_150663_1_){};
}
