package me.limeglass.funky.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;

public class MusicManager {

	private static HashMap<String, SongPlayer> players = new HashMap<String, SongPlayer>();
	
	public static Set<String> getSongs() {
		return players.keySet();
	}
	
	public static Collection<SongPlayer> getSongPlayers() {
		return players.values();
	}
	
	public static Set<Entry<String, SongPlayer>> getSongsEntry() {
		return players.entrySet();
	}
	
	public static HashMap<String, SongPlayer> getMap() {
		return players;
	}
	
	public static String getSongID(SongPlayer songPlayer) {
		if (containsSongPlayer(songPlayer)) {
			for (Entry<String, SongPlayer> entry : getSongsEntry()) {
				if (entry.getValue() == songPlayer) return entry.getKey();
			}
		}
		return null;
	}
	
	public static SongPlayer getSongPlayer(String songID) {
		if (containsSong(songID)) {
			return players.get(songID);
		}
		return null;
	}
	
	public static boolean containsSong(String song) {
		return players.containsKey(song);
	}
	
	public static boolean containsSongPlayer(SongPlayer songPlayer) {
		for (SongPlayer songPlayers : players.values()) {
			if (songPlayers == songPlayer) return true;
		}
		return false;
	}
	
	public static void add(String song, SongPlayer songPlayer) {
		if (!containsSong(song)) {
			players.put(song, songPlayer);
		}
	}
	
	public static void removeSong(String song) {
		if (containsSong(song)) {
			players.remove(song);
		}
	}
	
	public static void removeSongPlayer(SongPlayer songPlayer) {
		if (containsSongPlayer(songPlayer)) {
			players.remove(getSongID(songPlayer));
		}
	}
}
