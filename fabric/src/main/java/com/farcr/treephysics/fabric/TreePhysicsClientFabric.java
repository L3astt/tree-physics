package com.farcr.treephysics.fabric;

import com.farcr.treephysics.TreePhysics;
import com.farcr.treephysics.TreePhysicsClient;
import com.farcr.treephysics.index.TreePhysicsClientConfig;
import com.farcr.treephysics.index.TreePhysicsParticleTypes;
import com.farcr.treephysics.particle.collision_dust.CollisionDustProvider;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

public class TreePhysicsClientFabric {
    public static void init() {
        TreePhysicsClient.init();
        NeoForgeConfigRegistry.INSTANCE.register(TreePhysics.MOD_ID, ModConfig.Type.CLIENT, TreePhysicsClientConfig.SPEC);
        ConfigScreenFactoryRegistry.INSTANCE.register(TreePhysics.MOD_ID, (title, parent) -> new ConfigurationScreen(TreePhysics.MOD_ID, parent));

        ParticleFactoryRegistry.getInstance().register(TreePhysicsParticleTypes.COLLISION_DUST.value(), CollisionDustProvider::new);
    }
}
