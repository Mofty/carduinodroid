package swp.tuilmenau.carduinodroid.controller;

import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;

public class MyPreviewCallback implements PreviewCallback {

	private Cam cam;

	public MyPreviewCallback(Cam cam) {
		this.cam = cam;
	}

	public void onPreviewFrame(byte[] data, Camera camera) {
		cam.onPreviewFrame(data, camera);
	}

}
