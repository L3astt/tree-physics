package com.farcr.treephysics.fabric.mixin;

import com.farcr.treephysics.api.util.TreeUtil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AlterGroundDecorator.class)
public class AlterGroundDecoratorMixin {

    @WrapOperation(method = "placeBlockAt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/feature/Feature;isGrassOrDirt(Lnet/minecraft/world/level/LevelSimulatedReader;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean treephysics$isGrassOrDirt(LevelSimulatedReader level, BlockPos pos, Operation<Boolean> original) {
        return original.call(level, pos) && !level.isStateAtPosition(pos, TreeUtil::isRoot);
    }
}
