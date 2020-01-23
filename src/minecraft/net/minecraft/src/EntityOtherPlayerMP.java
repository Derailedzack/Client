// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import me.moderator_man.osm.OSM;

// Referenced classes of package net.minecraft.src:
//            EntityPlayer, MathHelper, ItemStack, InventoryPlayer, 
//            World, Entity

public class EntityOtherPlayerMP extends EntityPlayer
{

    public EntityOtherPlayerMP(World world, String s)
    {
        super(world);
        field_20924_a = 0.0F;
        username = s;
        yOffset = 0.0F;
        stepHeight = 0.0F;
        if(s != null && s.length() > 0)
        {
        	skinUrl = OSM.INSTANCE.get("https://www.oldschoolminecraft.com/getskin?username=" + s);
        	cloakUrl = OSM.INSTANCE.get(String.format("https://www.oldschoolminecraft.com/getcloak?username=%s", s));
        	
        	//skinUrl = OSM.INSTANCE.get((new StringBuilder()).append("https://www.oldschoolminecraft.com/getskin?username=").append(s).toString());
        	//cloakUrl = OSM.INSTANCE.get((new StringBuilder()).append("https://www.oldschoolminecraft.com/getcloak?username=").append(s).toString());
        }
        noClip = true;
        renderOffsetY = 0.25F;
        renderDistanceWeight = 10D;
    }

    protected void resetHeight()
    {
        yOffset = 0.0F;
    }

    public boolean attackEntityFrom(Entity entity, int i)
    {
        return true;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f, 
            float f1, int i)
    {
        otherPlayerMPX = d;
        otherPlayerMPY = d1;
        otherPlayerMPZ = d2;
        otherPlayerMPYaw = f;
        otherPlayerMPPitch = f1;
        otherPlayerMPPosRotationIncrements = i;
    }

    public void onUpdate()
    {
        renderOffsetY = 0.0F;
        super.onUpdate();
        prevLegYaw = legYaw;
        double d = posX - prevPosX;
        double d1 = posZ - prevPosZ;
        float f = MathHelper.sqrt_double(d * d + d1 * d1) * 4F;
        if(f > 1.0F)
        {
            f = 1.0F;
        }
        legYaw += (f - legYaw) * 0.4F;
        legSwing += legYaw;
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    public void onLivingUpdate()
    {
        super.updatePlayerActionState();
        if(otherPlayerMPPosRotationIncrements > 0)
        {
            double d = posX + (otherPlayerMPX - posX) / (double)otherPlayerMPPosRotationIncrements;
            double d1 = posY + (otherPlayerMPY - posY) / (double)otherPlayerMPPosRotationIncrements;
            double d2 = posZ + (otherPlayerMPZ - posZ) / (double)otherPlayerMPPosRotationIncrements;
            double d3;
            for(d3 = otherPlayerMPYaw - (double)rotationYaw; d3 < -180D; d3 += 360D) { }
            for(; d3 >= 180D; d3 -= 360D) { }
            rotationYaw += d3 / (double)otherPlayerMPPosRotationIncrements;
            rotationPitch += (otherPlayerMPPitch - (double)rotationPitch) / (double)otherPlayerMPPosRotationIncrements;
            otherPlayerMPPosRotationIncrements--;
            setPosition(d, d1, d2);
            setRotation(rotationYaw, rotationPitch);
        }
        prevCameraYaw = cameraYaw;
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        float f1 = (float)Math.atan(-motionY * 0.20000000298023224D) * 15F;
        if(f > 0.1F)
        {
            f = 0.1F;
        }
        if(!onGround || health <= 0)
        {
            f = 0.0F;
        }
        if(onGround || health <= 0)
        {
            f1 = 0.0F;
        }
        cameraYaw += (f - cameraYaw) * 0.4F;
        cameraPitch += (f1 - cameraPitch) * 0.8F;
    }

    public void outfitWithItem(int i, int j, int k)
    {
        ItemStack itemstack = null;
        if(j >= 0)
        {
            itemstack = new ItemStack(j, 1, k);
        }
        if(i == 0)
        {
            inventory.mainInventory[inventory.currentItem] = itemstack;
        } else
        {
            inventory.armorInventory[i - 1] = itemstack;
        }
    }

    public void func_6420_o()
    {
    }

    private int otherPlayerMPPosRotationIncrements;
    private double otherPlayerMPX;
    private double otherPlayerMPY;
    private double otherPlayerMPZ;
    private double otherPlayerMPYaw;
    private double otherPlayerMPPitch;
    float field_20924_a;
}
