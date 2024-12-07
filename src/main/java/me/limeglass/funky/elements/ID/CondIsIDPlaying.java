package me.limeglass.funky.elements.ID;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyCondition;
import me.limeglass.funky.utils.MusicManager;
import me.limeglass.funky.utils.annotations.Patterns;

@Name("ID - Song is playing")
@Description("Check if a song with an ID is playing.")
@Patterns("(song|track|music|noteblock) [with] id %string% (1¦is|2¦is(n't| not)) playing")
public class CondIsIDPlaying extends FunkyCondition {

	public boolean check(Event event) {
		if (isNull(event, String.class)) return false;
		return (MusicManager.containsSong(expressions.getSingle(event, String.class))) ? isNegated() : !isNegated();
	}
}