package me.limeglass.funky.elements.conditions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyCondition;
import me.limeglass.funky.utils.annotations.Patterns;

@Name("Player has song")
@Description("Check if a song is playing for a user.")
@Patterns("%player% (1¦[does] ha(s|ve)|2¦do[es](n't| not) have) [a[n[y]]] (song|track|music|noteblock)[s] playing")
public class CondPlayerCanHear extends FunkyCondition {

	public boolean check(Event event) {
		if (isNull(event, Player.class)) return false;
		return (NoteBlockAPI.isReceivingSong(expressions.getSingle(event, Player.class))) ? isNegated() : !isNegated();
	}
}