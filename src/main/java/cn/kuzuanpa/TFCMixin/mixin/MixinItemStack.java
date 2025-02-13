package cn.kuzuanpa.TFCMixin.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemStack.class)
public class MixinItemStack {

    @Shadow public int stackSize;

    @Redirect(method = "writeToNBT", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NBTTagCompound;setByte(Ljava/lang/String;B)V"))
    private void raiseStackSizeLimit(NBTTagCompound instance, String p_74774_1_, byte p_74774_2_) {
        if(this.stackSize > Short.MAX_VALUE) instance.setInteger("Count", this.stackSize);
        else if(this.stackSize > Byte.MAX_VALUE) instance.setShort("Count", (short) this.stackSize);
        else instance.setByte("Count", (byte) this.stackSize);
    }

    @Redirect(method = "readFromNBT", at = @At(value = "FIELD", target = "Lnet/minecraft/item/ItemStack;stackSize:I"))
    private void raiseStackSizeLimit(ItemStack instance, int value, @Local(argsOnly = true) NBTTagCompound tag) {
        instance.stackSize = tag.getInteger("Count");
    }
}
