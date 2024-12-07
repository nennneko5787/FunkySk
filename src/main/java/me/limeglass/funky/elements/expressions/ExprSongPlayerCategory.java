package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.model.SoundCategory;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;
import me.limeglass.funky.utils.annotations.RegisterEnum;
import me.limeglass.funky.utils.annotations.User;

@Name("Song player song")
@Description("Returns the song currently playing in the song player(s).")
@Properties({"funkysongplayers", "(song|track|music)[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("song[ ]player[s]")
@RegisterEnum("funkysoundcategory")
@User("funkysoundcategor(y|ies)")
@Changers(ChangeMode.SET)
public class ExprSongPlayerCategory extends FunkyPropertyExpression<SongPlayer, SoundCategory> {

	@Override
	protected SoundCategory[] get(Event event, SongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<SoundCategory> songs = new ArrayList<SoundCategory>();
		for (SongPlayer songPlayer : songPlayers) {
			songs.add(songPlayer.getCategory());
		}
		return songs.toArray(new SoundCategory[songs.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (SongPlayer songPlayer : expressions.getAll(event, SongPlayer.class)) {
			songPlayer.setCategory(((SoundCategory)delta[0]));
		}
	}
}