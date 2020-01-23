package me.moderator_man.osm.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class CPacket96ToggleCapability extends Packet
{
	public String label;
	public boolean flag;
	
	@Override
	public void readPacketData(DataInputStream datainputstream) throws IOException
	{
		label = datainputstream.readUTF();
		flag = datainputstream.readBoolean();
	}

	@Override
	public void writePacketData(DataOutputStream dataoutputstream) throws IOException
	{
		dataoutputstream.writeUTF(label);
		dataoutputstream.writeBoolean(flag);
	}

	@Override
	public void processPacket(NetHandler nethandler)
	{
		nethandler.handleToggleCapability(this);
	}

	@Override
	public int getPacketSize()
	{
		return 0;
	}
}
