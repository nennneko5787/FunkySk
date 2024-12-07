package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.FadeType;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;
import me.limeglass.funky.utils.annotations.RegisterEnum;

@Name("Song fade type")
@Description("Returns the fade type of the song player(s). The fade type is how the song fades. Currently there is only linear")
@Properties({"funkysongplayers", "fad(e|ing)[[ ]type][s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[song[ ]player[s]]")
@RegisterEnum("fadetype")
@Changers(ChangeMode.SET)
public class ExprSongPlayerFade extends FunkyPropertyExpression<SongPlayer, FadeType> {

	@Override
	protected FadeType[] get(Event event, SongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<FadeType> fades = new ArrayList<FadeType>();
		for (SongPlayer songPlayer : songPlayers) {
			fades.add(songPlayer.getFadeType());
		}
		return fades.toArray(new FadeType[fades.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (SongPlayer songPlayer : expressions.getAll(event, SongPlayer.class)) {
			songPlayer.setFadeType((FadeType)delta[0]);
		}
	}
}