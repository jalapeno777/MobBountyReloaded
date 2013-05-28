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
import com.conventnunnery.plugins.conventlib.configuration.ConventYamlConfiguration;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class MobBountyConfigs {
    private final Map<ConfigurationFile, ConventYamlConfiguration> configurations;
    private final MobBountyReloaded plugin;

    public MobBountyConfigs(final MobBountyReloaded plugin) {
        this.plugin = plugin;

        configurations = new HashMap<ConfigurationFile, ConventYamlConfiguration>();

        loadConfig();
    }

    public MobBountyReloaded getPlugin() {
        return plugin;
    }

    private void createConfig(ConfigurationFile config) {
        ConventYamlConfiguration file = new ConventYamlConfiguration(
                new File(plugin.getDataFolder(), config.filename));
        saveDefaults(file, config);
        configurations.put(config, file);
    }

    public ConventYamlConfiguration getConfiguration(ConfigurationFile file) {
        return configurations.get(file);
    }

    /**
     * Loads the plugin's configuration files
     */
    public final void loadConfig() {
        for (ConfigurationFile file : ConfigurationFile.values()) {
            File confFile = new File(plugin.getDataFolder(), file.filename);
            if (confFile.exists()) {
                ConventYamlConfiguration config = new ConventYamlConfiguration(
                        confFile);
                config.load();
                if (needToUpdate(config, file)) {
                    plugin.getLogger().info("Backing up " + file.filename);
                    backup(file);
                    plugin.getLogger().info("Updating " + file.filename);
                    saveDefaults(config, file);
                }
                configurations.put(file, config);
            } else {
                File parentFile = confFile.getParentFile();
                if (!parentFile.exists()) {
                    boolean mkdirs = parentFile.mkdirs();
                    if (!mkdirs) {
                        continue;
                    }
                }
                createConfig(file);
            }
        }
    }

    /**
     * Saves the plugin's configs
     */
    public void saveConfig() {
        for (ConfigurationFile file : ConfigurationFile.values()) {
            if (configurations.containsKey(file)) {
                try {
                    configurations.get(file).save(
                            new File(plugin.getDataFolder(), file.filename));
                } catch (IOException e) {
                    plugin.getLogger().log(Level.WARNING,
                            "Could not save " + file.filename, e);
                }
            }
        }
    }

    private boolean needToUpdate(ConventYamlConfiguration config, ConfigurationFile file) {
        YamlConfiguration inPlugin = YamlConfiguration.loadConfiguration(plugin
                .getResource(file.filename));
        if (inPlugin == null) {
            return false;
        }
        String configVersion = config.getString("version");
        String currentVersion = inPlugin.getString("version");
        return configVersion == null || currentVersion != null && !(configVersion.equalsIgnoreCase(currentVersion));
    }

    private boolean backup(ConfigurationFile file) {
        File actualFile = new File(plugin.getDataFolder(), file.filename);
        if (!actualFile.exists()) {
            return false;
        }
        File newFile = new File(plugin.getDataFolder(), file.filename.replace(".yml", "_old.yml"));
        return actualFile.renameTo(newFile);
    }

    private void saveDefaults(ConventYamlConfiguration config,
                              ConfigurationFile file) {
        YamlConfiguration yc = ConventYamlConfiguration.loadConfiguration(plugin
                .getResource(file.filename));
        for (String key : config.getKeys(true)) {
            config.set(key, null);
        }
        config.setDefaults(yc);
        config.options().copyDefaults(true);
        switch (file) {
            case KILLSTREAK:
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
                config.set("allowedCreatures", mobs);
                break;
            case LOCALE:
                List<String> mobs1 = new ArrayList<String>();
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
                                    !creature.getName().equalsIgnoreCase("null")) { mobs1.add(creature.getName()); }
                    }
                }
                config.set("en.MBRMobs", "&7Mob: " + mobs1.toString().replace("[", "").replace("]", ""));
                config.set("en.MBWRMobs", "&7Mob: " + mobs1.toString().replace("[", "").replace("]", ""));
                List<String> envs = new ArrayList<String>();
                for (World.Environment env : World.Environment.values()) {
                    envs.add(env.name());
                }
                config.set("en.MBEMEnvs", "&7Environment: " + envs.toString().replace("[", "").replace("]", ""));
                List<String> biomes = new ArrayList<String>();
                for (Biome b : Biome.values()) {
                    biomes.add(b.name());
                }
                config.set("en.MBBMBiomes", "&7Biomes: " + biomes.toString().replace("[", "").replace("]", ""));
                break;
            case MULTIPLIERS:
                for (World.Environment env : World.Environment.values()) {
                    config.set("Environment." + env.name().toUpperCase(), 1.0);
                }
                for (Biome mbb : Biome.values()) {
                    config.set("Biome." + mbb.name(), 1.0);
                }
                break;
            case REWARDS:
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
                            config.set("Default." + et.name(),
                                    new Double(0.0));
                    }
                }
                break;
            default:
                break;
        }
        config.save();
    }

    public enum ConfigurationFile {
        GENERAL("general.yml"), KILLSTREAK("killstreak.yml"), LOCALE("locale.yml"), MULTIPLIERS("multiplier.yml"),
        REWARDS("reward.yml");
        public final String filename;

        private ConfigurationFile(String path) {
            this.filename = path;
        }
    }
}
