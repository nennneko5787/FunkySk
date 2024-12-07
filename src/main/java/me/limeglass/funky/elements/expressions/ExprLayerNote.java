package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Layer;
import com.xxmicloxx.NoteBlockAPI.model.Note;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.DetermineSingle;
import me.limeglass.funky.utils.annotations.Patterns;
import me.limeglass.funky.utils.annotations.RegisterType;

@Name("Layer notes")
@Description("Returns a note at the defined tick of the song.")
@Patterns({"[(all [[of] the]|the)] note[s] of [layer[s]] %layers% [at [tick] %-number%]", "[layer[s]] %layers%['s] note[s] [at [tick] %-number%]"})
@RegisterType("note")
@DetermineSingle
@Changers(ChangeMode.SET)
public class ExprLayerNote extends FunkyExpression<Note> {
	
	@Override
	protected Note[] get(Event event) {
		if (isNull(event, Layer.class)) return null;
		Number tick = expressions.getSingle(event, Number.class);
		ArrayList<Note> notes = new ArrayList<Note>();
		for (Layer layer : expressions.getAll(event, Layer.class)) {
			if (!isNull(event, Number.class)) {
				notes.add(layer.getNote(tick.intValue()));
			} else {
				notes.addAll(layer.getNotesAtTicks().values());
			}
		}
		if (notes == null || notes.isEmpty()) return null;
		return notes.toArray(new Note[notes.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (areNull(event) || delta == null) {
			Skript.error("You can't have the tick number null when attempting to set the notes");
			return;
		}
		if (!expressions.getExpressions()[0].isSingle()) {
			Skript.error("You can only set one layer at a time when setting the expression: ExprLayerNote.");
			return;
		}
		int tick = expressions.getSingle(event, Number.class).intValue();
		expressions.getSingle(event, Layer.class).setNote(tick, (Note)delta[0]);
	}
}