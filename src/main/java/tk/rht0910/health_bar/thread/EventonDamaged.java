package tk.rht0910.health_bar.thread;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import tk.rht0910.tomeito_core.utils.Log;

public class EventonDamaged implements Runnable {
	private EntityDamageByEntityEvent event = null;
	public EventonDamaged(EntityDamageByEntityEvent event) {
		this.event = event;
	}

	@SuppressWarnings("deprecation")
	@Override
	public synchronized void run() {
		Log.info("Running event in thread!");
		Double double_max_health = null;
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
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
		e.setCustomNameVisible(false);
		e.setCustomName(name);
	}

	public synchronized void resume() {
		this.notifyAll();
	}
}
