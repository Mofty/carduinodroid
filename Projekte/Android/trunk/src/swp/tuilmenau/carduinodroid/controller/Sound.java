package swp.tuilmenau.carduinodroid.controller;
import android.content.Context;
import android.media.SoundPool;


public class Sound {
	SoundPool soundpool;
	int soundID;
	
	public Sound(Context context){
		soundpool = new SoundPool (1, 3, 0);
		soundID = soundpool.load(context, resId, 1);
		
	}
	public void horn (){
		soundpool.play(soundID, 1, 1, 1, 0, 1);
	}

}
