package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyExpression;
import me.limeglass.funky.utils.annotations.DetermineSingle;
import me.limeglass.funky.utils.annotations.Patterns;
import me.limeglass.funky.utils.annotations.RegisterType;

@Name("New song player")
@Description("Returns new song player(s) from the given song(s).")
@Patterns("[a] [new] song [music] player[s] from [the] [song[s]] %songs%")
@RegisterType("funkysongplayer")
@DetermineSingle
public class ExprSongPlayer extends FunkyExpression<SongPlayer> {
	
	@Override
	protected SongPlayer[] get(Event event) {
		if (areNull(event)) return null;
		ArrayList<SongPlayer> songPlayers = new ArrayList<SongPlayer>();
		for (Song song : expressions.getAll(event, Song.class)) {
			songPlayers.add(new RadioSongPlayer(song));
		}
		if (songPlayers == null || songPlayers.isEmpty()) return null;
		return songPlayers.toArray(new SongPlayer[songPlayers.size()]);
	}
}