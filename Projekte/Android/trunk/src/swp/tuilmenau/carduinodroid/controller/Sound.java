package swp.tuilmenau.carduinodroid.controller;

import swp.tuilmenau.carduinodroid.*;
import android.content.Context;
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
		soundpool = new SoundPool (1, 3, 0);
		soundID = soundpool.load(context, R.raw.horn, 1);		
	}
	/**
	 * Plays the SoundFile associated with the horn.
	 */
	public void horn ()
	{
		OnLoadCompleteListener onLoadCompleteListener = new OnLoadCompleteListener()
		{
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) 
			{
				soundPool.play(soundID, 1, 1, 1, 0, 1);
			}	
		};
		soundpool.setOnLoadCompleteListener(onLoadCompleteListener);
	}
}
