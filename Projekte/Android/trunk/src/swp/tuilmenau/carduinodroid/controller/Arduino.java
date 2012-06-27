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
import android.content.IntentFilter;
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

public class Arduino{
	
	private LOG log;
	Activity activity;
	IntentFilter usbFilter;
	// UsbManager to check if ADK is connected
    private UsbManager mUsbManager;
    // To read permission from ADK
    private PendingIntent mPermissionIntent;
    private boolean mPermissionRequestPending;
    // This is the permission
    private static final String ACTION_USB_PERMISSION = "swp.tuilmenau.carduinodroid.USB_PERMISSION";
    // snipped for brevity
    // This is where we read and write from ADK
    FileInputStream mInputStream;
    FileOutputStream mOutputStream;
    ParcelFileDescriptor mFileDescriptor;
    // Accesory!!!
    UsbAccessory mAccessory;
    public BroadcastReceiver mUsbReceiver;
	
	public Arduino(Activity nactivity, LOG Log){
		log = Log;
		activity = nactivity;
		
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
	                if (accessory != null && accessory.equals(mAccessory)){
	                    closeAccessory();
	                }
	            }
	        }
		};
	    
		mUsbManager = UsbManager.getInstance(activity);
		mPermissionIntent = PendingIntent.getBroadcast(activity, 0, new Intent(ACTION_USB_PERMISSION), 0);
		IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
		filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
		activity.registerReceiver(mUsbReceiver, filter);
 
		UsbAccessory[] accessories = mUsbManager.getAccessoryList();
		UsbAccessory accessory = (accessories == null ? null : accessories[0]);
		if (accessory != null) {
			if (mUsbManager.hasPermission(accessory)) {
				openAccessory(accessory);
			} else {
				synchronized (mUsbReceiver) {
					if (!mPermissionRequestPending) {
						mUsbManager.requestPermission(accessory,mPermissionIntent);
						mPermissionRequestPending = true;
					}
				}
			}
		} else {
			log.write(LOG.WARNING, "mAccessory is null");
		}
		
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
            mAccessory = accessory;
            // get the file descriptor
            FileDescriptor fd = mFileDescriptor.getFileDescriptor();
            // set one to read
            mInputStream = new FileInputStream(fd);
            // set one to write
            mOutputStream = new FileOutputStream(fd);
     
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
    public void closeAccessory(){
        try{
            if (mFileDescriptor != null){
                mFileDescriptor.close();
            } 
        }
        catch (IOException e) {
 
        }
        finally {
            mFileDescriptor = null;
            mAccessory = null;
        }
    }
	
	// ***** Send Command ***************************************
	/** 
	 * With this command you would be able to send the direction and
	 * speed to your arduino.
	 */
    public void SendCommand(boolean front,int speed, boolean right,int dir){
    	byte[] buffer = new byte[4]; //Erstellen eines Array der zu übergebenden Daten
    	//Verifizieren der Größe
    	if(speed > 16){
            speed = 16;}
        if(dir > 8){
            dir = 8;}
        if(front) buffer[0] = 1;
        	else buffer[0]=0;
        buffer[1] = (byte)speed;
        if(right) buffer[2] = 1;
        	else buffer[2] = 0;
        buffer[3] = (byte)dir;
        		
        
        if (mOutputStream != null){
            try{
                // write it
                mOutputStream.write(buffer);
                log.write(LOG.INFO, "Speed: "+speed+"(front: "+front+") and direction: "+dir+"(right: "+right+") sent to Arduino.");
            }
            catch (IOException e){
                log.write(LOG.WARNING, "Failed to send commands to Arduino.");
            }
        }      
    }   
}
