package cn.kuzuanpa.TFCMixin.mixin.entity;

import com.bioxx.tfc.Entities.Mobs.EntityOcelotTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySheepTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityCreeper.class)
public abstract class MixinCreeperAvoid extends EntityMob {

    public MixinCreeperAvoid(World p_i1604_1_) {
        super(p_i1604_1_);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void avoidOcelotTFC(World p_i1696_1_, CallbackInfo ci){
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityOcelotTFC.class, 6.0F, 1.0D, 1.2D));
    }
}
