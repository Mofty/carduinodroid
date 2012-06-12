package swp.tuilmenau.carduinodroid.controller;

import java.io.OutputStream;

import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;

public class CameraPreview implements PreviewCallback{

	private OutputStream outputStream;
	private YuvImage buffer;
	
	public CameraPreview() {
		
	}


	public void setOutputstream(OutputStream noutputStream) {
		outputStream = noutputStream;
	}


	public void onPreviewFrame(byte[] data, Camera camera) {
		Size temp = camera.getParameters().getPreviewSize();
		buffer = new YuvImage(data, camera.getParameters().getPreviewFormat(), temp.width, temp.height, null);
		Rect rect = new Rect(0,0, temp.width, temp.height);
		if(outputStream != null)
		buffer.compressToJpeg(rect, 50, outputStream);
	}


    
}
