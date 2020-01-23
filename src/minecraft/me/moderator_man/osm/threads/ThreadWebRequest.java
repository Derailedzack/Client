package me.moderator_man.osm.threads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import me.moderator_man.osm.api.APIResponse;
import me.moderator_man.osm.api.ISubscriber;

public class ThreadWebRequest extends Thread
{
	private String url;
	private String postContent;
	private ISubscriber subscriber;

	public ThreadWebRequest(String url, String postContent, ISubscriber subscriber)
	{
		this.url = url;
		this.postContent = postContent;
		this.subscriber = subscriber;
	}

	public void run()
	{
		try
		{
			HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();
			httpClient.setRequestMethod("POST");
			httpClient.setRequestProperty("User-Agent", "OSM/1.0");
			httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			httpClient.setDoOutput(true);
			try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream()))
			{
				wr.writeBytes(postContent);
				wr.flush();
			}
			try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream())))
			{
				String line;
				StringBuilder response = new StringBuilder();
				while ((line = in.readLine()) != null)
					response.append(line);
				subscriber.requestComplete(new APIResponse(response.toString()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
