package com.farcr.treephysics.index;

import com.farcr.treephysics.TreePhysics;
import com.farcr.treephysics.particle.collision_dust.CollisionDustParticleType;
import foundry.veil.platform.registry.RegistrationProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class TreePhysicsParticleTypes {
    private static final RegistrationProvider<ParticleType<?>> PARTICLE_TYPES = RegistrationProvider.get(BuiltInRegistries.PARTICLE_TYPE, TreePhysics.MOD_ID);

    public static final Holder<CollisionDustParticleType> COLLISION_DUST = PARTICLE_TYPES.register(
            "collision_dust",
            () -> new CollisionDustParticleType(false)
    ).asHolder();

    public static void init() {

    }

}
