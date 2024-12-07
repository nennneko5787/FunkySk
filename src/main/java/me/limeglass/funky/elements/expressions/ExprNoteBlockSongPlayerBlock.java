package me.limeglass.funky.elements.expressions;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.songplayer.NoteBlockSongPlayer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.lang.FunkyPropertyExpression;
import me.limeglass.funky.utils.annotations.Changers;
import me.limeglass.funky.utils.annotations.Properties;
import me.limeglass.funky.utils.annotations.PropertiesAddition;

@Name("Noteblock player block")
@Description("Returns the actual noteblock block of the noteblock song player(s).")
@Properties({"noteblocksongplayers", "[note[ ]]block[s]", "{1}[(all [[of] the]|the)]"})
@PropertiesAddition("position song[ ]player[s]")
@Changers(ChangeMode.SET)
public class ExprNoteBlockSongPlayerBlock extends FunkyPropertyExpression<NoteBlockSongPlayer, Block> {

	@Override
	protected Block[] get(Event event, NoteBlockSongPlayer[] songPlayers) {
		if (isNull(event)) return null;
		ArrayList<Block> blocks = new ArrayList<Block>();
		for (NoteBlockSongPlayer songPlayer : songPlayers) {
			blocks.add(songPlayer.getNoteBlock());
		}
		return blocks.toArray(new Block[blocks.size()]);
	}
	
	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		if (isNull(event) || delta == null) return;
		for (NoteBlockSongPlayer songPlayer : expressions.getAll(event, NoteBlockSongPlayer.class)) {
			songPlayer.setNoteBlock((Block)delta[0]);
		}
	}
}