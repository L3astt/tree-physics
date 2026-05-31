package com.farcr.treephysics.neoforge;

import com.farcr.treephysics.TreePhysics;
import com.farcr.treephysics.TreePhysicsClient;
import com.farcr.treephysics.index.TreePhysicsClientConfig;
import com.farcr.treephysics.index.TreePhysicsParticleTypes;
import com.farcr.treephysics.particle.collision_dust.CollisionDustProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = TreePhysics.MOD_ID, dist = Dist.CLIENT)
public class TreePhysicsClientNeoForge {
    public TreePhysicsClientNeoForge(IEventBus eventBus, ModContainer container) {
        TreePhysicsClient.init();

        eventBus.addListener(this::registerParticleProviders);
        container.registerConfig(ModConfig.Type.CLIENT, TreePhysicsClientConfig.SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    private void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(TreePhysicsParticleTypes.COLLISION_DUST.value(), CollisionDustProvider::new);
    }
}
