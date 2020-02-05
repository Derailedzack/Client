// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.util.Comparator;

// Referenced classes of package net.minecraft.src:
//            StatCrafting, GuiSlotStatsItem, StatList, GuiStats, 
//            StatFileWriter

class SorterStatsItem
    implements Comparator
{

    SorterStatsItem(GuiSlotStatsItem guislotstatsitem, GuiStats guistats)
    {
//        super();
        field_27372_b = guislotstatsitem;
        field_27373_a = guistats;
    }

    public int func_27371_a(StatCrafting statcrafting, StatCrafting statcrafting1)
    {
        int i = statcrafting.getItemID();
        int j = statcrafting1.getItemID();
        StatBase statbase = null;
        StatBase statbase1 = null;
        if(field_27372_b.field_27271_e == 0)
        {
            statbase = StatList.objectBreakStats[i];
            statbase1 = StatList.objectBreakStats[j];
        } else
        if(field_27372_b.field_27271_e == 1)
        {
            statbase = StatList.objectCraftStats[i];
            statbase1 = StatList.objectCraftStats[j];
        } else
        if(field_27372_b.field_27271_e == 2)
        {
            statbase = StatList.objectUseStats[i];
            statbase1 = StatList.objectUseStats[j];
        }
        if(statbase != null || statbase1 != null)
        {
            if(statbase == null)
            {
                return 1;
            }
            if(statbase1 == null)
            {
                return -1;
            }
            int k = GuiStats.getStatsFileWriter(field_27372_b.slotGuiStats).writeStat(statbase);
            int l = GuiStats.getStatsFileWriter(field_27372_b.slotGuiStats).writeStat(statbase1);
            if(k != l)
            {
                return (k - l) * field_27372_b.field_27270_f;
            }
        }
        return i - j;
    }

    public int compare(Object obj, Object obj1)
    {
        return func_27371_a((StatCrafting)obj, (StatCrafting)obj1);
    }

    final GuiStats field_27373_a; /* synthetic field */
    final GuiSlotStatsItem field_27372_b; /* synthetic field */
}
