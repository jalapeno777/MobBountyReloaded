/*
 * Copyright (c) 2013. ToppleTheNun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.conventnunnery.mobbountyreloaded.commands;

import com.conventnunnery.mobbountyreloaded.MobBountyReloaded;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MBWorldReward implements CommandExecutor {

	private MobBountyReloaded plugin;

	public MBWorldReward(MobBountyReloaded plugin) {
		setPlugin(plugin);
	}

	private void commandUsage(CommandSender sender, String command) {
		String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBWRUsage");
		if (message != null) {
			message = getPlugin().getAPI().formatString(message, "", "", "",
					0.0, 0.0, 0.0, command, "",
					"mbr.admin.command.worldreward", "", "", 0, "", "", 0);
			getPlugin().getAPI().sendMessage(sender, message);
		}

		message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBWRWorlds");
		if (message != null) {
			List<World> worlds = getPlugin().getServer().getWorlds();
			List<String> worldsS = new ArrayList<String>();

			for (World w : worlds) {
				worldsS.add(w.getName());
			}

			message = getPlugin().getAPI().formatString(message, "", "",
					worldsS.toString().replace("[", "").replace("]", ""), 0.0, 0.0, 0.0, "", "",
					"mbr.admin.command.worldreward", "", "", 0, "", "", 0);
			getPlugin().getAPI().sendMessage(sender, message);
		}

		message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBWRMobs");
		if (message != null)
			getPlugin().getAPI().sendMessage(sender, message);
	}

	public MobBountyReloaded getPlugin() {
		return plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
	                         String label, String[] args) {
		if (!(sender instanceof Player)) {
			getPlugin().getAPI().sendMessage(sender,
					"Commands are designed to be run by players only.");
			return true;
		}
		Player player = ((Player) sender);
		if (getPlugin().getPermissionManager().hasPermission(player,
				"mbr.admin.command.worldreward")) {
			if (args.length == 3) {
				EntityType mob = EntityType.fromName(args[1]);

				if (mob != null) {
					switch (mob) {
						case ARROW:
						case BOAT:
						case COMPLEX_PART:
						case DROPPED_ITEM:
						case EGG:
						case ENDER_CRYSTAL:
						case ENDER_PEARL:
						case ENDER_SIGNAL:
						case EXPERIENCE_ORB:
						case FALLING_BLOCK:
						case FIREBALL:
						case FIREWORK:
						case FISHING_HOOK:
						case ITEM_FRAME:
						case LIGHTNING:
						case MINECART:
						case MINECART_FURNACE:
						case MINECART_HOPPER:
						case MINECART_CHEST:
						case MINECART_MOB_SPAWNER:
						case MINECART_TNT:
						case PAINTING:
						case PRIMED_TNT:
						case SMALL_FIREBALL:
						case SNOWBALL:
						case SPLASH_POTION:
						case THROWN_EXP_BOTTLE:
						case WEATHER:
						case WITHER_SKULL:
							String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBWRDeny");
							if (message != null) {
								getPlugin()
										.getAPI()
										.sendMessage(
												sender,
												getPlugin()
														.getAPI()
														.formatString(
																message,
																"",
																mob.getName(),
																"",
																"",
																"",
																"",
																"",
																"",
																"mbr.admin.command.worldreward",
																"", "", "", "", "",
																""));
							}
							return true;
					}
					if (args[2].matches("[-]?[0-9]+([.][0-9]+)?")) {
						Double amount = Double.parseDouble(args[2]);
						getPlugin().getSettingsManager().setCreatureReward(
								args[0], mob.name(), amount.toString());

						String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBWRChange");
						if (message != null) {
							getPlugin().getAPI().sendMessage(
									sender,
									getPlugin().getAPI().formatString(message,
											"", mob.getName(), "",
											Double.valueOf(args[2]),
											Double.valueOf(args[2]),
											Double.valueOf(args[2]), "", "",
											"mbr.admin.command.worldreward",
											"", "", 0, "", "", 0));
						}
					} else if (args[2]
							.matches("[-]?[0-9]+([.][0-9]+)?[:][-]?[0-9]+([.][0-9]+)?")) {
						getPlugin().getSettingsManager().setCreatureReward(
								args[0], mob.name(), args[2]);

						String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBWRChange");
						if (message != null) {
							getPlugin().getAPI().sendMessage(
									sender,
									getPlugin().getAPI().formatString(message,
											"", mob.getName(), "",
											Double.valueOf(args[2]),
											Double.valueOf(args[2]),
											Double.valueOf(args[2]), "", "",
											"mbr.admin.command.worldreward",
											"", "", 0, "", "", 0));
						}
					} else
						this.commandUsage(sender, label);
				} else
					this.commandUsage(sender, label);
			} else
				this.commandUsage(sender, label);
		} else {
			String message = getPlugin().getSettingsManager().getL_MESSAGES().get("NoAccess");
			if (message != null)
				getPlugin().getAPI().sendMessage(sender, message);
		}

		return true;
	}

	public void setPlugin(MobBountyReloaded plugin) {
		this.plugin = plugin;
	}
}
