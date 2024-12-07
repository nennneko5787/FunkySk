package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Note;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Note instrument")
@Description("Returns the instrument(s) of the Note(s). The return number is from a byte.")
@Properties({"notes", "instrument[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("[note[s]]")
@Changers(ChangeMode.SET)
public class ExprNoteInstrument extends FunkyPropertyExpression<Note, Number> {

	@Override
	protected Number[] get(Event event, Note[] notes) {
		if (isNull(event)) return null;
		ArrayList<Number> instruments = new ArrayList<Number>();
		for (Note note : notes) {
			instruments.add(note.getInstrument());
		}
		return instruments.toArray(new Number[instruments.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (Note note : expressions.getAll(event, Note.class)) {
			note.setInstrument(((Number)delta[0]).byteValue());
		}
	}
}