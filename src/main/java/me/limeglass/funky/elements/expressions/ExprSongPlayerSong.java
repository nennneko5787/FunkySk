package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Song player song")
@Description("Returns the song currently playing in the song player(s).")
@Properties({"funkysongplayers", "(song|track|music)[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("song[ ]player[s]")
public class ExprSongPlayerSong extends FunkyPropertyExpression<SongPlayer, Song> {

	@Override
	protected Song[] get(Event event, SongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<Song> songs = new ArrayList<Song>();
		for (SongPlayer songPlayer : songPlayers) {
			songs.add(songPlayer.getSong());
		}
		return songs.toArray(new Song[songs.size()]);
	}
}