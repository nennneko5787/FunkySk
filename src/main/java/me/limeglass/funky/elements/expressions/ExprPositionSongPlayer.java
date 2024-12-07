package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyExpression;
import me.limeglass.funky.utils.annotations.DetermineSingle;
import me.limeglass.funky.utils.annotations.Patterns;
import me.limeglass.funky.utils.annotations.RegisterType;

@Name("Position song player")
@Description("Returns new position song player(s) from the given song(s). A position song player is a song player that is locked on a location.")
@Patterns("[a] [new] position song [music] player[s] from [the] [song[s]] %songs% [with %-location%]")
@RegisterType("positionsongplayer")
@DetermineSingle
public class ExprPositionSongPlayer extends FunkyExpression<PositionSongPlayer> {
	
	@Override
	protected PositionSongPlayer[] get(Event event) {
		if (expressions.getAll(event, Song.class) == null) return null;
		ArrayList<PositionSongPlayer> songPlayers = new ArrayList<PositionSongPlayer>();
		for (Song song : expressions.getAll(event, Song.class)) {
			songPlayers.add(new PositionSongPlayer(song));
		}
		if (songPlayers == null || songPlayers.isEmpty()) return null;
		if (expressions.getSingle(event, Location.class) != null) {
			for (PositionSongPlayer songPlayer : songPlayers) {
				songPlayer.setTargetLocation(expressions.getSingle(event, Location.class));
			}
		}
		return songPlayers.toArray(new PositionSongPlayer[songPlayers.size()]);
	}
}