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
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MBCmd implements CommandExecutor {

	private MobBountyReloaded plugin;

	public MBCmd(MobBountyReloaded plugin) {
		setPlugin(plugin);
	}

	public MobBountyReloaded getPlugin() {
		return plugin;
	}

	public void setPlugin(MobBountyReloaded plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
	                         String commandLabel, String[] args) {
		String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBInfo");
		getPlugin().getAPI().sendMessage(
				sender,
				ChatColor.DARK_GREEN
						+ "------------------------------------------");
		getPlugin().getAPI().sendMessage(sender,
				ChatColor.DARK_GREEN + "MobBountyReloaded Help");
		getPlugin().getAPI().sendMessage(sender,
				ChatColor.DARK_GREEN + "Coded by ToppleTheNun");
		if (message == null || message.equals("")) {

			getPlugin().getAPI().sendMessage(
					sender,
					ChatColor.DARK_GREEN
							+ "------------------------------------------");
			return true;
		}
		if (getPlugin().getPermissionManager().hasPermission(sender,
				"mbr.user.command.check")) {
			getPlugin().getAPI().sendMessage(
					sender,
					getPlugin().getAPI().formatString(message, "", "", "", 0.0,
							0.0, 0.0, "/mobbountycheck",
							"Checks the values of mobs", "", "", "", 0, "", "",
							0));
		}
		if (getPlugin().getPermissionManager().hasPermission(sender,
				"mbr.admin.command.load")) {
			getPlugin().getAPI().sendMessage(
					sender,
					getPlugin().getAPI().formatString(message, "", "", "", 0.0,
							0.0, 0.0, "/mobbountyload", "Reloads the configs",
							"", "", "", 0, "", "", 0));
		}
		if (getPlugin().getPermissionManager().hasPermission(sender,
				"mbr.admin.command.save")) {
			getPlugin().getAPI().sendMessage(
					sender,
					getPlugin().getAPI().formatString(message, "", "", "", 0.0,
							0.0, 0.0, "/mobbountysave", "Save the configs", "",
							"", "", 0, "", "", 0));
		}
		if (sender.hasPermission("mbr.admin.command.reward")) {
			getPlugin().getAPI().sendMessage(
					sender,
					getPlugin().getAPI().formatString(message, "", "", "", 0.0,
							0.0, 0.0, "/mobbountyreward <creature> <amount>",
							"Change the <creature>'s value to <amount>", "",
							"", "", 0, "", "", 0));
		}
		if (sender.hasPermission("mbr.admin.command.worldreward")) {
			getPlugin()
					.getAPI()
					.sendMessage(
							sender,
							getPlugin()
									.getAPI()
									.formatString(
											message,
											"",
											"",
											"",
											0.0,
											0.0,
											0.0,
											"/mobbountyworldreward <world> <creature> [amount]",
											"Change the <creature>'s value to [amount] in <world>",
											"", "", "", 0, "", "", 0));
		}
		if (sender.hasPermission("mbr.admin.command.groupmulti")) {
			getPlugin().getAPI().sendMessage(
					sender,
					getPlugin().getAPI().formatString(message, "", "", "", 0.0,
							0.0, 0.0, "/mobbountygroupmulti <group> <amount>",
							"Change <group>'s multiplier to <amount>", "", "",
							"", 0, "", "", 0));
		}
		if (sender.hasPermission("mbr.admin.command.usermulti")) {
			getPlugin().getAPI().sendMessage(
					sender,
					getPlugin().getAPI().formatString(message, "", "", "", 0.0,
							0.0, 0.0, "/mobbountyusermulti <user> <amount>",
							"Change <user>'s multiplier to <amount>", "", "",
							"", 0, "", "", 0));
		}
		if (sender.hasPermission("mbr.admin.command.envmulti")) {
			getPlugin().getAPI().sendMessage(
					sender,
					getPlugin().getAPI().formatString(message, "", "", "", 0.0,
							0.0, 0.0,
							"/mobbountyenvmulti <environment> <amount>",
							"Change <environment>'s multiplier to <amount>",
							"", "", "", 0, "", "", 0));
		}
		if (sender.hasPermission("mbr.admin.command.timemulti")) {
			getPlugin().getAPI().sendMessage(
					sender,
					getPlugin().getAPI().formatString(message, "", "", "", 0.0,
							0.0, 0.0, "/mobbountytimemulti <time> <amount>",
							"Change <time>'s multiplier to <amount>", "", "",
							"", 0, "", "", 0));
		}
		getPlugin().getAPI().sendMessage(
				sender,
				ChatColor.DARK_GREEN
						+ "------------------------------------------");
		return true;
	}
}
