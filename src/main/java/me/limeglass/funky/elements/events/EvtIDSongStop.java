package me.limeglass.funky.elements.events;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.event.SongStoppedEvent;
import com.xxmicloxx.NoteBlockAPI.event.SongDestroyingEvent;
import com.xxmicloxx.NoteBlockAPI.event.SongEndEvent;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.coll.CollectionUtils;
import me.limeglass.funky.elements.Events;
import me.limeglass.funky.utils.MusicManager;

public class EvtIDSongStop extends SkriptEvent {
	
	private String songID;
	
	static {
		for (Class<? extends Event> event : CollectionUtils.array(SongStoppedEvent.class, SongDestroyingEvent.class, SongEndEvent.class)) {
			Events.registerEvent(EvtIDSongStop.class, event, "[note[ ]block] song (end|destroy|stop)[ing] (with|of|for) id %string%", "song id stop[ing] %string%");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, SkriptParser.ParseResult parseResult) {
		if (args.length < 1 && ((Literal<String>) args[0]) != null) {
			return false;
		}
		songID = ((Literal<String>) args[0]).getSingle();
		return true;
	}
	
	@Override
	public boolean check(Event event) {
		SongPlayer songPlayer = ((SongStoppedEvent)event).getSongPlayer();
		if (songPlayer == null) return false;
		if (MusicManager.containsSongPlayer(songPlayer) && MusicManager.containsSong(songID)) {
			if (MusicManager.getSongPlayer(songID) == songPlayer) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString(Event event, boolean debug) {
		return "EvtIDSongStop with ID: " + songID;
	}
}
