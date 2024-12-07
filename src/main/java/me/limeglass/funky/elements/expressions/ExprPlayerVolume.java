package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Player volume")
@Description("Returns the volume of the user(s). This is like a preference system for each minecraft user.")
@Properties({"players", "sound volume[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[player[s]]")
@Changers(ChangeMode.SET)
public class ExprPlayerVolume extends FunkyPropertyExpression<Player, Number> {

	@Override
	protected Number[] get(Event event, Player[] players) {
		if (isNull(event)) return null;
		ArrayList<Number> volumes = new ArrayList<Number>();
		for (Player player : players) {
			volumes.add(NoteBlockAPI.getPlayerVolume(player));
		}
		return volumes.toArray(new Number[volumes.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (Player player : expressions.getAll(event, Player.class)) {
			NoteBlockAPI.setPlayerVolume(player, ((Number)delta[0]).byteValue());
		}
	}
}