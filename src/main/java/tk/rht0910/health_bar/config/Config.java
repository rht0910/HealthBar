package tk.rht0910.health_bar.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import tk.rht0910.health_bar.HealthBar;

public class Config {
	public static Boolean enable_healthbar_customname = null;
	//public static Boolean enable_healthbar_actionbar = null;

	/**
	 *
	 * <b>IMPORTANT: FileConfiguration is not supported.</b>
	 *
	 * @param path Configuration path
	 *
	 * @param def Default value.
	 *
	 * @return Returns requested configuration path, returning a default value if not found.
	 */
	public static Object load(String path, Object def) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(new File(HealthBar.getPlugin(HealthBar.class).getDataFolder(), "config.yml"));
		return config.get(path, def);
	}

	/**
	 * Loads all configuration.<br>
	 * <b>Recommended casting.</b>
	 */
	public static void use() {
		enable_healthbar_customname = (Boolean) load("enable_healthbar_customname", true);
		//enable_healthbar_actionbar = (Boolean) load("enable_healthbar_actionbar", false);
	}
}
