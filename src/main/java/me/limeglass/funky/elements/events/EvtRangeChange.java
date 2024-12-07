package me.limeglass.funky.elements.events;

import java.util.Objects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.event.PlayerRangeStateChangeEvent;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.limeglass.funky.elements.Events;

public class EvtRangeChange extends SkriptEvent {
	
	private Object argument;
	
	static {
		EventValues.registerEventValue(PlayerRangeStateChangeEvent.class, SongPlayer.class, new Getter<SongPlayer, PlayerRangeStateChangeEvent>() {
			@Override
			public SongPlayer get(PlayerRangeStateChangeEvent event) {
				return event.getSongPlayer();
			}
		}, 0);
		EventValues.registerEventValue(PlayerRangeStateChangeEvent.class, Boolean.class, new Getter<Boolean, PlayerRangeStateChangeEvent>() {
			@Override
			public Boolean get(PlayerRangeStateChangeEvent event) {
				return event.isInRange();
			}
		}, 0);
		EventValues.registerEventValue(PlayerRangeStateChangeEvent.class, Player.class, new Getter<Player, PlayerRangeStateChangeEvent>() {
			@Override
			public Player get(PlayerRangeStateChangeEvent event) {
				return event.getPlayer();
			}
		}, 0);
		Events.registerEvent(EvtRangeChange.class, PlayerRangeStateChangeEvent.class, "[player] range [state] change [(of|for) %-player/funkysongplayer%]");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
		if (args.length < 1 && ((Literal<String>) args[0]) != null) {
			return false;
		}
		if (((Literal<String>) args[0]) != null) argument = ((Literal<Object>) args[0]).getSingle();
		return true;
	}
	
	@Override
	public boolean check(Event event) {
		if (argument == null) return true;
		if (argument instanceof Player && ((PlayerRangeStateChangeEvent)event).getPlayer() == argument) return true;
		if (argument instanceof SongPlayer && ((PlayerRangeStateChangeEvent)event).getSongPlayer() == argument) return true;
		return false;
	}

	@Override
	public String toString(Event event, boolean debug) {
		return "EvtRangeChange with nullable Object: " + Objects.toString(argument);
	}
}
