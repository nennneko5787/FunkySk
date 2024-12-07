package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Song player playing")
@Description("Returns if the song player(s) are playing or not.")
@Properties({"funkysongplayers", "[music] playing [state[s]]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("song[ ]player[s]")
@Changers(ChangeMode.SET)
public class ExprSongPlayerPlaying extends FunkyPropertyExpression<SongPlayer, Boolean> {

	@Override
	protected Boolean[] get(Event event, SongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<Boolean> playings = new ArrayList<Boolean>();
		for (SongPlayer songPlayer : songPlayers) {
			playings.add(songPlayer.isPlaying());
		}
		return playings.toArray(new Boolean[playings.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (SongPlayer songPlayer : expressions.getAll(event, SongPlayer.class)) {
			songPlayer.setPlaying(((Boolean)delta[0]));
		}
	}
}