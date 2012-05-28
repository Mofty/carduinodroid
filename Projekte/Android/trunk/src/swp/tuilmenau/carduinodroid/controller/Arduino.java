package swp.tuilmenau.carduinodroid.controller;

//usbmanager expose ADK as a File Descriptor
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;//hold ADK Intent, because it is not always connected
import android.app.PendingIntent;//snipped for brevity this is precisely what USB-manager expose
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelFileDescriptor;//snipped for brevity what we needed to read from ADK, use future because I only have a android 2.3.7 OS with me
import com.android.future.usb.UsbManager;
import com.android.future.usb.UsbAccessory;

public class Arduino {
	
	private Controller_Android controller_Android;
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
	
	public Arduino(Context context,Controller_Android controller_android){
		controller_Android = controller_android;
		
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
	                    	controller_Android.log.write("Permission Denied For Accessory"+ accessory);
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
            controller_Android.log.write("Accessory Opened");
     
        }
        else {
        	controller_Android.log.write("Accessory Open Fail");
        }
    }
    
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
                controller_Android.log.write("Geschwindigkeit: "+speed+" und Richtung: "+dir+" gesendet.");
            }
            catch (IOException e){
                controller_Android.log.write("Senden fehlgeschlagen.");
            }
        }      
    }
    

}
