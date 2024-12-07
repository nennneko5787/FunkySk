package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Song;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Song title")
@Description("Returns the title of the song(s).")
@Properties({"songs", "(name|title)[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("song[s]")
public class ExprSongTitle extends FunkyPropertyExpression<Song, String> {

	@Override
	protected String[] get(Event event, Song[] songs) {
		if (isNull(event)) return null;
		ArrayList<String> titles = new ArrayList<String>();
		for (Song song : songs) {
			titles.add(song.getTitle());
		}
		return titles.toArray(new String[titles.size()]);
	}
}