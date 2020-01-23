// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.io.File;
import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;

// Referenced classes of package net.minecraft.src:
//            GuiScreen, StringTranslate, GuiSmallButton, TexturePackList, 
//            GuiTexturePackSlot, GuiButton, RenderEngine, FontRenderer

public class GuiTexturePacks extends GuiScreen
{

    public GuiTexturePacks(GuiScreen guiscreen)
    {
        refreshTimer = -1;
        fileLocation = "";
        guiScreen = guiscreen;
    }

    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        controlList.add(new GuiSmallButton(5, width / 2 - 154, height - 48, stringtranslate.translateKey("texturePack.openFolder")));
        controlList.add(new GuiSmallButton(6, width / 2 + 4, height - 48, stringtranslate.translateKey("gui.done")));
        mc.texturePackList.updateAvaliableTexturePacks();
        fileLocation = (new File(Minecraft.getMinecraftDir(), "texturepacks")).getAbsolutePath();
        guiTexturePackSlot = new GuiTexturePackSlot(this);
        guiTexturePackSlot.registerScrollButtons(controlList, 7, 8);
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(!guibutton.enabled)
        {
            return;
        }
        if(guibutton.id == 5)
        {
            Sys.openURL((new StringBuilder()).append("file://").append(fileLocation).toString());
        } else
        if(guibutton.id == 6)
        {
            mc.renderEngine.refreshTextures();
            mc.displayGuiScreen(guiScreen);
        } else
        {
            guiTexturePackSlot.actionPerformed(guibutton);
        }
    }

    protected void mouseClicked(int i, int j, int k)
    {
        super.mouseClicked(i, j, k);
    }

    protected void mouseMovedOrUp(int i, int j, int k)
    {
        super.mouseMovedOrUp(i, j, k);
    }

    public void drawScreen(int i, int j, float f)
    {
        guiTexturePackSlot.drawScreen(i, j, f);
        if(refreshTimer <= 0)
        {
            mc.texturePackList.updateAvaliableTexturePacks();
            refreshTimer += 20;
        }
        StringTranslate stringtranslate = StringTranslate.getInstance();
        drawCenteredString(fontRenderer, stringtranslate.translateKey("texturePack.title"), width / 2, 16, 0xffffff);
        drawCenteredString(fontRenderer, stringtranslate.translateKey("texturePack.folderInfo"), width / 2 - 77, height - 26, 0x808080);
        super.drawScreen(i, j, f);
    }

    public void updateScreen()
    {
        super.updateScreen();
        refreshTimer--;
    }

    static Minecraft getMinecraft(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.mc;
    }

    static Minecraft getMinecraft1(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.mc;
    }

    static Minecraft getMinecraft2(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.mc;
    }

    static Minecraft getMinecraft3(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.mc;
    }

    static Minecraft getMinecraft4(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.mc;
    }

    static Minecraft getMinecraft5(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.mc;
    }

    static Minecraft getMinecraft6(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.mc;
    }

    static Minecraft getMinecraft7(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.mc;
    }

    static Minecraft getMinecraft8(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.mc;
    }

    static FontRenderer getFontRenderer(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.fontRenderer;
    }

    static FontRenderer getFontRenderer1(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.fontRenderer;
    }

    static FontRenderer getFontRenderer2(GuiTexturePacks guitexturepacks)
    {
        return guitexturepacks.fontRenderer;
    }

    protected GuiScreen guiScreen;
    private int refreshTimer;
    private String fileLocation;
    private GuiTexturePackSlot guiTexturePackSlot;
}
