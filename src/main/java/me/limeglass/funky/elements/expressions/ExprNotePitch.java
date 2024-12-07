package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.model.Note;
import com.xxmicloxx.NoteBlockAPI.model.NotePitch;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Note pitch")
@Description("Returns the pitch(s) of the Note(s). The return number is from a byte.")
@Properties({"notes", "pitch[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("note[s]")
public class ExprNotePitch extends FunkyPropertyExpression<Note, Number> {

	@Override
	protected Number[] get(Event event, Note[] notes) {
		if (isNull(event)) return null;
		ArrayList<Number> keys = new ArrayList<Number>();
		for (Note note : notes) {
			keys.add(NotePitch.getPitch(note.getKey() - 33));
		}
		return keys.toArray(new Number[keys.size()]);
	}
}