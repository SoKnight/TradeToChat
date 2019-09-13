package ru.soknight.ttc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import ru.soknight.ttc.TradeToChat;
import ru.soknight.ttc.message.Messages;
import ru.soknight.ttc.utils.Locale;

public class CommandTtc implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("ttc.ttc")) {
			sender.sendMessage(Messages.getMessage("NoPermissions"));
			return true;
		}
		
		TradeToChat.getInstance().refreshConfig();
		Locale.refresh();
		sender.sendMessage(Messages.getMessage("SuccessReloaded"));
		return true;
	}
	
}
