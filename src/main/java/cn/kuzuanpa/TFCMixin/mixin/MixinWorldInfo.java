package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.bioxx.tfc.TerraFirmaCraft.TFCDimID;

@Mixin(DerivedWorldInfo.class)
public class MixinWorldInfo {
    @Shadow @Final private WorldInfo theWorldInfo;

    @Inject(method = "setSpawnPosition", at = @At("HEAD"))
    public void setSpawnPosition(int p_76081_1_, int p_76081_2_, int p_76081_3_, CallbackInfo ci){
        this.theWorldInfo.setSpawnPosition(p_76081_1_,p_76081_2_,p_76081_3_);
    }
}
