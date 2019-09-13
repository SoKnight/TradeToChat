package ru.soknight.ttc.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.chat.BaseComponent;
import ru.soknight.ttc.TradeToChat;
import ru.soknight.ttc.message.MessageConstructor;
import ru.soknight.ttc.message.Messages;

public class CommandPublic implements CommandExecutor {

	private static Map<Player, Long> cooldowns = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(Messages.getMessage("OnlyForPlayers"));
			return true;
		}

		Player p = (Player) sender;
		if(!p.hasPermission("ttc.public")) {
			p.sendMessage(Messages.getMessage("NoPermissions"));
			return true;
		}

		if(cooldowns.containsKey(p)) {
			long current = System.currentTimeMillis();
			long cooldown = cooldowns.get(p);
			long min = TradeToChat.getInstance().getConfig().getLong("General.Cooldown") * 1000;
			long time = current - cooldown;
			if((time < min) && !p.hasPermission("ttc.public.bypass")) {
				p.sendMessage(Messages.getMessage("Cooldown").replace("%time%", 
						String.valueOf((int) (min - time) / 1000)));
				return true;
			} else cooldowns.remove(p);
		}

		ItemStack inhand = p.getInventory().getItemInMainHand();
		if((inhand == null) || (inhand.getType().equals(Material.AIR))) {
			p.sendMessage(Messages.getMessage("MainHandEmpty"));
			return true;
		}

		String otherMsgString = TradeToChat.getInstance().getCfg().getString("General.TradeFormat")
 				.replace("&", "\u00A7").replace("%player%", p.getName());
		BaseComponent[] otherMsg = MessageConstructor.getMessage(otherMsgString, p, inhand);
		
		String selfMsgString = Messages.getMessage("PublicSuccess");
		BaseComponent[] selfMsg = MessageConstructor.getMessage(selfMsgString, p, inhand);
		
		boolean sendToYourself = TradeToChat.getInstance().getCfg().getBoolean("General.SendToYourself");
		List<Player> players = TradeToChat.getInstance().getServer().getOnlinePlayers().stream().collect(Collectors.toList());
		
		cooldowns.put(p, System.currentTimeMillis());
		p.spigot().sendMessage(selfMsg);
		for(Player op : players) {
			if(op.getName().equals(p.getName()) && !sendToYourself) continue;
			op.spigot().sendMessage(otherMsg);
		}
		return true;
	}

}
