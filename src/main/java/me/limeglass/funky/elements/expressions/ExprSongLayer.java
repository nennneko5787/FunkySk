package me.limeglass.funky.elements.expressions;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Layer;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;
import me.limeglass.funky.utils.annotations.RegisterType;

@Name("Song layers")
@Description("Returns the layers of the song(s). The layers are like a chart of all the notes.")
@Properties({"song", "layers", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[song[s]]")
@RegisterType("layer")
public class ExprSongLayer extends FunkyPropertyExpression<Song, Layer> {
	
	@Override
	protected Layer[] get(Event event, Song[] song) {
		if (isNull(event) || song.length > 1 || song[0].getLayerHashMap().values().size() < 1 || song[0].getLayerHashMap().values().isEmpty()) return null;
		return song[0].getLayerHashMap().values().toArray(new Layer[song[0].getLayerHashMap().values().size()]);
	}
}