package ru.soknight.ttc.message;

import org.bukkit.configuration.ConfigurationSection;

import ru.soknight.ttc.TradeToChat;

public class Messages {

	public static String getMessage(String section) {
		ConfigurationSection messages = TradeToChat.getInstance().getCfg().getConfigurationSection("Messages");
		if(!messages.isSet(section)) {
			TradeToChat.getInstance().getLogger().info("Couldn't get config message from section " + section);
			return null;
		} else return messages.getString(section).replace("&", "\u00A7");
	}

}
