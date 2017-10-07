// The licence notation is under the file.

package tk.rht0910.health_bar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import tk.rht0910.health_bar.config.Config;
import tk.rht0910.tomeito_core.utils.Log;

public class HealthBar extends JavaPlugin implements Listener {

	@Override
	public void onLoad() {
		Log.info("Loaded HealthBar!");
	}

	@Override
	public void onEnable() {
		Log.info("Tasks will be run as synchronous task.");
		Log.info("Running: Load and Save a config");
		Thread thread = new Thread(new Config());
		thread.start();
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
		String entityName = event.getEntity().getName();
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