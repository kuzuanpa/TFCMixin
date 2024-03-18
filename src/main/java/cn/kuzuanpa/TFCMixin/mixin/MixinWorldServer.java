package cn.kuzuanpa.TFCMixin.mixin;

import com.bioxx.tfc.WorldGen.TFCProvider;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldServer.class)
public class MixinWorldServer {
    @Inject(method = "tick",at = @At(value = "INVOKE",target = "Lnet/minecraft/world/storage/WorldInfo;setWorldTime(J)V"),slice = @Slice(from = @At(value = "INVOKE",target = "Lnet/minecraft/world/WorldServer;areAllPlayersAsleep()Z"),to = @At(value = "INVOKE",target = "Lnet/minecraft/world/WorldServer;wakeAllPlayers()V")))
    public void onTick(CallbackInfo ci){
        WorldInfo overWorldInfo = MinecraftServer.getServer().worldServerForDimension(0).getWorldInfo();
        long i = overWorldInfo.getWorldTime() + 24000L;
        if (((World)((Object)this)).provider instanceof TFCProvider) overWorldInfo.setWorldTime(i - i % 24000L);
    }

}
