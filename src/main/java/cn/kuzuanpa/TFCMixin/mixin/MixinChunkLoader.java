package cn.kuzuanpa.TFCMixin.mixin;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(AnvilChunkLoader.class)
public class MixinChunkLoader {
    @Unique public ChunkCoordIntPair TFCMixin$lastRemovedChunkPos = null;
    @Redirect(method = "writeNextIO", at = @At(value = "INVOKE", target = "Ljava/util/Set;remove(Ljava/lang/Object;)Z"))
    public boolean cacheChunkPos(Set instance, Object o){
        TFCMixin$lastRemovedChunkPos = (ChunkCoordIntPair) o;
        return false;
    }

    @Inject(method = "writeNextIO", at = @At(value = "INVOKE", target = "Ljava/lang/Exception;printStackTrace()V"))
    public void outputPosWhenFailed(CallbackInfoReturnable<Boolean> cir){
        FMLLog.log(Level.FATAL,"TFCMixin: Seems Exception happened in Chunk Position:"+TFCMixin$lastRemovedChunkPos+". You may consider remove it");
    }

}
