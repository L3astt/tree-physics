package com.farcr.treephysics;

import com.farcr.treephysics.event.CommonEvents;
import com.farcr.treephysics.index.TreePhysicsParticleTypes;
import com.farcr.treephysics.networking.TreePhysicsPackets;
import com.mojang.logging.LogUtils;
import dev.ryanhcode.sable.platform.SableEventPlatform;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class TreePhysics {
    public static final String MOD_ID = "treephysics";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        SableEventPlatform.INSTANCE.onSubLevelContainerReady(CommonEvents::containerReady);
        SableEventPlatform.INSTANCE.onPostPhysicsTick(CommonEvents::postPhysicsTick);

        TreePhysicsPackets.init();
        TreePhysicsParticleTypes.init();
    }

    public static ResourceLocation path(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

}
