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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MBGroupMulti implements CommandExecutor {
	private MobBountyReloaded _plugin;

	public MBGroupMulti(MobBountyReloaded plugin) {
		setPlugin(plugin);
	}

	private void commandUsage(CommandSender sender, String command) {
		String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGMUsage");
		if (message != null) {
			message = message.replace("%C", command);
			getPlugin().getAPI().sendMessage(sender, message);
		}
	}

	public MobBountyReloaded getPlugin() {
		return _plugin;
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
				"mbr.admin.command.mbgm")) {
			if (args.length == 2) {
				if (args[1].matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
					Double amount;
					try {
						amount = Double.parseDouble(args[1]);
					} catch (NumberFormatException e) {
						amount = 1.0;
					}

					getPlugin().getSettingsManager().getM_GROUP_MULTIPLIER().put(
							args[0].toUpperCase(), amount);

					String message = getPlugin().getSettingsManager().getL_MESSAGES().get("MBGMChange");
					if (message != null) {
						message = getPlugin().getAPI().formatString(message,
								player.getName(), "", args[0], amount, amount,
								amount, "", "", "", "", "", 0, "", "", 0);
						getPlugin().getAPI().sendMessage(sender, message);
					}
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

	public void setPlugin(MobBountyReloaded _plugin) {
		this._plugin = _plugin;
	}
}
