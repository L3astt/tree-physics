package com.farcr.treephysics.neoforge.data;

import com.farcr.treephysics.TreePhysics;
import com.farcr.treephysics.data.TreePhysicsLang;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class TreePhysicsLangNeoForge extends LanguageProvider {
    public TreePhysicsLangNeoForge(PackOutput output) {
        super(output, TreePhysics.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        TreePhysicsLang.provideLang(this::add);
    }
}
