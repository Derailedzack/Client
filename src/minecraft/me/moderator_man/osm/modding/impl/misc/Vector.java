package me.moderator_man.osm.modding.impl.misc;

/**
 * Signifies a 3D coordinate.
 */
public class Vector
{
	private int x, y, z;
	
	public Vector(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
}
