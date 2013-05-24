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
import com.conventnunnery.mobbountyreloaded.commands.MBCheck;
import com.conventnunnery.mobbountyreloaded.commands.MBCmd;
import com.conventnunnery.mobbountyreloaded.commands.MBEnvMulti;
import com.conventnunnery.mobbountyreloaded.commands.MBGeneral;
import com.conventnunnery.mobbountyreloaded.commands.MBGroupMulti;
import com.conventnunnery.mobbountyreloaded.commands.MBLoad;
import com.conventnunnery.mobbountyreloaded.commands.MBReward;
import com.conventnunnery.mobbountyreloaded.commands.MBSave;
import com.conventnunnery.mobbountyreloaded.commands.MBTimeMulti;
import com.conventnunnery.mobbountyreloaded.commands.MBUserMulti;
import com.conventnunnery.mobbountyreloaded.commands.MBWorldReward;

public class MobBountyCommand {

	private MobBountyReloaded plugin;

	public MobBountyCommand(final MobBountyReloaded instance) {
		setPlugin(instance);
		getPlugin().getCommand("mobbountyload").setExecutor(
				new MBLoad(getPlugin()));
		getPlugin().getCommand("mobbountysave").setExecutor(
				new MBSave(getPlugin()));
		getPlugin().getCommand("mobbountycheck").setExecutor(
				new MBCheck(getPlugin()));
		getPlugin().getCommand("mobbounty").setExecutor(new MBCmd(getPlugin()));
		getPlugin().getCommand("mobbountyreward").setExecutor(
				new MBReward(getPlugin()));
		getPlugin().getCommand("mobbountyworldreward").setExecutor(
				new MBWorldReward(getPlugin()));
		getPlugin().getCommand("mobbountygroupmulti").setExecutor(
				new MBGroupMulti(getPlugin()));
		getPlugin().getCommand("mobbountyusermulti").setExecutor(
				new MBUserMulti(getPlugin()));
		getPlugin().getCommand("mobbountytimemulti").setExecutor(
				new MBTimeMulti(getPlugin()));
		getPlugin().getCommand("mobbountyenvmulti").setExecutor(
				new MBEnvMulti(getPlugin()));
		getPlugin().getCommand("mobbountygeneral").setExecutor(
				new MBGeneral(getPlugin()));
	}

	public MobBountyReloaded getPlugin() {
		return plugin;
	}

	public void setPlugin(final MobBountyReloaded plugin) {
		this.plugin = plugin;
	}

}
