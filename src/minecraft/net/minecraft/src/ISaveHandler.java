// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.io.File;
import java.util.List;

// Referenced classes of package net.minecraft.src:
//            WorldInfo, WorldProvider, IChunkLoader

public interface ISaveHandler
{

    public abstract WorldInfo loadWorldInfo();

    public abstract void checkSessionLock();

    public abstract IChunkLoader getChunkLoader(WorldProvider worldprovider);

    public abstract void saveWorldInfoAndPlayer(WorldInfo worldinfo, List list);

    public abstract void saveWorldInfo(WorldInfo worldinfo);

    public abstract File getMapFile(String s);
}
