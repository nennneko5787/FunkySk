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

@Name("Note key")
@Description("Returns the key(s) of the Note(s). The return number is from a byte. Key is what can be used to get the pitch. Subtract it by 33 or just use the pitch from note syntax.")
@Properties({"notes", "key[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("note[s]")
@Changers(ChangeMode.SET)
public class ExprNoteKey extends FunkyPropertyExpression<Note, Number> {

	@Override
	protected Number[] get(Event event, Note[] notes) {
		if (isNull(event)) return null;
		ArrayList<Number> keys = new ArrayList<Number>();
		for (Note note : notes) {
			keys.add(note.getKey());
		}
		return keys.toArray(new Number[keys.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (Note note : expressions.getAll(event, Note.class)) {
			note.setKey(((Number)delta[0]).byteValue());
		}
	}
}