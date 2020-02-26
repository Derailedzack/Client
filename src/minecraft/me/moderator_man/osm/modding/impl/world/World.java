package me.moderator_man.osm.modding.impl.world;

import me.moderator_man.osm.modding.impl.misc.Vector;
import net.minecraft.client.Minecraft;

/**
 * Wrapper for World.
 */
public class World
{
	private net.minecraft.src.World nmsWorld;
	private boolean multiplayer;
	
	public World(String name)
	{
		nmsWorld = Minecraft.getMinecraft().theWorld;
		
		multiplayer = nmsWorld.multiplayerWorld;
	}
	
	public boolean isMultiplayer()
	{
		return multiplayer;
	}
	
	public Block getBlockAt(int x, int y, int z)
	{
		return getBlockAt(new Vector(x, y, z));
	}
	
	public Block getBlockAt(Vector vector)
	{
		//TODO
		return null;
	}
}
