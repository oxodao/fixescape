package fr.oxodao.fixescape;

import java.util.List;
import java.util.Set;
import java.lang.reflect.Modifier;
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
        mixins.add(hasKeyEvent() ? "CreativeSearchMixin" : "CreativeSearchLegacyMixin");

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

    private static boolean hasKeyEvent() {
        try {
            Class.forName("net.minecraft.client.input.KeyEvent", false, FixEscapeMixinPlugin.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

    private static boolean isModLoaded(String modId) {
        try {
            Class<?> loaderClass = Class.forName("net.neoforged.fml.loading.FMLLoader");
            Object loader = null;
            try {
                loader = loaderClass.getMethod("get").invoke(null);
            } catch (NoSuchMethodException ignored) {
                try {
                    loader = loaderClass.getMethod("getCurrent").invoke(null);
                } catch (NoSuchMethodException ignoredAgain) {
                }
            }
            var getLoadingModList = loaderClass.getMethod("getLoadingModList");
            Object loadingModList = getLoadingModList.invoke(
                    Modifier.isStatic(getLoadingModList.getModifiers()) ? null : loader);
            return loadingModList.getClass().getMethod("getModFileById", String.class).invoke(loadingModList, modId) != null;
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unable to query loaded mods", exception);
        }
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
