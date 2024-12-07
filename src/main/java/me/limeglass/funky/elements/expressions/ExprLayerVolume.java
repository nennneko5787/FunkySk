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

@Name("Layer volume")
@Description("Returns the volume of the layer(s).")
@Properties({"layers", "volume[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("layer[s]")
@Changers(ChangeMode.SET)
public class ExprLayerVolume extends FunkyPropertyExpression<Layer, Number> {

	@Override
	protected Number[] get(Event event, Layer[] layers) {
		if (isNull(event)) return null;
		ArrayList<Number> volumes = new ArrayList<Number>();
		for (Layer layer : layers) {
			volumes.add(layer.getVolume());
		}
		return volumes.toArray(new Number[volumes.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (Layer layer : expressions.getAll(event, Layer.class)) {
			layer.setVolume(((Number)delta[0]).byteValue());
		}
	}
}