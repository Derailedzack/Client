package me.moderator_man.osm.module;

import java.util.Random;

import me.moderator_man.osm.OSM;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.FontRenderer;

public class Module
{
	protected static Minecraft mc = Minecraft.getMinecraft();
	protected static EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
	protected static FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	
	private String name;
	private boolean toggled;
	private Category category;
	private int color;
	
	public Module(String name, Category category)
	{
		this.name = name;
		this.toggled = false;
		this.category = category;
		
		boolean b = Boolean.parseBoolean(OSM.INSTANCE.getProperties().getProperty(name + ".toggled", "false"));
		if (b)
			toggle();
		
		color = randomColor();
	}
	
	public static enum Category
	{
		COMBAT, MOVEMENT, PLAYER, WORLD, RENDER, EXPLOITS, MISC, FRIENDS
	}
	
	public void toggle()
	{
		color = randomColor();
		
		this.toggled = !this.toggled;
		
		if (this.toggled)
		{
			this.onEnable();
		} else
		{
			this.onDisable();
		}
		
		/*boolean debug = OSM.INSTANCE.OPTIONS.debug;
		if (mc.theWorld != null && mc.thePlayer != null && mc.theWorld.multiplayerWorld && debug)
		{
			//Omega.INSTANCE.tellPlayer(String.format("Mod '%s' was toggled (%s).", getName(), isToggled()));
		}*/
	}
	
	public void onEnable()
	{
		OSM.INSTANCE.EVENT_MANAGER.register(this);
	}
	
	public void onDisable()
	{
		OSM.INSTANCE.EVENT_MANAGER.unregister(this);
	}
	
	public boolean isToggled()
	{
		return toggled;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getKeyCode()
	{
		return Integer.parseInt(OSM.INSTANCE.getProperties().getProperty(name + ".keyBind", "0"));
	}
	
	public Category getCategory()
	{
		return category;
	}
	
	public int getColor()
	{
		return color;
	}
	
	private int randomColor()
    {
    	Random random = new Random();
        int nextInt = random.nextInt(0xffffff + 1);
        return nextInt;
    }
}
