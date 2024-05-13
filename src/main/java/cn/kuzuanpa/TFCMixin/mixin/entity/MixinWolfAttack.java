package cn.kuzuanpa.TFCMixin.mixin.entity;

import com.bioxx.tfc.Entities.Mobs.EntitySheepTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.text.LayoutQueue;

@Mixin(EntityWolf.class)
public abstract class MixinWolfAttack extends EntityTameable {

    public MixinWolfAttack(World p_i1604_1_) {
        super(p_i1604_1_);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void attackSkeleton(World p_i1696_1_, CallbackInfo ci){
        targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntitySheepTFC.class, 200, false));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget((EntityWolf)(Object)this, EntitySkeleton.class, 200, false));
        targetTasks.addTask(4, new EntityAINearestAttackableTarget((EntityWolf)(Object)this, EntitySkeletonTFC.class, 200, false));
    }
}
