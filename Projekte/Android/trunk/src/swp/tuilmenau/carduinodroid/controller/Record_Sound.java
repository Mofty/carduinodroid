package swp.tuilmenau.carduinodroid.controller;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.text.format.Time;

import swp.tuilmenau.carduinodroid.model.LOG;

 
public class Record_Sound 
{
	private MediaRecorder recorder = new MediaRecorder();
	private MediaPlayer player = new MediaPlayer();
 
	private boolean recording = false;
	private boolean playing = false;
	private File outfile = null;
	Time time;
	File storageDir;
	
	LOG log;
 
	public Record_Sound(LOG Log) 
	{
		log = Log;
		try 
		{
			// the soundfile
			storageDir = new File(Environment.getExternalStorageDirectory(), "/carduinodroid/Recording");
			storageDir.mkdirs();
			time = new Time();
			
			// init recorder
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			
			// init player
			player.setDataSource(outfile.getAbsolutePath());
		} 
		catch (IOException e) {
		} 
		catch (IllegalArgumentException e) {} 
		catch (IllegalStateException e) {}
	} 
	
	public void startRecord()
	{
		time.setToNow();
		try 
		{
			outfile = File.createTempFile("Sound_"+time.month+time.monthDay+"_"+time.hour+time.minute+time.second, ".3gp", storageDir);
		} catch (IOException e1) {}
		recorder.setOutputFile(outfile.getAbsolutePath());
		try 
		{
			recorder.prepare();
			recorder.start();
			recording = true;
		} 
		catch (IllegalStateException e) {} 
		catch (IOException e) {}
	}
 
	public void stopRecord() 
	{
		recorder.stop();
		recorder.reset();
		recorder.release();
		recording = false;
	}
 
	// zu testzwecken
	private void startPlay() 
	{
		try 
		{
			playing = true;
			player.prepare();
			player.start();
		} 
		catch (IllegalStateException e) {} 
		catch (IOException e) {}
	}
 
	private void stopPlay() 
	{
		player.stop();
		player.reset();
		player.release();
		playing = false;
	}
}
