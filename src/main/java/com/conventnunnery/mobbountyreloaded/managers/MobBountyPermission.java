/*
 * Copyright (c) 2013. ToppleTheNun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.conventnunnery.mobbountyreloaded.managers;

import com.conventnunnery.mobbountyreloaded.MobBountyReloaded;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;

public class MobBountyPermission {

	private MobBountyReloaded plugin;
	private Permission perms;

	public MobBountyPermission(MobBountyReloaded plugin) {
		setPlugin(plugin);
		if (getPlugin().getServer().getPluginManager().getPlugin("Vault") != null) {
			setupPermissions();
		}
	}

	public Permission getPermissions() {
		return perms;
	}

	public void setPermissions(Permission perms) {
		this.perms = perms;
	}

	public MobBountyReloaded getPlugin() {
		return plugin;
	}

	public void setPlugin(MobBountyReloaded plugin) {
		this.plugin = plugin;
	}

	public boolean hasPermission(CommandSender sender, String node) {
		String has = getPlugin().getSettingsManager().getL_MESSAGES().get("DebugHasPermission");
		String lacks = getPlugin().getSettingsManager().getL_MESSAGES().get("DebugLacksPermission");
		boolean debug = getPlugin().getSettingsManager().isG_DEBUG();
		if (sender.hasPermission(node)) {
			if (debug && sender.hasPermission("mbr.admin.debug") && has != null) {
				getPlugin().getAPI().sendMessage(
						sender,
						getPlugin().getAPI().formatString(has,
								sender.getName(), "", "", "", "", "", "",
								"", node, "", "", "", "", "", ""));
			}
			return true;
		}
		if (debug && sender.hasPermission("mbr.admin.debug") && lacks != null) {
			getPlugin().getAPI().sendMessage(
					sender,
					getPlugin().getAPI().formatString(lacks,
							sender.getName(), "", "", "", "", "", "", "",
							node, "", "", "", "", "", ""));
		}
		return false;
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getPlugin().getServer()
				.getServicesManager().getRegistration(Permission.class);
		setPermissions(rsp.getProvider());
		return getPermissions() != null;
	}
}
