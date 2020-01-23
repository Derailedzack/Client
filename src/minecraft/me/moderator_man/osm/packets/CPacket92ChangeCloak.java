package me.moderator_man.osm.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class CPacket92ChangeCloak extends Packet
{
	public String username;
	public String url;
	
	public CPacket92ChangeCloak()
	{
		username = "";
		url = ""; // blank = no cloak
	}
	
	@Override
	public void readPacketData(DataInputStream datainputstream) throws IOException
	{
		username = datainputstream.readUTF();
		url = datainputstream.readUTF();
	}

	@Override
	public void writePacketData(DataOutputStream dataoutputstream) throws IOException
	{
		dataoutputstream.writeUTF(username);
		dataoutputstream.writeUTF(url);
	}

	@Override
	public void processPacket(NetHandler nethandler)
	{
		nethandler.handleChangeCloak(this);
	}

	@Override
	public int getPacketSize()
	{
		return username.length() + url.length();
	}
}
