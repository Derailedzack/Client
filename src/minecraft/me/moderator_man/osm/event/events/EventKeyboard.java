package me.moderator_man.osm.event.events;

import me.moderator_man.osm.event.Event;
import me.moderator_man.osm.event.Type;

public class EventKeyboard extends Event
{
private int keyCode;
	
	public EventKeyboard(int keyCode)
	{
		super(Type.PRE);
		this.keyCode = keyCode;
	}
	
	public int getKeyCode()
	{
		return keyCode;
	}
}
