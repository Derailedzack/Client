package me.moderator_man.osm.api;

import me.moderator_man.osm.threads.ThreadWebRequest;

public abstract class APIRequest implements ISubscriber
{
	protected String endpoint;
	protected String postContent;
	
	public APIRequest(String endpoint)
	{
		this.endpoint = endpoint;
	}

	public void send()
	{
		new ThreadWebRequest(Endpoints.API_BASE + endpoint, postContent, this).start();
	}
}
