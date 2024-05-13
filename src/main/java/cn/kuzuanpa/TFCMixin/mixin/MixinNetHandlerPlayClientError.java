package cn.kuzuanpa.TFCMixin.mixin;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClientError {
    @Shadow
    private WorldClient clientWorldController;
    @ModifyConstant(method = "handleEntityProperties", constant = {@Constant(doubleValue = 0.0)})
    private double fixNullIAttributeInstanceAlwaysCrash(double original) {
        return 0.00001;
    }
    @Inject(method = "handleEntityProperties", at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/ai/attributes/BaseAttributeMap;registerAttribute(Lnet/minecraft/entity/ai/attributes/IAttribute;)Lnet/minecraft/entity/ai/attributes/IAttributeInstance;"))
    private void logWhatEntityNull(S20PacketEntityProperties p_147290_1_, CallbackInfo ci){
        System.out.println("Null IAttribute Found, that shouldn't happen. Entity:"+clientWorldController.getEntityByID(p_147290_1_.func_149442_c()));
    }

}
