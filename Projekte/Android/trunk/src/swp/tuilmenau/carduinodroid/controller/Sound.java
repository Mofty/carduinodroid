package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.R;
import swp.tuilmenau.carduinodroid.model.LOG;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Provides methods to load Soundfiles into RAM and play then.
 * 
 * @author Paul Thorwirth & Felix Lewandowski
 * @version 1.0
 * @see SoundPool
 * @see AudioManager
 */
public class Sound 
{
	LOG log;
	
	SoundPool soundpool;
	AudioManager audioManager;
	int soundID;
	int volume;
	
	/**
	 * Initializes the SoundPool and loads the audio-file for the horn. 
	 * Sets the Media-Volume to Maximum.
	 * 
	 * @param activity The current Activity
	 * @param log The Log
	 */
	public Sound(Activity activity, LOG log)
	{
		this.log = log;
		soundpool = new SoundPool (5, AudioManager.STREAM_MUSIC, 0);
		audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
		soundID = soundpool.load(activity, R.raw.horn, 1);
		volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
		
	}
	
	/**
	 * Plays the SoundFile associated with the horn.
	 */
	public void horn()
	{
		soundpool.play(soundID, 1, 1, 1, 0, 1);
		log.write(LOG.INFO, "Sound played.");
	}
	
	/**
	 * Sets the Media-Volume to the previously saved value.
	 */
	public void resetVolume()
	{
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
	}
}
