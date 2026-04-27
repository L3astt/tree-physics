package com.farcr.treephysics.api.tree_gathering;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TreeContext {
    Block logBlock = null;
    Block leafBlock = null;

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
