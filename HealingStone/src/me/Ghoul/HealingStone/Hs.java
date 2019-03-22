package me.Ghoul.HealingStone;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class Hs extends JavaPlugin {

	private Economy econ = null;

	@Override
	public void onEnable() {

		if (!setupEconomy()) {
			Bukkit.getConsoleSender().sendMessage("Disabled due to no Vault dependency found!");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		new EventsClass(this);

		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		reloadConfig();

	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	public Economy getEconomy() {
		return econ;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLable, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only Players Can Send This Command!");
			return true;

		}

		Player p = (Player) sender;
		if (args.length == 0) {
			if (cmd.getName().equalsIgnoreCase("hs")) {
				sender.sendMessage(ChatColor.GREEN + "Use /Help For More Info!");

			}
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("Stone")) {
				if (sender.hasPermission("hs.getstone")) {
					ItemStack is = new ItemStack(Material.getMaterial(getConfig().getString("Healingstone.BlockType")));
					ItemMeta im = is.getItemMeta();
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.AQUA + "[---------------------]");
					im.setDisplayName(ChatColor.GREEN + "Healing Stone");
					lore.add(ChatColor.AQUA + "[---------------------]");
					im.setLore(lore);
					is.setItemMeta(im);
					p.getInventory().addItem(is);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Message.Stone")));

					return true;
				}
			}

			if (args[0].equalsIgnoreCase("Help")) {
				sender.sendMessage(ChatColor.GREEN + "/hs (Help/Stone/Crystal)");
				return true;
			}
		}

		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("Crystal+")) {
				if (sender.hasPermission("hs.getcrystal.+")) {
					ItemStack is = new ItemStack(
							Material.getMaterial(getConfig().getString("Healingstone.CrystalType")));
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(ChatColor.GREEN + "+5 Healing Crystal");
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.DARK_PURPLE + "[---------------------]");
					lore.add(ChatColor.AQUA + "Right Click For +5 Health!");
					lore.add(ChatColor.DARK_PURPLE + "[---------------------]");
					im.setLore(lore);
					is.setItemMeta(im);
					p.getInventory().addItem(is);
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("Message.Crystal+")));

					return true;
				}
			}
		}

		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("Crystal++")) {
				if (sender.hasPermission("hs.getcrystal.++")) {
					ItemStack is = new ItemStack(
							Material.getMaterial(getConfig().getString("Healingstone.CrystalType")));
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(ChatColor.GREEN + "+10 Healing Crystal");
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.DARK_PURPLE + "[---------------------]");
					lore.add(ChatColor.AQUA + "Right Click For +10 Health!");
					lore.add(ChatColor.DARK_PURPLE + "[---------------------]");
					im.setLore(lore);
					is.setItemMeta(im);
					p.getInventory().addItem(is);
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("Message.Crystal++")));

					return true;
				}
			}
		}

		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("Crystal+++")) {
				if (sender.hasPermission("hs.getcrystal.+++")) {
					ItemStack is = new ItemStack(
							Material.getMaterial(getConfig().getString("Healingstone.CrystalType")));
					ItemMeta im = is.getItemMeta();
					im.setDisplayName(ChatColor.GREEN + "+15 Healing Crystal");
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(ChatColor.DARK_PURPLE + "[---------------------]");
					lore.add(ChatColor.AQUA + "Right Click For +15 Health!");
					lore.add(ChatColor.DARK_PURPLE + "[---------------------]");
					im.setLore(lore);
					is.setItemMeta(im);
					p.getInventory().addItem(is);
					p.sendMessage(
							ChatColor.translateAlternateColorCodes('&', getConfig().getString("Message.Crystal+++")));

					return true;
				}
			}
		}

		return false;
	}
}

// public void onDisable() {
// These are not needed
// getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\nHealingStone
// Deactivated\n\n");
// saveConfig();
// }
