package me.limeglass.funky.elements.ID;

import java.util.ArrayList;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyExpression;
import me.limeglass.funky.utils.MusicManager;
import me.limeglass.funky.utils.annotations.DetermineSingle;
import me.limeglass.funky.utils.annotations.Patterns;

@Name("ID - Song music players")
@Description("Returns the song's music player(s) of string(s).")
@Patterns("[(all [[of] the]|the)] song[ ]player[s] (from|of) [note[ ]block] id[s] %strings%")
@DetermineSingle
public class ExprSongPlayersOfID extends FunkyExpression<SongPlayer> {
	
	@Override
	protected SongPlayer[] get(Event event) {
		if (areNull(event)) return null;
		ArrayList<SongPlayer> songPlayers = new ArrayList<SongPlayer>();
		for (String song : expressions.getAll(event, String.class)) {
			songPlayers.add(MusicManager.getSongPlayer(song));
		}
		if (songPlayers == null || songPlayers.isEmpty()) return null;
		return songPlayers.toArray(new SongPlayer[songPlayers.size()]);
	}
}