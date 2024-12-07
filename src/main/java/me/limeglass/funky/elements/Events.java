package me.limeglass.funky.elements;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.eclipse.jdt.annotation.Nullable;

import com.xxmicloxx.NoteBlockAPI.event.SongDestroyingEvent;
import com.xxmicloxx.NoteBlockAPI.event.SongEndEvent;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.event.SongStoppedEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.limeglass.funky.FunkySk;
import me.limeglass.funky.Syntax;
import me.limeglass.funky.utils.ReflectionUtil;

public class Events {
	
	public Events() {
		EventValues.registerEventValue(InventoryClickEvent.class, Inventory.class, new Getter<Inventory, InventoryClickEvent>() {
			@Override
			public Inventory get(InventoryClickEvent event) {
				return event.getClickedInventory();
			}
		}, 0);
		EventValues.registerEventValue(InventoryClickEvent.class, Number.class, new Getter<Number, InventoryClickEvent>() {
			@Override
			public Number get(InventoryClickEvent event) {
				return event.getSlot();
			}
		}, 0);
		EventValues.registerEventValue(SongStoppedEvent.class, SongPlayer.class, new Getter<SongPlayer, SongStoppedEvent>() {
			@Override
			public SongPlayer get(SongStoppedEvent event) {
				return event.getSongPlayer();
			}
		}, 0);
		registerEvent(null, SongStoppedEvent.class, "[note[ ]block] song[[ ]player] stop[ping]");
		EventValues.registerEventValue(SongDestroyingEvent.class, SongPlayer.class, new Getter<SongPlayer, SongDestroyingEvent>() {
			@Override
			public SongPlayer get(SongDestroyingEvent event) {
				return event.getSongPlayer();
			}
		}, 0);
		registerEvent(null, SongDestroyingEvent.class, "[note[ ]block] song[[ ]player] destroy[ing]");
		EventValues.registerEventValue(SongEndEvent.class, SongPlayer.class, new Getter<SongPlayer, SongEndEvent>() {
			@Override
			public SongPlayer get(SongEndEvent event) {
				return event.getSongPlayer();
			}
		}, 0);
		registerEvent(null, SongEndEvent.class, "[note[ ]block] song[[ ]player] end[ing]");
	}
	
	public static void registerEvent(@Nullable Class<? extends SkriptEvent> skriptEvent, Class<? extends Event> event, String... patterns) {
		if (skriptEvent == null) skriptEvent = SimpleEvent.class;
		for (int i = 0; i < patterns.length; i++) {
			patterns[i] = FunkySk.getNameplate() + patterns[i];
		}
		Object[] values = new Object[] {true, patterns, getEventValues(event)};
		String[] nodes = new String[] {"enabled", "patterns", "eventvalues"};
		for (int i = 0; i < nodes.length; i++) {
			if (!FunkySk.getSyntaxData().isSet("Syntax.Events." + event.getSimpleName() + "." + nodes[i])) {
				FunkySk.getSyntaxData().set("Syntax.Events." + event.getSimpleName() + "." + nodes[i], values[i]);
			}
		}
		Syntax.save();
		if (FunkySk.getSyntaxData().getBoolean("Syntax.Events." + event.getSimpleName() + ".enabled", true)) {
			//TODO find a way to make the stupid Spigot Yaml read properly for user editing of event patterns.
			Skript.registerEvent(event.getSimpleName(), skriptEvent, event, patterns);
		}
	}
	
	@SafeVarargs
	private final static List<String> getEventValues(Class<? extends Event>... events) {
		List<String> classes = new ArrayList<String>();
		try {
			Method method = EventValues.class.getDeclaredMethod("getEventValuesList", int.class);
			method.setAccessible(true);
			for (Class<? extends Event> event : events) {
				for (int i = -1; i < 2; i++) {
					List<?> eventValueInfos = (List<?>) method.invoke(EventValues.class, i);
					if (eventValueInfos != null) {
						for (Object eventValueInfo : eventValueInfos) {
							Class<?> e = ReflectionUtil.getField("event", eventValueInfo.getClass(), eventValueInfo);
							if (e != null && (e.isAssignableFrom(event) || event.isAssignableFrom(e))) {
								Class<?> clazz = ReflectionUtil.getField("c", eventValueInfo.getClass(), eventValueInfo);
								if (clazz != null) classes.add(clazz.getSimpleName());
							}
						}
					}
				}
			}
		} catch (SecurityException | IllegalArgumentException | NoSuchMethodException | IllegalAccessException | InvocationTargetException error) {
			error.printStackTrace();
		}
		return classes;
	}
}