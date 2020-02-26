package me.moderator_man.osm.modding.api.world;

import me.moderator_man.osm.modding.impl.misc.Vector;

public interface IWorld
{
	public boolean isMultiplayer();
	
	public IBlock getBlockAt(int x, int y, int z);
	
	public IBlock getBlockAt(Vector vector);
}
