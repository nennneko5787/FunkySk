package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.NoteBlockSongPlayer;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyExpression;
import me.limeglass.funky.utils.annotations.DetermineSingle;
import me.limeglass.funky.utils.annotations.Patterns;
import me.limeglass.funky.utils.annotations.RegisterType;

@Name("Noteblock song player")
@Description("Returns new noteblock song player(s) from the given song(s). A noteblock song player is a song player that is locked on a block which is a noteblock.")
@Patterns("[a] [new] note[ ]block song [music] player[s] from [the] [song[s]] %songs% [with [note[ ]block] %-block%]")
@RegisterType("noteblocksongplayer")
@DetermineSingle
public class ExprNoteBlockSongPlayer extends FunkyExpression<NoteBlockSongPlayer> {
	
	@Override
	protected NoteBlockSongPlayer[] get(Event event) {
		if (expressions.getAll(event, Song.class) == null) return null;
		ArrayList<NoteBlockSongPlayer> songPlayers = new ArrayList<NoteBlockSongPlayer>();
		for (Song song : expressions.getAll(event, Song.class)) {
			songPlayers.add(new NoteBlockSongPlayer(song));
		}
		if (songPlayers == null || songPlayers.isEmpty()) return null;
		if (expressions.getSingle(event, Block.class) != null) {
			for (NoteBlockSongPlayer songPlayer : songPlayers) {
				songPlayer.setNoteBlock(expressions.getSingle(event, Block.class));
			}
		}
		return songPlayers.toArray(new NoteBlockSongPlayer[songPlayers.size()]);
	}
}