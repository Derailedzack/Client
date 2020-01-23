// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            MapData

public class MapCoord
{

    public MapCoord(MapData mapdata, byte byte0, byte byte1, byte byte2, byte byte3)
    {
//        super();
        data = mapdata;
        iconSize = byte0;
        centerX = byte1;
        centerZ = byte2;
        iconRotation = byte3;
    }

    public byte iconSize;
    public byte centerX;
    public byte centerZ;
    public byte iconRotation;
    final MapData data; /* synthetic field */
}
