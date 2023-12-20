package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import static com.bioxx.tfc.TerraFirmaCraft.TFCDimID;

@Mixin(Entity.class)
public class MixinEntityInPortal {
    @ModifyArg(method = "onEntityUpdate", at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/Entity;travelToDimension(I)V"))
    //Make Entities go to TFCDim insteadof OverWorld.
    public int getRespawnDimension(int oldDIMID){
        return oldDIMID==-1?-1:TFCDimID;
    }
}
