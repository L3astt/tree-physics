package com.farcr.treephysics.fabric.mixin;

import com.farcr.treephysics.api.manager.TreeManager;
import com.farcr.treephysics.index.TreePhysicsClientConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

    @WrapOperation(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;playLocalSound(Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V", ordinal = 11))
    private void treephysics$playLocalSound(ClientLevel instance, BlockPos pos, SoundEvent soundEvent, SoundSource soundSource, float volume, float pitch, boolean b, Operation<Void> original, @Local BlockState blockState) {
        if(blockState.is(BlockTags.LEAVES) && TreeManager.get(instance).isTree(pos)) {
            volume *= (float) TreePhysicsClientConfig.LEAF_VOLUME.getAsDouble();
        }
        original.call(instance, pos, soundEvent, soundSource, volume, pitch, b);
    }

}
