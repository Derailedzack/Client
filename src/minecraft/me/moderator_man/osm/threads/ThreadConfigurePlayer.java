package me.moderator_man.osm.threads;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;

public class ThreadConfigurePlayer extends Thread
{
	private EntityPlayer player;
	
	public ThreadConfigurePlayer(EntityPlayer player)
	{
		this.player = player;
	}
	
	@Override
	public void run()
	{
		try
		{
			ThreadGrabLink threadCloakLink = new ThreadGrabLink("https://www.oldschoolminecraft.com/getcloak?username=" + player.username);
			ThreadGrabLink threadSkinLink = new ThreadGrabLink("https://www.oldschoolminecraft.com/getskin?username=" + player.username);
			threadCloakLink.start();
			threadSkinLink.start();
			
			while (!threadSkinLink.isComplete())
			{
				Thread.sleep(500);
			}
			
			while (!threadCloakLink.isComplete())
			{
				Thread.sleep(500);
			}
			
			String cloakUrl = threadCloakLink.getLink();
			String skinUrl = threadSkinLink.getLink();
			
			System.out.println("Setting skinUrl = " + skinUrl);
			System.out.println("Setting cloakUrl = " + cloakUrl);
			
			player.cloakUrl = cloakUrl;
			player.skinUrl = skinUrl;
			
			Minecraft.getMinecraft().renderGlobal.obtainEntitySkin(player);
			player.updateCloak();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private boolean complete = false;
	public boolean isComplete()
	{
		return complete;
	}
}