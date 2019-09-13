package ru.soknight.ttc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ru.soknight.ttc.TradeToChat;

public class Locale {

	public static FileConfiguration locale;
	
	public static void refresh() {
		File dataFolder = TradeToChat.getInstance().getDataFolder();
		if(!dataFolder.isDirectory()) dataFolder.mkdirs();
        File localeFile = new File(TradeToChat.getInstance().getDataFolder(), "locale.yml");

        if(!localeFile.exists())
            try { 
            	Files.copy(TradeToChat.getInstance().getResource("locale.yml"), localeFile.toPath(), 
            			StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) { e.printStackTrace(); }

        locale = YamlConfiguration.loadConfiguration(localeFile);
        return;
	}
	
	public static String getLocalizedName(Material type) {
		String name = type.toString();
		if(locale.isSet(name)) return locale.getString(name);
		return "";
	}
	
}
