package ru.soknight.ttc.utils;

import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftItemStack;

import net.minecraft.server.v1_14_R1.ItemStack;
import net.minecraft.server.v1_14_R1.NBTTagCompound;

public class JsonUtils {

	public static String itemStackToJson(org.bukkit.inventory.ItemStack target) {
		
		ItemStack itemStack = CraftItemStack.asNMSCopy(target);
		NBTTagCompound compound = new NBTTagCompound();
		compound = itemStack.save(compound);

		return compound.toString();
	}

}
