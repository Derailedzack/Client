package me.moderator_man.osm.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class CPacket91ChangeSkin extends Packet
{
	public String username;
	public String url;
	
	public CPacket91ChangeSkin()
	{
		username = "";
		url = "https://i.imgur.com/9kw1uYu.png"; // steve
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
		nethandler.handleChangeSkin(this);
	}

	@Override
	public int getPacketSize()
	{
		return username.length() + url.length();
	}
}
