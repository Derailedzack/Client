// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.text.NumberFormat;

// Referenced classes of package net.minecraft.src:
//            IStatType, StatBase

final class StatTypeSimple
    implements IStatType
{

    StatTypeSimple()
    {
    }

    public String format(int i)
    {
        return StatBase.func_27083_i().format(i);
    }
}
