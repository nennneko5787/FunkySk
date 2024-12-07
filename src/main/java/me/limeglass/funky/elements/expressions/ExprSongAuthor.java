package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Song;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Song author")
@Description("Returns the author of the song(s).")
@Properties({"songs", "(author|creator)[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("song[s]")
public class ExprSongAuthor extends FunkyPropertyExpression<Song, String> {

	@Override
	protected String[] get(Event event, Song[] songs) {
		if (isNull(event)) return null;
		ArrayList<String> authors = new ArrayList<String>();
		for (Song song : songs) {
			authors.add(song.getAuthor());
		}
		return authors.toArray(new String[authors.size()]);
	}
}