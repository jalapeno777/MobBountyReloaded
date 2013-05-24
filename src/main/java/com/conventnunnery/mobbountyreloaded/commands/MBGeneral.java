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
import com.conventnunnery.mobbountyreloaded.utils.MobBountyUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MBGeneral implements CommandExecutor {
	private MobBountyReloaded _plugin;

	public MBGeneral(MobBountyReloaded plugin) {
		setPlugin(plugin);
	}

	private void commandUsage(CommandSender sender, String command) {
		String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGUsage");
		if (message != null) {
			message = message.replace("%C", command);
			getPlugin().getAPI().sendMessage(sender, message);
		}

		message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGProperty");
		if (message != null)
			getPlugin().getAPI().sendMessage(sender, message);
	}

	public MobBountyReloaded getPlugin() {
		return _plugin;
	}

	public void setPlugin(MobBountyReloaded plugin) {
		_plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
	                         String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Commands are designed to be run by players only.");
			return true;
		}
		Player player = ((Player) sender);
		if (getPlugin().getPermissionManager().hasPermission(player,
				"mbr.admin.command.mbg")) {
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("locale")) {
					getPlugin().getSettingsManager().setG_LOCALE(args[1]
							.toLowerCase());
					String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGChange");
					if (message != null) {
						message = message.replace("%S", "locale").replace("%V",
								args[1].toLowerCase());
						getPlugin().getAPI().sendMessage(sender, message);
					}
				} else if (args[1].equalsIgnoreCase("true")
						|| args[1].equalsIgnoreCase("yes")
						|| args[1].equalsIgnoreCase("1")) {
					if (args[0].equalsIgnoreCase("debug")) {
						getPlugin().getSettingsManager().setG_DEBUG(true);
						String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGChange");
						if (message != null) {
							message = message.replace("%S", "debugMode")
									.replace("%V", "true");
							getPlugin().getAPI().sendMessage(sender, message);
						}
					} else if (args[0].equalsIgnoreCase("usekillcache")) {
						getPlugin().getSettingsManager().setG_KILLCACHE_USE(true);

						String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGChange");
						if (message != null) {
							message = message.replace("%S", "usekillcache")
									.replace("%V", "true");
							getPlugin().getAPI().sendMessage(sender, message);
						}
					} else if (args[0].equalsIgnoreCase("allowcreative")) {
						getPlugin().getSettingsManager().setG_ALLOW_CREATIVE(true);
						String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGChange");
						if (message != null) {
							message = message.replace("%S", "allowCreative")
									.replace("%V", "true");
							getPlugin().getAPI().sendMessage(sender, message);
						}
					} else
						this.commandUsage(sender, label);
				} else if (args[1].equalsIgnoreCase("false")
						|| args[1].equalsIgnoreCase("no")
						|| args[1].equalsIgnoreCase("0")) {
					if (args[0].equalsIgnoreCase("debug")) {
						getPlugin().getSettingsManager().setG_DEBUG(false);
						String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGChange");
						if (message != null) {
							message = message.replace("%S", "debugMode")
									.replace("%V", "false");
							getPlugin().getAPI().sendMessage(sender, message);
						}
					} else if (args[0].equalsIgnoreCase("usekillcache")) {
						getPlugin().getSettingsManager().setG_KILLCACHE_USE(false);
						String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGChange");
						if (message != null) {
							message = message.replace("%S", "usekillcache")
									.replace("%V", "false");
							getPlugin().getAPI().sendMessage(sender, message);
						}
					} else if (args[0].equalsIgnoreCase("allowcreative")) {
						getPlugin().getSettingsManager().setG_ALLOW_CREATIVE(false);
						String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGChange");
						if (message != null) {
							message = message.replace("%S", "allowcreative")
									.replace("%V", "false");
							getPlugin().getAPI().sendMessage(sender, message);
						}
					} else
						this.commandUsage(sender, label);
				} else if (args[1].matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
					if (args[0].equalsIgnoreCase("killcachetimelimit")) {
						getPlugin().getSettingsManager().setG_KILLCACHE_TIME_LIMIT(MobBountyUtils
								.getInt(args[1], 30000));
						String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGChange");
						if (message != null) {
							message = message.replace("%S",
									"killcachetimelimit")
									.replace("%V", args[1]);
							getPlugin().getAPI().sendMessage(sender, message);
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
}
