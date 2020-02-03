package me.moderator_man.osm.module;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet19EntityAction;

public class ModAssistant
{
	private Minecraft mc;
	private NetClientHandler netClientHandler;
	
	public ModAssistant(Minecraft minecraft)
	{
		mc = minecraft;
		netClientHandler = mc.getSendQueue();
	}
	
	/**
	 * Create a new PlayerSPWrapper object.
	 * @return PlayerSPWrapper
	 */
	public PlayerSPWrapper newPlayerSPWrapper()
	{
		return new PlayerSPWrapper(mc.thePlayer);
	}
	
	/**
	 * Send a chat message to the server.
	 * @param msg The message to be sent to the server
	 */
	@Deprecated
	public void sendChatMessage(String msg)
	{
		if (mc.theWorld.multiplayerWorld)
		{
			//new me.moderator_man.omega.mod.api.packets.Packet3Chat(msg).send();
			//netClientHandler.addToSendQueue(new Packet3Chat(msg));
		}
	}
	
	/**
	 * Send a Packet19EntityAction packet to the server.
	 * @param entity The entity to perform the action on
	 * @param i This probably decides what the action actually is.
	 */
	@Deprecated
	public void sendEntityAction(Entity entity, int i)
	{
		if (mc.theWorld.multiplayerWorld)
		{
			netClientHandler.addToSendQueue(new Packet19EntityAction(entity, i));
		}
	}
	
	/**
	 * Send a packet to the server
	 * @param id
	 * @param data
	 */
	@Deprecated
	public void sendPacket(int id, Object[] data)
	{
		switch (id)
		{
			default:
				// invalid packet
				break;
			case 0:
				// keep alive
				break;
			case 1:
				// login
				break;
			case 2:
				// handshake
				break;
			case 3:
				// chat
				break;
			case 4:
				// update time
				break;
			case 5:
				// player inventory
				break;
			case 6:
				// spawn position
				break;
			case 7:
				// use entity
				break;
			case 8:
				// update health
				break;
			case 9:
				// respawn
				break;
			case 10:
				// flying
				break;
			case 11:
				// player position
				break;
			case 12:
				// player look
				break;
			case 13:
				// player look move
				break;
			case 14:
				// block dig
				break;
			case 15:
				// place
				break;
			case 16:
				// block item switch
				break;
			case 17:
				// sleep
				break;
			case 18:
				// animation
				break;
			case 19:
				// entity action
				break;
			case 20:
				// named entity spawn
				break;
			case 21:
				// pickup spawn
				break;
			case 22:
				// collect
				break;
			case 23:
				// vehicle spawn
				break;
			case 24:
				// mob spawn
				break;
			case 25:
				// entity painting
				break;
			case 27:
				// position
				break;
			case 28:
				// entity velocity
				break;
			case 29:
				// destroy entity
				break;
			case 30:
				// entity
				break;
			case 31:
				// rel entity move
				break;
			case 32:
				// entity look
				break;
			case 33:
				// rel entity move look
				break;
			case 34:
				// entity teleport
				break;
			case 38:
				// entity status
				break;
			case 39:
				// attach entity
				break;
			case 40:
				// entity metadata
				break;
			case 50:
				// pre chunk
				break;
			case 51:
				// map chunk
				break;
			case 52:
				// multi block block
				break;
			case 53:
				// block change
				break;
			case 54:
				// play note block
				break;
			case 60:
				// explosion
				break;
			case 61:
				// door change
				break;
			case 70:
				// bed
				break;
			case 71:
				// weather
				break;
			case 100:
				// open window
				break;
			case 101:
				// close window
				break;
			case 102:
				// window click
				break;
			case 103:
				// set slot
				break;
			case 104:
				// window items
				break;
			case 105:
				// update progressbar
				break;
			case 106:
				// transaction
				break;
			case 130:
				// update sign
				break;
			case 131:
				// map data
				break;
			case 200:
				// statistic
				break;
			case 255:
				// kick disconnect
				break;
		}
	}
	
	/**
	 * Does the actual gameplay have focus?
	 * @return boolean
	 */
	public boolean inGameHasFocus()
	{
		return mc.inGameHasFocus;
	}
	
	/**
	 * Get a keybinding from the game settings.
	 * @param description The <code>description</code> of the bind (e.g., <code>"key.sneak"</code>)
	 * @return KeyBinding
	 */
	public KeyBinding getKeyBinding(String description)
	{
		for (KeyBinding kb : mc.gameSettings.keyBindings)
		{
			if (kb.keyDescription.equalsIgnoreCase(description))
			{
				return kb;
			}
		}
		
		return null;
	}
	
	/**
	 * Set the world time.
	 * @param time The time to set the world to.
	 */
	public void setWorldTime(long time)
	{
		mc.theWorld.setWorldTime(time);
	}
	
	/**
	 * Set the worlds rain strength.
	 * @param strength The strength to set the worlds rain to.
	 */
	public void setRainStrength(float strength)
	{
		mc.theWorld.setRainStrength(strength);
	}
	
	/**
	 * Set the worlds raining flag.
	 * @param flag
	 */
	public void setRaining(boolean flag)
	{
		mc.theWorld.getWorldInfo().setRaining(flag);
	}
	
	/**
	 * Is the world multiplayer?
	 * @return multiplayerWorld
	 */
	public boolean isMultiplayerWorld()
	{
		return mc.theWorld.multiplayerWorld;
	}
}
