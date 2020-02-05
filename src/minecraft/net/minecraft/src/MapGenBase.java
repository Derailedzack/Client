// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            World, IChunkProvider

public class MapGenBase
{

    public MapGenBase()
    {
        range = 8;
        rand = new Random();
    }

    public void generate(IChunkProvider ichunkprovider, World world, int i, int j, byte abyte0[])
    {
        int k = range;
        rand.setSeed(world.getRandomSeed());
        long l = (rand.nextLong() / 2L) * 2L + 1L;
        long l1 = (rand.nextLong() / 2L) * 2L + 1L;
        for(int i1 = i - k; i1 <= i + k; i1++)
        {
            for(int j1 = j - k; j1 <= j + k; j1++)
            {
                rand.setSeed((long)i1 * l + (long)j1 * l1 ^ world.getRandomSeed());
                recursiveGenerate(world, i1, j1, i, j, abyte0);
            }

        }

    }

    protected void recursiveGenerate(World world, int i, int j, int k, int l, byte abyte0[])
    {
    }

    protected int range;
    protected Random rand;
}
