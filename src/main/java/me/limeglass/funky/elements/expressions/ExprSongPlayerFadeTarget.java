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

@Name("Song fade target")
@Description("Returns the fade target of the song player(s).")
@Properties({"funkysongplayers", "fad(e|ing)[ ]target[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[song[ ]player[s]]")
@Changers(ChangeMode.SET)
public class ExprSongPlayerFadeTarget extends FunkyPropertyExpression<SongPlayer, Number> {

	@Override
	protected Number[] get(Event event, SongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<Number> fades = new ArrayList<Number>();
		for (SongPlayer songPlayer : songPlayers) {
			fades.add(songPlayer.getFadeTarget());
		}
		return fades.toArray(new Number[fades.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (SongPlayer songPlayer : expressions.getAll(event, SongPlayer.class)) {
			songPlayer.setFadeTarget(((Number)delta[0]).byteValue());
		}
	}
}