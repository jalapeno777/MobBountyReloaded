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

public class MBSave implements CommandExecutor {

	private MobBountyReloaded plugin;

	public MBSave(MobBountyReloaded plugin) {
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
	                         String cmdLbl, String[] args) {
		String message;
		if (!getPlugin().getPermissionManager().hasPermission(sender,
				"mbr.admin.command.save")) {
			message = getPlugin().getAPI().formatString(
					getPlugin().getSettingsManager().getL_MESSAGES().get("NoAccess"), "", "", "",
					0.0, 0.0, 0.0, "", "", "mbr.admin.command.save", "", "", 0,
					"", "", 0);
			if (message != null) {
				getPlugin().getAPI().sendMessage(sender, message);
			}
			return true;
		}
		getPlugin().getSettingsManager().saveSettings();
		getPlugin().getConfigManager().saveConfig();
		message = getPlugin().getAPI().formatString(
				getPlugin().getSettingsManager().getL_MESSAGES().get("MBSSaved"), "", "", "", 0.0,
				0.0, 0.0, "", "", "mbr.admin.command.save", "", "", 0, "", "",
				0);
		if (message != null) {
			getPlugin().getAPI().sendMessage(sender, message);
		}
		return true;
	}

}
