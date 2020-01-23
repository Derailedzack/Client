// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;


public class StepSound
{

    public StepSound(String s, float f, float f1)
    {
        label = s;
        volume = f;
        pitch = f1;
    }

    public float getVolume()
    {
        return volume;
    }

    public float getPitch()
    {
        return pitch;
    }

    public String stepSoundDir()
    {
        return (new StringBuilder()).append("step.").append(label).toString();
    }

    public String stepSoundDir2()
    {
        return (new StringBuilder()).append("step.").append(label).toString();
    }

    public final String label;
    public final float volume;
    public final float pitch;
}
