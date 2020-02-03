// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            GuiSlot, GuiTexturePacks, TexturePackList, TexturePackBase, 
//            RenderEngine, Tessellator

class GuiTexturePackSlot extends GuiSlot
{

    public GuiTexturePackSlot(GuiTexturePacks guitexturepacks)
    {
        super(GuiTexturePacks.getMinecraft(guitexturepacks), guitexturepacks.width, guitexturepacks.height, 32, (guitexturepacks.height - 55) + 4, 36);
        parentTexturePackGui = guitexturepacks;
    }

    protected int getSize()
    {
        List list = GuiTexturePacks.getMinecraft1(parentTexturePackGui).texturePackList.availableTexturePacks();
        return list.size();
    }

    protected void elementClicked(int i, boolean flag)
    {
        List list = GuiTexturePacks.getMinecraft2(parentTexturePackGui).texturePackList.availableTexturePacks();
        GuiTexturePacks.getMinecraft3(parentTexturePackGui).texturePackList.setTexturePack((TexturePackBase)list.get(i));
        GuiTexturePacks.getMinecraft4(parentTexturePackGui).renderEngine.refreshTextures();
    }

    protected boolean isSelected(int i)
    {
        List list = GuiTexturePacks.getMinecraft5(parentTexturePackGui).texturePackList.availableTexturePacks();
        return GuiTexturePacks.getMinecraft6(parentTexturePackGui).texturePackList.selectedTexturePack == list.get(i);
    }

    protected int getContentHeight()
    {
        return getSize() * 36;
    }

    protected void drawBackground()
    {
        parentTexturePackGui.drawDefaultBackground();
    }

    protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator)
    {
        TexturePackBase texturepackbase = (TexturePackBase)GuiTexturePacks.getMinecraft7(parentTexturePackGui).texturePackList.availableTexturePacks().get(i);
        texturepackbase.bindThumbnailTexture(GuiTexturePacks.getMinecraft8(parentTexturePackGui));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_I(0xffffff);
        tessellator.addVertexWithUV(j, k + l, 0.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(j + 32, k + l, 0.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(j + 32, k, 0.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(j, k, 0.0D, 0.0D, 0.0D);
        tessellator.draw();
        parentTexturePackGui.drawString(GuiTexturePacks.getFontRenderer(parentTexturePackGui), texturepackbase.texturePackFileName, j + 32 + 2, k + 1, 0xffffff);
        parentTexturePackGui.drawString(GuiTexturePacks.getFontRenderer1(parentTexturePackGui), texturepackbase.firstDescriptionLine, j + 32 + 2, k + 12, 0x808080);
        parentTexturePackGui.drawString(GuiTexturePacks.getFontRenderer2(parentTexturePackGui), texturepackbase.secondDescriptionLine, j + 32 + 2, k + 12 + 10, 0x808080);
    }

    final GuiTexturePacks parentTexturePackGui; /* synthetic field */
}
