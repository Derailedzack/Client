package me.moderator_man.osm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.SRVRecord;
import org.xbill.DNS.Type;

import me.moderator_man.osm.command.CommandManager;
import me.moderator_man.osm.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet;

public class OSM
{
	public static OSM INSTANCE = new OSM();
	
	private Properties properties;
	
	public CommandManager COMMAND_MANAGER;
	public EventManager EVENT_MANAGER;
	public Memory MEMORY;
	public ResourceConverter RESOURCE_CONVERTER;
	public boolean OMEGABUKKIT_SERVER;
	
	public void onEnable()
	{
		try
		{
			String osmcfg = "osm.cfg";
			File cfg = new File(osmcfg);
			
			if (!cfg.exists())
			{
				System.out.println("osm.cfg was missing, creating one...");
				cfg.createNewFile();
			}
			
			properties = new Properties();
			properties.load(new FileInputStream(cfg));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		COMMAND_MANAGER = new CommandManager();
		EVENT_MANAGER = new EventManager();
		MEMORY = new Memory();
		RESOURCE_CONVERTER = new ResourceConverter();
		OMEGABUKKIT_SERVER = false;
		
		COMMAND_MANAGER.onEnable();
		RESOURCE_CONVERTER.onEnable();
		
		//Minecraft.getMinecraft().gameSettings.debugCamEnable = true;
		//Minecraft.getMinecraft().gameSettings.noclip = true;
		
		System.out.println("Startup complete.");
	}
	
	public void addMsg(String msg)
	{
		//TODO: add prefix
		getPlayer().addChatMessage(msg);
	}
	
	public EntityPlayer getPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
	
	public void sendPacket(Packet packet)
	{
		if (!Minecraft.getMinecraft().isMultiplayerWorld())
			return;
		
		Minecraft.getMinecraft().getSendQueue().addToSendQueue(packet);
	}
	
	public Properties getProperties()
	{
		return properties;
	}
	
	public int lookupSRV(String query)
	{
		try
		{
			Record[] records = new Lookup(query, Type.SRV).run();
            for (Record record : records)
            {
                 SRVRecord srv = (SRVRecord) record;
                 int port = srv.getPort();
                 return port;
            }
            return 25565; // default
		} catch (Exception ex) {
			ex.printStackTrace();
			return 25565; // default
		}
	}
	
	public String hash(String base)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes("UTF-8"));
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < hash.length; i++)
	        {
	            String hex = Integer.toHexString(0xff & hash[i]);
	            if(hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	
	public String get(String url)
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
		} catch (Exception ex)
		{
			ex.printStackTrace();
			return "";
		}
	}
	
	public String post(String url, String postContent)
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
				return response.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
}