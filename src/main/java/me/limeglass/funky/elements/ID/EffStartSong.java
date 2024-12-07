package me.limeglass.funky.elements.ID;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyEffect;
import me.limeglass.funky.utils.MusicManager;
import me.limeglass.funky.utils.annotations.Patterns;

@Name("Start song")
@Description("Plays a new song for a user with an optional ID system to be able to easily manage the song being played.")
@Patterns("(start|play) [new] [note[[ ]block]] [song] %song% to %players% [with id %-string%]")
public class EffStartSong extends FunkyEffect {

	@Override
	protected void execute(Event event) {
		if (isNull(event, Song.class)) return;
		SongPlayer songPlayer = new RadioSongPlayer(expressions.getSingle(event, Song.class));
		if (songPlayer != null) {
			for (Player player : expressions.getAll(event, Player.class)) {
				songPlayer.addPlayer(player);
			}
		}
		if (!isNull(event, String.class)) MusicManager.add(expressions.getSingle(event, String.class), songPlayer);
		songPlayer.setEnable10Octave(true);
		songPlayer.setAutoDestroy(true);
		songPlayer.setPlaying(true);
	}
}