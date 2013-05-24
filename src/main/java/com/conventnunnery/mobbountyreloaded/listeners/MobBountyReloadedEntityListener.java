/*
 * Copyright (c) 2013. ToppleTheNun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.conventnunnery.mobbountyreloaded.listeners;

import com.conventnunnery.mobbountyreloaded.MobBountyAPI;
import com.conventnunnery.mobbountyreloaded.MobBountyReloaded;
import com.conventnunnery.mobbountyreloaded.event.MobBountyReloadedKillstreakEvent;
import com.conventnunnery.mobbountyreloaded.event.MobBountyReloadedPaymentEvent;
import com.conventnunnery.mobbountyreloaded.utils.MobBountyPlayerKillData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.List;

public class MobBountyReloadedEntityListener implements Listener {

	private MobBountyReloaded plugin;

	public MobBountyReloadedEntityListener(MobBountyReloaded mbr) {
		setPlugin(mbr);
		getPlugin().getServer().getPluginManager()
				.registerEvents(this, getPlugin());
	}

	private Player getLastDamager(LivingEntity entity) {
		if (entity.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent) entity
					.getLastDamageCause();
			Entity damagerEntity = edbee.getDamager();
			if (damagerEntity instanceof Player) {
				return (Player) damagerEntity;
			}
			if (damagerEntity instanceof Projectile) {
				if (((Projectile) damagerEntity).getShooter() instanceof Player) {
					return (Player) ((Projectile) damagerEntity).getShooter();
				}
				return null;
			}
			if (damagerEntity instanceof Tameable) {
				if (((Tameable) damagerEntity).getOwner() instanceof Player) {
					return (Player) ((Tameable) damagerEntity).getOwner();
				}
				return null;
			}
		}
		return null;
	}

	public MobBountyReloaded getPlugin() {
		return plugin;
	}

	private void setPlugin(MobBountyReloaded mbr) {
		this.plugin = mbr;
	}

	private void handleKillCache(Player killer,
	                             MobBountyPlayerKillData playerData, double amount) {
		playerData.cacheSize++;
		if (amount >= 0.0)
			playerData.cacheEarned = playerData.cacheEarned + Math.abs(amount);
		else
			playerData.cacheEarned = playerData.cacheEarned - Math.abs(amount);
		long timeLimit = getPlugin().getSettingsManager().getG_KILLCACHE_TIME_LIMIT();
		long cTime = System.currentTimeMillis() - playerData.cacheTime;
		if (cTime >= timeLimit) {
			if (playerData.cacheEarned > 0.0) {
				String message = getPlugin().getSettingsManager().getL_MESSAGES().get("CacheAwarded");
				if (message != null) {
					message = getPlugin()
							.getAPI()
							.formatString(
									message,
									killer.getName(),
									"",
									killer.getWorld().getName(),
									playerData.cacheEarned,
									playerData.cacheEarned,
									playerData.cacheEarned,
									"",
									"",
									"",
									"",
									String.valueOf(String.valueOf(Math
											.round((System.currentTimeMillis() - playerData.cacheTime) / 1000))),
									playerData.cacheSize, "", "",
									playerData.killStreak);
					getPlugin().getAPI().sendMessage(killer, message);
				}
			} else if (playerData.cacheEarned < 0.0) {
				String message = getPlugin().getSettingsManager().getL_MESSAGES().get("CacheFined");
				if (message != null) {
					message = getPlugin().getAPI()
							.formatString(
									message,
									killer.getName(),
									"",
									killer.getWorld().getName(),
									playerData.cacheEarned,
									playerData.cacheEarned,
									playerData.cacheEarned,
									"",
									"",
									"",
									"",
									String.valueOf(String.valueOf(Math
											.round(cTime / 1000))),
									playerData.cacheSize, "", "",
									playerData.killStreak);
					getPlugin().getAPI().sendMessage(killer, message);
				}
			}
		}
		playerData.cacheTime = System.currentTimeMillis();
		MobBountyAPI.playerData.put(killer.getName(), playerData);
	}

	@EventHandler(
			priority = EventPriority.LOW)
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity() == event.getEntity().getKiller())
			return;
		Player player = getLastDamager(event.getEntity());
		EntityType creature = event.getEntity().getType();
		if (player == null)
			return;
		if (player.getGameMode() == GameMode.CREATIVE
				&& !getPlugin().getSettingsManager().isG_ALLOW_CREATIVE()) {
			return;
		}
		if (!getPlugin().getPermissionManager().hasPermission(player,
				"mbr.user.collect")) {
			return;
		}
		double amount = getPlugin().getAPI().getEntityValue(
				event.getEntity());
		if (amount == 0.0) {
			return;
		}
		if (amount < 0.0
				&& getPlugin().getPermissionManager().hasPermission(player,
				"mbr.user.finebypass")) {
			return;
		}
		MobBountyReloadedPaymentEvent mbrpe = new MobBountyReloadedPaymentEvent(
				player.getName(), amount, event.getEntity());
		getPlugin().getServer().getPluginManager().callEvent(mbrpe);
		MobBountyReloadedKillstreakEvent mbrke = null;
		List<String> creatureNames = getPlugin().getSettingsManager().getKS_ALLOWED_CREATURES();
		if (creatureNames == null) {
			creatureNames = new ArrayList<String>();
		}
		if (creatureNames.contains(creature.getName())) {
			mbrke = new MobBountyReloadedKillstreakEvent(player.getName(),
					event.getEntity());
			getPlugin().getServer().getPluginManager().callEvent(mbrke);
		}
		if (!mbrpe.isCancelled()) {
			Player playerEvent = Bukkit.getServer().getPlayer(
					mbrpe.getPlayerName());
			if (playerEvent != null) {
				MobBountyPlayerKillData playerKillData = MobBountyAPI.playerData
						.get(playerEvent.getName());
				if (playerKillData == null) {
					playerKillData = new MobBountyPlayerKillData();
					MobBountyAPI.playerData.put(playerEvent.getName(),
							playerKillData);
				}
				double amountEvent = mbrpe.getAmount();
				if (amountEvent != 0.0) {
					double mult = getPlugin().getAPI().getMult(playerEvent);
					double amt = mult * amountEvent;
					getPlugin().getAPI()
							.makeTransaction(mbrpe.getPlayerName(), amt);
					boolean bln = getPlugin().getSettingsManager().isG_KILLCACHE_USE();
					if (bln) {
						handleKillCache(playerEvent, playerKillData, amt);
					} else {
						sendNonKillCache(mbrpe, playerEvent, amountEvent, amt);
					}
				}
			}
		}
		if (mbrke != null && !mbrke.isCancelled()) {
			MobBountyPlayerKillData playerData = MobBountyAPI.playerData
					.get(mbrke.getPlayerName());
			if (playerData == null) {
				playerData = new MobBountyPlayerKillData();
				MobBountyAPI.playerData.put(mbrke.getPlayerName(), playerData);
			}
			playerData.killStreak++;
			Double confBonus = getPlugin().getSettingsManager().getKS_KILLBONUSES()
					.get(String.valueOf(playerData.killStreak));
			if (confBonus == null || confBonus == 0.0) {
				return;
			}
			getPlugin().getEconManager().payAccount(mbrke.getPlayerName(),
					confBonus);
			Player playerEvent = getPlugin().getServer().getPlayer(
					mbrke.getPlayerName());
			if (playerEvent != null) {
				String string = getPlugin().getSettingsManager().getL_MESSAGES().get("Killstreak");
				if (string != null) {
					getPlugin().getAPI().sendMessage(
							playerEvent,
							getPlugin().getAPI().formatString(string,
									playerEvent.getName(),
									mbrke.getEntity().getType().getName(),
									playerEvent.getWorld().getName(), confBonus,
									confBonus, confBonus, "", "", "", "", "", 0,
									"", "", playerData.killStreak));
				}
				MobBountyAPI.playerData.put(playerEvent.getName(), playerData);
			}
		}
	}

	private void sendFineMessage(MobBountyReloadedPaymentEvent mbrpe,
	                             Player player, double amt) {
		if (mbrpe == null || player == null)
			return;
		String string = getPlugin().getSettingsManager().getL_MESSAGES().get("Fined");
		if (string == null) {
			return;
		}
		String name = mbrpe.getEntity().getType().getName();
		if (name == null) {
			if (mbrpe.getEntity() instanceof Player) {
				name = "Player";
			} else {
				name = "";
			}
		}
		getPlugin().getAPI()
				.sendMessage(
						player,
						getPlugin().getAPI()
								.formatString(
										string,
										player.getName(),
										name,
										player.getWorld().getName(),
										amt,
										amt,
										amt,
										"",
										"",
										"mbr.user.collect",
										"",
										"",
										0,
										"",
										"",
										MobBountyAPI.playerData.get(player
												.getName()).killStreak));
	}

	private void sendNonKillCache(MobBountyReloadedPaymentEvent mbrpe,
	                              Player player, double amount, double amt) {
		if (amount > 0.0) {
			sendPayMessage(mbrpe, player, amt);
		} else if (amount < 0.0) {
			sendFineMessage(mbrpe, player, amt);
		}
	}

	private void sendPayMessage(MobBountyReloadedPaymentEvent mbrpe,
	                            Player player, double amt) {
		if (mbrpe == null || player == null)
			return;
		String string = getPlugin().getSettingsManager().getL_MESSAGES().get("Awarded");
		if (string == null) {
			return;
		}
		String name = mbrpe.getEntity().getType().getName();
		if (name == null) {
			if (mbrpe.getEntity() instanceof Player) {
				name = "Player";
			} else {
				name = "";
			}
		}
		getPlugin().getAPI()
				.sendMessage(
						player,
						getPlugin().getAPI()
								.formatString(
										string,
										player.getName(),
										name,
										player.getWorld().getName(),
										amt,
										amt,
										amt,
										"",
										"",
										"mbr.user.collect",
										"",
										"",
										0,
										"",
										"",
										MobBountyAPI.playerData.get(player
												.getName()).killStreak));
	}
}