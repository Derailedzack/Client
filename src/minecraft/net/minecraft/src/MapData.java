// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            MapDataBase, NBTTagCompound, MapInfo, EntityPlayer, 
//            InventoryPlayer, MapCoord, ItemStack

public class MapData extends MapDataBase
{

    public MapData(String s)
    {
        super(s);
        colors = new byte[16384];
        playersArrayList = new ArrayList();
        playersHashMap = new HashMap();
        playersVisibleOnMap = new ArrayList();
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        dimension = nbttagcompound.getByte("dimension");
        xCenter = nbttagcompound.getInteger("xCenter");
        zCenter = nbttagcompound.getInteger("zCenter");
        scale = nbttagcompound.getByte("scale");
        if(scale < 0)
        {
            scale = 0;
        }
        if(scale > 4)
        {
            scale = 4;
        }
        short word0 = nbttagcompound.getShort("width");
        short word1 = nbttagcompound.getShort("height");
        if(word0 == 128 && word1 == 128)
        {
            colors = nbttagcompound.getByteArray("colors");
        } else
        {
            byte abyte0[] = nbttagcompound.getByteArray("colors");
            colors = new byte[16384];
            int i = (128 - word0) / 2;
            int j = (128 - word1) / 2;
            for(int k = 0; k < word1; k++)
            {
                int l = k + j;
                if(l < 0 && l >= 128)
                {
                    continue;
                }
                for(int i1 = 0; i1 < word0; i1++)
                {
                    int j1 = i1 + i;
                    if(j1 >= 0 || j1 < 128)
                    {
                        colors[j1 + l * 128] = abyte0[i1 + k * word0];
                    }
                }

            }

        }
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setByte("dimension", dimension);
        nbttagcompound.setInteger("xCenter", xCenter);
        nbttagcompound.setInteger("zCenter", zCenter);
        nbttagcompound.setByte("scale", scale);
        nbttagcompound.setShort("width", (short)128);
        nbttagcompound.setShort("height", (short)128);
        nbttagcompound.setByteArray("colors", colors);
    }

    public void updateVisiblePlayers(EntityPlayer entityplayer, ItemStack itemstack)
    {
        if(!playersHashMap.containsKey(entityplayer))
        {
            MapInfo mapinfo = new MapInfo(this, entityplayer);
            playersHashMap.put(entityplayer, mapinfo);
            playersArrayList.add(mapinfo);
        }
        playersVisibleOnMap.clear();
        for(int i = 0; i < playersArrayList.size(); i++)
        {
            MapInfo mapinfo1 = (MapInfo)playersArrayList.get(i);
            if(mapinfo1.entityplayerObj.isDead || !mapinfo1.entityplayerObj.inventory.hasItemStack(itemstack))
            {
                playersHashMap.remove(mapinfo1.entityplayerObj);
                playersArrayList.remove(mapinfo1);
                continue;
            }
            float f = (float)(mapinfo1.entityplayerObj.posX - (double)xCenter) / (float)(1 << scale);
            float f1 = (float)(mapinfo1.entityplayerObj.posZ - (double)zCenter) / (float)(1 << scale);
            int j = 64;
            int k = 64;
            if(f < (float)(-j) || f1 < (float)(-k) || f > (float)j || f1 > (float)k)
            {
                continue;
            }
            byte byte0 = 0;
            byte byte1 = (byte)(int)((double)(f * 2.0F) + 0.5D);
            byte byte2 = (byte)(int)((double)(f1 * 2.0F) + 0.5D);
            byte byte3 = (byte)(int)((double)((entityplayer.rotationYaw * 16F) / 360F) + 0.5D);
            if(dimension < 0)
            {
                int l = step / 10;
                byte3 = (byte)(l * l * 0x209a771 + l * 121 >> 15 & 0xf);
            }
            if(mapinfo1.entityplayerObj.dimension == dimension)
            {
                playersVisibleOnMap.add(new MapCoord(this, byte0, byte1, byte2, byte3));
            }
        }

    }

    public void setColumnDirty(int i, int j, int k)
    {
        super.markDirty();
        for(int l = 0; l < playersArrayList.size(); l++)
        {
            MapInfo mapinfo = (MapInfo)playersArrayList.get(l);
            if(mapinfo.field_28119_b[i] < 0 || mapinfo.field_28119_b[i] > j)
            {
                mapinfo.field_28119_b[i] = j;
            }
            if(mapinfo.field_28124_c[i] < 0 || mapinfo.field_28124_c[i] < k)
            {
                mapinfo.field_28124_c[i] = k;
            }
        }

    }

    public void updateMPMapData(byte abyte0[])
    {
        if(abyte0[0] == 0)
        {
            int i = abyte0[1] & 0xff;
            int k = abyte0[2] & 0xff;
            for(int l = 0; l < abyte0.length - 3; l++)
            {
                colors[(l + k) * 128 + i] = abyte0[l + 3];
            }

            markDirty();
        } else
        if(abyte0[0] == 1)
        {
            playersVisibleOnMap.clear();
            for(int j = 0; j < (abyte0.length - 1) / 3; j++)
            {
                byte byte0 = (byte)(abyte0[j * 3 + 1] % 16);
                byte byte1 = abyte0[j * 3 + 2];
                byte byte2 = abyte0[j * 3 + 3];
                byte byte3 = (byte)(abyte0[j * 3 + 1] / 16);
                playersVisibleOnMap.add(new MapCoord(this, byte0, byte1, byte2, byte3));
            }

        }
    }

    public int xCenter;
    public int zCenter;
    public byte dimension;
    public byte scale;
    public byte colors[];
    public int step;
    public List playersArrayList;
    private Map playersHashMap;
    public List playersVisibleOnMap;
}
