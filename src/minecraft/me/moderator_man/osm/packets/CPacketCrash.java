package me.moderator_man.osm.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class CPacketCrash extends Packet
{
	
    public CPacketCrash()
    {
    }

    public CPacketCrash(boolean flag)
    {
        onGround = flag;
    }

    public void processPacket(NetHandler nethandler)
    {
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        onGround = datainputstream.read() != 0;
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
    	dataoutputstream.writeDouble(xPosition);
    	dataoutputstream.writeDouble(yPosition);
    	dataoutputstream.writeDouble(zPosition);
    	dataoutputstream.writeDouble(stance);
    	dataoutputstream.writeFloat(yaw);
    	dataoutputstream.writeFloat(pitch);
    	dataoutputstream.writeBoolean(onGround);
    	dataoutputstream.writeBoolean(moving);
    	dataoutputstream.writeBoolean(rotating);
    }

    public int getPacketSize()
    {
        return 8 + 8 + 8 + 8 + 4 + 4 + 1 + 1 + 1;
    }
    
    public int getPacketId()
    {
    	// fake packet10flying
    	return 10;
    }

    public double xPosition;
    public double yPosition;
    public double zPosition;
    public double stance;
    public float yaw;
    public float pitch;
    public boolean onGround;
    public boolean moving;
    public boolean rotating;
}
