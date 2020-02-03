package me.moderator_man.osm.module;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;

public class PlayerSPWrapper
{
	private EntityPlayerSP player;

	public PlayerSPWrapper(EntityPlayerSP player)
	{
		this.player = player;
	}
	
	/**
	 * Is the player on the ground?
	 * @return boolean
	 */
	public boolean onGround()
	{
		return player.onGround;
	}
	
	/**
	 * Set the players onGround flag.
	 * @param flag
	 */
	public void onGround(boolean flag)
	{
		player.onGround = flag;
	}
	
	/**
	 * Get the players X motion.
	 * @return double
	 */
	public double motionX()
	{
		return player.motionX;
	}
	
	/**
	 * Get the players Y motion.
	 * @return double
	 */
	public double motionY()
	{
		return player.motionY;
	}
	
	/**
	 * Get the players Z motion.
	 * @return double
	 */
	public double motionZ()
	{
		return player.motionZ;
	}
	
	/**
	 * Set the players X motion.
	 * @return double
	 */
	public void motionX(double val)
	{
		player.motionX = val;
	}
	
	/**
	 * Set the players Y motion.
	 * @return double
	 */
	public void motionY(double val)
	{
		player.motionY = val;
	}
	
	/**
	 * Set the players Z motion.
	 * @return double
	 */
	public void motionZ(double val)
	{
		player.motionZ = val;
	}
	
	/**
	 * The players rotation pitch.
	 * @return float
	 */
	public float rotationPitch()
	{
		return player.rotationPitch;
	}
	
	/**
	 * The players rotation yaw.
	 * @return float
	 */
	public float rotationYaw()
	{
		return player.rotationYaw;
	}
	
	/**
	 * moveFlying
	 * @param f
	 * @param f1
	 * @param f2
	 */
	public void moveFlying(float f, float f1, float f2)
	{
		player.moveFlying(f, f1, f2);
	}
	
	/**
	 * Get original EntityPlayer object
	 * @return
	 */
	public EntityPlayer getPlayer()
	{
		return player;
	}
}
