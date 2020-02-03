// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import me.moderator_man.osm.OSM;
import me.moderator_man.osm.event.EventTarget;
import me.moderator_man.osm.event.events.EventKeyboard;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            EntityPlayerSP, MathHelper, World, Packet19EntityAction, 
//            NetClientHandler, AxisAlignedBB, Packet11PlayerPosition, Packet13PlayerLookMove, 
//            Packet12PlayerLook, Packet10Flying, Packet14BlockDig, Packet3Chat, 
//            Packet18Animation, Packet9Respawn, Packet101CloseWindow, Container, 
//            InventoryPlayer, StatBase, Session, Entity, 
//            EntityItem

public class EntityClientPlayerMP extends EntityPlayerSP
{

    public EntityClientPlayerMP(Minecraft minecraft, World world, Session session, NetClientHandler netclienthandler)
    {
        super(minecraft, world, session, 0);
        inventoryUpdateTickCounter = 0;
        hasSetHealth = false;
        wasOnGround = false;
        wasSneaking = false;
        timeSinceMoved = 0;
        sendQueue = netclienthandler;
    }

    public boolean attackEntityFrom(Entity entity, int i)
    {
        return false;
    }

    public void heal(int i)
    {
    }
    
    @EventTarget
    public void onKeyboard(EventKeyboard event)
    {
    	if (event.getKeyCode() == Minecraft.getMinecraft().gameSettings.keyBindJump.keyCode)
    	{
    		long currentJumpTime = System.currentTimeMillis();
        	long difference = currentJumpTime - lastJumpTime;
        	if (lastJumpTime != -1 && difference < 400)
        	{
        		if (!playerCapabilities.getFlag("canFly"))
        		{
        			lastJumpTime = System.currentTimeMillis();
        			return;
        		}
        		/*playerCapabilities.setFlag("flight", !playerCapabilities.getFlag("flight"));
        		if (playerCapabilities.getFlag("flight"))
        			playerCapabilities.setFlag("nofall", !playerCapabilities.getFlag("nofall"));*/
        	} else {
        		lastJumpTime = System.currentTimeMillis();
        	}
    	}
    }
    
    public void onUpdate()
    {
    	/*if (playerCapabilities.getFlag("flight"))
        {
        	if(movementInput.sneak)
            {
                motionY -= 0.14999999999999999D;
            }
            if(movementInput.jump)
            {
                motionY += 0.14999999999999999D;
            }
        }*/
    	
    	/*if (playerCapabilities.getFlag("nofall"))
    		sendQueue.addToSendQueue(new Packet11PlayerPosition(motionX, -999D, -999D, motionZ, !onGround));
    	else
    		sendQueue.addToSendQueue(new Packet11PlayerPosition(motionX, -999D, -999D, motionZ, onGround));*/
    	
        if(!worldObj.blockExists(MathHelper.floor_double(posX), 64, MathHelper.floor_double(posZ)))
        {
            return;
        } else
        {
            super.onUpdate();
            sendMotionUpdates();
            return;
        }
    }

    public void sendMotionUpdates()
    {
        if(inventoryUpdateTickCounter++ == 20)
        {
            sendInventoryChanged();
            inventoryUpdateTickCounter = 0;
        }
        boolean flag = isSneaking();
        if(flag != wasSneaking)
        {
            if(flag)
            {
                sendQueue.addToSendQueue(new Packet19EntityAction(this, 1));
            } else
            {
                sendQueue.addToSendQueue(new Packet19EntityAction(this, 2));
            }
            wasSneaking = flag;
        }
        double d = posX - oldPosX;
        double d1 = boundingBox.minY - oldMinY;
        double d2 = posY - oldPosY;
        double d3 = posZ - oldPosZ;
        double d4 = rotationYaw - oldRotationYaw;
        double d5 = rotationPitch - oldRotationPitch;
        boolean flag1 = d1 != 0.0D || d2 != 0.0D || d != 0.0D || d3 != 0.0D;
        boolean flag2 = d4 != 0.0D || d5 != 0.0D;
        if(ridingEntity != null)
        {
            if(flag2)
            {
                sendQueue.addToSendQueue(new Packet11PlayerPosition(motionX, -999D, -999D, motionZ, onGround));
            } else
            {
                sendQueue.addToSendQueue(new Packet13PlayerLookMove(motionX, -999D, -999D, motionZ, rotationYaw, rotationPitch, onGround));
            }
            flag1 = false;
        } else
        if(flag1 && flag2)
        {
            sendQueue.addToSendQueue(new Packet13PlayerLookMove(posX, boundingBox.minY, posY, posZ, rotationYaw, rotationPitch, onGround));
            timeSinceMoved = 0;
        } else
        if(flag1)
        {
            sendQueue.addToSendQueue(new Packet11PlayerPosition(posX, boundingBox.minY, posY, posZ, onGround));
            timeSinceMoved = 0;
        } else
        if(flag2)
        {
            sendQueue.addToSendQueue(new Packet12PlayerLook(rotationYaw, rotationPitch, onGround));
            timeSinceMoved = 0;
        } else
        {
            sendQueue.addToSendQueue(new Packet10Flying(onGround));
            if(wasOnGround != onGround || timeSinceMoved > 200)
            {
                timeSinceMoved = 0;
            } else
            {
                timeSinceMoved++;
            }
        }
        wasOnGround = onGround;
        if(flag1)
        {
            oldPosX = posX;
            oldMinY = boundingBox.minY;
            oldPosY = posY;
            oldPosZ = posZ;
        }
        if(flag2)
        {
            oldRotationYaw = rotationYaw;
            oldRotationPitch = rotationPitch;
        }
    }

    public void dropCurrentItem()
    {
        sendQueue.addToSendQueue(new Packet14BlockDig(4, 0, 0, 0, 0));
    }

    private void sendInventoryChanged()
    {
    }

    protected void joinEntityItemWithWorld(EntityItem entityitem)
    {
    }

    public void sendChatMessage(String s)
    {
    	if (OSM.INSTANCE.COMMAND_MANAGER.process(s))
    		sendQueue.addToSendQueue(new Packet3Chat(s));
    }

    public void swingItem()
    {
        super.swingItem();
        sendQueue.addToSendQueue(new Packet18Animation(this, 1));
    }

    public void respawnPlayer()
    {
        sendInventoryChanged();
        sendQueue.addToSendQueue(new Packet9Respawn((byte)dimension));
    }

    protected void damageEntity(int i)
    {
        health -= i;
    }

    public void closeScreen()
    {
        sendQueue.addToSendQueue(new Packet101CloseWindow(craftingInventory.windowId));
        inventory.setItemStack(null);
        super.closeScreen();
    }

    public void setHealth(int i)
    {
        if(hasSetHealth)
        {
            super.setHealth(i);
        } else
        {
            health = i;
            hasSetHealth = true;
        }
    }

    public void addStat(StatBase statbase, int i)
    {
        if(statbase == null)
        {
            return;
        }
        if(statbase.isIndependent)
        {
            super.addStat(statbase, i);
        }
    }

    public void incrementStat(StatBase statbase, int i)
    {
        if(statbase == null)
        {
            return;
        }
        if(!statbase.isIndependent)
        {
            super.addStat(statbase, i);
        }
    }

    public NetClientHandler sendQueue;
    private int inventoryUpdateTickCounter;
    private boolean hasSetHealth;
    private double oldPosX;
    private double oldMinY;
    private double oldPosY;
    private double oldPosZ;
    private float oldRotationYaw;
    private float oldRotationPitch;
    private boolean wasOnGround;
    private boolean wasSneaking;
    private int timeSinceMoved;
}
