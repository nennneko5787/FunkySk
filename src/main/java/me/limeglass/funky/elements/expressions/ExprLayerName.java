package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Layer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Layer name")
@Description("Returns the name of the layer(s).")
@Properties({"layers", "name[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("layer[s]")
@Changers(ChangeMode.SET)
public class ExprLayerName extends FunkyPropertyExpression<Layer, String> {

	@Override
	protected String[] get(Event event, Layer[] layers) {
		if (isNull(event)) return null;
		ArrayList<String> names = new ArrayList<String>();
		for (Layer layer : layers) {
			names.add(layer.getName());
		}
		return names.toArray(new String[names.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (Layer layer : expressions.getAll(event, Layer.class)) {
			layer.setName((String)delta[0]);
		}
	}
}