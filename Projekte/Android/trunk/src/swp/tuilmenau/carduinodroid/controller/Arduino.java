package swp.tuilmenau.carduinodroid.controller;

//usbmanager expose ADK as a File Descriptor
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import swp.tuilmenau.carduinodroid.model.LOG;
import android.app.Activity;//hold ADK Intent, because it is not always connected
import android.app.PendingIntent;//snipped for brevity this is precisely what USB-manager expose
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.ParcelFileDescriptor;//snipped for brevity what we needed to read from ADK, use future because I only have a android 2.3.7 OS with me
import com.android.future.usb.UsbManager;
import com.android.future.usb.UsbAccessory;

/**
 * This method is about the control of our arduino controller.
 * But it is still untested.
 * 
 * @version 12.06.12
 * @author Lars
 */ 

public class Arduino {
	
	private LOG log;
	// UsbManager to check if ADK is connected
    private UsbManager mUsbManager;
    // To read permission from ADK
    private PendingIntent mPermissionIntent;
    private boolean mPermissionRequestPending;
    // This is the permission
    private static final String ACTION_USB_PERMISSION = "com.google.android.ADKTestProject.action.USB_PERMISSION";
    // snipped for brevity
    // This is where we read and write from ADK
    FileInputStream mFileInputStream;
    FileOutputStream mFileOutputStream;
    ParcelFileDescriptor mFileDescriptor;
    // Accesory!!!
    UsbAccessory mUsbAccessory;
    private final BroadcastReceiver mUsbReceiver;
	
	public Arduino(Context context, LOG Log){
		log = Log;
		
		mUsbReceiver = new BroadcastReceiver(){
			 
	        @Override
	        public void onReceive(Context context, Intent intent) {
	            String action = intent.getAction();
	            // permission ok?
	            if(ACTION_USB_PERMISSION.equals(action)){
	                synchronized (this) {
	                    // get accessory definition
	                    UsbAccessory accessory = UsbManager.getAccessory(intent);
	                    // any extra permission?
	                    if (intent.getBooleanExtra(
	                            UsbManager.EXTRA_PERMISSION_GRANTED, false)){
	                        // start accessory!!!!
	                        openAccessory(accessory);
	                    }
	                    else {
	                        // oops
	                    	log.write(LOG.WARNING, "Permission Denied For Accessory"+ accessory);
	                    }
	                    mPermissionRequestPending = false;
	                }
	            }
	            // it is not connected
	            else if(UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)) {
	                // get accessory anyway
	                UsbAccessory accessory = UsbManager.getAccessory(intent);
	                // accessory is still not close cleanly
	                if (accessory != null && accessory.equals(mUsbAccessory)){
	                    closeAccessory();
	                }
	            }
	        }
	    };
	}
    
	// ***** Open Accessory ***************************************
	/** 
	 * It opens the accessory to be able to send commands with your usb
	 * port. 
	 */
    private void openAccessory(UsbAccessory accessory){
        // the interface is a file file descriptor
        mFileDescriptor = mUsbManager.openAccessory(accessory);
        if (mFileDescriptor != null) {
            mUsbAccessory = accessory;
            // get the file descriptor
            FileDescriptor fd = mFileDescriptor.getFileDescriptor();
            // set one to read
            mFileInputStream = new FileInputStream(fd);
            // set one to write
            mFileOutputStream = new FileOutputStream(fd);
     
            //Thread thread = new Thread(null,this,"ADKTestProject");
            //thread.start();
            log.write(LOG.INFO, "Accessory Opened");
     
        }
        else {
        	log.write(LOG.WARNING, "Accessory Open Fail");
        }
    }
    
	// ***** Close Accessory ***************************************
	/** 
	 * It closes the accessory on your usb port. If u don't need it.
	 */
    private void closeAccessory(){
        try{
            if (mFileDescriptor != null){
                mFileDescriptor.close();
            } 
        }
        catch (IOException e) {
 
        }
        finally {
            mFileDescriptor = null;
            mUsbAccessory = null;
        }
    }
	
	// ***** Send Command ***************************************
	/** 
	 * With this command you would be able to send the direction and
	 * speed to your arduino.
	 */
    public void SendCommand(int speed, int dir){
    	byte[] buffer = new byte[2]; //Erstellen eines Array der zu übergebenden Daten
    	//Verifizieren der Größe
    	if(speed > 10){
            speed = 10;}
        if(dir > 12){
            dir = 12;}
        buffer[0] = (byte)speed;
        buffer[1] = (byte)dir;
        
        if (mFileOutputStream != null && buffer[1] != -1){
            try{
                // write it
                mFileOutputStream.write(buffer);
                log.write(LOG.INFO, "Speed: "+speed+" and direction: "+dir+" sent to Arduino.");
            }
            catch (IOException e){
                log.write(LOG.WARNING, "Failed to send commands to Arduino.");
            }
        }      
    }   
}
