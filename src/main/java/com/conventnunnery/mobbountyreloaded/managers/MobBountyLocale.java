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

public class MobBountyLocale {
	private final MobBountyReloaded _plugin;

	public MobBountyLocale(MobBountyReloaded plugin) {
		_plugin = plugin;
	}

	/**
	 * Gets a string from locale.yml
	 *
	 * @param key Key to look for
	 * @return String requested
	 */
	public String getString(String key) {
		String locale = _plugin.getConfigManager().getConfiguration(MobBountyConfigs.ConfigurationFile
                .LOCALE).getString("locale");

		if (locale != null)
			locale = locale.toLowerCase();
		else
			locale = "en";

		String value = _plugin.getConfigManager().getConfiguration(MobBountyConfigs.ConfigurationFile
                .LOCALE).getString(locale + "." + key);

		if (value == null)
			value = _plugin.getConfigManager().getConfiguration(MobBountyConfigs.ConfigurationFile
                    .LOCALE).getString("en." + key);

		if (value != null)
			value = value.replace('&', '\u00A7').replace("\u00A7\u00A7", "&");

		return value;
	}
}
