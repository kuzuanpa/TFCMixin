package cn.kuzuanpa.TFCMixin;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.Level;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Mod(modid = TFCMixin.MODID, useMetadata = true)
public class TFCMixin {
    static boolean optifineChecked = false;
    static boolean ftbChecked = false;
    static boolean suChecked = false;
    public static boolean outputDataWhenChunkSavingFailed = true;
    public TFCMixin(){
        if(!optifineChecked)try{Class.forName("optifine.OptiFineTweaker");throw new IllegalArgumentException("Optifine Not Supported!");}catch (ClassNotFoundException e){optifineChecked=true;}
        if(!ftbChecked)try{Class.forName("ftb.utils.mod.FTBU");throw new IllegalArgumentException("FTBU Not Supported!");}catch (ClassNotFoundException e){ftbChecked=true;}
        if(!suChecked)try{Class.forName("serverutils.ServerUtilities");throw new IllegalArgumentException("ServerUtilities Not Supported!");}catch (ClassNotFoundException e){suChecked=true;}
        System.out.println("TFCMixin Loaded Successfully!");
    }
    public static final String MODID = "tfc-mixin";

    public static boolean tipsLoaded = false;
    public static final List<String> tips = new ArrayList<>();

    public static void loadScreenTips(){
        try{
            if(tipsLoaded)return;
            String path = "config/Betterloadingscreen/tips/"+FMLCommonHandler.instance().getCurrentLanguage()+".txt";
            if(!Files.exists(Paths.get(path)))path = "config/Betterloadingscreen/tips/en_US.txt";
            Files.readAllLines(Paths.get(path)).stream().filter(str->!str.startsWith("#")).map(String::trim).forEach(tips::add);
            tipsLoaded = true;
        }catch (Throwable e){
            tips.add("Exception occurred while loading tips, please send log to dev");
            FMLLog.log(Level.ERROR,e,"");
        }
    }
}
