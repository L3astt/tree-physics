package com.farcr.treephysics.neoforge.mixin.compatibility.spawn;

import com.farcr.treephysics.api.util.TreeUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.ninni.spawn.server.level.feature.DateTreeFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DateTreeFeature.class)
public class DateTreeFeatureMixin {

    @WrapOperation(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    private boolean treephysics$setBlock(WorldGenLevel instance, BlockPos pos, BlockState state, int i, Operation<Boolean> original) {
        if(TreeUtil.isLog(state) && TreeUtil.canBeRoots(instance, pos.below())) {
            original.call(instance, pos.below(), TreeUtil.getRootForState(instance.getBlockState(pos.below())), i);
        }

        return original.call(instance, pos, state, i);
    }

}
