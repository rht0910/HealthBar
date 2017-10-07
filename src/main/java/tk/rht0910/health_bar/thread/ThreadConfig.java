package tk.rht0910.health_bar.thread;

import tk.rht0910.health_bar.HealthBar;
import tk.rht0910.health_bar.config.Config;
import tk.rht0910.tomeito_core.utils.Log;

public class ThreadConfig implements Runnable {
	HealthBar hb = null;

	@Override
	public void run() {
		Log.info("Loading configuration...");
		Config.use();
		hb = HealthBar.getPlugin(HealthBar.class);
		hb.getConfig().set("enable_healthbar_customname", Config.enable_healthbar_customname);
		//hb.getConfig().set("enable_healthbar_actionbar", enable_healthbar_actionbar);
		hb.getConfig().options().copyDefaults(true);
		hb.saveConfig();
		hb.reloadConfig();
		Log.info("Completed: Load & Save config");
	}
}
