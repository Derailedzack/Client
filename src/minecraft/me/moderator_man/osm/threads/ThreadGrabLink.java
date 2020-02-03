package me.moderator_man.osm.threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ThreadGrabLink extends Thread
{
	private String url;
	private String link;
	private boolean complete;
	
	public ThreadGrabLink(String url)
	{
		this.url = url;
		this.complete = false;
	}
	
	public void run()
	{
		String link = get(url);
		this.link = link;
		this.complete = true;
		System.out.println(String.format("Grabbing link (from '%s'): got '%s'", url, link));
	}
	
	private String get(String url)
	{
		try
		{
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
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
	
	public boolean isComplete()
	{
		return complete;
	}
	
	public String getLink()
	{
		return link;
	}
}
