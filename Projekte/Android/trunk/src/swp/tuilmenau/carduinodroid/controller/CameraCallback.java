package swp.tuilmenau.carduinodroid.controller;

import android.hardware.Camera;

/**
 * @author Robin
 * @see android.hardware.Camera.PreviewCallback
 * @see android.hardware.Camera.ShutterCallback
 * @see android.hardware.Camera.PictureCallback
 */
public interface CameraCallback {

        public abstract void onPreviewFrame(byte[] data, Camera camera);

        public abstract void onShutter();

        public abstract void onRawPictureTaken(byte[] data, Camera camera);

        public abstract void onJpegPictureTaken(byte[] data, Camera camera);

        public abstract String onGetVideoFilename();
}