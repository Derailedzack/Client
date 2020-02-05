// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.util.List;

// Referenced classes of package net.minecraft.src:
//            GuiSlot, GuiStats, StatList, StatBase, 
//            StatFileWriter, FontRenderer, Tessellator

class GuiSlotStatsGeneral extends GuiSlot
{

    public GuiSlotStatsGeneral(GuiStats guistats)
    {
        super(GuiStats.getMinecraft(guistats), guistats.width, guistats.height, 32, guistats.height - 64, 10);
        statsGui = guistats;
        setShowSelectionBox(false);
    }

    protected int getSize()
    {
        return StatList.generalStats.size();
    }

    protected void elementClicked(int i, boolean flag)
    {
    }

    protected boolean isSelected(int i)
    {
        return false;
    }

    protected int getContentHeight()
    {
        return getSize() * 10;
    }

    protected void drawBackground()
    {
        statsGui.drawDefaultBackground();
    }

    protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator)
    {
        StatBase statbase = (StatBase)StatList.generalStats.get(i);
        statsGui.drawString(GuiStats.getFontRenderer1(statsGui), statbase.statName, j + 2, k + 1, i % 2 != 0 ? 0x909090 : 0xffffff);
        String s = statbase.func_27084_a(GuiStats.getStatsFileWriter(statsGui).writeStat(statbase));
        statsGui.drawString(GuiStats.getFontRenderer2(statsGui), s, (j + 2 + 213) - GuiStats.getFontRenderer3(statsGui).getStringWidth(s), k + 1, i % 2 != 0 ? 0x909090 : 0xffffff);
    }

    final GuiStats statsGui; /* synthetic field */
}
