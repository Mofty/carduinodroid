package swp.tuilmenau.carduinodroid.controller;

import java.io.File;
import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Environment;
import android.text.format.Time;

import swp.tuilmenau.carduinodroid.model.LOG;

/**
 * The method starts and stops recording the Sound. 
 * After that, it will we saved at the Smartphone.
 * 
 * @version 1.0
 * @author Christian Schulze & Paul Thorwirth
 * @see MediaRecorder
 */ 
public class Record_Sound 
{
	private MediaRecorder recorder;

	private File outfile = null;
	Time time;
	File storageDir;

	LOG log;

	public Record_Sound(LOG Log) 
	{
		this.log = Log;
		storageDir = new File(Environment.getExternalStorageDirectory(), "/carduinodroid/Recording");
		storageDir.mkdirs();
		time = new Time();		
	} 

	private void init()
	{
		time.setToNow();
		recorder = new MediaRecorder();
		try 
		{	
			outfile = new File(storageDir,"REC_"+ time.format("%d%m_%H%M%S") + ".mp4");
			outfile.createNewFile();
			outfile.canWrite();
			outfile.canRead();
			
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
			recorder.setOutputFile(outfile.getAbsolutePath());
		}
		catch (IOException e) {log.write(LOG.WARNING, "Could not create soundfile");}
		catch (IllegalArgumentException e) {log.write(LOG.WARNING, "Illegal sound argument");} 
		catch (IllegalStateException e) {log.write(LOG.WARNING, "Illegal sound state");}
	}
	/**
	 * Starts the recording.
	 */
	public void startRecord()
	{
		init();
		try 
		{
			recorder.prepare();
			recorder.start();
			log.write(LOG.INFO, "Start recording");
		} 
		catch (IllegalStateException e) {log.write(LOG.WARNING, "Invalid recorder state");} 
		catch (IOException e) {log.write(LOG.WARNING, "Could not create soundfile");}
	}


	/**
	 * Stops the Recording.
	 */
	public void stopRecord ()
	{
		recorder.stop();
		recorder.reset();
	}
}
