package cn.kuzuanpa.TFCMixin;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
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

    public static class RenderWailaBackground {
        static final ResourceLocation cornerLeftTop       = new ResourceLocation("tfcmixin","textures/gui/waila/cornerLeftTop.png");
        static final ResourceLocation cornerLeftBottom    = new ResourceLocation("tfcmixin","textures/gui/waila/cornerLeftBottom.png");
        static final ResourceLocation cornerRightTop      = new ResourceLocation("tfcmixin","textures/gui/waila/cornerRightTop.png");
        static final ResourceLocation cornerRightBottom   = new ResourceLocation("tfcmixin","textures/gui/waila/cornerRightBottom.png");
        static final ResourceLocation lineTop             = new ResourceLocation("tfcmixin","textures/gui/waila/lineTop.png");
        static final ResourceLocation lineLeft            = new ResourceLocation("tfcmixin","textures/gui/waila/lineLeft.png");
        static final ResourceLocation lineRight           = new ResourceLocation("tfcmixin","textures/gui/waila/lineRight.png");
        static final ResourceLocation lineBottom          = new ResourceLocation("tfcmixin","textures/gui/waila/lineBottom.png");
        static final ResourceLocation background          = new ResourceLocation("tfcmixin","textures/gui/waila/background.png");

        static int imageSize;

        static {
            try {
                imageSize = ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(cornerLeftTop).getInputStream()).getHeight();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void drawTextureRect(Tessellator tessellator, int x, int y, int u, int v, int width, int height, int imageSize){
            float zLevel = 0.0F;
            final float f = 0.00390625F*( (1024F/w/imageSize));
            final float f1 = 0.00390625F*( (1024F/w/imageSize));
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(x, y + height, zLevel, (u * f), (v + height) * f1);
            tessellator.addVertexWithUV(x + width, y + height, zLevel, (u + width) * f, (v + height) * f1);
            tessellator.addVertexWithUV(x + width, y, zLevel, (u + width) * f, v * f1);
            tessellator.addVertexWithUV(x, y, zLevel, (u * f), v * f1);
            tessellator.draw();
        }

        public static int w = 4;
        public static void draw(int x, int y, int width, int height){

            try {
                Minecraft.getMinecraft().getTextureManager().bindTexture(cornerLeftTop);
                drawTextureRect(Tessellator.instance, x, y, 0, 0, w, w, imageSize);

                Minecraft.getMinecraft().getTextureManager().bindTexture(cornerLeftBottom);
                drawTextureRect(Tessellator.instance, x, y + height - w, 0, 0, w, w, imageSize);

                Minecraft.getMinecraft().getTextureManager().bindTexture(cornerRightTop);
                drawTextureRect(Tessellator.instance, x + width - w, y, 0, 0, w, w, imageSize);

                Minecraft.getMinecraft().getTextureManager().bindTexture(cornerRightBottom);
                drawTextureRect(Tessellator.instance, x + width - w, y + height - w, 0, 0, w, w, imageSize);

                Minecraft.getMinecraft().getTextureManager().bindTexture(lineTop);
                drawTextureRect(Tessellator.instance, x + w, y, 0, 0, width - w * 2, w, imageSize);

                Minecraft.getMinecraft().getTextureManager().bindTexture(lineBottom);
                drawTextureRect(Tessellator.instance, x + w, y + height - w, 0, 0, width - w * 2, w, imageSize);

                Minecraft.getMinecraft().getTextureManager().bindTexture(lineLeft);
                drawTextureRect(Tessellator.instance, x, y + w, 0, 0, w, height - w * 2, imageSize);

                Minecraft.getMinecraft().getTextureManager().bindTexture(lineRight);
                drawTextureRect(Tessellator.instance, x + width - w, y + w, 0, 0, w, height - w * 2, imageSize);

                Minecraft.getMinecraft().getTextureManager().bindTexture(background);
                drawTextureRect(Tessellator.instance, x + w, y + w, 0, 0, width - w * 2, height - w * 2, imageSize);

            }catch (Exception e){e.printStackTrace();}
        }
    }
}
