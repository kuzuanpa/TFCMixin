package cn.kuzuanpa.TFCMixin;

import com.google.common.collect.Lists;
import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@LateMixin
public class TFCMixinLate implements ILateMixinLoader {
    @Override
    public String getMixinConfig() {
        return "mixins.TFCMixin.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> set) {
        return Lists.newArrayList("MixinWailaDrawTooltipBox");
    }
}
