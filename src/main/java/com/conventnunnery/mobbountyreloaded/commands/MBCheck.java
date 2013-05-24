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
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Map;

public class MBCheck implements CommandExecutor {

    private MobBountyReloaded plugin;

    public MBCheck(MobBountyReloaded plugin) {
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
                             String label, String[] args) {
        if (!(sender instanceof Player)) {
            getPlugin().getAPI().sendMessage(sender,
                    "Commands are designed to be run by players only.");
            return true;
        }
        Player player = ((Player) sender);
        if (getPlugin().getPermissionManager().hasPermission(player,
                "mbr.user.command.check")) {
            World world = player.getWorld();

            double reward;

            Map<String, String> map = getPlugin().getSettingsManager().getR_CREATURE_VALUES()
                    .get(world.getName());

            if (map == null)
                map = getPlugin().getSettingsManager().getR_CREATURE_VALUES()
                        .get("Default");

            for (EntityType et : EntityType.values()) {

                String rewardTest = map.get(et.name());

                if (rewardTest != null && et.getName() != null) {
                    if (rewardTest.contains(":")) {
                        String[] rewardRange = rewardTest.split(":");
                        double from;
                        double to;

                        if (MobBountyUtils.getDouble(rewardRange[0], 0.0) > MobBountyUtils
                                .getDouble(rewardRange[1], 0.0)) {
                            from = MobBountyUtils
                                    .getDouble(rewardRange[1], 0.0);
                            to = MobBountyUtils.getDouble(rewardRange[0], 0.0);
                        } else {
                            from = MobBountyUtils
                                    .getDouble(rewardRange[0], 0.0);
                            to = MobBountyUtils.getDouble(rewardRange[1], 0.0);
                        }

                        if (getPlugin().getEconManager().getEcon() != null) {
                            if (from > 0.0) {
                                String message = getPlugin()
                                        .getSettingsManager().getL_MESSAGES().get("MBRewardRange");
                                if (message != null) {
                                    message = getPlugin().getAPI()
                                            .formatString(message,
                                                    player.getName(),
                                                    et.getName(),
                                                    world.getName(),
                                                    (from + to) / 2, from, to,
                                                    "", "",
                                                    "mbr.user.command.check",
                                                    "", "", 0, "", "", 0);
                                    getPlugin().getAPI().sendMessage(player,
                                            message);
                                }
                            } else if (from < 0.0) {
                                String message = getPlugin()
                                        .getSettingsManager().getL_MESSAGES().get("MBFineRange");
                                if (message != null) {
                                    message = getPlugin().getAPI()
                                            .formatString(message,
                                                    player.getName(),
                                                    et.getName(),
                                                    world.getName(),
                                                    (from + to) / 2, from, to,
                                                    "", "",
                                                    "mbr.user.command.check",
                                                    "", "", 0, "", "", 0);
                                    getPlugin().getAPI().sendMessage(player,
                                            message);
                                }
                            }
                        }
                    } else {
                        reward = MobBountyUtils.getDouble(rewardTest, 0.0);

                        if (getPlugin().getEconManager().getEcon() != null) {
                            if (reward > 0.0) {
                                String message = getPlugin()
                                        .getSettingsManager().getL_MESSAGES().get("MBReward");
                                if (message != null) {
                                    message = getPlugin().getAPI()
                                            .formatString(message,
                                                    player.getName(),
                                                    et.getName(),
                                                    world.getName(), reward,
                                                    reward, reward, "", "",
                                                    "mbr.user.command.check",
                                                    "", "", 0, "", "", 0);
                                    getPlugin().getAPI().sendMessage(player,
                                            message);
                                }
                            } else if (reward < 0.0) {
                                String message = getPlugin()
                                        .getSettingsManager().getL_MESSAGES().get("MBFine");
                                if (message != null) {
                                    message = getPlugin().getAPI()
                                            .formatString(message,
                                                    player.getName(),
                                                    et.getName(),
                                                    world.getName(), reward,
                                                    reward, reward, "", "",
                                                    "mbr.user.command.check",
                                                    "", "", 0, "", "", 0);
                                    getPlugin().getAPI().sendMessage(player,
                                            message);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            String message = getPlugin().getSettingsManager().getL_MESSAGES().get("NoAccess");
            if (message != null)
                getPlugin().getAPI().sendMessage(sender, message);
        }

        return true;
    }

}
