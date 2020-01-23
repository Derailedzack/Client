// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            EntityFX, Entity, MathHelper, World, 
//            RenderManager, Tessellator

public class EntityPickupFX extends EntityFX
{

    public EntityPickupFX(World world, Entity entity, Entity entity1, float f)
    {
        super(world, entity.posX, entity.posY, entity.posZ, entity.motionX, entity.motionY, entity.motionZ);
        age = 0;
        maxAge = 0;
        entityToPickUp = entity;
        entityPickingUp = entity1;
        maxAge = 3;
        yOffs = f;
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {
        float f6 = ((float)age + f) / (float)maxAge;
        f6 *= f6;
        double d = entityToPickUp.posX;
        double d1 = entityToPickUp.posY;
        double d2 = entityToPickUp.posZ;
        double d3 = entityPickingUp.lastTickPosX + (entityPickingUp.posX - entityPickingUp.lastTickPosX) * (double)f;
        double d4 = entityPickingUp.lastTickPosY + (entityPickingUp.posY - entityPickingUp.lastTickPosY) * (double)f + (double)yOffs;
        double d5 = entityPickingUp.lastTickPosZ + (entityPickingUp.posZ - entityPickingUp.lastTickPosZ) * (double)f;
        double d6 = d + (d3 - d) * (double)f6;
        double d7 = d1 + (d4 - d1) * (double)f6;
        double d8 = d2 + (d5 - d2) * (double)f6;
        int i = MathHelper.floor_double(d6);
        int j = MathHelper.floor_double(d7 + (double)(yOffset / 2.0F));
        int k = MathHelper.floor_double(d8);
        float f7 = worldObj.getLightBrightness(i, j, k);
        d6 -= interpPosX;
        d7 -= interpPosY;
        d8 -= interpPosZ;
        GL11.glColor4f(f7, f7, f7, 1.0F);
        RenderManager.instance.renderEntityWithPosYaw(entityToPickUp, (float)d6, (float)d7, (float)d8, entityToPickUp.rotationYaw, f);
    }

    public void onUpdate()
    {
        age++;
        if(age == maxAge)
        {
            setEntityDead();
        }
    }

    public int getFXLayer()
    {
        return 3;
    }

    private Entity entityToPickUp;
    private Entity entityPickingUp;
    private int age;
    private int maxAge;
    private float yOffs;
}
