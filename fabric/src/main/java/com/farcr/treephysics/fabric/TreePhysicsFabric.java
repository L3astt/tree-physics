package com.farcr.treephysics.fabric;

import com.farcr.treephysics.TreePhysics;
import com.farcr.treephysics.api.grouping.BlockGroupingManager;
import com.farcr.treephysics.event.CommonEvents;
import com.farcr.treephysics.index.TreePhysicsCommands;
import com.farcr.treephysics.index.TreePhysicsConfig;
import foundry.veil.fabric.util.FabricReloadListener;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import net.neoforged.fml.config.ModConfig;

import static com.farcr.treephysics.TreePhysics.path;

public class TreePhysicsFabric {
    public static void init() {
        TreePhysics.init();
        NeoForgeConfigRegistry.INSTANCE.register(TreePhysics.MOD_ID, ModConfig.Type.COMMON, TreePhysicsConfig.SPEC);
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricReloadListener(path("grouping"), BlockGroupingManager.INSTANCE));
        registerEventListeners();
    }

    private static void registerEventListeners() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            CommonEvents.playerBreakBlock(world, player, pos);
            return true;
        });

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            CommonEvents.playerLoggedIn(handler.player);
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            TreePhysicsCommands.registerCommands(registryAccess, dispatcher);
        });

    }
}
