package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.NoteBlockSongPlayer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Noteblock player distance")
@Description("Returns the distance that the noteblock song player(s) can be heard from. The default distance is 16.")
@Properties({"noteblocksongplayers", "[sound] (distance|range)[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("note[ ]block song[ ]player[s]")
@Changers(ChangeMode.SET)
public class ExprNoteBlockSongPlayerDistance extends FunkyPropertyExpression<NoteBlockSongPlayer, Number> {

	@Override
	protected Number[] get(Event event, NoteBlockSongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<Number> ranges = new ArrayList<Number>();
		for (NoteBlockSongPlayer songPlayer : songPlayers) {
			ranges.add(songPlayer.getDistance());
		}
		return ranges.toArray(new Number[ranges.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (NoteBlockSongPlayer songPlayer : expressions.getAll(event, NoteBlockSongPlayer.class)) {
			songPlayer.setDistance(((Number)delta[0]).intValue());
		}
	}
}