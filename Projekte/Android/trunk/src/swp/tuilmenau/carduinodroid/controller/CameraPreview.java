package swp.tuilmenau.carduinodroid.controller;

import java.io.OutputStream;

import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.*;
import android.util.Log;

public class CameraPreview implements PreviewCallback
{
	private OutputStream outputStream;
	private YuvImage buffer;
	
	public CameraPreview() 
	{
		Log.v("previewframe", "erstellt");
	}

	public void setOutputstream(OutputStream noutputStream) {
		outputStream = noutputStream;
		Log.v("previewframe", "outputstream erstellt" + outputStream.toString());
	}

	public void onPreviewFrame(byte[] data, Camera camera) {
		Log.v("previewframe", data[12]+ "previewframe");
		Size temp = camera.getParameters().getPreviewSize();
		buffer = new YuvImage(data, camera.getParameters().getPreviewFormat(), temp.width, temp.height, null);
		Rect rect = new Rect(0,0, temp.width, temp.height);
		if(outputStream != null)
		buffer.compressToJpeg(rect, 50, outputStream);
	}
}
