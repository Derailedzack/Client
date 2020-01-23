// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.io.IOException;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            IChunkProvider, EmptyChunk, ChunkCoordIntPair, Chunk, 
//            IChunkLoader, World, IProgressUpdate

public class ChunkProvider
    implements IChunkProvider
{

    public ChunkProvider(World world, IChunkLoader ichunkloader, IChunkProvider ichunkprovider)
    {
        droppedChunksSet = new HashSet();
        chunkMap = new HashMap();
        chunkList = new ArrayList();
        emptyChunk = new EmptyChunk(world, new byte[32768], 0, 0);
        worldObj = world;
        chunkLoader = ichunkloader;
        chunkProvider = ichunkprovider;
    }

    public boolean chunkExists(int i, int j)
    {
        return chunkMap.containsKey(Integer.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j)));
    }

    public Chunk prepareChunk(int i, int j)
    {
        int k = ChunkCoordIntPair.chunkXZ2Int(i, j);
        droppedChunksSet.remove(Integer.valueOf(k));
        Chunk chunk = (Chunk)chunkMap.get(Integer.valueOf(k));
        if(chunk == null)
        {
            chunk = loadChunkFromFile(i, j);
            if(chunk == null)
            {
                if(chunkProvider == null)
                {
                    chunk = emptyChunk;
                } else
                {
                    chunk = chunkProvider.provideChunk(i, j);
                }
            }
            chunkMap.put(Integer.valueOf(k), chunk);
            chunkList.add(chunk);
            if(chunk != null)
            {
                chunk.func_4143_d();
                chunk.onChunkLoad();
            }
            if(!chunk.isTerrainPopulated && chunkExists(i + 1, j + 1) && chunkExists(i, j + 1) && chunkExists(i + 1, j))
            {
                populate(this, i, j);
            }
            if(chunkExists(i - 1, j) && !provideChunk(i - 1, j).isTerrainPopulated && chunkExists(i - 1, j + 1) && chunkExists(i, j + 1) && chunkExists(i - 1, j))
            {
                populate(this, i - 1, j);
            }
            if(chunkExists(i, j - 1) && !provideChunk(i, j - 1).isTerrainPopulated && chunkExists(i + 1, j - 1) && chunkExists(i, j - 1) && chunkExists(i + 1, j))
            {
                populate(this, i, j - 1);
            }
            if(chunkExists(i - 1, j - 1) && !provideChunk(i - 1, j - 1).isTerrainPopulated && chunkExists(i - 1, j - 1) && chunkExists(i, j - 1) && chunkExists(i - 1, j))
            {
                populate(this, i - 1, j - 1);
            }
        }
        return chunk;
    }

    public Chunk provideChunk(int i, int j)
    {
        Chunk chunk = (Chunk)chunkMap.get(Integer.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j)));
        if(chunk == null)
        {
            return prepareChunk(i, j);
        } else
        {
            return chunk;
        }
    }

    private Chunk loadChunkFromFile(int i, int j)
    {
        if(chunkLoader == null)
        {
            return null;
        }
        try
        {
            Chunk chunk = chunkLoader.loadChunk(worldObj, i, j);
            if(chunk != null)
            {
                chunk.lastSaveTime = worldObj.getWorldTime();
            }
            return chunk;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    private void unloadAndSaveChunkData(Chunk chunk)
    {
        if(chunkLoader == null)
        {
            return;
        }
        try
        {
            chunkLoader.saveExtraChunkData(worldObj, chunk);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void unloadAndSaveChunk(Chunk chunk)
    {
        if(chunkLoader == null)
        {
            return;
        }
        try
        {
            chunk.lastSaveTime = worldObj.getWorldTime();
            chunkLoader.saveChunk(worldObj, chunk);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        Chunk chunk = provideChunk(i, j);
        if(!chunk.isTerrainPopulated)
        {
            chunk.isTerrainPopulated = true;
            if(chunkProvider != null)
            {
                chunkProvider.populate(ichunkprovider, i, j);
                chunk.setChunkModified();
            }
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        int i = 0;
        for(int j = 0; j < chunkList.size(); j++)
        {
            Chunk chunk = (Chunk)chunkList.get(j);
            if(flag && !chunk.neverSave)
            {
                unloadAndSaveChunkData(chunk);
            }
            if(!chunk.needsSaving(flag))
            {
                continue;
            }
            unloadAndSaveChunk(chunk);
            chunk.isModified = false;
            if(++i == 24 && !flag)
            {
                return false;
            }
        }

        if(flag)
        {
            if(chunkLoader == null)
            {
                return true;
            }
            chunkLoader.saveExtraData();
        }
        return true;
    }

    public boolean unload100OldestChunks()
    {
        for(int i = 0; i < 100; i++)
        {
            if(!droppedChunksSet.isEmpty())
            {
                Integer integer = (Integer)droppedChunksSet.iterator().next();
                Chunk chunk = (Chunk)chunkMap.get(integer);
                chunk.onChunkUnload();
                unloadAndSaveChunk(chunk);
                unloadAndSaveChunkData(chunk);
                droppedChunksSet.remove(integer);
                chunkMap.remove(integer);
                chunkList.remove(chunk);
            }
        }

        if(chunkLoader != null)
        {
            chunkLoader.chunkTick();
        }
        return chunkProvider.unload100OldestChunks();
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return (new StringBuilder()).append("ServerChunkCache: ").append(chunkMap.size()).append(" Drop: ").append(droppedChunksSet.size()).toString();
    }

    private Set droppedChunksSet;
    private Chunk emptyChunk;
    private IChunkProvider chunkProvider;
    private IChunkLoader chunkLoader;
    private Map chunkMap;
    private List chunkList;
    private World worldObj;
}
