package com.farcr.treephysics.api.flood_fill;

import com.farcr.treephysics.index.TreePhysicsTags;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class TreeResult {
    private final Map<TagKey<Block>, Set<BlockPos>> collectedBlocks = new Object2ObjectOpenHashMap<>();
    private final Set<BlockPos> allBlocks = new ObjectOpenHashSet<>();
    private boolean root = false;
    private Block logBlock = null;
    private Block leafBlock = null;

    public TreeResult(Collection<TagKey<Block>> tags) {
        for (TagKey<Block> tag : tags) {
            collectedBlocks.put(tag, new ObjectOpenHashSet<>());
        }
    }

    public void add(BlockPos pos, BlockState state) {
        for (TagKey<Block> key : this.collectedBlocks.keySet()) {
            if(state.is(key)) {
                this.collectedBlocks.get(key).add(pos);
                break;
            }
        }
        this.allBlocks.add(pos);
    }

    public void afterSpread(BlockGetter blockGetter, BlockPos pos) {
        if(!this.root && blockGetter.getBlockState(pos.below()).is(TreePhysicsTags.ROOTS) && blockGetter.getBlockState(pos).is(BlockTags.LOGS)) {
            this.root = true;
        }
    }

    public Set<BlockPos> getBlocks(TagKey<Block> tag) {
        return this.collectedBlocks.get(tag);
    }

    public Set<BlockPos> getBlocks() {
        return this.allBlocks;
    }

    public boolean hasRoot() {
        return root;
    }

    public boolean isLeaf(BlockState state) {
        if (this.leafBlock != null) {
            return state.is(this.leafBlock);
        } else {
            if (state.getBlock() instanceof LeavesBlock) {
                this.leafBlock = state.getBlock();
                return true;
            }
            return false;
        }
    }

    public boolean isLog(BlockState state) {
        if (this.logBlock != null) {
            return state.is(this.logBlock);
        } else {
            if (state.is(BlockTags.LOGS)) {
                this.logBlock = state.getBlock();
                return true;
            }
            return false;
        }
    }
}
