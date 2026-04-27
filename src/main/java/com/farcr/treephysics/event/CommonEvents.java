package com.farcr.treephysics.event;

import com.farcr.treephysics.TreePhysics;
import com.farcr.treephysics.api.manager.TreeServerHandler;
import com.farcr.treephysics.api.manager.TreeSubLevelObserver;
import com.farcr.treephysics.api.tree_gathering.TreeGatherer;
import dev.ryanhcode.sable.api.physics.PhysicsPipeline;
import dev.ryanhcode.sable.api.sublevel.ServerSubLevelContainer;
import dev.ryanhcode.sable.api.sublevel.SubLevelContainer;
import dev.ryanhcode.sable.sublevel.system.SubLevelPhysicsSystem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = TreePhysics.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void blockBreak(BlockEvent.BreakEvent event) {
        if(!event.getPlayer().isShiftKeyDown() && event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).is(ItemTags.AXES)) {
            TreeGatherer.trySplit((ServerLevel) event.getLevel(), event.getPos());
        }
    }

    public static void containerReady(Level level, SubLevelContainer container) {
        if(!(container instanceof ServerSubLevelContainer serverContainer)) {
            return;
        }

        serverContainer.addObserver(new TreeSubLevelObserver(serverContainer.getLevel()));
    }

    public static void postPhysicsTick(SubLevelPhysicsSystem system, double timeStep) {
        ServerLevel level = system.getLevel();
        TreeServerHandler handler = TreeServerHandler.get(level);
        PhysicsPipeline pipeline = system.getPipeline();
        handler.physicsTick(level, system, pipeline, timeStep);
    }
}
