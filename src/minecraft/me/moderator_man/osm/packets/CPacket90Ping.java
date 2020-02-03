package me.moderator_man.osm.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class CPacket90Ping extends Packet
{
	public String msg;
	
	public CPacket90Ping()
	{
		msg = "Ping!";
	}
	
	@Override
	public void readPacketData(DataInputStream datainputstream) throws IOException
	{
		msg = datainputstream.readUTF();
	}

	@Override
	public void writePacketData(DataOutputStream dataoutputstream) throws IOException
	{
		dataoutputstream.writeUTF(msg);
	}

	@Override
	public void processPacket(NetHandler nethandler)
	{
		nethandler.handlePing(this);
	}

	@Override
	public int getPacketSize()
	{
		return msg.length();
	}
	
}
