package swp.tuilmenau.carduinodroid.controller;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.GestureDetector.OnGestureListener;


public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback, OnGestureListener{    
        private Camera camera = null;
        private SurfaceHolder holder = null;
        private CameraCallback callback = null;
        private GestureDetector gesturedetector = null;
        
        public CameraSurface(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
                
                initialize(context);
        }

        public CameraSurface(Context context, Camera camera) {
                super(context);
                this.camera = camera;
                initialize(context);
        }

        public CameraSurface(Context context, AttributeSet attrs) {
                super(context, attrs);
                
                initialize(context);
        }
        
        public void setCallback(CameraCallback callback){
                this.callback = callback;
        }
        
        public void startPreview(){
                camera.startPreview();
        }
        
        public void startTakePicture(){
                camera.autoFocus(new AutoFocusCallback() {
                        
                        public void onAutoFocus(boolean success, Camera camera) {
                                takePicture();
                        }
                });
        }
        
        public void takePicture() {
                camera.takePicture(
                                new ShutterCallback() {
                                        
                                        public void onShutter(){
                                                if(null != callback) callback.onShutter();
                                        }
                                },
                                new PictureCallback() {
                                        
                                        public void onPictureTaken(byte[] data, Camera camera){
                                                if(null != callback) callback.onRawPictureTaken(data, camera);
                                        }
                                },
                                new PictureCallback() {
                                        
                                        public void onPictureTaken(byte[] data, Camera camera){
                                                if(null != callback) callback.onJpegPictureTaken(data, camera);
                                        }
                                });
        }
        
        
        public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
                if(null != camera)
                {
                        camera.startPreview();
                }
        }

        
        public void surfaceCreated(SurfaceHolder holder) {
                List<Size> bla = camera.getParameters().getSupportedPreviewSizes();

                for(int i = 0; i < bla.size(); i++)
                {
                	Log.e("cam", bla.get(i).width + ":" + bla.get(i).height + ":fps:" + camera.getParameters().getPreviewFrameRate());
                }

                try {
                        camera.setPreviewDisplay(holder);
                        camera.setPreviewCallback(new Camera.PreviewCallback() {
                                
                                public void onPreviewFrame(byte[] data, Camera camera) {
                                        if(null != callback) callback.onPreviewFrame(data, camera);
                                }
                        });
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        
        public void surfaceDestroyed(SurfaceHolder holder) {
                camera.stopPreview();
                camera.release();
                
                camera = null;
        }
        
        @Override
        public boolean onTouchEvent(MotionEvent event) {
                return gesturedetector.onTouchEvent(event);
        }
        
        
        public boolean onDown(MotionEvent e) {
                return false;
        }

        
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
        }

        
        public void onLongPress(MotionEvent e) {
                startTakePicture();
        }

        
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,float distanceY) {
                return false;
        }

        
        public void onShowPress(MotionEvent e) {
        }

        
        public boolean onSingleTapUp(MotionEvent e) {
                return false;
        }

        private void initialize(Context context) {
                holder = getHolder();
                
                holder.addCallback(this);
                holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                
                gesturedetector = new GestureDetector(this);
        }
}