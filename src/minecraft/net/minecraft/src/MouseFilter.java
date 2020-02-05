// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;


public class MouseFilter
{

    public MouseFilter()
    {
    }

    public float smooth(float f, float f1)
    {
        targetValue += f;
        f = (targetValue - remainingValue) * f1;
        lastAmount = lastAmount + (f - lastAmount) * 0.5F;
        if(f > 0.0F && f > lastAmount || f < 0.0F && f < lastAmount)
        {
            f = lastAmount;
        }
        remainingValue += f;
        return f;
    }

    private float targetValue;
    private float remainingValue;
    private float lastAmount;
}
