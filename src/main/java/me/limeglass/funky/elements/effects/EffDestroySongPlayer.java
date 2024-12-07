package me.limeglass.funky.elements.effects;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyEffect;
import me.limeglass.funky.utils.annotations.Patterns;

@Name("Destroy song player")
@Description("Destory/Remove the song player(s).")
@Patterns("(destroy|remove) [note[[ ]block]] song[ ]player[s] %funkysongplayers%")
public class EffDestroySongPlayer extends FunkyEffect {

	@Override
	protected void execute(Event event) {
		if (isNull(event, String.class)) return;
		for (SongPlayer songPlayer : expressions.getAll(event, SongPlayer.class)) {
			songPlayer.destroy();
		}
	}
}