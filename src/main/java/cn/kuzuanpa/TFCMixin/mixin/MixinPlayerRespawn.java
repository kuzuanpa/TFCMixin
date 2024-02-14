package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static com.bioxx.tfc.TerraFirmaCraft.TFCDimID;

@Mixin(WorldProvider.class)
public class MixinPlayerRespawn {
    @ModifyConstant(method = "getRespawnDimension", constant = @Constant(intValue = 0),remap = false)
    public int getRespawnDimension(int constant){
        return TFCDimID;
    }
}
