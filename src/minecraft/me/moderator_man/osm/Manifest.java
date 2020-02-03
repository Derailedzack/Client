package me.moderator_man.osm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Manifest
{
	private ArrayList<String> developers;
	private ArrayList<String> admins;
	private ArrayList<String> moderators;
	private ArrayList<String> beta_testers;
	private ArrayList<String> supporters;
	
	public Manifest()
	{
		developers = new ArrayList<String>();
		admins = new ArrayList<String>();
		moderators = new ArrayList<String>();
		beta_testers = new ArrayList<String>();
		supporters = new ArrayList<String>();
	}
	
	public Manifest grab()
	{
		JSONObject resobj = new JSONObject(get("https://www.oldschoolminecraft.com/client_manifest?v=1"));
		
		JSONArray developers = resobj.getJSONArray("developers");
		JSONArray admins = resobj.getJSONArray("admins");
		JSONArray moderators = resobj.getJSONArray("moderators");
		JSONArray beta_testers = resobj.getJSONArray("beta_testers");
		JSONArray supporters = resobj.getJSONArray("supporters");
		
		if (developers != null)
		{
			for (int i = 0; i < developers.length(); i++)
			{
				this.developers.add(developers.getString(i));
			}
		}
		
		if (admins != null)
		{
			for (int i = 0; i < developers.length(); i++)
			{
				this.admins.add(admins.getString(i));
			}
		}
		
		if (moderators != null)
		{
			for (int i = 0; i < developers.length(); i++)
			{
				this.moderators.add(moderators.getString(i));
			}
		}
		
		if (beta_testers != null)
		{
			for (int i = 0; i < developers.length(); i++)
			{
				this.beta_testers.add(beta_testers.getString(i));
			}
		}
		
		if (supporters != null)
		{
			for (int i = 0; i < developers.length(); i++)
			{
				this.supporters.add(supporters.getString(i));
			}
		}
		
		return this;
	}
	
	public ArrayList<String> getDevelopers()
	{
		return developers;
	}
	
	public ArrayList<String> getAdmins()
	{
		return developers;
	}
	
	public ArrayList<String> getModerators()
	{
		return developers;
	}
	
	public ArrayList<String> getBetaTesters()
	{
		return developers;
	}
	
	public ArrayList<String> getSupporters()
	{
		return developers;
	}
	
	private String get(String url)
	{
		try
		{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				response.append(inputLine);
			in.close();
			return response.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
}
