package me.moderator_man.osm.event;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CopyOnWriteArrayList;

import me.moderator_man.osm.OSM;

public class Event
{
	private boolean cancelled;
    private Type type;

    public Event(Type type)
    {
        this.type = type;
        this.cancelled = false;
    }

    public void call()
    {
        cancelled = false;
        CopyOnWriteArrayList<Data> dataList = OSM.INSTANCE.EVENT_MANAGER.get(this.getClass());
        if (dataList == null)
            return;
        for (Data data : dataList)
        {
        	try
            {
                data.getTarget().invoke(data.getSource(), this);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public Type getType()
    {
        return type;
    }

    public boolean isCancelled()
    {
        return cancelled;
    }

    public void setCancelled(boolean cancelled)
    {
        this.cancelled = cancelled;
    }
}
