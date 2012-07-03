package swp.tuilmenau.carduinodroid.controller;


import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

import swp.tuilmenau.carduinodroid.model.LOG;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import com.android.future.usb.UsbManager;
import com.android.future.usb.UsbAccessory;

/**
 * Establishes a connection with the Arduino-Board and provides methods to send commands to control the car.
 * 
 * @version 1.0
 * @author Paul Thorwirth
 * @author Lars Vogel
 * 
 * @see UsbManager
 * @see UsbAccessory
 */ 

public class Arduino
{	
    private static final String ACTION_USB_PERMISSION = "swp.tuilmenau.carduinodroid.action.USB_PERMISSION";
    
	private LOG log;
	private Activity activity;
	private IntentFilter usbFilter;
    private UsbManager mUsbManager;
    private PendingIntent mPermissionIntent;
    private boolean mPermissionRequestPending;
    private FileOutputStream mOutputStream;
    private ParcelFileDescriptor mFileDescriptor;
    private UsbAccessory mAccessory;
    public BroadcastReceiver mUsbReceiver;
	
    /**
     * Defines and registers a BroadcastReciever to react on USB-Events.
     * Also the USB-Permission is requested.
     * 
     * @param nactivity The current Activity
     * @param Log The Log
     * 
     * @see BroadcastReciever
     */
	public Arduino(Activity nactivity, LOG Log)
	{
		log = Log;
		activity = nactivity;
		
		mUsbReceiver = new BroadcastReceiver()
		{	 
	        @Override
	        public void onReceive(Context context, Intent intent)
	        {
	            String action = intent.getAction();
	            if(ACTION_USB_PERMISSION.equals(action)){
	                synchronized (this)
	                {
	                    UsbAccessory accessory = UsbManager.getAccessory(intent);
	                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false))
	                    {
	                        openAccessory(accessory);
	                    }
	                    else 
	                    {
	                        log.write(LOG.WARNING, "Permission Denied For Accessory"+ accessory);
	                    }
	                    mPermissionRequestPending = false;
	                }
	            }
	            else if(UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action))
	            {
	                UsbAccessory accessory = UsbManager.getAccessory(intent);
	                if (accessory != null && accessory.equals(mAccessory))
	                {
	                    closeAccessory();
	                }
	            }
	        }
		};
	    
		mUsbManager = UsbManager.getInstance(activity);
		mPermissionIntent = PendingIntent.getBroadcast(activity, 0, new Intent(ACTION_USB_PERMISSION), 0);
		usbFilter = new IntentFilter(ACTION_USB_PERMISSION);
		usbFilter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
		activity.registerReceiver(mUsbReceiver, usbFilter);
 
		UsbAccessory[] accessories = mUsbManager.getAccessoryList();
		UsbAccessory accessory = (accessories == null ? null : accessories[0]);
		if (accessory != null) 
		{
			if (mUsbManager.hasPermission(accessory)) 
			{
				openAccessory(accessory);
			} 
			else 
			{
				synchronized (mUsbReceiver) 
				{
					if (!mPermissionRequestPending) 
					{
						mUsbManager.requestPermission(accessory,mPermissionIntent);
						mPermissionRequestPending = true;
					}
				}
			}
		}
		else 
		{
			log.write(LOG.WARNING, "mAccessory is null");
		}
	}
    		
	/**
	 * Opens and prepares an Accessory.
	 * 
	 * @param accessory The Accessory to open.
	 */
	private void openAccessory(UsbAccessory accessory)
	{
        mFileDescriptor = mUsbManager.openAccessory(accessory);
        if (mFileDescriptor != null) 
        {
            mAccessory = accessory;
            FileDescriptor fd = mFileDescriptor.getFileDescriptor();
            mOutputStream = new FileOutputStream(fd);
            log.write(LOG.INFO, "Accessory Opened");
        }
        else 
        {
        	log.write(LOG.WARNING, "Accessory Open Fail");
        }
    }
    
	/** 
	 * It closes the accessory on your USB-Port.
	 */
    public void closeAccessory()
    {
        try
        {
            if (mFileDescriptor != null)
            {
                mFileDescriptor.close();
            } 
        }
        catch (IOException e) { }
        finally 
        {
            mFileDescriptor = null;
            mAccessory = null;
        }
    }
	
	/**
	 * Sends a control command to the Arduino-Board.
	 * 
	 * @param front Determines whether to drive forward or backward.
	 * @param speed The Speed at wich the car should drive. (Range 0 - 16)
	 * @param right Determines whether to turn left or right.
	 * @param dir The Angle at which the car should turn. (Range 0 - 8)
	 */
    public void SendCommand(boolean front,int speed, boolean right,int dir)
    {
    	byte[] buffer = new byte[4]; 
    	if(speed > 16)
    	{
            speed = 16;
        }
        if(dir > 8)
        {
            dir = 8;
        }
        if(front) buffer[0] = 1;
        	else buffer[0]=0;
        buffer[1] = (byte)speed;
        if(right) buffer[2] = 1;
        	else buffer[2] = 0;
        buffer[3] = (byte)dir;
        		
        Log.e("arduino", front+" "+ speed + " " + right + " "+ dir);
        if (mOutputStream != null)
        {
            try
            {
                mOutputStream.write(buffer);
                log.write(LOG.INFO, "Speed: "+speed+"(front: "+front+") and direction: "+dir+"(right: "+right+") sent to Arduino.");
            }
            catch (IOException e)
            {
                log.write(LOG.WARNING, "Failed to send commands to Arduino.");
            }
        }      
    }   
}
