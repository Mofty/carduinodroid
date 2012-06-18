package swp.tuilmenau.carduinodroid.controller;

/**
* The method starts and stops recording the Sound. 
* After that, it will we saved at the smartphone.
* @since version 1.00
* @author Christian Schulze
*/

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
 
	/**
	 * This method create the soundfile initialize the recorder. 
	 * 
	 * @param Log will be informed by every single change.
	 */
	
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
		catch (IllegalArgumentException e) {
			log.write(LOG.WARNING, "Illegal sound argument");
		} 
		catch (IllegalStateException e) {
			log.write(LOG.WARNING, "Illegal sound state");
		}
	} 
	
	/**
	 * This method starts the recording.
	 */
	
	public void startRecord()
	{
		time.setToNow();
		try 
		{
			outfile = File.createTempFile("Sound_"+time.month+time.monthDay+"_"+time.hour+time.minute+time.second, ".3gp", storageDir);
		} catch (IOException e) {}
			recorder.setOutputFile(outfile.getAbsolutePath());
			log.write(LOG.WARNING, "Could not create soundfile");
		try 
		{
			recorder.prepare();
			recorder.start();
			log.write(LOG.INFO, "Start recording");
		} 
		catch (IllegalStateException e) {
			log.write(LOG.WARNING, "Invalid recorder state");
		} 
		catch (IOException e) {
			log.write(LOG.WARNING, "Could not create soundfile");
		}
	}
 
	/**
	 * This method stops recording.
	 */
	
	public void stopRecord() 
	{
		recorder.stop();
		recorder.reset();
		log.write(LOG.INFO, "Stop recording");
	}

}
