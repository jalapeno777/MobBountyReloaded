/*
 * Copyright (c) 2013. ToppleTheNun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.conventnunnery.mobbountyreloaded.utils;

import org.bukkit.Bukkit;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MobBountyUtils {

	protected MobBountyUtils() {

	}

	public static boolean containsIgnoreCase(List<String> l, String s) {
		for (String aL : l) {
			if (aL.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	public static boolean getBoolean(String string, boolean fallback) {
		boolean b;
		try {
			b = Boolean.parseBoolean(string);
		} catch (Exception e) {
			b = fallback;
		}
		return b;
	}

	public static double getDouble(String string, double fallBack) {
		double d;
		try {
			d = Double.parseDouble(string);
		} catch (Exception e) {
			d = fallBack;
		}
		return d;
	}

	public static int getInt(String string, int fallBack) {
		int i;
		try {
			i = (int) Math.round(getDouble(string, fallBack));
		} catch (Exception e) {
			i = fallBack;
		}
		return i;
	}

	public static int handleRange(int i1, int i2) {
		Random rand = new Random();
		int result;
		int range;
		int loc;
		if ((double) i1 > (double) i2) {
			range = (int) (((double) i1 * 100) - ((double) i2 * 100));
			loc = (int) ((double) i2 * 100);
		} else {
			range = (int) (((double) i2 * 100) - ((double) i1 * 100));
			loc = (int) ((double) i1 * 100);
		}
		result = (loc + rand.nextInt(range + 1)) / 100;
		return result;
	}

	public static int handleRange(String string, String splitter, int fallback) {
		Random rand = new Random();
		int result;
		int range;
		int loc;
		String[] splitString = string.split(splitter);
		if (splitString.length > 1) {
			if (getDouble(splitString[0], 0) > getDouble(splitString[1], 0)) {
				range = (int) ((getDouble(splitString[0], 0) * 100) - (getDouble(
						splitString[1], 0) * 100));
				loc = (int) (getDouble(splitString[1], 0) * 100);
			} else {
				range = (int) ((getDouble(splitString[1], 0) * 100) - (getDouble(
						splitString[0], 0) * 100));
				loc = (int) (getDouble(splitString[0], 0) * 100);
			}
			result = (loc + rand.nextInt(range + 1)) / 100;
			return result;
		}
		return fallback;
	}

	public static boolean hasPlugin(String pluginName) {
		return Bukkit.getServer().getPluginManager().getPlugin(pluginName) != null;
	}

	public static List<String> removeIgnoreCase(List<String> l, String s) {
		Iterator<String> iter = l.iterator();
		while (iter.hasNext()) {
			if (iter.next().equalsIgnoreCase(s)) {
				iter.remove();
				break;
			}
		}
		return l;
	}
}
