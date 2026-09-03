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
        var mixins = new java.util.ArrayList<String>();
        mixins.add("CreativeSearchMixin");

        if (isModLoaded("ae2")) {
            mixins.add("AETextFieldMixin");
        }
        if (isModLoaded("emi")) {
            mixins.add("EmiSearchWidgetMixin");
        }
        if (isModLoaded("refinedstorage")) {
            mixins.add("RefinedStorageSearchFieldMixin");
        }

        return mixins;
    }

    private static boolean isModLoaded(String modId) {
        return FMLLoader.getCurrent().getLoadingModList().getModFileById(modId) != null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
