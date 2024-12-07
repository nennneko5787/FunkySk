package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Song;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Song delay")
@Description("Returns the delay of the song(s) if they have a delay.")
@Properties({"songs", "delay[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("song[s]")
public class ExprSongDelay extends FunkyPropertyExpression<Song, Number> {

	@Override
	protected Number[] get(Event event, Song[] songs) {
		if (isNull(event)) return null;
		ArrayList<Number> delays = new ArrayList<Number>();
		for (Song song : songs) {
			delays.add(song.getDelay());
		}
		return delays.toArray(new Number[delays.size()]);
	}
}