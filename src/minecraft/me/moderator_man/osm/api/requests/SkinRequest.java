package me.moderator_man.osm.api.requests;

import org.json.JSONObject;

import me.moderator_man.osm.api.APIRequest;
import me.moderator_man.osm.api.APIResponse;
import me.moderator_man.osm.api.Endpoints;
import net.minecraft.src.EntityPlayer;

public class SkinRequest extends APIRequest
{
	private EntityPlayer player;
	
	public SkinRequest(EntityPlayer player)
	{
		super(Endpoints.SKIN_GET);
		this.player = player;
		JSONObject obj = new JSONObject();
		obj.append("username", player.username);
		postContent = obj.toString();
	}
	
	@Override
	public void requestComplete(APIResponse response)
	{
		if (response.isSuccess())
		{
			player.skinUrl = response.getString("url");
		} else {
			System.out.println(String.format("Skin request failed for '%s'.", player.username));
		}
	}
}
