// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            TileEntity, PistonBlockTextures, Block, BlockPistonMoving, 
//            World, Entity, NBTTagCompound

public class TileEntityPiston extends TileEntity
{

    public TileEntityPiston()
    {
    }

    public TileEntityPiston(int i, int j, int k, boolean flag, boolean flag1)
    {
        storedBlockID = i;
        storedMetadata = j;
        field_31025_c = k;
        field_31024_i = flag;
        field_31023_j = flag1;
    }

    public int getStoredBlockID()
    {
        return storedBlockID;
    }

    public int getBlockMetadata()
    {
        return storedMetadata;
    }

    public boolean isExtending()
    {
        return field_31024_i;
    }

    public int getPistonOrientation()
    {
        return field_31025_c;
    }

    public boolean func_31012_k()
    {
        return field_31023_j;
    }

    public float getProgress(float f)
    {
        if(f > 1.0F)
        {
            f = 1.0F;
        }
        return field_31020_l + (field_31022_k - field_31020_l) * f;
    }

    public float func_31017_b(float f)
    {
        if(field_31024_i)
        {
            return (getProgress(f) - 1.0F) * (float)PistonBlockTextures.offsetsXForSide[field_31025_c];
        } else
        {
            return (1.0F - getProgress(f)) * (float)PistonBlockTextures.offsetsXForSide[field_31025_c];
        }
    }

    public float func_31014_c(float f)
    {
        if(field_31024_i)
        {
            return (getProgress(f) - 1.0F) * (float)PistonBlockTextures.offsetsYForSide[field_31025_c];
        } else
        {
            return (1.0F - getProgress(f)) * (float)PistonBlockTextures.offsetsYForSide[field_31025_c];
        }
    }

    public float func_31013_d(float f)
    {
        if(field_31024_i)
        {
            return (getProgress(f) - 1.0F) * (float)PistonBlockTextures.offsetsZForSide[field_31025_c];
        } else
        {
            return (1.0F - getProgress(f)) * (float)PistonBlockTextures.offsetsZForSide[field_31025_c];
        }
    }

    private void func_31010_a(float f, float f1)
    {
        if(!field_31024_i)
        {
            f--;
        } else
        {
            f = 1.0F - f;
        }
        AxisAlignedBB axisalignedbb = Block.pistonMoving.getAxisAlignedBB(worldObj, xCoord, yCoord, zCoord, storedBlockID, f, field_31025_c);
        if(axisalignedbb != null)
        {
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(null, axisalignedbb);
            if(!list.isEmpty())
            {
                field_31018_m.addAll(list);
                Entity entity;
                for(Iterator iterator = field_31018_m.iterator(); iterator.hasNext(); entity.moveEntity(f1 * (float)PistonBlockTextures.offsetsXForSide[field_31025_c], f1 * (float)PistonBlockTextures.offsetsYForSide[field_31025_c], f1 * (float)PistonBlockTextures.offsetsZForSide[field_31025_c]))
                {
                    entity = (Entity)iterator.next();
                }

                field_31018_m.clear();
            }
        }
    }

    public void clearPistonTileEntity()
    {
        if(field_31020_l < 1.0F)
        {
            field_31020_l = field_31022_k = 1.0F;
            worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
            invalidate();
            if(worldObj.getBlockId(xCoord, yCoord, zCoord) == Block.pistonMoving.blockID)
            {
                worldObj.setBlockAndMetadataWithNotify(xCoord, yCoord, zCoord, storedBlockID, storedMetadata);
            }
        }
    }

    public void updateEntity()
    {
        field_31020_l = field_31022_k;
        if(field_31020_l >= 1.0F)
        {
            func_31010_a(1.0F, 0.25F);
            worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
            invalidate();
            if(worldObj.getBlockId(xCoord, yCoord, zCoord) == Block.pistonMoving.blockID)
            {
                worldObj.setBlockAndMetadataWithNotify(xCoord, yCoord, zCoord, storedBlockID, storedMetadata);
            }
            return;
        }
        field_31022_k += 0.5F;
        if(field_31022_k >= 1.0F)
        {
            field_31022_k = 1.0F;
        }
        if(field_31024_i)
        {
            func_31010_a(field_31022_k, (field_31022_k - field_31020_l) + 0.0625F);
        }
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        storedBlockID = nbttagcompound.getInteger("blockId");
        storedMetadata = nbttagcompound.getInteger("blockData");
        field_31025_c = nbttagcompound.getInteger("facing");
        field_31020_l = field_31022_k = nbttagcompound.getFloat("progress");
        field_31024_i = nbttagcompound.getBoolean("extending");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("blockId", storedBlockID);
        nbttagcompound.setInteger("blockData", storedMetadata);
        nbttagcompound.setInteger("facing", field_31025_c);
        nbttagcompound.setFloat("progress", field_31020_l);
        nbttagcompound.setBoolean("extending", field_31024_i);
    }

    private int storedBlockID;
    private int storedMetadata;
    private int field_31025_c;
    private boolean field_31024_i;
    private boolean field_31023_j;
    private float field_31022_k;
    private float field_31020_l;
    private static List field_31018_m = new ArrayList();

}
