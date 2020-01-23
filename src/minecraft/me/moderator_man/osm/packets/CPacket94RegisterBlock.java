package me.moderator_man.osm.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.Block;
import net.minecraft.src.NetHandler;
import net.minecraft.src.Packet;

public class CPacket94RegisterBlock extends Packet
{
	// begin block
	public int blockIndexInTexture;
	public int blockID;
	public float blockHardness;
	public float blockResistance;
	public boolean blockConstructorCalled;
	public boolean enableStats;
	public double minX;
	public double minY;
	public double minZ;
	public double maxX;
	public double maxY;
	public double maxZ;
	public float blockParticleGravity;
	public float slipperiness;
	public String blockName;
	// end block
	
	// begin step sound
	public String label;
	public float volume;
	public float pitch;
	// end step sound
	
	// begin material
	public boolean canBurn;
	public boolean groundCover;
	public boolean isOpaque;
	public boolean canHarvest;
	public int mobilityFlag;
	// end material
	
	// begin material map color
	public int colorIndex;
	public int colorValue;
	// end material map color
	
	public CPacket94RegisterBlock() {}
	
	public CPacket94RegisterBlock(Block block)
	{
		blockIndexInTexture = block.blockIndexInTexture;
		blockID = block.blockID;
		blockHardness = block.blockHardness;
		blockResistance = block.blockResistance;
		blockConstructorCalled = block.blockConstructorCalled;
		enableStats = block.enableStats;
		minX = block.minX;
		minY = block.minY;
		minZ = block.minZ;
		maxX = block.maxX;
		maxY = block.maxY;
		maxZ = block.maxZ;
		blockParticleGravity = block.blockParticleGravity;
		slipperiness = block.slipperiness;
		blockName = block.blockName;
		
		label = block.stepSound.label;
		volume = block.stepSound.volume;
		pitch = block.stepSound.pitch;
		
		canBurn = block.blockMaterial.canBurn;
		groundCover = block.blockMaterial.groundCover;
		isOpaque = block.blockMaterial.isOpaque;
		canHarvest = block.blockMaterial.canHarvest;
		mobilityFlag = block.blockMaterial.mobilityFlag;
		
		colorIndex = block.blockMaterial.materialMapColor.colorIndex;
		colorValue = block.blockMaterial.materialMapColor.colorValue;
	}
	
	@Override
	public void readPacketData(DataInputStream datainputstream) throws IOException
	{
		blockIndexInTexture = datainputstream.readInt();
		blockID = datainputstream.readInt();
		blockHardness = datainputstream.readFloat();
		blockResistance = datainputstream.readFloat();
		blockConstructorCalled = datainputstream.readBoolean();
		enableStats = datainputstream.readBoolean();
		minX = datainputstream.readDouble();
		minY = datainputstream.readDouble();
		minZ = datainputstream.readDouble();
		maxX = datainputstream.readDouble();
		maxY = datainputstream.readDouble();
		maxZ = datainputstream.readDouble();
		blockParticleGravity = datainputstream.readFloat();
		slipperiness = datainputstream.readFloat();
		blockName = datainputstream.readUTF();
		
		label = datainputstream.readUTF();
		volume = datainputstream.readFloat();
		pitch = datainputstream.readFloat();
		
		canBurn = datainputstream.readBoolean();
		groundCover = datainputstream.readBoolean();
		isOpaque = datainputstream.readBoolean();
		canHarvest = datainputstream.readBoolean();
		mobilityFlag = datainputstream.readInt();
		
		colorIndex = datainputstream.readInt();
		colorValue = datainputstream.readInt();
	}

	@Override
	public void writePacketData(DataOutputStream dataoutputstream) throws IOException
	{
		dataoutputstream.writeInt(blockIndexInTexture);
		dataoutputstream.writeInt(blockID);
		dataoutputstream.writeFloat(blockHardness);
		dataoutputstream.writeFloat(blockResistance);
		dataoutputstream.writeBoolean(blockConstructorCalled);
		dataoutputstream.writeBoolean(enableStats);
		dataoutputstream.writeDouble(minX);
		dataoutputstream.writeDouble(minY);
		dataoutputstream.writeDouble(minZ);
		dataoutputstream.writeDouble(maxX);
		dataoutputstream.writeDouble(maxY);
		dataoutputstream.writeDouble(maxZ);
		dataoutputstream.writeFloat(blockParticleGravity);
		dataoutputstream.writeFloat(slipperiness);
		dataoutputstream.writeUTF(blockName);
		
		dataoutputstream.writeUTF(label);
		dataoutputstream.writeFloat(volume);
		dataoutputstream.writeFloat(pitch);
		
		dataoutputstream.writeBoolean(canBurn);
		dataoutputstream.writeBoolean(groundCover);
		dataoutputstream.writeBoolean(isOpaque);
		dataoutputstream.writeBoolean(canHarvest);
		dataoutputstream.writeInt(mobilityFlag);
		
		dataoutputstream.writeInt(colorIndex);
		dataoutputstream.writeInt(colorValue);
	}

	@Override
	public void processPacket(NetHandler nethandler)
	{
		nethandler.handleRegisterBlock(this);
	}

	@Override
	public int getPacketSize()
	{
		return 4+4+4+4+1+1+8+8+8+8+8+8+8+4+4
				+blockName.length()
				+label.length()
				+4+4+1+1+1+1+4+4+4;
	}
}
