// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            MaterialTransparent, MapColor, MaterialLiquid, MaterialLogic, 
//            MaterialPortal

public class Material
{

    public Material(MapColor mapcolor)
    {
        canHarvest = true;
        materialMapColor = mapcolor;
    }

    public boolean getIsLiquid()
    {
        return false;
    }

    public boolean isSolid()
    {
        return true;
    }

    public boolean getCanBlockGrass()
    {
        return true;
    }

    public boolean getIsSolid()
    {
        return true;
    }

    public Material setIsTranslucent()
    {
        isOpaque = true;
        return this;
    }

    public Material setNoHarvest()
    {
        canHarvest = false;
        return this;
    }

    public Material setBurning()
    {
        canBurn = true;
        return this;
    }

    public boolean getBurning()
    {
        return canBurn;
    }

    public Material setIsGroundCover()
    {
        groundCover = true;
        return this;
    }

    public boolean getIsGroundCover()
    {
        return groundCover;
    }

    public boolean getIsTranslucent()
    {
        if(isOpaque)
        {
            return false;
        } else
        {
            return getIsSolid();
        }
    }

    public boolean getIsHarvestable()
    {
        return canHarvest;
    }

    public int getMaterialMobility()
    {
        return mobilityFlag;
    }

    public Material setNoPushMobility()
    {
        mobilityFlag = 1;
        return this;
    }

    public Material setImmovableMobility()
    {
        mobilityFlag = 2;
        return this;
    }
    
    //TODO: OSM
    public Material setCanBurn(boolean flag)
    {
    	canBurn = flag;
    	return this;
    }
    
    public Material setGroundCover(boolean flag)
    {
    	groundCover = flag;
    	return this;
    }
    
    public Material setIsOpaque(boolean flag)
    {
    	isOpaque = flag;
    	return this;
    }
    
    public Material setCanHarvest(boolean flag)
    {
    	canHarvest = flag;
    	return this;
    }
    
    public Material setMobilityFlag(int flag)
    {
    	mobilityFlag = flag;
    	return this;
    }

    public static final Material air;
    public static final Material grassMaterial;
    public static final Material ground;
    public static final Material wood;
    public static final Material rock;
    public static final Material iron;
    public static final Material water;
    public static final Material lava;
    public static final Material leaves;
    public static final Material plants;
    public static final Material sponge;
    public static final Material cloth;
    public static final Material fire;
    public static final Material sand;
    public static final Material circuits;
    public static final Material glass;
    public static final Material tnt;
    public static final Material coral;
    public static final Material ice;
    public static final Material snow;
    public static final Material builtSnow;
    public static final Material cactus;
    public static final Material clay;
    public static final Material pumpkin;
    public static final Material portal;
    public static final Material cakeMaterial;
    public static final Material web;
    public static final Material piston;
    
    public boolean canBurn;
    public boolean groundCover;
    public boolean isOpaque;
    public final MapColor materialMapColor;
    public boolean canHarvest;
    public int mobilityFlag;

    static 
    {
        air = new MaterialTransparent(MapColor.airColor);
        grassMaterial = new Material(MapColor.grassColor);
        ground = new Material(MapColor.dirtColor);
        wood = (new Material(MapColor.woodColor)).setBurning();
        rock = (new Material(MapColor.stoneColor)).setNoHarvest();
        iron = (new Material(MapColor.ironColor)).setNoHarvest();
        water = (new MaterialLiquid(MapColor.waterColor)).setNoPushMobility();
        lava = (new MaterialLiquid(MapColor.tntColor)).setNoPushMobility();
        leaves = (new Material(MapColor.foliageColor)).setBurning().setIsTranslucent().setNoPushMobility();
        plants = (new MaterialLogic(MapColor.foliageColor)).setNoPushMobility();
        sponge = new Material(MapColor.clothColor);
        cloth = (new Material(MapColor.clothColor)).setBurning();
        fire = (new MaterialTransparent(MapColor.airColor)).setNoPushMobility();
        sand = new Material(MapColor.sandColor);
        circuits = (new MaterialLogic(MapColor.airColor)).setNoPushMobility();
        glass = (new Material(MapColor.airColor)).setIsTranslucent();
        tnt = (new Material(MapColor.tntColor)).setBurning().setIsTranslucent();
        coral = (new Material(MapColor.foliageColor)).setNoPushMobility();
        ice = (new Material(MapColor.iceColor)).setIsTranslucent();
        snow = (new MaterialLogic(MapColor.snowColor)).setIsGroundCover().setIsTranslucent().setNoHarvest().setNoPushMobility();
        builtSnow = (new Material(MapColor.snowColor)).setNoHarvest();
        cactus = (new Material(MapColor.foliageColor)).setIsTranslucent().setNoPushMobility();
        clay = new Material(MapColor.clayColor);
        pumpkin = (new Material(MapColor.foliageColor)).setNoPushMobility();
        portal = (new MaterialPortal(MapColor.airColor)).setImmovableMobility();
        cakeMaterial = (new Material(MapColor.airColor)).setNoPushMobility();
        web = (new Material(MapColor.clothColor)).setNoHarvest().setNoPushMobility();
        piston = (new Material(MapColor.stoneColor)).setImmovableMobility();
    }
}
