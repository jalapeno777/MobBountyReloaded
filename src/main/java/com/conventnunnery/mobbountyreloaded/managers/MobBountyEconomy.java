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
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.plugin.RegisteredServiceProvider;

public class MobBountyEconomy {

	private static final String LOG_TAG = "[MobBountyReloaded]";
	private MobBountyReloaded mbr;
	private Economy econ;

	public MobBountyEconomy(MobBountyReloaded mbr) {
		setMBR(mbr);
		if (!setupVaultEconomy()) {
			getMBR().getServer().getPluginManager().disablePlugin(getMBR());
		}
	}

	public boolean fineAccount(String accountName, double amount) {
		double amt = Math.abs(amount);
		if (getEcon() != null) {
			EconomyResponse r = getEcon().withdrawPlayer(accountName, amt);
			if (r.transactionSuccess())
				getMBR().getAPI().logToFile(
						"Fined " + accountName + " an amount of " + amt);
			else
				getMBR().getAPI().logToFile(
						"Failed to fine " + accountName + " an amount of "
								+ amt);
			return (r.transactionSuccess());
		}
		return false;
	}

	public String format(String accountName, double amt) {
		if (getEcon() != null)
			return getEcon().format(amt);
		return String.valueOf(amt);
	}

	/**
	 * Gets the economy plugin used by MBR.
	 *
	 * @return Server economy plugin
	 */
	public Economy getEcon() {
		return econ;
	}

	private void setEcon(Economy econ) {
		this.econ = econ;
	}

	/**
	 * @return The copy of MBR used by the plugin.
	 */
	public MobBountyReloaded getMBR() {
		return mbr;
	}

	private void setMBR(MobBountyReloaded mbr) {
		this.mbr = mbr;
	}

	public boolean payAccount(String accountName, double amount) {
		double amt = Math.abs(amount);
		if (getEcon() != null) {
			EconomyResponse r = getEcon().depositPlayer(accountName, amt);
			if (r.transactionSuccess())
				getMBR().getAPI().logToFile(
						"Paid " + accountName + " an amount of " + amt);
			else

				getMBR().getAPI().logToFile(
						"Failed to pay " + accountName + " an amount of "
								+ amt);
			return (r.transactionSuccess());
		}
		return false;
	}

	private boolean setupVaultEconomy() {
		if (getMBR().getServer().getPluginManager().getPlugin("Vault") == null) {
			getMBR().getAPI().log("Vault was not found. Disabling the plugin.");
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getMBR().getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			getMBR().getAPI()
					.log("No economy plugin found. Disabling the plugin.");
			return false;
		}
		setEcon(rsp.getProvider());
		return econ != null;
	}

}
