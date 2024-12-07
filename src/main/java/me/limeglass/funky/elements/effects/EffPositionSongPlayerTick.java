package me.limeglass.funky.elements.effects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyEffect;
import me.limeglass.funky.utils.annotations.Patterns;

@Name("Position song player tick")
@Description("Play a tick of a song to the user(s).")
@Patterns("play tick %number% from [position song[ ]player] %positionsongplayer% to %players%")
public class EffPositionSongPlayerTick extends FunkyEffect {

	@Override
	protected void execute(Event event) {
		if (areNull(event)) return;
		for (Player player : expressions.getAll(event, Player.class)) {
			expressions.getSingle(event, PositionSongPlayer.class).playTick(player, (expressions.getSingle(event, Number.class)).intValue());
		}
	}
}