// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityAnimal, World, Item, NBTTagCompound

public class EntityChicken extends EntityAnimal
{

    public EntityChicken(World world)
    {
        super(world);
        field_753_a = false;
        wingRotation = 0.0F;
        destPos = 0.0F;
        wingRotDelta = 1.0F;
        texture = "/mob/chicken.png";
        setSize(0.3F, 0.4F);
        health = 4;
        timeUntilNextEgg = rand.nextInt(6000) + 6000;
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        oFlap = wingRotation;
        oFlapSpeed = destPos;
        destPos += (double)(onGround ? -1 : 4) * 0.29999999999999999D;
        if(destPos < 0.0F)
        {
            destPos = 0.0F;
        }
        if(destPos > 1.0F)
        {
            destPos = 1.0F;
        }
        if(!onGround && wingRotDelta < 1.0F)
        {
            wingRotDelta = 1.0F;
        }
        wingRotDelta *= 0.90000000000000002D;
        if(!onGround && motionY < 0.0D)
        {
            motionY *= 0.59999999999999998D;
        }
        wingRotation += wingRotDelta * 2.0F;
        if(!worldObj.multiplayerWorld && --timeUntilNextEgg <= 0)
        {
            worldObj.playSoundAtEntity(this, "mob.chickenplop", 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            dropItem(Item.egg.shiftedIndex, 1);
            timeUntilNextEgg = rand.nextInt(6000) + 6000;
        }
    }

    protected void fall(float f)
    {
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "mob.chicken";
    }

    protected String getHurtSound()
    {
        return "mob.chickenhurt";
    }

    protected String getDeathSound()
    {
        return "mob.chickenhurt";
    }

    protected int getDropItemId()
    {
        return Item.feather.shiftedIndex;
    }

    public boolean field_753_a;
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta;
    public int timeUntilNextEgg;
}
