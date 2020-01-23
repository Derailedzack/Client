package me.moderator_man.osm;

import org.json.JSONObject;

public class ResourceConverter
{
	private JSONObject meta = new JSONObject();
	
	public void onEnable()
	{
		String meta_raw = OSM.INSTANCE.get("https://launchermeta.mojang.com/v1/packages/4759bad2824e419da9db32861fcdc3a274336532/pre-1.6.json");
		//System.out.println(meta_raw);
		meta = new JSONObject(meta_raw).getJSONObject("objects");
	}
	
	public String convertResource(String resource)
	{
		String hash = meta.getJSONObject(resource).getString("hash");
		String sub = hash.substring(0, 2);
		return String.format("http://resources.download.minecraft.net/%s/%s", sub, hash);
	}
}
