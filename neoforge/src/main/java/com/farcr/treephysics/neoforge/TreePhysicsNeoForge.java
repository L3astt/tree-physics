package com.farcr.treephysics.neoforge;

import com.farcr.treephysics.TreePhysics;
import com.farcr.treephysics.index.TreePhysicsConfig;
import com.farcr.treephysics.neoforge.event.CommonEventsNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(TreePhysics.MOD_ID)
public class TreePhysicsNeoForge {
    public TreePhysicsNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        TreePhysics.init();

        NeoForge.EVENT_BUS.register(CommonEventsNeoForge.class);
        modEventBus.register(CommonEventsNeoForge.Mod.class);
        modContainer.registerConfig(ModConfig.Type.COMMON, TreePhysicsConfig.SPEC);
    }
}
