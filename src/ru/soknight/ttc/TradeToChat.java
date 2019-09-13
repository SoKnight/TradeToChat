package ru.soknight.ttc;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ru.soknight.ttc.commands.CommandPublic;
import ru.soknight.ttc.commands.CommandTtc;
import ru.soknight.ttc.utils.Locale;

public class TradeToChat extends JavaPlugin {

	private static TradeToChat instance;
	private FileConfiguration config;

	@Override
	public void onEnable() {
		instance = this;
		refreshConfig();
		Locale.refresh();
		registerCommands();
		getLogger().info("Enabled!");
	}

	private void registerCommands() {
		getCommand("public").setExecutor(new CommandPublic());
		getCommand("ttc").setExecutor(new CommandTtc());
	}

	public void refreshConfig() {
		File file = new File(getDataFolder(), "config.yml");
		if(!file.exists()) {
			getConfig().options().copyDefaults(true);
			try {
				getConfig().save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
	}

	public FileConfiguration getCfg() {
		return config;
	}

	public static TradeToChat getInstance() {
		return instance;
	}


}
