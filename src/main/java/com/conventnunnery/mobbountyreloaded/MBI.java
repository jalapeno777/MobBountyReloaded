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

import java.util.Map;

import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.conventnunnery.mobbountyreloaded.MobBountyReloaded;
import com.conventnunnery.mobbountyreloaded.utils.MobBountyTime;
import com.conventnunnery.mobbountyreloaded.utils.MobBountyUtils;

public class MBI {

	private MobBountyReloaded mbr;

	public MBI(MobBountyReloaded mbr) {
		setMBR(mbr);
	}

	public double getBiomeMult(Player player) {
		Biome biome = player.getLocation().getBlock().getBiome();
		Double biomeMult;
		biomeMult = getMBR().getSettingsManager().getM_BIOME_MULTIPLIER()
				.get(biome.name().toUpperCase());
		if (biomeMult == null) {
			biomeMult = 1.0;
		}
		return biomeMult;
	}

	public double getEnvironmentMult(Player player) {
		Environment env = player.getWorld().getEnvironment();
		Double envMult = getMBR().getSettingsManager()
				.getM_ENVIRONMENT_MULTIPLIER().get(env.name().toUpperCase());
		if (envMult == null)
			envMult = 1.0;
		return envMult;
	}

	public double getFortuneMult(Player player) {
		if (!(player.getItemInHand().containsEnchantment(
				Enchantment.LOOT_BONUS_BLOCKS) || player.getItemInHand()
				.containsEnchantment(Enchantment.LOOT_BONUS_MOBS))) {
			return 1.0;
		}
		return getMBR().getSettingsManager().getM_FORTUNE_MULTIPLIER();
	}

	public double getGroupMult(Player player) {
		double groupMult = 1.0;
		if (getMBR().getPermissionManager().getPermissions() == null)
			return groupMult;
		if (getMBR().getPermissionManager().getPermissions().getName()
				.equalsIgnoreCase("SuperPerms")) {
			return groupMult;
		}
		String key = getMBR().getPermissionManager().getPermissions()
				.getPrimaryGroup(player).toUpperCase();
		if (!getMBR().getSettingsManager().getM_GROUP_MULTIPLIER()
				.containsKey(key))
			return 1.0;
		groupMult = getMBR()
				.getSettingsManager()
				.getM_GROUP_MULTIPLIER()
				.get(getMBR().getPermissionManager().getPermissions()
						.getPrimaryGroup(player).toUpperCase());
		return groupMult;
	}

	public MobBountyReloaded getMBR() {
		return mbr;
	}

	public void setMBR(MobBountyReloaded mbr) {
		this.mbr = mbr;
	}

	public double getTimeMult(Player player) {
		MobBountyTime time = MobBountyTime.getTimeOfDay(player.getWorld()
				.getTime());
		if (!getMBR().getSettingsManager().getM_TIME_MULTIPLIER()
				.containsKey(time.name().toUpperCase())) {
			return 1.0;
		}
		Double timeMult = getMBR().getSettingsManager().getM_TIME_MULTIPLIER()
				.get(time.name().toUpperCase());
		if (timeMult == null) {
			timeMult = 1.0;
		}
		return timeMult;
	}

	public double getUserMult(Player player) {
		if (!getMBR().getSettingsManager().getM_USER_MULTIPLIER()
				.containsKey(player.getName().toUpperCase()))
			return 1.0;
		return getMBR().getSettingsManager().getM_USER_MULTIPLIER()
				.get(player.getName().toUpperCase());
	}

	public double getValue(LivingEntity entity) {
		String wn = entity.getWorld().getName();
		Map<String, String> map = getMBR().getSettingsManager()
				.getR_CREATURE_VALUES().get(wn);
		if (map == null) {
			map = getMBR().getSettingsManager().getR_CREATURE_VALUES()
					.get("Default");
			if (map == null) {
				return 0.0;
			}
		}
		String name = entity.getType().name();
		if (!map.containsKey(name))
			return 0.0;
		String s = map.get(name);
		if (s == null)
			return 0.0;
		double baseReward;
		if (s.contains(":")) {
			baseReward = MobBountyUtils.handleRange(s, ":", 0);
		} else {
			baseReward = MobBountyUtils.getDouble(s, 0);
		}
		return baseReward;
	}

	public double getWorldMult(Player player) {
		Double worldMult = getMBR().getSettingsManager()
				.getM_WORLD_MULTIPLIER().get(player.getWorld().getName());
		if (worldMult == null)
			worldMult = 1.0;
		return worldMult;
	}
}