package swp.tuilmenau.carduinodroid.controller;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;

 
public class Record_Sound extends Activity {
	private MediaRecorder recorder = new MediaRecorder();
	private MediaPlayer player = new MediaPlayer();
 
	private boolean recording = false;
	private boolean playing = false;
	private File outfile = null;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		try {
			// the soundfile
			File storageDir = new File(Environment.getExternalStorageDirectory(), "swp.tuilmenau.carduinodroid.controller");
			storageDir.mkdir();
			outfile = File.createTempFile("Sound", ".3gp", storageDir);
 
			// init recorder
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			recorder.setOutputFile(outfile.getAbsolutePath());
 
			// init player
			player.setDataSource(outfile.getAbsolutePath());
		} catch (IOException e) {

		} catch (IllegalArgumentException e) {

		} catch (IllegalStateException e) {
			
		}
 
	} 
	private void startRecord() {
		try {
			recorder.prepare();
			recorder.start();
			recording = true;
		} catch (IllegalStateException e) {
				;
		} catch (IOException e) {
		}
	}
 
	private void stopRecord() {
		recorder.stop();
		recorder.reset();
		recorder.release();
		recording = false;
	}
 
	private void startPlay() {
		try {
			playing = true;
			player.prepare();
			player.start();
		} catch (IllegalStateException e) {
			
		} catch (IOException e) {
			
		}
	}
 
	private void stopPlay() {
		player.stop();
		player.reset();
		player.release();
		playing = false;
	}
}
