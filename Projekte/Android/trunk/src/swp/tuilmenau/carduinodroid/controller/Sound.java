package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

/**
 * Wraps all other classes into this class to be used by the activity.
 * 
 * @author Paul Thorwirth & Felix Lewandowski
 * @version 1.0
 * @see SoundPool
 */
public class Sound 
{
	SoundPool soundpool;
	int soundID;
	
	public Sound(Context context)
	{
		soundpool = new SoundPool (5, AudioManager.STREAM_MUSIC, 0);
		soundID = soundpool.load(context, R.raw.horn, 1);	
	}
	/**
	 * Plays the SoundFile associated with the horn.
	 */
	public void horn () // Brauchst du hier einen OnLoadComplete Listener ???Nur um den Sound abzuspielen
	{
		soundpool.play(soundID, 1, 1, 1, 0, 1);
	}
}
