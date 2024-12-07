package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Song player destroy")
@Description("Returns if the song player(s) should destroy on finish.")
@Properties({"funkysongplayers", "[auto] destroy[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("song[ ]player[s]")
@Changers(ChangeMode.SET)
public class ExprSongPlayerAutoDestory extends FunkyPropertyExpression<SongPlayer, Boolean> {

	@Override
	protected Boolean[] get(Event event, SongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<Boolean> destorys = new ArrayList<Boolean>();
		for (SongPlayer songPlayer : songPlayers) {
			destorys.add(songPlayer.getAutoDestroy());
		}
		return destorys.toArray(new Boolean[destorys.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (SongPlayer songPlayer : expressions.getAll(event, SongPlayer.class)) {
			songPlayer.setAutoDestroy(((Boolean)delta[0]));
		}
	}
}