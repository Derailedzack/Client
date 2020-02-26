package me.moderator_man.osm.modding.impl.world;

import me.moderator_man.osm.modding.api.world.IBlock;
import me.moderator_man.osm.modding.impl.sound.StepSound;

public class Block implements IBlock
{
	private String name;
	private int textureIndex;
	private float hardness;
	private float resistance;
	private StepSound stepSound;
	
	public IBlock setName(String name)
	{
		this.name = name;
		return this;
	}

	public IBlock setTextureIndex(int index)
	{
		this.textureIndex = index;
		return this;
	}

	public IBlock setHardness(float hardness)
	{
		this.hardness = hardness;
		return this;
	}

	public IBlock setResistance(float resistance)
	{
		this.resistance = resistance;
		return this;
	}

	public IBlock setStepSound(StepSound stepSound)
	{
		this.stepSound = stepSound;
		return this;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getTextureIndex()
	{
		return textureIndex;
	}
	
	public float getHardness()
	{
		return hardness;
	}
	
	public float getResistance()
	{
		return resistance;
	}
	
	public StepSound getStepSound()
	{
		return stepSound;
	}
}
