/*
 * Copyright (c) 2013. ToppleTheNun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of
 * the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.conventnunnery.mobbountyreloaded.managers;

import com.conventnunnery.mobbountyreloaded.MobBountyReloaded;
import com.conventnunnery.mobbountyreloaded.utils.configuration.CommentedYamlConfiguration;
import com.conventnunnery.mobbountyreloaded.utils.configuration.MobBountyReloadedConfFile;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobBountyConfigs {
    private final Map<MobBountyReloadedConfFile, YamlConfiguration> _configurations;
    private final MobBountyReloaded _plugin;

    public MobBountyConfigs(final MobBountyReloaded plugin) {
        _plugin = plugin;

        _configurations = new HashMap<MobBountyReloadedConfFile, YamlConfiguration>();

        loadConfig();
    }

    private void createConfig(final MobBountyReloadedConfFile config,
                              final File file) {
        switch (config) {
            case GENERAL:
                CommentedYamlConfiguration generalConf = new CommentedYamlConfiguration();
                getPlugin().getAPI().log("Creating General.yml");
                generalConf.addComment("locale",
                        "The locale version to use in locale.yml");
                generalConf.set("locale", "en");
                generalConf.addComment("killCache.use",
                        "Whether or not to use killCache");
                generalConf.set("killCache.use", false);
                generalConf.addComment("killCache.timeLimit",
                        "Milliseconds between earning updates");
                generalConf.set("killCache.timeLimit", 30000);
                generalConf.addComment("debugMode",
                        "Whether or not the plugin is in debugMode");
                generalConf.set("debugMode", false);
                generalConf.addComment("creativeEarning",
                        "Earning money in creative mode");
                generalConf.set("creativeEarning.allow", false);
                try {
                    generalConf.save(file);
                } catch (IOException ignored) {
                }

                _configurations.put(config, generalConf);
                break;

            case LOCALE:
                CommentedYamlConfiguration localeConf = new CommentedYamlConfiguration();
                getPlugin().getAPI().log("Creating Locale.yml");
                localeConf.addComment("en",
                        "You may use various symbols in your strings here",
                        "and the plugin will automatically convert them.",
                        "  %P - the player who killed",
                        "  %M - the type of mob killed",
                        "  %A - the reward/fine/amount",
                        "  %W - the world it occured in",
                        "  %1 - the first number in a reward range",
                        "  %2 - the second number in a reward range",
                        "  %C - command", "  %H - help for command",
                        "  %D - permission", "  %E - environment",
                        "  %K - kill cache amount", "  %T - kill cache time",
                        "  %S - setting changed", "  %V - value of setting",
                        "  %X - player killstreak value", "  %B - biome");
                localeConf.set("en.Awarded",
                        "&2You have been awarded &F%A &2for killing a &F%M&2.");
                localeConf
                        .set("en.CacheAwarded",
                                "&2You have been awarded &F%A &2for killing &F%K &2mobs in &F%T &2seconds.");
                localeConf.set("en.Fined",
                        "&4You have been fined &F%A &4for killing a &F%M&4.");
                localeConf
                        .set("en.CacheFined",
                                "&4You have been fined &F%A &4for killing &F%K &4mobs in &F%T &4seconds.");
                localeConf.set("en.NoAccess",
                        "&CYou do not have access to that command.");
                localeConf
                        .set("en.Killstreak",
                                "&2You earned &F%A&2 for having a killstreak of &F%X&2!");
                localeConf.set("en.MBReward", "&2%M : &F%A");
                localeConf.set("en.MBRewardRange", "&2%M : &F%1 - %2");
                localeConf.set("en.MBFine", "&4%M : &F%A");
                localeConf.set("en.MBFineRange", "&4%M : &F%A - %2");
                localeConf.set("en.MBInfo", "&d%C &F- &2%H");
                localeConf.set("en.MBGChange",
                        "&2General setting &F%S &2has been changed to &F%V&2.");
                localeConf.set("en.MBGUsage",
                        "&CUsage: /%C [property] <amount>");
                localeConf
                        .set("en.MBGProperty",
                                "&7Property: locale, debug, usekillcache, killcachetimelimit, allowcreative");
                localeConf
                        .set("en.MBRChange",
                                "&2Default reward/fine for mob &F%M &2has been changed to &F%A&2.");
                localeConf.set("en.MBRUsage", "&CUsage: /%C [mob] <amount>");
                localeConf.set("en.MBRDeny", "&CYou cannot set for that mob.");
                List<String> mobs = new ArrayList<String>();
                for (EntityType creature : EntityType.values()) {
                    switch (creature) {
                        case ARROW:
                        case BOAT:
                        case COMPLEX_PART:
                        case DROPPED_ITEM:
                        case EGG:
                        case ENDER_CRYSTAL:
                        case ENDER_PEARL:
                        case ENDER_SIGNAL:
                        case EXPERIENCE_ORB:
                        case FALLING_BLOCK:
                        case FIREBALL:
                        case FIREWORK:
                        case FISHING_HOOK:
                        case ITEM_FRAME:
                        case LIGHTNING:
                        case MINECART:
                        case MINECART_FURNACE:
                        case MINECART_HOPPER:
                        case MINECART_CHEST:
                        case MINECART_MOB_SPAWNER:
                        case MINECART_TNT:
                        case PAINTING:
                        case PRIMED_TNT:
                        case SMALL_FIREBALL:
                        case SNOWBALL:
                        case SPLASH_POTION:
                        case THROWN_EXP_BOTTLE:
                        case WEATHER:
                        case WITHER_SKULL:
                            continue;
                        default:
                            if (creature.getName() != null && !creature.getName().equalsIgnoreCase("") &&
                                    !creature.getName().equalsIgnoreCase("null")) { mobs.add(creature.getName()); }
                    }
                }
                localeConf.set("en.MBRMobs", "&7Mob: "
                        + mobs.toString().replace("[", "").replace("]", ""));
                localeConf
                        .set("en.MBWRChange",
                                "&2Reward for mob &F%M &2in world &F%W &2has been changed to &F%A&2.");
                localeConf
                        .set("en.MBWRReset",
                                "&2Reward for mob &F%M &2in world &F%W &2has been reset to default.");
                localeConf.set("en.MBWRUsage",
                        "&CUsage: /%C <world> <mob> [amount]");
                localeConf.set("en.MBWRWorlds", "&7World: %W");
                localeConf.set("en.MBWRMobs", "&7Mob: "
                        + mobs.toString().replace("[", "").replace("]", ""));
                localeConf.set("en.MBWRDeny", "&CYou cannot set for that mob.");
                localeConf
                        .set("en.MBEMChange",
                                "&2Multiplier for the &F%E &2environment has been changed to &F%A&2.");
                localeConf.set("en.MBEMUsage",
                        "&CUsage: /%C <environment> [amount]");
                List<String> envs = new ArrayList<String>();
                for (Environment env : Environment.values()) {
                    envs.add(env.name());
                }
                localeConf.set("en.MBEMEnvs", "&7Environment: "
                        + envs.toString().replace("[", "").replace("]", ""));
                localeConf.set("en.MBBMChange", "&2Multiplier for the &F%B &2biome has been changed to &F%A&2.");
                localeConf.set("en.MBBMUsage", "&CUsage: /%C <biome> [amount]");
                List<String> biomes = new ArrayList<String>();
                for (Biome b : Biome.values()) {
                    biomes.add(b.name());
                }
                localeConf.set("en.MBBMBiomes", "&7Biomes: " + biomes.toString().replace("[", "").replace("]", ""));
                localeConf
                        .set("en.MBTMChange",
                                "&2Multiplier during the &F%T &2has been changed to &F%A&2.");
                localeConf.set("en.MBTMUsage", "&CUsage: /%C <time> [amount]");
                localeConf.set("en.MBTMTimes",
                        "&7Time: Day, Sunset, Night, Sunrise");
                localeConf
                        .set("en.MBWMChange",
                                "&2Multiplier for world &F%W &2has been changed to &F%A&2.");
                localeConf.set("en.MBWMUsage", "&CUsage: /%C <world> [amount]");
                localeConf.set("en.MBWMWorlds", "&7World: %W");
                localeConf
                        .set("en.MBGMChange",
                                "&2Multiplier for group &F%W &2has been changed to &F%A&2.");
                localeConf.set("en.MBGMUsage", "&CUsage: /%C <group> [amount]");
                localeConf
                        .set("en.MBUMChange",
                                "&2Multiplier for user &F%P &2has been changed to &F%A&2.");
                localeConf.set("en.MBUMUsage", "&CUsage: /%C <player> <amount>");
                localeConf.set("en.MBSSaved",
                        "&2MobBountyReloaded config has been saved.");
                localeConf.set("en.MBLLoaded",
                        "&2MobBountyReloaded config has been loaded.");
                localeConf.set("en.DebugHasPermission",
                        "&F%P &2has permission: &F%D");
                localeConf.set("en.DebugLacksPermission",
                        "&F%P &4lacks permission: &F%D");
                localeConf.set("en.DebugGeneral", "%M");
                try {
                    localeConf.save(file);
                } catch (IOException ignored) {
                }

                _configurations.put(config, localeConf);
                break;

            case MULTIPLIERS:
                CommentedYamlConfiguration multiplierConfig = new CommentedYamlConfiguration();
                getPlugin().getAPI().log("Creating Multipliers.yml");
                multiplierConfig.addComment("Environment",
                        "These multipliers are used for determining",
                        "rewards based on world type.");
                for (Environment env : Environment.values()) {
                    multiplierConfig.set("Environment."
                            + env.name().toUpperCase(), 1.0);
                }
                multiplierConfig.addComment("Time",
                        "These multipliers are used for determining",
                        "rewards based on the time in the world.");
                multiplierConfig.set("Time.Day", 1.0);
                multiplierConfig.set("Time.Sunset", 1.0);
                multiplierConfig.set("Time.Night", 1.0);
                multiplierConfig.set("Time.Sunrise", 1.0);
                multiplierConfig.addComment("Group",
                        "These multipliers are used for determining",
                        "rewards for a group.");
                multiplierConfig.set("Group.Default", 1.0);
                multiplierConfig.addComment("User",
                        "These multipliers are used for determining",
                        "rewards for a user.");
                multiplierConfig.set("User.Default", 1.0);
                multiplierConfig.addComment("World",
                        "These multipliers are used for determining",
                        "rewards for a world.");
                multiplierConfig.set("World.world", 1.0);
                multiplierConfig.addComment("fortuneToolsMultiplier",
                        "This multiplier is used for determining",
                        "rewards when using fortune tools.");
                multiplierConfig.set("fortuneToolsMultiplier", 1.0);
                multiplierConfig
                        .addComment("Biome", "These multipliers are used for determining", "rewards for a biome.");
                for (Biome mbb : Biome.values()) {
                    multiplierConfig
                            .set("Biome." + mbb.name(), 1.0);
                }
                try {
                    multiplierConfig.save(file);
                } catch (IOException ignored) {
                }

                _configurations.put(config, multiplierConfig);
                break;

            case REWARDS:
                CommentedYamlConfiguration rewardConfig = new CommentedYamlConfiguration();
                getPlugin().getAPI().log("Creating Rewards.yml");
                rewardConfig
                        .addComment(
                                "Default",
                                "You can do some cool things with the MBR reward system.",
                                "If you add a colon (:) to your reward, it will produce a range.",
                                "Having '28.5:30.0' will produce a reward between those two numbers.",
                                "If you'd like to make a creature not give a reward, set its value to '0.0'.",
                                "",
                                "To give a value to a world, give it a format like so:",
                                "worldnamehere:", "  creaturenamehere: valuehere");
                for (EntityType et : EntityType.values()) {
                    switch (et) {
                        case ARROW:
                        case BOAT:
                        case COMPLEX_PART:
                        case DROPPED_ITEM:
                        case EGG:
                        case ENDER_CRYSTAL:
                        case ENDER_PEARL:
                        case ENDER_SIGNAL:
                        case EXPERIENCE_ORB:
                        case FALLING_BLOCK:
                        case FIREBALL:
                        case FIREWORK:
                        case FISHING_HOOK:
                        case ITEM_FRAME:
                        case LIGHTNING:
                        case MINECART:
                        case MINECART_FURNACE:
                        case MINECART_HOPPER:
                        case MINECART_CHEST:
                        case MINECART_MOB_SPAWNER:
                        case MINECART_TNT:
                        case PAINTING:
                        case PRIMED_TNT:
                        case SMALL_FIREBALL:
                        case SNOWBALL:
                        case SPLASH_POTION:
                        case THROWN_EXP_BOTTLE:
                        case WEATHER:
                        case WITHER_SKULL:
                            continue;
                        default:
                            rewardConfig.set("Default." + et.name(),
                                    new Double(0.0));
                    }
                }
                try {
                    rewardConfig.save(file);
                } catch (IOException ignored) {
                }

                _configurations.put(config, rewardConfig);
                break;

            case KILLSTREAK:
                CommentedYamlConfiguration killConf = new CommentedYamlConfiguration();
                getPlugin().getAPI().log("Creating Killstreak.yml");
                killConf.addComment(
                        "KillBonus",
                        "Killstreaks have changed as of v302. Kill bonuses are",
                        "now separate from normal rewards. As such, multiplying is",
                        "no longer an option. You can, however, still specify how much",
                        "each killstreak is worth.");
                killConf.set("KillBonus.5", 5.0);
                killConf.set("KillBonus.10", 10.0);
                String[] allowedCreatures = {"Bat", "Blaze", "CaveSpider",
                        "Chicken", "Cow", "Creeper", "ElectrifiedCreeper",
                        "Enderdragon", "Enderman", "Ghast", "Giant",
                        "IronGolem", "MagmaCube", "Monster", "Mooshroom",
                        "Ocelot", "Pig", "PigZombie", "Player", "SelfTamedCat",
                        "SelfTamedWolf", "Sheep", "Silverfish", "Skeleton",
                        "Slime", "SnowGolem", "Spider", "Squid", "TamedCat",
                        "TamedWolf", "Unknown", "Villager", "Witch", "Wither",
                        "Wolf", "Zombie"};
                killConf.set("allowedCreatures",
                        Arrays.asList(allowedCreatures));
                try {
                    killConf.save(file);
                } catch (IOException ignored) {
                }

                _configurations.put(config, killConf);
                break;
        }

    }

    public FileConfiguration getFileConfiguration(MobBountyReloadedConfFile file) {
        return _configurations.get(file);
    }

    public MobBountyReloaded getPlugin() {
        return _plugin;
    }

    /**
     * Gets a value for path in file
     *
     * @param file File to search in
     * @param path Path to search for
     * @return Value contained by path
     */
    public String getProperty(final MobBountyReloadedConfFile file,
                              final String path) {
        FileConfiguration conf = _configurations.get(file);

        if (conf != null) {
            String prop = conf.getString(path, "NULL");

            if (!prop.equalsIgnoreCase("NULL")) { return prop; }
            conf.set(path, null);
        }

        return null;
    }

    /**
     * Gets a value for path in file
     *
     * @param file File to search in
     * @param path Path to search for
     * @return Values contained by path
     */
    public List<String> getPropertyList(final MobBountyReloadedConfFile file,
                                        final String path) {
        FileConfiguration conf = _configurations.get(file);

        if (conf != null) {
            List<String> prop = conf.getStringList(path);
            if (!prop.contains("NULL")) { return prop; }
            conf.set(path, null);
        }

        return null;
    }

    /**
     * Loads the plugin's configuration files
     */
    public void loadConfig() {
        for (MobBountyReloadedConfFile file : MobBountyReloadedConfFile
                .values()) {
            File confFile = new File(file.getPath());

            if (confFile.exists()) {
                if (_configurations.containsKey(file)) {
                    _configurations.remove(file);
                }

                YamlConfiguration conf = YamlConfiguration
                        .loadConfiguration(confFile);
                _configurations.put(file, conf);
            } else {
                File parentFile = confFile.getParentFile();
                if (!parentFile.exists()) {
                    boolean mkDirs = true;
                    try {
                        mkDirs = parentFile.mkdirs();
                    } catch (SecurityException ignored) {
                    }
                    if (!mkDirs) {
                        continue;
                    }
                }
                createConfig(file, confFile);
            }
        }

    }

    /**
     * Saves the plugin's configs
     */
    public void saveConfig() {
        for (MobBountyReloadedConfFile file : MobBountyReloadedConfFile
                .values()) {
            if (_configurations.containsKey(file)) {
                try {
                    _configurations.get(file).save(new File(file.getPath()));
                } catch (IOException ignored) {

                }
            }
        }
    }

    /**
     * Sets path to value in file
     *
     * @param file  File to set in
     * @param path  Path to set
     * @param value Value to set
     * @return if completed
     */
    public boolean setProperty(final MobBountyReloadedConfFile file,
                               final String path, final Object value) {
        FileConfiguration conf = _configurations.get(file);

        if (conf != null) {
            conf.set(path, value);
            try {
                conf.save(new File(file.getPath()));
            } catch (IOException ignored) {

            }
            return true;
        }

        return false;
    }
}
