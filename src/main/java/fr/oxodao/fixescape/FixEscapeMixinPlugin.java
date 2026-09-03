package fr.oxodao.fixescape;

import java.util.List;
import java.util.Set;
import net.neoforged.fml.loading.FMLLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

/**
 * Adds optional integration mixins only when their target mod is part of the
 * current loading set.
 */
public class FixEscapeMixinPlugin implements IMixinConfigPlugin {
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        var loadingModList = FMLLoader.getLoadingModList();
        var mixins = new java.util.ArrayList<String>();

        if (loadingModList.getModFileById("ae2") != null) {
            mixins.add("AETextFieldMixin");
        }
        if (loadingModList.getModFileById("emi") != null) {
            mixins.add("EmiSearchWidgetMixin");
        }
        if (loadingModList.getModFileById("refinedstorage") != null) {
            mixins.add("RefinedStorageSearchFieldMixin");
        }

        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
