// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

// Referenced classes of package net.minecraft.src:
//            ThreadDownloadImageData, ImageBuffer

class ThreadDownloadImage extends Thread
{

	ThreadDownloadImage(ThreadDownloadImageData threaddownloadimagedata, String s, ImageBuffer imagebuffer)
	{
//        super();
		imageData = threaddownloadimagedata;
		location = s;
		buffer = imagebuffer;
	}

	public void run()
	{
		System.out.println("Downloading image: " + location);
		
		HttpURLConnection httpurlconnection = null;
		try
		{
			URL url = new URL(location);
			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setDoInput(true);
			httpurlconnection.setDoOutput(false);
			httpurlconnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			httpurlconnection.connect();
			int code = httpurlconnection.getResponseCode();
			if (code / 100 == 4)
			{
				System.out.println(String.format("Image download failed for '%s': response not ok (%s)", location, code));
				return;
			}
			if (buffer == null)
			{
				imageData.image = ImageIO.read(httpurlconnection.getInputStream());
			} else
			{
				imageData.image = buffer.parseUserSkin(ImageIO.read(httpurlconnection.getInputStream()));
			}
		} catch (Exception exception)
		{
			System.out.println(String.format("Image download failed for '%s'", location));
			// exception.printStackTrace();
		} finally
		{
			if (httpurlconnection != null)
				httpurlconnection.disconnect();
		}
	}

	final String location; /* synthetic field */
	final ImageBuffer buffer; /* synthetic field */
	final ThreadDownloadImageData imageData; /* synthetic field */
}
