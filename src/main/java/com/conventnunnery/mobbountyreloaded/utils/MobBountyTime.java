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

public enum MobBountyTime {
	DAY("Day", 0, 10000), NIGHT("Night", 11500, 7000), SUNRISE("Sunrise",
			18500, 1500), SUNSET("Sunset", 10000, 1500);

	public static MobBountyTime getTimeFromString(String time) {
		if (time.equalsIgnoreCase("day"))
			return MobBountyTime.DAY;
		else if (time.equalsIgnoreCase("sunset"))
			return MobBountyTime.SUNSET;
		else if (time.equalsIgnoreCase("night"))
			return MobBountyTime.NIGHT;
		else if (time.equalsIgnoreCase("sunrise"))
			return MobBountyTime.SUNRISE;

		return null;
	}

	public static MobBountyTime getTimeOfDay(long time) {
		if (time >= 0 && time < 10000)
			return MobBountyTime.DAY;
		else if (time >= 10000 && time < 11500)
			return MobBountyTime.SUNSET;
		else if (time >= 11500 && time < 18500)
			return MobBountyTime.NIGHT;

		return MobBountyTime.SUNRISE;
	}

	private final int _length;

	private final String _name;

	private final int _starts;

	private MobBountyTime(final String name, final int starts, final int length) {
		_name = name;
		_starts = starts;
		_length = length;
	}

	public int getLength() {
		return _length;
	}

	public String getName() {
		return _name;
	}

	public int getStartingTime() {
		return _starts;
	}
}
