package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Song;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Song length")
@Description("Returns the length of the song(s).")
@Properties({"songs", "length[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("song[s]")
public class ExprSongLength extends FunkyPropertyExpression<Song, Number> {

	@Override
	protected Number[] get(Event event, Song[] songs) {
		if (isNull(event)) return null;
		ArrayList<Number> lengths = new ArrayList<Number>();
		for (Song song : songs) {
			lengths.add(song.getLength());
		}
		return lengths.toArray(new Number[lengths.size()]);
	}
}