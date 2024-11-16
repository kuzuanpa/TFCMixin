package cn.kuzuanpa.TFCMixin;

import cpw.mods.fml.common.Mod;

@Mod(modid = TFCMixin.MODID, useMetadata = true)
public class TFCMixin {
    static boolean optifineChecked = false;
    static boolean ftbChecked = false;
    static boolean suChecked = false;
    public TFCMixin(){
        if(!optifineChecked)try{Class.forName("optifine.OptiFineTweaker");throw new IllegalArgumentException("Optifine Not Supported!");}catch (ClassNotFoundException e){optifineChecked=true;}
        if(!ftbChecked)try{Class.forName("ftb.utils.mod.FTBU");throw new IllegalArgumentException("FTBU Not Supported!");}catch (ClassNotFoundException e){ftbChecked=true;}
        if(!suChecked)try{Class.forName("serverutils.ServerUtilities");throw new IllegalArgumentException("ServerUtilities Not Supported!");}catch (ClassNotFoundException e){suChecked=true;}
        System.out.println("TFCMixin Loaded Successfully!");
    }
    public static final String MODID = "tfc-mixin";
    }
