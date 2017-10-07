// IMPORTANT: The licence notation is under the file.

package tk.rht0910.health_bar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import tk.rht0910.health_bar.thread.EventonDamaged;
import tk.rht0910.tomeito_core.utils.Log;

public class HealthBar extends JavaPlugin implements Listener {

	@Override
	public void onLoad() {
		Log.info("Loaded HealthBar!");
	}

	@Override
	public void onEnable() {
		Log.info("Tasks will be run as asynchronous task.");
		Log.info("Tasks:");
		Log.info(" - Register events");
		Log.info(" - Load & Save config");
		Log.info("Running Registering events(sync mode!)");
		Bukkit.getPluginManager().registerEvents(this, this);
		/*Thread config_thread = new Thread(new ThreadConfig());
		config_thread.start();*/
		Log.info("Running load & save configuration(temporary disabled)");
		Log.info("Enabled HealthBar by tomeito0110.");
	}

	@Override
	public void onDisable() {
		Log.info("Disabling, Running tasks in synchronous mode.");
		Log.info("Running: Removing all HealthBars...");

		Log.info("Tasks completed, HealthBar will be disabled!");
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(ChatColor.GREEN + "Don't show help :p");
		return false;
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Entity e = event.getEntity();
		Log.info("Trapped event!");
		String name = "";
		if(e.getName().isEmpty() || e.getName() == null) {
			name = e.getCustomName();
		} else {
			name = e.getName();
		}
		EventonDamaged ed = new EventonDamaged(event, name);
		Thread thread = new Thread(ed);
		thread.start();
		/*Double double_max_health = null;
		Double double_health = null;
		Integer int_health = null;
		Integer int_max_health = null;
		Integer health = null;
		Integer length = null;
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		String percent = null;
		String greenbar = ChatColor.GREEN + "|";
		String graybar = ChatColor.GRAY + "|";
		String greenhealth = "";
		String name = "";
		String total = null;
		Entity e = event.getEntity();
		if (e instanceof LivingEntity) {
			Log.info("Living entity found!");
			double_health = ((LivingEntity) e).getHealth();
			double_max_health = ((LivingEntity) e).getMaxHealth();
			BigDecimal bd_health = new BigDecimal(double_health.toString());
			BigDecimal bd_max_health = new BigDecimal(double_max_health.toString());
			int_health = bd_health.setScale(0, BigDecimal.ROUND_HALF_UP).toBigInteger().intValue();
			int_max_health = bd_max_health.setScale(0, BigDecimal.ROUND_HALF_UP).toBigInteger().intValue();
		} else {
			Log.info("Not living entity");
			return;
		}
		Log.info("percent...");
		percent = String.format("%.2f",( (double) int_health / (double) int_max_health * 10) ); // Example => 1
		BigDecimal bd = new BigDecimal(percent);
		BigDecimal good = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
		health = good.intValue();
		for(int i=0;i <= health; i++) {
			sb.append(greenbar);
		}
		length = sb.length();
		greenhealth = sb.toString();
		for(int i=0;i<=10-length;i++) {
			sb2.append(graybar);
		}
		total = greenhealth + sb2.toString();
		if(e.getName().isEmpty() || e.getName() == null) {
			name = e.getCustomName();
		} else {
			name = e.getName();
		}
		e.setCustomName(name + "[" + total + "]");
		e.setCustomNameVisible(true);
		waitForThreeSeconds();
		e.setCustomNameVisible(false);
		e.setCustomName(name);*/
	}

	/**
	 * Waiting for 3 seconds
	 */
	public void waitForThreeSeconds() {
	    long endTime = System.currentTimeMillis() + 3 * 1000; // 3秒後の時間を設定
	    boolean interrupted = false;
	    try {
	        while (true) {
	            try {
	                long rest = endTime - System.currentTimeMillis();
	                if (rest <= 0) {
	                    return;
	                } else {
	                    Thread.sleep(rest);
	                }
	            } catch (InterruptedException e) {
	                interrupted = true; // 一度でも割り込まれたらフラグ設定
	            }
	        }
	    } finally {
	        // 一度でも割り込まれていれば、割り込みステータスを設定して終了
	        if (interrupted) {
	        	try {
	        		Thread.currentThread().interrupt();
	        	} catch(SecurityException ex) {
	        		ex.printStackTrace();
	        	}
	        }
	    }
	}
}

/* ----- Licence(LGPLv3) ----- */
/*
 * It has always been the FSF's position that dynamically linking applications to libraries creates a single work derived from both the library code and the application code. The GPL requires that all derivative works be licensed as a whole under the terms of the GPL, an effect which can be described as “hereditary.” So, if an application links to a library licensed under the GPL, the application too must be licensed under the GPL. By contrast, libraries licensed under the GNU Lesser General Public License (LGPL) may be linked to proprietary applications.
 *
 * In July of 2003, Slashdot published a story claiming that I had claimed that the LGPL did not function as intended in the case of Java. This story was based on a misunderstanding of a response to a question sent to licensing@gnu.org, and many attempts to clarify the issue in the Slashdot story did not get across. I have received numerous questions about the story since, via both licensing@gnu.org and personal email.
 *
 * FSF's position has remained constant throughout: the LGPL works as intended with all known programming languages, including Java. Applications which link to LGPL libraries need not be released under the LGPL. Applications need only follow the requirements in section 6 of the LGPL: allow new versions of the library to be linked with the application; and allow reverse engineering to debug this.
 *
 * The typical arrangement for Java is that each library an application uses is distributed as a separate JAR (Java Archive) file. Applications use Java's “import” functionality to access classes from these libraries. When the application is compiled, function signatures are checked against the library, creating a link. The application is then generally a derivative work of the library. So, the copyright holder for the library must authorize distribution of the work. The LGPL permits this distribution.
 *
 * If you distribute a Java application that imports LGPL libraries, it's easy to comply with the LGPL. Your application's license needs to allow users to modify the library, and reverse engineer your code to debug these modifications. This doesn't mean you need to provide source code or any details about the internals of your application. Of course, some changes the users may make to the library may break the interface, rendering the library unable to work with your application. You don't need to worry about that—people who modify the library are responsible for making it work.
 *
 * When you distribute the library with your application (or on its own), you need to include source code for the library. But if your application instead requires users to obtain the library on their own, you don't need to provide source code for the library.
 *
 * The only difference between Java and C from the LGPL's perspective is that Java is an object-oriented language, supporting inheritance. The LGPL contains no special provisions for inheritance, because none are needed. Inheritance creates derivative works in the same way as traditional linking, and the LGPL permits this type of derivative work in the same way as it permits ordinary function calls.
 */