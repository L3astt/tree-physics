package com.farcr.treephysics.index;

import com.farcr.treephysics.TreePhysics;
import com.farcr.treephysics.particle.collision_dust.CollisionDustParticleType;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TreePhysicsParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, TreePhysics.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, CollisionDustParticleType> COLLISION_DUST = PARTICLE_TYPES.register(
            "collision_dust",
            () -> new CollisionDustParticleType(false)
    );

    public static void init(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

}
