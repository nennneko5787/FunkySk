package me.limeglass.funky.elements.conditions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyCondition;
import me.limeglass.funky.utils.annotations.Patterns;

@Name("Player in range")
@Description("Check if a minecraft player is in range of the position song player.")
@Patterns({"%player% (1¦is|2¦is(n't| not)) in range [of [the]] position song[ ]player %positionsongplayer%", "%player% (1�can|2�can(n't| not)) hear [the] position song[ ]player %positionsongplayer%"})
public class CondPlayerInRange extends FunkyCondition {

	public boolean check(Event event) {
		if (areNull(event)) return false;
		PositionSongPlayer songPlayer = expressions.getSingle(event, PositionSongPlayer.class);
		return (songPlayer.isInRange(expressions.getSingle(event, Player.class))) ? isNegated() : !isNegated();
	}
}