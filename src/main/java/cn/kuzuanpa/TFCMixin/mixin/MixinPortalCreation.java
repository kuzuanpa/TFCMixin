package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.block.BlockFire;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.bioxx.tfc.TerraFirmaCraft.TFCDimID;

@Mixin(BlockFire.class)
public class MixinPortalCreation {
    @Inject(method = "onBlockAdded", at = @At("HEAD"))
    //Try to create portal in TFCDim
    public void getRespawnDimension(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_, CallbackInfo ci){
        if (p_149726_1_.provider.dimensionId == TFCDimID) Blocks.portal.func_150000_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
    }
}
