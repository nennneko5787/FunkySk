package me.limeglass.funky.elements.expressions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.bukkit.event.Event;

import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.model.Song;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.limeglass.funky.FunkySk;
import me.limeglass.funky.lang.FunkyExpression;
import me.limeglass.funky.utils.annotations.DetermineSingle;
import me.limeglass.funky.utils.annotations.Patterns;
import me.limeglass.funky.utils.annotations.RegisterType;

@Name("Grab a song")
@Description("Returns songs based from the file strings. Can accept a file path string or a url link.")
@Patterns("[a] [new] song[s] [from [the]] (file[s] [path]|url|web)[s] %strings%")
@RegisterType("song")
@DetermineSingle
public class ExprSong extends FunkyExpression<Song> {
	
	@Override
	protected Song[] get(Event event) {
		if (areNull(event)) return null;
		ArrayList<com.xxmicloxx.NoteBlockAPI.model.Song> songs = new ArrayList<>();
		for (String song : expressions.getAll(event, String.class)) {
			if (isValidLink(song)) {
				try {
					InputStream input = new URL(song).openStream();
					if (input != null) songs.add(NBSDecoder.parse(input));
				} catch (IOException e) {}
			} else if (isValidFile(song)) {
				File file = new File(song);
				if (file.exists()) {
					songs.add(NBSDecoder.parse(file));
				} else {
					if (FunkySk.getInstance().getConfig().getBoolean("InvalidFile")) Skript.error("The file for the song " + file.getAbsolutePath() + " does not exist!");
				}
			}
		}
		if (songs == null || songs.isEmpty()) return null;
		return songs.toArray(new Song[songs.size()]);
	}
	
	private Boolean isValidLink(String link) {
		try {
			InputStream input = new URL(link).openStream();
			if (input != null) return true;
		} catch (IOException e) {
			FunkySk.debugMessage("Invalid link: " + link);
		}
		return false;
	}
	
	private Boolean isValidFile(String file) {
		File f = new File(file);
		if (f != null && f.exists()) {
			FunkySk.debugMessage("Invalid file: " + file);
			return true;
		}
		return false;
	}
}