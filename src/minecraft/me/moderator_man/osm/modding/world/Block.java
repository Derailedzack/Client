package me.moderator_man.osm.modding.world;

import me.moderator_man.osm.modding.sound.StepSound;

public class Block
{
	private int indexTexture;
	private int id;
	private float hardness;
	private float resistance;
	private double minX;
	private double minY;
	private double minZ;
	private double maxX;
	private double maxY;
	private double maxZ;
	private StepSound stepSound;
	private float particleGravity;
	private Material material;
	private float slipperiness;
	private String name;
	
	public Block(net.minecraft.src.Block block)
	{
		//TODO: fill in the data for the NMS block
	}
	
	/**
	 * Create a custom block.
	 * TODO
	 */
	public Block() {}
}
