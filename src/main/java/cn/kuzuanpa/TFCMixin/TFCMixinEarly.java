package cn.kuzuanpa.TFCMixin;

import com.google.common.collect.Lists;
import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.Name("TFCEarlyMixin")
public class TFCMixinEarly implements IFMLLoadingPlugin,IEarlyMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.TFCMixin.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> set) {
       return Lists.newArrayList("MixinUnicodeFontShadow");
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
