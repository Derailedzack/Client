package me.moderator_man.osm.api.requests;

import org.json.JSONObject;

import me.moderator_man.osm.api.APIRequest;
import me.moderator_man.osm.api.APIResponse;
import me.moderator_man.osm.api.Endpoints;
import net.minecraft.src.EntityPlayer;

public class CloakRequest extends APIRequest
{
	private EntityPlayer player;
	
	public CloakRequest(EntityPlayer player)
	{
		super(Endpoints.CLOAK_GET);
		JSONObject obj = new JSONObject();
		obj.append("username", player.username);
		postContent = obj.toString();
	}
	
	@Override
	public void requestComplete(APIResponse response)
	{
		if (response.isSuccess())
		{
			player.cloakUrl = response.getString("url");
		} else {
			System.out.println(String.format("Cloak request failed for '%s'.", player.username));
		}
	}
}
