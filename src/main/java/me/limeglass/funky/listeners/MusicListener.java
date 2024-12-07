package me.limeglass.funky.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.xxmicloxx.NoteBlockAPI.NoteBlockAPI;
import com.xxmicloxx.NoteBlockAPI.event.SongDestroyingEvent;
import com.xxmicloxx.NoteBlockAPI.event.SongEndEvent;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.event.SongStoppedEvent;

import me.limeglass.funky.FunkySk;
import me.limeglass.funky.utils.MusicManager;

import java.util.Map.Entry;
import org.bukkit.event.EventHandler;

public class MusicListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerQuitEvent event) {
		if (FunkySk.getInstance().getConfig().getBoolean("PlayerDisconnectSong", true)) NoteBlockAPI.stopPlaying(event.getPlayer());
	}
	
	@EventHandler
	public void onSongStop(SongStoppedEvent event) {
		check(event.getSongPlayer());
	}
	
	@EventHandler
	public void onSongEnd(SongEndEvent event) {
		check(event.getSongPlayer());
	}
	
	@EventHandler
	public void onSongDestory(SongDestroyingEvent event) {
		check(event.getSongPlayer());
	}
	
	private void check(SongPlayer songPlayer) {
		for (Entry<String, SongPlayer> entry : MusicManager.getSongsEntry()) {
			if (entry.getValue() == songPlayer) {
				MusicManager.removeSong(entry.getKey());
			}
		}
	}
}
