package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Song;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Song description")
@Description("Returns the description of the song(s).")
@Properties({"songs", "desc[ription[s]]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("song[s]")
public class ExprSongDescription extends FunkyPropertyExpression<Song, String> {

	@Override
	protected String[] get(Event event, Song[] songs) {
		if (isNull(event)) return null;
		ArrayList<String> descriptions = new ArrayList<String>();
		for (Song song : songs) {
			descriptions.add(song.getDescription());
		}
		return descriptions.toArray(new String[descriptions.size()]);
	}
}