package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Position player distance")
@Description("Returns the distance that the position song player(s) can be heard from. The default distance is 16.")
@Properties({"positionsongplayers", "[sound] (distance|range)[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("position song[ ]player[s]")
@Changers(ChangeMode.SET)
public class ExprPositionSongPlayerDistance extends FunkyPropertyExpression<PositionSongPlayer, Number> {

	@Override
	protected Number[] get(Event event, PositionSongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<Number> ranges = new ArrayList<Number>();
		for (PositionSongPlayer songPlayer : songPlayers) {
			ranges.add(songPlayer.getDistance());
		}
		return ranges.toArray(new Number[ranges.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (PositionSongPlayer songPlayer : expressions.getAll(event, PositionSongPlayer.class)) {
			songPlayer.setDistance(((Number)delta[0]).intValue());
		}
	}
}