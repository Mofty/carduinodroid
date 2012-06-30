package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.R;
import swp.tuilmenau.carduinodroid.model.LOG;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Provides methods to load Soundfiles into RAM and play then.
 * 
 * @author Paul Thorwirth & Felix Lewandowski
 * @version 1.0
 * @see SoundPool
 */
public class Sound 
{
	LOG log;
	
	SoundPool soundpool;
	AudioManager audioManager;
	int soundID;
	int volume;
	
	public Sound(Context context, LOG log)
	{
		this.log = log;
		soundpool = new SoundPool (5, AudioManager.STREAM_MUSIC, 0);
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		soundID = soundpool.load(context, R.raw.horn, 1);
	}
	
	/**
	 * Plays the SoundFile associated with the horn.
	 */
	public void horn (int ID)
	{
		volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
		soundpool.play(soundID, 1, 1, 1, 0, 1);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
		log.write(LOG.INFO, "Sound played.");
	}
}
