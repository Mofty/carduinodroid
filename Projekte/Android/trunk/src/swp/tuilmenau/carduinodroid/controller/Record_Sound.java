package swp.tuilmenau.carduinodroid.controller;

import java.io.File;
import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Environment;
import android.text.format.Time;

import swp.tuilmenau.carduinodroid.model.LOG;

/**
 * Used to record audio and save it to the sdcard. 
 * 
 * @version 1.0
 * @author Christian Schulze & Paul Thorwirth
 * @see MediaRecorder
 */ 
public class Record_Sound 
{
	private MediaRecorder recorder;

	private File outfile;
	private Time time;
	private File storageDir;

	private LOG log;

	/**
	 * Creates the directories for saving the audio-files.
	 * 
	 * @param Log The Log
	 */
	public Record_Sound(LOG Log) 
	{
		this.log = Log;
		storageDir = new File(Environment.getExternalStorageDirectory(), "/carduinodroid/Recording");
		storageDir.mkdirs();
		time = new Time();		
	} 

	/**
	 * Initializes the MediaRecorder.
	 * 
	 * @see MediaRecorder
	 */
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
			recorder.prepare();
		}
		catch (IOException e) {log.write(LOG.WARNING, "Could not create soundfile");}
		catch (IllegalArgumentException e) {log.write(LOG.WARNING, "Illegal sound argument");} 
		catch (IllegalStateException e) {log.write(LOG.WARNING, "Illegal sound state");}
	}
	/**
	 * Starts the recording.
	 */
	public void start()
	{
		init();
		try 
		{
			recorder.start();
			log.write(LOG.INFO, "Starting recording");
		} 
		catch (IllegalStateException e) { log.write(LOG.WARNING, "Invalid recorder state"); } 
	}


	/**
	 * Stops the Recording.
	 */
	public void stop()
	{
		recorder.stop();
		recorder.reset();
		log.write(LOG.INFO, "Stopped recording");
	}
}
