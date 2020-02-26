package me.moderator_man.osm.modding.api.world;

import me.moderator_man.osm.modding.impl.sound.StepSound;

public interface IBlock
{
	public IBlock setName(String name);
	
	public IBlock setTextureIndex(int index);
	
	public IBlock setHardness(float hardness);
	
	public IBlock setResistance(float resistance);
	
	public IBlock setStepSound(StepSound stepSound);
}
