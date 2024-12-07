package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Position player location")
@Description("Returns the location of the position song player(s).")
@Properties({"positionsongplayers", "(target [location]|location)[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("position song[ ]player[s]")
@Changers(ChangeMode.SET)
public class ExprPositionSongPlayerTarget extends FunkyPropertyExpression<PositionSongPlayer, Location> {

	@Override
	protected Location[] get(Event event, PositionSongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<Location> locations = new ArrayList<Location>();
		for (PositionSongPlayer songPlayer : songPlayers) {
			locations.add(songPlayer.getTargetLocation());
		}
		return locations.toArray(new Location[locations.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (PositionSongPlayer songPlayer : expressions.getAll(event, PositionSongPlayer.class)) {
			songPlayer.setTargetLocation((Location)delta[0]);
		}
	}
}