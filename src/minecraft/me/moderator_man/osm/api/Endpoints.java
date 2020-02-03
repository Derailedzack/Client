package me.moderator_man.osm.api;

public class Endpoints
{
	public static boolean SECURE = true;
	public static String DOMAIN = "api.oldschoolminecraft.com";
	public static String SESSION_DOMAIN = "api.oldschoolminecraft.com"; // was sessions.oldschoolminecraft.com
	public static String SESSION_PORT = "8080";
	public static String SESSION_BASE = String.format("%s://%s:%s/", SECURE ? "https" : "http", SESSION_DOMAIN, SESSION_PORT);
	public static String API_BASE = String.format("%s://%s/", SECURE ? "https" : "http", DOMAIN);
	
	public static String SKIN_GET = "skins/get";
	public static String CLOAK_GET = "cloaks/get";
	
	public static String SESSION_JOIN = "joinserver";
}
