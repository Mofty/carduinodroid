package swp.tuilmenau.carduinodroid.controller;

import java.io.File;
import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Environment;
import android.text.format.Time;

import swp.tuilmenau.carduinodroid.model.LOG;

 
public class Record_Sound 
{
	private MediaRecorder recorder = new MediaRecorder();
 
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
		} catch (IOException e) {}
		recorder.setOutputFile(outfile.getAbsolutePath());
		try 
		{
			recorder.prepare();
			recorder.start();
		} 
		catch (IllegalStateException e) {} 
		catch (IOException e) {}
	}
 
	public void stopRecord() 
	{
		recorder.stop();
		recorder.reset();
	}

}
