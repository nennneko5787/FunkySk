package me.limeglass.funky.elements.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyEffect;
import me.limeglass.funky.utils.annotations.Patterns;

@Name("Stop listening")
@Description("Stops a song from being played to the minecraft player(s). Doesn't seem to work well, use the player list of a song player.")
@Patterns("stop [a[n[y]]] (song|track|music|noteblock) [from [being] playing] (for|to) %players%")
public class EffPlayerStopPlaying extends FunkyEffect {

	@Override
	protected void execute(Event event) {
		if (isNull(event, String.class)) return;
		for (Player player : expressions.getAll(event, Player.class)) {
			NoteBlockAPI.stopPlaying(player);
		}
	}
}