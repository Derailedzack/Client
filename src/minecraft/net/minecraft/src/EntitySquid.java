// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityWaterMob, ItemStack, Item, AxisAlignedBB, 
//            Material, World, MathHelper, NBTTagCompound, 
//            EntityPlayer

public class EntitySquid extends EntityWaterMob
{

    public EntitySquid(World world)
    {
        super(world);
        squidPitch = 0.0F;
        prevSquidPitch = 0.0F;
        squidYaw = 0.0F;
        prevSquidYaw = 0.0F;
        squidRotation = 0.0F;
        prevSquidRotation = 0.0F;
        tentacleAngle = 0.0F;
        lastTentacleAngle = 0.0F;
        randomMotionSpeed = 0.0F;
        rotationVelocity = 0.0F;
        rotateSpeed = 0.0F;
        randomMotionVecX = 0.0F;
        randomMotionVecY = 0.0F;
        randomMotionVecZ = 0.0F;
        texture = "/mob/squid.png";
        setSize(0.95F, 0.95F);
        rotationVelocity = (1.0F / (rand.nextFloat() + 1.0F)) * 0.2F;
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
        return null;
    }

    protected String getHurtSound()
    {
        return null;
    }

    protected String getDeathSound()
    {
        return null;
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected int getDropItemId()
    {
        return 0;
    }

    protected void dropFewItems()
    {
        int i = rand.nextInt(3) + 1;
        for(int j = 0; j < i; j++)
        {
            entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
        }

    }

    public boolean interact(EntityPlayer entityplayer)
    {
        return false;
    }

    public boolean isInWater()
    {
        return worldObj.handleMaterialAcceleration(boundingBox.expand(0.0D, -0.60000002384185791D, 0.0D), Material.water, this);
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        prevSquidPitch = squidPitch;
        prevSquidYaw = squidYaw;
        prevSquidRotation = squidRotation;
        lastTentacleAngle = tentacleAngle;
        squidRotation += rotationVelocity;
        if(squidRotation > 6.283185F)
        {
            squidRotation -= 6.283185F;
            if(rand.nextInt(10) == 0)
            {
                rotationVelocity = (1.0F / (rand.nextFloat() + 1.0F)) * 0.2F;
            }
        }
        if(isInWater())
        {
            if(squidRotation < 3.141593F)
            {
                float f = squidRotation / 3.141593F;
                tentacleAngle = MathHelper.sin(f * f * 3.141593F) * 3.141593F * 0.25F;
                if((double)f > 0.75D)
                {
                    randomMotionSpeed = 1.0F;
                    rotateSpeed = 1.0F;
                } else
                {
                    rotateSpeed = rotateSpeed * 0.8F;
                }
            } else
            {
                tentacleAngle = 0.0F;
                randomMotionSpeed = randomMotionSpeed * 0.9F;
                rotateSpeed = rotateSpeed * 0.99F;
            }
            if(!isMultiplayerEntity)
            {
                motionX = randomMotionVecX * randomMotionSpeed;
                motionY = randomMotionVecY * randomMotionSpeed;
                motionZ = randomMotionVecZ * randomMotionSpeed;
            }
            float f1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
            renderYawOffset += ((-(float)Math.atan2(motionX, motionZ) * 180F) / 3.141593F - renderYawOffset) * 0.1F;
            rotationYaw = renderYawOffset;
            squidYaw = squidYaw + 3.141593F * rotateSpeed * 1.5F;
            squidPitch += ((-(float)Math.atan2(f1, motionY) * 180F) / 3.141593F - squidPitch) * 0.1F;
        } else
        {
            tentacleAngle = MathHelper.abs(MathHelper.sin(squidRotation)) * 3.141593F * 0.25F;
            if(!isMultiplayerEntity)
            {
                motionX = 0.0D;
                motionY -= 0.080000000000000002D;
                motionY *= 0.98000001907348633D;
                motionZ = 0.0D;
            }
            squidPitch += (double)(-90F - squidPitch) * 0.02D;
        }
    }

    public void moveEntityWithHeading(float f, float f1)
    {
        moveEntity(motionX, motionY, motionZ);
    }

    protected void updatePlayerActionState()
    {
        if(rand.nextInt(50) == 0 || !inWater || randomMotionVecX == 0.0F && randomMotionVecY == 0.0F && randomMotionVecZ == 0.0F)
        {
            float f = rand.nextFloat() * 3.141593F * 2.0F;
            randomMotionVecX = MathHelper.cos(f) * 0.2F;
            randomMotionVecY = -0.1F + rand.nextFloat() * 0.2F;
            randomMotionVecZ = MathHelper.sin(f) * 0.2F;
        }
        despawnEntity();
    }

    public float squidPitch;
    public float prevSquidPitch;
    public float squidYaw;
    public float prevSquidYaw;
    public float squidRotation;
    public float prevSquidRotation;
    public float tentacleAngle;
    public float lastTentacleAngle;
    private float randomMotionSpeed;
    private float rotationVelocity;
    private float rotateSpeed;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;
}
