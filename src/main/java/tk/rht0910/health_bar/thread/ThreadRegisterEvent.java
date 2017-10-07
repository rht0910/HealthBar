package tk.rht0910.health_bar.thread;

import org.bukkit.Bukkit;

import tk.rht0910.health_bar.HealthBar;
import tk.rht0910.tomeito_core.utils.Log;

public class ThreadRegisterEvent implements Runnable {
	@Override
	public void run() {
		Log.info("Registering events...");
		Bukkit.getServer().getPluginManager().registerEvents(new HealthBar(), new HealthBar());
		Log.info("Events added!");
		Log.info("Completed: Register events");
	}
}
