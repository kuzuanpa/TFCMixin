package cn.kuzuanpa.thinkerMixin;

import cpw.mods.fml.common.Mod;

@Mod(modid = thinkerMixin.MODID, useMetadata = true)
public class thinkerMixin {
    public static final String MODID = "thinkermixin";
    public static boolean isSkyRenderDisabled = false;
    public static void disableSkyRender(){
        isSkyRenderDisabled = true;
    }
    public static void enableSkyRender(){
        isSkyRenderDisabled = false;
    }

}
