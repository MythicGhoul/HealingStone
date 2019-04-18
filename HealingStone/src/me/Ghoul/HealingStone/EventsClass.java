package me.Ghoul.HealingStone;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.milkbowl.vault.economy.EconomyResponse;

public class EventsClass implements Listener {

	Hs plugin; // <-- Your "main" class (the one that extends JavaPlugin)

	public EventsClass(final Hs plugin) { // passing a parameter of your main class so as to initialize the variable
											// above
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin); // Registering this class to implement listener
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		Action action = event.getAction();
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();

		if (player.isOp()) {
			if (action.equals(Action.LEFT_CLICK_BLOCK)) {
				if (block.getType().equals(Material.getMaterial(plugin.getConfig().getString("Healingstone.BlockType")))) {
					Location loc = block.getLocation();
					getConfig.set("location.World", loc.getWorld().getName());
					getconfig.set("location.X", loc.getX());
					getconfig.set("location.Y", loc.getY());
					getconfig.set("location.Z", loc.getZ());
					getconfig.set("location.Yaw", loc.getYaw());
					getconfig.set("location.Pitch", loc.getPitch());
					saveConfig();
					
				}
			}
		}

		if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
			if (block.getType().equals(Material.getMaterial(plugin.getConfig().getString("Healingstone.BlockType")))) {
				if (player.hasPermission("hs.maxhealth")) {
					if (player.getHealth() == player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&',
								plugin.getConfig().getString("Message.Healthfull")));
						return;
					}

					final EconomyResponse response = plugin.getEconomy().withdrawPlayer(player,
							plugin.getConfig().getDouble("Economy.Cost"));
					if (!response.transactionSuccess()) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&',
								plugin.getConfig().getString("Message.Nofunds")));
						player.sendMessage(ChatColor.GREEN + "You Need" + " " + "$"
								+ (plugin.getConfig().getString("Economy.Cost")) + " " + "To Heal");
						// Not enough money

					} else {
						player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						player.sendMessage(ChatColor.translateAlternateColorCodes('&',
								plugin.getConfig().getString("Message.Healthrestored")));
						return;

					}

				}

			}
		}
		if (action.equals(Action.RIGHT_CLICK_AIR)) {
			if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR)) {

				if (player.hasPermission("hs.crystal.+")) {
					if (player.getHealth() == player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&',
								plugin.getConfig().getString("Message.Healthfull")));
						return;

					} else {
						if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
								.equals(ChatColor.GREEN + "+5 Healing Crystal")) {
							player.setHealth(Math.min(20, player.getHealth() + 5));
							player.getInventory().getItemInMainHand()
									.setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
							player.sendMessage(ChatColor.translateAlternateColorCodes('&',
									plugin.getConfig().getString("Message.CrystalHeal")));
							return;
						}
					}

					if (action.equals(Action.RIGHT_CLICK_AIR)) {
						if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR)) {
							if (player.hasPermission("hs.crystal.++")) {
								if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
										.equals(ChatColor.GREEN + "+10 Healing Crystal")) {
									if (player.getHealth() == player.getAttribute(Attribute.GENERIC_MAX_HEALTH)
											.getValue()) {
										player.sendMessage(ChatColor.translateAlternateColorCodes('&',
												plugin.getConfig().getString("Message.Healthfull")));
										return;

									} else {
										player.setHealth(Math.min(20, player.getHealth() + 10));
										player.getInventory().getItemInMainHand()
												.setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
										player.sendMessage(ChatColor.translateAlternateColorCodes('&',
												plugin.getConfig().getString("Message.CrystalHeal")));
										return;
									}
								}

								if (action.equals(Action.RIGHT_CLICK_AIR)) {
									if (player.getInventory().getItemInMainHand().getType()
											.equals(Material.NETHER_STAR)) {
										if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
												.equals(ChatColor.GREEN + "+15 Healing Crystal")) {
											if (player.hasPermission("hs.crystal.+++")) {
												if (player.getHealth() == player
														.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
													player.sendMessage(ChatColor.translateAlternateColorCodes('&',
															plugin.getConfig().getString("Message.Healthfull")));
													return;

												} else {
													player.setHealth(Math.min(20, player.getHealth() + 15));
													player.getInventory().getItemInMainHand().setAmount(
															player.getInventory().getItemInMainHand().getAmount() - 1);
													player.sendMessage(ChatColor.translateAlternateColorCodes('&',
															plugin.getConfig().getString("Message.CrystalHeal")));
													return;
												}
											}
										}

									}
								}
							}

						}
					}
				}
			}
		}

	}
}
