package net.cubespace.geSuitHomes;

import net.cubespace.geSuitHomes.commands.DelHomeCommand;
import net.cubespace.geSuitHomes.commands.HomeCommand;
import net.cubespace.geSuitHomes.commands.HomesCommand;
import net.cubespace.geSuitHomes.commands.ImportHomesCommand;
import net.cubespace.geSuitHomes.commands.SetHomeCommand;
import net.cubespace.geSuitHomes.listeners.HomesListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class geSuitHomes extends JavaPlugin {
	public static geSuitHomes instance;
	public static String CHANNEL_NAME = "bungeecord:gesuithomes";

	@Override
	public void onEnable() {
		instance = this;
		registerListeners();
		registerChannels();
		registerCommands();
	}
	
	private void registerCommands() {
		getCommand("sethome").setExecutor(new SetHomeCommand());
		getCommand("home").setExecutor(new HomeCommand());
		getCommand("delhome").setExecutor(new DelHomeCommand());
		getCommand("homes").setExecutor(new HomesCommand());
		getCommand("importhomes").setExecutor(new ImportHomesCommand());
	}

	private void registerChannels() {
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, CHANNEL_NAME);
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(
				new HomesListener(), this);
	}

    public static geSuitHomes getInstance() {
        return instance;
    }


}
