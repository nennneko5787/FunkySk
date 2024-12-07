package me.limeglass.funky.elements.conditions;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyCondition;
import me.limeglass.funky.utils.annotations.Patterns;

@Name("Player has song")
@Description("Check if a song is playing for a user.")
@Patterns("song[ ]player %funkysongplayer% (1¦is|2¦is(n't| not)) playing [[a[n[y]]] (song|track|music|noteblock)[s]]")
public class CondSongPlayerPlaying extends FunkyCondition {

	public boolean check(Event event) {
		if (isNull(event, SongPlayer.class)) return false;
		return (expressions.getSingle(event, SongPlayer.class).isPlaying()) ? isNegated() : !isNegated();
	}
}