package me.moderator_man.osm;

public class PlayerCapabilities
{
	public PlayerCapabilities()
	{
		/*field_35759_a = false;
        flight = false;
        field_35758_c = false;
        field_35756_d = false;
        nofall = false;*/
	}
	
	public boolean getFlag(String cap)
	{
		return (boolean) memgetcap(cap);
	}
	
	public void setFlag(String cap, boolean flag)
	{
		memsetcap(cap, flag);
	}
	
	private Object memgetcap(String key)
	{
		return OSM.INSTANCE.MEMORY.get("capabilities." + key, false);
	}
	
	private void memsetcap(String key, boolean flag)
	{
		OSM.INSTANCE.MEMORY.set("capabilities." + key, flag);
	}
	
	/*public boolean field_35759_a;
    public boolean flight;
    public boolean field_35758_c;
    public boolean field_35756_d;
    public boolean nofall;*/
}
