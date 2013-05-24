/*
 * Copyright (c) 2013. ToppleTheNun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.conventnunnery.mobbountyreloaded;

import com.conventnunnery.mobbountyreloaded.utils.MobBountyPlayerKillData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MobBountyAPI {

	public static Map<String, MobBountyPlayerKillData> playerData;
	private MobBountyReloaded mbr;
	private MBI mbi;

	public MobBountyAPI(final MobBountyReloaded plugin) {
		setMobBountyReloaded(plugin);
		setMBI(new MBI(getMobBountyReloaded()));
		playerData = new HashMap<String, MobBountyPlayerKillData>();
	}

	public boolean containsIgnoreCase(Collection<String> collection, String string) {
		for (String s : collection) {
			if (s.equalsIgnoreCase(string))
				return true;
		}
		return false;
	}

	/**
	 * Formats and returns a string. No value should ever be null.
	 *
	 * @param string          Message to format
	 * @param playerName      Player's name
	 * @param creatureName    Creature's name
	 * @param worldName       World's name
	 * @param amount          Amount
	 * @param a1              First amount
	 * @param a2              Second amount
	 * @param commandName     Command's name
	 * @param commandHelp     Command's help
	 * @param permission      Permission used
	 * @param environment     Environment
	 * @param time            Time
	 * @param killCacheAmount Amount of kills in player's kill cache
	 * @param setting         Setting that was changed
	 * @param settingValue    New value of setting
	 * @param killStreak      Amount of killstreak
	 * @return Formatted string
	 */
	public String formatString(final String string, final String playerName,
	                           final String creatureName, final String worldName,
	                           final double amount, final double a1, final double a2,
	                           final String commandName, final String commandHelp,
	                           final String permission, final String environment,
	                           final String time, final int killCacheAmount, final String setting,
	                           final String settingValue, final int killStreak) {
		String message = string;
		message = message.replace("%P", playerName != null ? playerName : "");
		message = message.replace("%M", creatureName != null ? creatureName : "");
		message = message.replace("%W", worldName);
		message = message.replace("%A", getMobBountyReloaded().getEconManager()
				.format(playerName, Math.abs(amount)));
		message = message.replace("%1", getMobBountyReloaded().getEconManager()
				.format(playerName, Math.abs(a1)));
		message = message.replace("%2", getMobBountyReloaded().getEconManager()
				.format(playerName, Math.abs(a2)));
		message = message.replace("%C", commandName != null ? commandName : "");
		message = message.replace("%H", commandHelp != null ? commandHelp : "");
		message = message.replace("%D", permission != null ? permission : "");
		message = message.replace("%E", environment != null ? environment : "");
		message = message.replace("%T", time != null ? time : "");
		message = message.replace("%K",
				String.valueOf(Math.abs(killCacheAmount)));
		message = message.replace("%S", setting != null ? setting : "");
		message = message.replace("%V", settingValue != null ? settingValue : "");
		message = message.replace("%X", String.valueOf(killStreak));
		return message;
	}

	/**
	 * Formats and returns a string. No value should ever be null.
	 *
	 * @param string          Message to format
	 * @param playerName      Player's name
	 * @param creatureName    Creature's name
	 * @param worldName       World's name
	 * @param amount          Amount
	 * @param a1              First amount
	 * @param a2              Second amount
	 * @param commandName     Command's name
	 * @param commandHelp     Command's help
	 * @param permission      Permission used
	 * @param environment     Environment
	 * @param time            Time
	 * @param killCacheAmount Amount of kills in player's kill cache
	 * @param setting         Setting that was changed
	 * @param settingValue    New value of setting
	 * @param killStreak      value for killstreak amount
	 * @return Formatted string
	 */
	public String formatString(final String string, final String playerName,
	                           final String creatureName, final String worldName,
	                           final String amount, final String a1, final String a2,
	                           final String commandName, final String commandHelp,
	                           final String permission, final String environment,
	                           final String time, final String killCacheAmount,
	                           final String setting, final String settingValue,
	                           final String killStreak) {
		String message = string;
		message = message.replace("%P", playerName != null ? playerName : "");
		message = message.replace("%M", creatureName != null ? creatureName : "");
		message = message.replace("%W", worldName != null ? worldName : "");
		message = message.replace("%A", amount != null ? amount : "");
		message = message.replace("%1", a1 != null ? a1 : "");
		message = message.replace("%2", a2 != null ? a2 : "");
		message = message.replace("%C", commandName != null ? commandName : "");
		message = message.replace("%H", commandHelp != null ? commandHelp : "");
		message = message.replace("%D", permission != null ? permission : "");
		message = message.replace("%E", environment != null ? permission : "");
		message = message.replace("%T", time != null ? permission : "");
		message = message.replace("%K", killCacheAmount != null ? killCacheAmount : "");
		message = message.replace("%S", setting != null ? setting : "");
		message = message.replace("%V", settingValue != null ? setting : "");
		message = message.replace("%X", killStreak != null ? killStreak : "");
		return message;
	}

	/**
	 * Gets a creature's value
	 *
	 * @param entity Kind of creature
	 * @return Value
	 */
	public double getEntityValue(final LivingEntity entity) {
		return getMBI().getValue(entity);
	}

	/**
	 * Gets the MBI instance
	 *
	 * @return The MBI instance
	 */
	public MBI getMBI() {
		return mbi;
	}

	/**
	 * Sets the MBI instance
	 *
	 * @param mbi The MBI instance to set
	 */
	public void setMBI(final MBI mbi) {
		this.mbi = mbi;
	}

	/**
	 * Gets the copy of MBR.
	 *
	 * @return The copy of MobBountyReloaded that this API references.
	 */
	public MobBountyReloaded getMobBountyReloaded() {
		return mbr;
	}

	private void setMobBountyReloaded(final MobBountyReloaded mbr) {
		this.mbr = mbr;
	}

	/**
	 * Gets the multiplier for the player's earnings
	 *
	 * @param player The player to return for
	 * @return The multiplier value
	 */
	public double getMult(final Player player) {
		return getMBI().getEnvironmentMult(player)
				* getMBI().getTimeMult(player) * getMBI().getWorldMult(player)
				* getMBI().getFortuneMult(player)
				* getMBI().getUserMult(player) * getMBI().getGroupMult(player)
				* getMBI().getBiomeMult(player);
	}

	/**
	 * Prints out a message to the console.
	 *
	 * @param message A message that is logged by the plugin.
	 */
	public void log(final String message) {
		getMobBountyReloaded().getLogger().info(message);
	}

	public void logToFile(String message) {
		if (!getMobBountyReloaded().getSettingsManager().isG_DEBUG())
			return;
		File dataFolder = new File("plugins/MobBountyReloaded/");
		if (!dataFolder.exists()) {
			boolean b = true;
			try {
				b = dataFolder.mkdirs();
			} catch (SecurityException ignored) {
			}
			if (!b)
				return;
		}
		File saveTo = new File("plugins/MobBountyReloaded/debug.log");
		if (!saveTo.exists()) {
			boolean b = true;
			try {
				b = saveTo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!b)
				return;
		}
		FileWriter fw;
		try {
			fw = new FileWriter(saveTo, true);
		} catch (IOException e) {
			return;
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(Calendar.getInstance().getTime().toString() + " |:| "
				+ message);
		pw.flush();
		pw.close();
	}

	/**
	 * Makes a transaction to playerName's account of amt
	 *
	 * @param playerName Player's name
	 * @param amt        Amount to transfer
	 * @return Success
	 */
	public boolean makeTransaction(final String playerName, final double amt) {
		if (amt == 0.0)
			return true;
		if (amt > 0.0)
			return getMobBountyReloaded().getEconManager().payAccount(
					playerName, amt);
		return amt < 0.0 && getMobBountyReloaded().getEconManager().fineAccount(
				playerName, amt);
	}

	public void sendMessage(CommandSender sender, String message) {
		if (sender instanceof Player) {
			if (((Player) sender).getListeningPluginChannels().contains(
					"SimpleNotice")) {
				((Player) sender).sendPluginMessage(getMobBountyReloaded(),
						"SimpleNotice", message
						.getBytes(java.nio.charset.Charset
								.forName("UTF-8")));
				return;
			}
		}
		sender.sendMessage(message);
	}
}
