package com.farcr.treephysics.neoforge.event;

import com.farcr.treephysics.TreePhysics;
import com.farcr.treephysics.api.grouping.BlockGroupingManager;
import com.farcr.treephysics.api.util.TreeUtil;
import com.farcr.treephysics.event.CommonEvents;
import com.farcr.treephysics.index.TreePhysicsCommands;
import com.farcr.treephysics.index.TreePhysicsConfig;
import com.farcr.treephysics.neoforge.data.TreePhysicsLangNeoForge;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.event.level.AlterGroundEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = TreePhysics.MOD_ID)
public class CommonEventsNeoForge {

    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        CommonEvents.playerLoggedIn(player);
    }

    @SubscribeEvent
    public static void blockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        Level level = player.level();
        BlockPos pos = event.getPos();
        CommonEvents.playerBreakBlock(level, player, pos);
    }

    @SubscribeEvent
    public static void entityPlace(BlockEvent.EntityPlaceEvent event) {
        if(event.getLevel() instanceof Level level) CommonEvents.entityPlace(level, event.getPos());
    }

    @SubscribeEvent
    public static void useItemOnBlock(UseItemOnBlockEvent event) {
        CommonEvents.useItemOnBlock(event.getLevel(), event.getPos(), () -> event.setCanceled(true));
    }

    @SubscribeEvent
    public static void alterGround(AlterGroundEvent event) {
        if(!TreePhysicsConfig.ROOTED_DIRT_GENERATION.getAsBoolean()) {
            return;
        }

        TreeDecorator.Context context = event.getContext();
        LevelSimulatedReader reader = context.level();

        AlterGroundEvent.StateProvider provider = event.getStateProvider();
        event.setStateProvider((random, pos) -> {
            boolean isRoot = reader.isStateAtPosition(pos, TreeUtil::isRoot);
            if(isRoot) {
                return TreeUtil.getDefaultRoot();
            }
            return provider.getState(random, pos);
        });
    }

    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CommandBuildContext context = event.getBuildContext();
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        TreePhysicsCommands.registerCommands(context, dispatcher);
    }

    @SubscribeEvent
    public static void addReloadListener(AddReloadListenerEvent event) {
        event.addListener(BlockGroupingManager.INSTANCE);
    }

    @EventBusSubscriber(modid = TreePhysics.MOD_ID)
    public static class Mod {

        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) {
            if (event.includeClient()) {
                PackOutput output = event.getGenerator().getPackOutput();
                event.addProvider(new TreePhysicsLangNeoForge(output));
            }
        }
    }
}
