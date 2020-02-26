package me.moderator_man.osm.command;

import java.util.HashMap;

public class CommandManager
{
	private HashMap<String, Command> commands;
	
	public CommandManager()
	{
		commands = new HashMap<String, Command>();
	}
	
	public void onEnable()
	{
		//register("test", new Test());
	}
	
	public void register(String label, Command command)
	{
		commands.put(label, command);
	}
	
	public Command getByLabel(String label)
	{
		return commands.get(label);
	}
	
	public boolean contains(String label)
	{
		boolean flag = commands.containsKey(label);
		return flag;
	}
	
	public boolean process(String msg)
	{
		boolean disableCommands = true;
		if (disableCommands)
			return true;
		
		if (msg.startsWith("."))
		{
			msg = msg.substring(1, msg.length());
			String[] cmdData = msg.split(" ");
			if (contains(cmdData[0]))
			{
				getByLabel(cmdData[0]).call(cmdData);
			}
			
			return false; // dont allow message to be sent
		}
		
		return true; // allow chat message to be sent
	}
}
