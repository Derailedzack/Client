package me.moderator_man.osm.modding.impl.world;

import net.minecraft.src.MapColor;

public enum Material
{
	AIR(net.minecraft.src.Material.air),
	GRASS(net.minecraft.src.Material.grassMaterial),
	GROUND(net.minecraft.src.Material.ground),
	WOOD(net.minecraft.src.Material.wood),
	ROCK(net.minecraft.src.Material.rock),
	IRON(net.minecraft.src.Material.iron),
	WATER(net.minecraft.src.Material.water),
	LAVA(net.minecraft.src.Material.lava),
	LEAVES(net.minecraft.src.Material.leaves),
	PLANTS(net.minecraft.src.Material.plants),
	SPONGE(net.minecraft.src.Material.sponge),
	CLOTH(net.minecraft.src.Material.cloth),
	FIRE(net.minecraft.src.Material.fire),
	SAND(net.minecraft.src.Material.sand),
	CIRCUITS(net.minecraft.src.Material.circuits),
	GLASS(net.minecraft.src.Material.glass),
	TNT(net.minecraft.src.Material.tnt),
	CORAL(net.minecraft.src.Material.coral),
	ICE(net.minecraft.src.Material.ice),
	SNOW(net.minecraft.src.Material.snow),
	BUILT_SNOW(net.minecraft.src.Material.builtSnow),
	CACTUS(net.minecraft.src.Material.cactus),
	CLAY(net.minecraft.src.Material.clay),
	PUMPKIN(net.minecraft.src.Material.pumpkin),
	PORTAL(net.minecraft.src.Material.portal),
	CAKE(net.minecraft.src.Material.cakeMaterial),
	WEB(net.minecraft.src.Material.web),
	PISTON (net.minecraft.src.Material.piston);
	
	private Material(net.minecraft.src.Material material)
	{
		canBurn = material.canBurn;
		groundCover = material.groundCover;
		isOpaque = material.isOpaque;
		materialMapColor = material.materialMapColor;
		canHarvest = material.canHarvest;
		mobilityFlag = material.mobilityFlag;
	}
	
	private boolean canBurn;
	private boolean groundCover;
	private boolean isOpaque;
	private MapColor materialMapColor;
	private boolean canHarvest;
	private int mobilityFlag;
	
	public boolean canBurn()
	{
		return canBurn;
	}
	
	/**
	 * I'm pretty sure this is what it SHOULD be called, but I'm not 100% sure.
	 */
	public boolean canCoverGround()
	{
		return groundCover;
	}
	
	public boolean isOpaque()
	{
		return isOpaque;
	}
	
	public MapColor getMapColor()
	{
		return materialMapColor;
	}
	
	public boolean canHarvest()
	{
		return canHarvest;
	}
	
	public int getMobilityFlag()
	{
		return mobilityFlag;
	}
}
