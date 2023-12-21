package cn.kuzuanpa.TFCMixin.mixin;

import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.bioxx.tfc.TerraFirmaCraft.TFCDimID;

@Mixin(ServerConfigurationManager.class)
public class MixinPlayerFirstJoin{
    @Final
    @Shadow
    private MinecraftServer mcServer;


    //Make player born in TFCDim when first join.
    @Inject(method = "initializeConnectionToPlayer", at = @At(value = "HEAD"),remap = false)
    public void initializeConnectionToPlayer(NetworkManager p_72355_1_, EntityPlayerMP p_72355_2_, NetHandlerPlayServer nethandlerplayserver, CallbackInfo ci){
        World playerWorld = this.mcServer.worldServerForDimension(p_72355_2_.dimension);
        if (this.readPlayerDataFromFile(p_72355_2_)==null)
        {
            p_72355_2_.dimension=TFCDimID;
            playerWorld = this.mcServer.worldServerForDimension(p_72355_2_.dimension);
            ChunkCoordinates spawnPoint = playerWorld.provider.getSpawnPoint();
            p_72355_2_.setPosition(spawnPoint.posX, spawnPoint.posY, spawnPoint.posZ);
        }
        p_72355_2_.setWorld(playerWorld);
        p_72355_2_.theItemInWorldManager.setWorld((WorldServer)p_72355_2_.worldObj);
    }

    @Shadow
    public NBTTagCompound readPlayerDataFromFile(EntityPlayerMP p_72380_1_){
        return null;
    }

}