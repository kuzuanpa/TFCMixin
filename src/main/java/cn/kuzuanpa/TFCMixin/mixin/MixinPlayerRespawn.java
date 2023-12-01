package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.bioxx.tfc.TerraFirmaCraft.TFCDimID;

@Mixin(WorldProvider.class)
public class MixinPlayerRespawn {
    @ModifyConstant(method = "getRespawnDimension", constant = @Constant(intValue = 0),remap = false)
    public int getRespawnDimension(int constant){
        return TFCDimID;
    }
}
