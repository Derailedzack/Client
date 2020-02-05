package me.moderator_man.osm.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class CPacket95Authenticate extends Packet
{
	public String serverId;
	
	public CPacket95Authenticate() {}
	
	public CPacket95Authenticate(String serverId)
	{
		this.serverId = serverId;
	}
	
	@Override
	public void readPacketData(DataInputStream datainputstream) throws IOException
	{
		serverId = datainputstream.readUTF();
	}

	@Override
	public void writePacketData(DataOutputStream dataoutputstream) throws IOException
	{
		dataoutputstream.writeUTF(serverId);
	}

	@Override
	public void processPacket(NetHandler nethandler)
	{
		nethandler.handleAuthenticate(this);
	}

	@Override
	public int getPacketSize()
	{
		return serverId.length();
	}
}
