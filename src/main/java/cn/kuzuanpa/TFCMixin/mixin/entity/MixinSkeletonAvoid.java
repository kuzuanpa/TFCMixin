package cn.kuzuanpa.TFCMixin.mixin.entity;

import com.bioxx.tfc.Entities.Mobs.EntityOcelotTFC;
import com.bioxx.tfc.Entities.Mobs.EntityWolfTFC;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntitySkeleton.class)
public abstract class MixinSkeletonAvoid extends EntityMob {

    public MixinSkeletonAvoid(World p_i1604_1_) {
        super(p_i1604_1_);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void avoidWolf(World p_i1696_1_, CallbackInfo ci){
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityWolfTFC.class, 6.0F, 1.0D, 1.2D));
    }
}
