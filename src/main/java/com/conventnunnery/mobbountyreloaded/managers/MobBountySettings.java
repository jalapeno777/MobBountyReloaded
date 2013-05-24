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
import com.conventnunnery.mobbountyreloaded.utils.MobBountyUtils;
import com.conventnunnery.mobbountyreloaded.utils.configuration.MobBountyReloadedConfFile;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MobBountySettings {

    private final MobBountyReloaded plugin;
    private String G_LOCALE;
    private boolean G_KILLCACHE_USE;
    private long G_KILLCACHE_TIME_LIMIT;
    private boolean G_DEBUG;
    private boolean G_ALLOW_CREATIVE;
    private Map<String, String> L_MESSAGES;
    private Map<String, Double> M_ENVIRONMENT_MULTIPLIER;
    private double M_FORTUNE_MULTIPLIER;
    private Map<String, Double> M_BIOME_MULTIPLIER;
    private Map<String, Double> M_TIME_MULTIPLIER;
    private Map<String, Double> M_GROUP_MULTIPLIER;
    private Map<String, Double> M_USER_MULTIPLIER;
    private Map<String, Double> M_WORLD_MULTIPLIER;
    private Map<String, Map<String, String>> R_CREATURE_VALUES;
    private Map<String, Double> KS_KILLBONUSES;
    private List<String> KS_ALLOWED_CREATURES;

    public MobBountySettings(MobBountyReloaded plugin) {
        this.plugin = plugin;
        R_CREATURE_VALUES = new HashMap<String, Map<String, String>>();
        M_ENVIRONMENT_MULTIPLIER = new HashMap<String, Double>();
        M_TIME_MULTIPLIER = new HashMap<String, Double>();
        M_GROUP_MULTIPLIER = new HashMap<String, Double>();
        M_USER_MULTIPLIER = new HashMap<String, Double>();
        M_BIOME_MULTIPLIER = new HashMap<String, Double>();
        M_WORLD_MULTIPLIER = new HashMap<String, Double>();
        KS_KILLBONUSES = new HashMap<String, Double>();
        KS_ALLOWED_CREATURES = new ArrayList<String>();
        L_MESSAGES = new HashMap<String, String>();
        G_LOCALE = null;
        G_KILLCACHE_USE = false;
        G_KILLCACHE_TIME_LIMIT = 0;
        G_DEBUG = false;
        G_ALLOW_CREATIVE = false;
        M_FORTUNE_MULTIPLIER = 0;
        loadSettings();
    }

    public String getG_LOCALE() {
        return G_LOCALE;
    }

    public void setG_LOCALE(String g_LOCALE) {
        G_LOCALE = g_LOCALE;
    }

    public boolean isG_KILLCACHE_USE() {
        return G_KILLCACHE_USE;
    }

    public void setG_KILLCACHE_USE(boolean g_KILLCACHE_USE) {
        G_KILLCACHE_USE = g_KILLCACHE_USE;
    }

    public long getG_KILLCACHE_TIME_LIMIT() {
        return G_KILLCACHE_TIME_LIMIT;
    }

    public void setG_KILLCACHE_TIME_LIMIT(long g_KILLCACHE_TIME_LIMIT) {
        G_KILLCACHE_TIME_LIMIT = g_KILLCACHE_TIME_LIMIT;
    }

    public boolean isG_DEBUG() {
        return G_DEBUG;
    }

    public void setG_DEBUG(boolean g_DEBUG) {
        G_DEBUG = g_DEBUG;
    }

    public boolean isG_ALLOW_CREATIVE() {
        return G_ALLOW_CREATIVE;
    }

    public void setG_ALLOW_CREATIVE(boolean g_ALLOW_CREATIVE) {
        G_ALLOW_CREATIVE = g_ALLOW_CREATIVE;
    }

    public Map<String, String> getL_MESSAGES() {
        return L_MESSAGES;
    }

    public void setL_MESSAGES(Map<String, String> l_MESSAGES) {
        L_MESSAGES = l_MESSAGES;
    }

    public Map<String, Double> getM_ENVIRONMENT_MULTIPLIER() {
        return M_ENVIRONMENT_MULTIPLIER;
    }

    public void setM_ENVIRONMENT_MULTIPLIER(Map<String, Double> m_ENVIRONMENT_MULTIPLIER) {
        M_ENVIRONMENT_MULTIPLIER = m_ENVIRONMENT_MULTIPLIER;
    }

    public double getM_FORTUNE_MULTIPLIER() {
        return M_FORTUNE_MULTIPLIER;
    }

    public void setM_FORTUNE_MULTIPLIER(double m_FORTUNE_MULTIPLIER) {
        M_FORTUNE_MULTIPLIER = m_FORTUNE_MULTIPLIER;
    }

    public Map<String, Double> getM_BIOME_MULTIPLIER() {
        return M_BIOME_MULTIPLIER;
    }

    public void setM_BIOME_MULTIPLIER(Map<String, Double> m_BIOME_MULTIPLIER) {
        M_BIOME_MULTIPLIER = m_BIOME_MULTIPLIER;
    }

    public Map<String, Double> getM_TIME_MULTIPLIER() {
        return M_TIME_MULTIPLIER;
    }

    public void setM_TIME_MULTIPLIER(Map<String, Double> m_TIME_MULTIPLIER) {
        M_TIME_MULTIPLIER = m_TIME_MULTIPLIER;
    }

    public Map<String, Double> getM_GROUP_MULTIPLIER() {
        return M_GROUP_MULTIPLIER;
    }

    public void setM_GROUP_MULTIPLIER(Map<String, Double> m_GROUP_MULTIPLIER) {
        M_GROUP_MULTIPLIER = m_GROUP_MULTIPLIER;
    }

    public Map<String, Double> getM_USER_MULTIPLIER() {
        return M_USER_MULTIPLIER;
    }

    public void setM_USER_MULTIPLIER(Map<String, Double> m_USER_MULTIPLIER) {
        M_USER_MULTIPLIER = m_USER_MULTIPLIER;
    }

    public Map<String, Double> getM_WORLD_MULTIPLIER() {
        return M_WORLD_MULTIPLIER;
    }

    public void setM_WORLD_MULTIPLIER(Map<String, Double> m_WORLD_MULTIPLIER) {
        M_WORLD_MULTIPLIER = m_WORLD_MULTIPLIER;
    }

    public Map<String, Double> getKS_KILLBONUSES() {
        return KS_KILLBONUSES;
    }

    public void setKS_KILLBONUSES(Map<String, Double> KS_KILLBONUSES) {
        this.KS_KILLBONUSES = KS_KILLBONUSES;
    }

    public List<String> getKS_ALLOWED_CREATURES() {
        return KS_ALLOWED_CREATURES;
    }

    public void setKS_ALLOWED_CREATURES(List<String> KS_ALLOWED_CREATURES) {
        this.KS_ALLOWED_CREATURES = KS_ALLOWED_CREATURES;
    }

    public Map<String, Map<String, String>> getR_CREATURE_VALUES() {
        return R_CREATURE_VALUES;
    }

    public void setR_CREATURE_VALUES(Map<String, Map<String, String>> r_CREATURE_VALUES) {
        R_CREATURE_VALUES = r_CREATURE_VALUES;
    }

    public MobBountyReloaded getPlugin() {
        return plugin;
    }

    private void saveGeneralSettings() {
        getPlugin().getConfigManager().setProperty(MobBountyReloadedConfFile.GENERAL, "locale", G_LOCALE);
        getPlugin().getConfigManager().setProperty(MobBountyReloadedConfFile.GENERAL, "killCache.use", G_KILLCACHE_USE);
        getPlugin().getConfigManager()
                .setProperty(MobBountyReloadedConfFile.GENERAL, "killCache.timeLimt", G_KILLCACHE_TIME_LIMIT);
        getPlugin().getConfigManager().setProperty(MobBountyReloadedConfFile.GENERAL, "debugMode", G_DEBUG);
        getPlugin().getConfigManager()
                .setProperty(MobBountyReloadedConfFile.GENERAL, "creativeEarning.allow", G_ALLOW_CREATIVE);
    }

    private void saveKillstreakSettings() {
        for (Map.Entry<String, Double> entry : KS_KILLBONUSES.entrySet()) {
            getPlugin().getConfigManager()
                    .setProperty(MobBountyReloadedConfFile.KILLSTREAK, "KillBonus." + entry.getKey(), entry.getValue());
        }
        getPlugin().getConfigManager()
                .setProperty(MobBountyReloadedConfFile.KILLSTREAK, "allowedCreatures", KS_ALLOWED_CREATURES);
    }

    private void saveRewardSettings() {
        for (Map.Entry<String, Map<String, String>> entry : R_CREATURE_VALUES.entrySet()) {
            for (Map.Entry<String, String> entry1 : entry.getValue().entrySet()) {
                getPlugin().getConfigManager()
                        .setProperty(MobBountyReloadedConfFile.REWARDS, entry.getKey() + "." + entry1.getKey(),
                                entry1.getValue());
            }
        }
    }

    private void saveLocaleSettings() {
        for (Map.Entry<String, String> entry : L_MESSAGES.entrySet()) {
            getPlugin().getConfigManager()
                    .setProperty(MobBountyReloadedConfFile.LOCALE, G_LOCALE + "." + entry.getKey(),
                            entry.getValue().replace('\u00A7', '&'));
        }
    }

    private void saveMultiplierSettings() {
        getPlugin().getConfigManager()
                .setProperty(MobBountyReloadedConfFile.MULTIPLIERS, "fortuneToolsMultiplier", M_FORTUNE_MULTIPLIER);
        for (Map.Entry<String, Double> entry : M_BIOME_MULTIPLIER.entrySet()) {
            getPlugin().getConfigManager()
                    .setProperty(MobBountyReloadedConfFile.MULTIPLIERS, "Biome." + entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Double> entry : M_WORLD_MULTIPLIER.entrySet()) {
            getPlugin().getConfigManager()
                    .setProperty(MobBountyReloadedConfFile.MULTIPLIERS, "World." + entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Double> entry : M_USER_MULTIPLIER.entrySet()) {
            getPlugin().getConfigManager()
                    .setProperty(MobBountyReloadedConfFile.MULTIPLIERS, "User." + entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Double> entry : M_GROUP_MULTIPLIER.entrySet()) {
            getPlugin().getConfigManager()
                    .setProperty(MobBountyReloadedConfFile.MULTIPLIERS, "Group." + entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Double> entry : M_ENVIRONMENT_MULTIPLIER.entrySet()) {
            getPlugin().getConfigManager()
                    .setProperty(MobBountyReloadedConfFile.MULTIPLIERS, "Environment." + entry.getKey(),
                            entry.getValue());
        }
        for (Map.Entry<String, Double> entry : M_TIME_MULTIPLIER.entrySet()) {
            getPlugin().getConfigManager()
                    .setProperty(MobBountyReloadedConfFile.MULTIPLIERS, "Time." + entry.getKey(), entry.getValue());
        }
    }

    public void saveSettings() {
        saveGeneralSettings();
        saveKillstreakSettings();
        saveRewardSettings();
        saveLocaleSettings();
        saveMultiplierSettings();
    }

    private void loadGeneralSettings() {
        G_LOCALE = getPlugin().getConfigManager().getProperty(
                MobBountyReloadedConfFile.GENERAL, "locale");
        G_KILLCACHE_USE = MobBountyUtils.getBoolean(
                getPlugin().getConfigManager().getProperty(
                        MobBountyReloadedConfFile.GENERAL, "killCache.use"),
                false);
        G_KILLCACHE_TIME_LIMIT = MobBountyUtils.getInt(
                getPlugin().getConfigManager().getProperty(
                        MobBountyReloadedConfFile.GENERAL, "killCache.timeLimit"),
                30000);
        G_DEBUG = MobBountyUtils.getBoolean(getPlugin().getConfigManager()
                .getProperty(MobBountyReloadedConfFile.GENERAL, "debugMode"),
                false);
        G_ALLOW_CREATIVE = MobBountyUtils.getBoolean(
                getPlugin().getConfigManager().getProperty(
                        MobBountyReloadedConfFile.GENERAL,
                        "creativeEarning.allow"), false);
    }

    private void loadKillstreakSettings() {
        KS_KILLBONUSES.clear();
        FileConfiguration fc = getPlugin().getConfigManager()
                .getFileConfiguration(MobBountyReloadedConfFile.KILLSTREAK);
        if (fc.isConfigurationSection("KillBonus")) {
            ConfigurationSection cs = fc.getConfigurationSection("KillBonus");
            for (String s : cs.getKeys(false)) {
                KS_KILLBONUSES.put(s, fc.getDouble(s.replace("'", "")));
            }
        }
        KS_ALLOWED_CREATURES.clear();
        KS_ALLOWED_CREATURES = getPlugin().getConfigManager().getPropertyList(
                MobBountyReloadedConfFile.KILLSTREAK, "allowedCreatures");
    }

    private void loadLocaleSettings() {
        L_MESSAGES.clear();
        Set<String> keys = new HashSet<String>();
        FileConfiguration fc = getPlugin().getConfigManager().getFileConfiguration(MobBountyReloadedConfFile.LOCALE);
        if (fc.isConfigurationSection("en")) {
            ConfigurationSection cs = fc.getConfigurationSection("en");
            keys = cs.getKeys(false);
        }
        for (String key : keys) {
            L_MESSAGES.put(key, getPlugin().getLocaleManager().getString(key));
        }
    }

    private void loadMultiplierSettings() {
        M_FORTUNE_MULTIPLIER = MobBountyUtils.getDouble(
                getPlugin().getConfigManager().getProperty(
                        MobBountyReloadedConfFile.MULTIPLIERS,
                        "fortuneToolsMultiplier"), 1.0);
        FileConfiguration mfc = getPlugin().getConfigManager()
                .getFileConfiguration(MobBountyReloadedConfFile.MULTIPLIERS);
        M_ENVIRONMENT_MULTIPLIER.clear();
        M_TIME_MULTIPLIER.clear();
        M_GROUP_MULTIPLIER.clear();
        M_USER_MULTIPLIER.clear();
        M_BIOME_MULTIPLIER.clear();
        M_WORLD_MULTIPLIER.clear();
        if (mfc.isConfigurationSection("Environment")) {
            ConfigurationSection ecs = mfc
                    .getConfigurationSection("Environment");
            for (String s : ecs.getKeys(false)) {
                M_ENVIRONMENT_MULTIPLIER.put(s.toUpperCase(),
                        ecs.getDouble(s));
            }
        }
        if (mfc.isConfigurationSection("Time")) {
            ConfigurationSection tcs = mfc.getConfigurationSection("Time");
            for (String s : tcs.getKeys(false)) {
                M_TIME_MULTIPLIER.put(s, tcs.getDouble(s));
            }
        }
        if (mfc.isConfigurationSection("Group")) {
            ConfigurationSection gcs = mfc.getConfigurationSection("Group");
            for (String s : gcs.getKeys(false)) {
                M_GROUP_MULTIPLIER.put(s, gcs.getDouble(s));
            }
        }
        if (mfc.isConfigurationSection("User")) {
            ConfigurationSection ucs = mfc.getConfigurationSection("User");
            for (String s : ucs.getKeys(false)) {
                M_USER_MULTIPLIER.put(s, ucs.getDouble(s));
            }
        }
        if (mfc.isConfigurationSection("Biome")) {
            ConfigurationSection tbs = mfc.getConfigurationSection("Biome");
            for (String s : tbs.getKeys(false)) {
                M_TIME_MULTIPLIER.put(s, tbs.getDouble(s));
            }
        }
        if (mfc.isConfigurationSection("World")) {
            ConfigurationSection wcs = mfc.getConfigurationSection("World");
            for (String s : wcs.getKeys(false)) {
                M_WORLD_MULTIPLIER.put(s, wcs.getDouble(s));
            }
        }
    }

    private void loadRewardSettings() {
        R_CREATURE_VALUES.clear();
        FileConfiguration fc = getPlugin().getConfigManager()
                .getFileConfiguration(MobBountyReloadedConfFile.REWARDS);
        for (String s : fc.getKeys(false)) {
            Map<String, String> map = new HashMap<String, String>();
            ConfigurationSection cs = fc.getConfigurationSection(s);
            for (String s2 : cs.getKeys(false)) {
                if (cs.isConfigurationSection(s2))
                    continue;
                map.put(s2, cs.getString(s2));
                getPlugin().getLogger().info(s + " : " + s2 + " : " + cs.getString(s2));
            }
            R_CREATURE_VALUES.put(s, map);
        }
    }

    public void loadSettings() {
        loadGeneralSettings();
        loadLocaleSettings();
        loadRewardSettings();
        loadMultiplierSettings();
        loadKillstreakSettings();
    }

    public void setCreatureReward(String world, String entityType, String reward) {
        Map<String, String> map2 = R_CREATURE_VALUES.get(world);
        if (map2 == null) {
            map2 = new HashMap<String, String>();
        }
        map2.put(entityType, reward);
        R_CREATURE_VALUES.put(world, map2);
    }
}
