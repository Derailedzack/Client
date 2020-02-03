package me.moderator_man.osm.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class CPacket93RequestCosmetics extends Packet
{
	public String username;
	
	public CPacket93RequestCosmetics() {}
	
	public CPacket93RequestCosmetics(String username)
	{
		this.username = username;
	}
	
	@Override
	public void readPacketData(DataInputStream datainputstream) throws IOException
	{
		username = datainputstream.readUTF();
	}

	@Override
	public void writePacketData(DataOutputStream dataoutputstream) throws IOException
	{
		dataoutputstream.writeUTF(username);
	}

	@Override
	public void processPacket(NetHandler nethandler)
	{
		nethandler.handleRequestCosmetics(this);
	}

	@Override
	public int getPacketSize()
	{
		return username.length();
	}
}
