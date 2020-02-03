package me.moderator_man.osm.api.requests.session;

import org.json.JSONObject;

import me.moderator_man.osm.api.APIRequest;
import me.moderator_man.osm.api.APIResponse;
import me.moderator_man.osm.api.Endpoints;
import me.moderator_man.osm.threads.ThreadWebRequest;
import net.minecraft.src.EntityPlayer;

public class JoinServerRequest extends APIRequest
{
	public JoinServerRequest(EntityPlayer player)
	{
		super(Endpoints.SESSION_JOIN);
		JSONObject obj = new JSONObject();
		obj.append("username", player.username);
		postContent = obj.toString();
	}
	
	@Override
	public void requestComplete(APIResponse response)
	{
		if (response.isSuccess())
		{
			// nothing to do?
		} else {
			System.out.println(String.format("Joining server failed."));
		}
	}
	
	@Override
	public void send()
	{
		new ThreadWebRequest(Endpoints.SESSION_BASE + endpoint, postContent, this).start();
	}
}
