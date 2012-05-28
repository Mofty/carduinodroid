package swp.tuilmenau.carduinodroid.controller;

import android.content.Context;
import com.android.future.usb.UsbAccessory;
import com.android.future.usb.UsbManager;

public class Arduino {
	UsbManager Manager = (UsbManager) getSystemService (Context.USB_SERVICE);
}
