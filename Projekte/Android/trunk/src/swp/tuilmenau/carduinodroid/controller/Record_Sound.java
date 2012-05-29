package swp.tuilmenau.carduinodroid.controller;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;

import swp.tuilmenau.carduinodroid.model.LOG;

 
public class Record_Sound 
{
	private MediaRecorder recorder = new MediaRecorder();
	private MediaPlayer player = new MediaPlayer();
 
	private boolean recording = false;
	private boolean playing = false;
	private File outfile = null;
	
	LOG log;
 
	public Record_Sound(LOG Log) 
	{
		log = Log;
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
	public void startRecord() {
		try {
			recorder.prepare();
			recorder.start();
			recording = true;
		} catch (IllegalStateException e) {} catch (IOException e) {}
	}
 
	public void stopRecord() {
		recorder.stop();
		recorder.reset();
		recorder.release();
		recording = false;
	}
 
	// zu testzwecken
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
