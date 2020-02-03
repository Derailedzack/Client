package me.moderator_man.osm.command.commands;

import me.moderator_man.osm.OSM;
import me.moderator_man.osm.command.Command;
import me.moderator_man.osm.packets.CPacket91ChangeSkin;

public class Test extends Command
{
	@Override
	public void call(String[] args)
	{
		if (args.length-1 < 2)
		{
			OSM.INSTANCE.addMsg("Invalid arguments! Usage: .test <username> <url>");
			return;
		}
		
		String username = args[1];
		String url = args[2];
		
		CPacket91ChangeSkin packet = new CPacket91ChangeSkin();
		packet.username = username;
		packet.url = url;
		OSM.INSTANCE.sendPacket(packet);
	}
}
