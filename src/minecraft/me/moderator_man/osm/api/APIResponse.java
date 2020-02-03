package me.moderator_man.osm.api;

import org.json.JSONObject;

public class APIResponse
{
	private String response;
	private JSONObject jsonResponse;
	private boolean success;
	
	public APIResponse(String response)
	{
		this.response = response;
		this.jsonResponse = new JSONObject(response);
		this.success = jsonResponse.getBoolean("success");
	}
	
	public String getRawResponse()
	{
		return response;
	}
	
	public JSONObject getJSONResponse()
	{
		return jsonResponse;
	}
	
	public boolean isSuccess()
	{
		return success;
	}
	
	public String getFailReason()
	{
		return jsonResponse.getString("fail_reason");
	}
	
	public String getString(String key)
	{
		return jsonResponse.getString(key);
	}
	
	public boolean getBoolean(String key)
	{
		return jsonResponse.getBoolean(key);
	}
	
	public int getInt(String key)
	{
		return jsonResponse.getInt(key);
	}
}
