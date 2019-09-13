package ru.soknight.ttc.message;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import ru.soknight.ttc.TradeToChat;
import ru.soknight.ttc.utils.JsonUtils;
import ru.soknight.ttc.utils.Locale;

public class MessageConstructor {

	public static BaseComponent[] getMessage(String msg, Player p, ItemStack inhand) {
		String parts[] = msg.split("%item%");
		TextComponent before = new TextComponent(parts[0]);
		BaseComponent[] itembs;
		ChatColor color = ChatColor.valueOf(TradeToChat.getInstance().getCfg().getString("General.DefaultNameColor"));
		if(inhand.getItemMeta().hasDisplayName()) 
			itembs = TextComponent.fromLegacyText(color + "\u00A7o" + inhand.getItemMeta().getDisplayName());
		else itembs = TextComponent.fromLegacyText(color + Locale.getLocalizedName(inhand.getType()));
		TextComponent item = new TextComponent(itembs);
		item.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, getItem(inhand)));
		TextComponent after = new TextComponent(parts[1]);
		BaseComponent[] component = new BaseComponent[] { before, item, after };
		return component;
	}

	private static BaseComponent[] getItem(ItemStack inhand) {
		String itemJson = JsonUtils.itemStackToJson(inhand);
		BaseComponent[] item = new BaseComponent[] {
				new TextComponent(itemJson)
		};
		return item;
	}







}
