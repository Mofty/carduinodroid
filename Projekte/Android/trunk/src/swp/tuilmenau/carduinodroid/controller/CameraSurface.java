package swp.tuilmenau.carduinodroid.controller;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * @author Robin
 * @see android.view.SurfaceView
 * 
 */
public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback, OnGestureListener
{    
        private Camera camera = null;
        SurfaceHolder holder = null;
        private CameraCallback callback = null;
        private GestureDetector gesturedetector = null;
		private Context context;
		private Cam cam;
        
        /**
         * Calls the constructor of SurfaceView and initialize the SurfaceHolder
         * @param context
         * @param attrs
         * @param defStyle
         * @see android.view.SurfaceView#SurfaceView(android.content.Context, android.util.AttributeSet, int)
         */
        public CameraSurface(Context context, AttributeSet attrs, int defStyle) 
        {
        	super(context, attrs, defStyle);        
                initialize(context);
        }

        /**
         * Calls the constructor of SurfaceView and initialize the SurfaceHolder
         * @param context
         * @param camera
         * @see android.view.SurfaceView#SurfaceView(android.content.Context)
         */
        public CameraSurface(Context context, Camera camera, Cam cam) 
        {
                super(context);
                this.cam = cam;
                this.context = context;
                this.camera = camera;
                initialize(context);
        }
        
        public void setCamera(Camera camera){
            this.camera = camera;
            initialize(context);
        }

        /**
         * Calls the constructor of SurfaceView and initialize the SurfaceHolder
         * @param context
         * @param attrs
         * @see android.view.SurfaceView#SurfaceView(android.content.Context, android.util.AttributeSet)
         */
        public CameraSurface(Context context, AttributeSet attrs) 
        {
                super(context, attrs);
                
                initialize(context);
        }
        
        /**
         * Set the CameraCallback
         * @param callback the CameraCallback
         */
        public void setCallback(CameraCallback callback)
        {
                this.callback = callback;
        }
        
        /**
         * Starts the Preview
         */
        public void startPreview()
        {
                camera.startPreview();
        }
        
        /**
         * not used
         */
        public void startTakePicture()
        {
        
        }
        
        /**
         * not used
         */
        public void takePicture() 
        {
        
        }
        
        
        /* (non-Javadoc)
         * @see android.view.SurfaceHolder.Callback#surfaceChanged(android.view.SurfaceHolder, int, int, int)
         */
        public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) 
        {
        	 try {
                 camera.setPreviewDisplay(holder);
                 camera.setPreviewCallback(cam.previewcallback);
         } catch (IOException e) { e.printStackTrace(); }
        }

        
        /* (non-Javadoc)
         * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
         */
        public void surfaceCreated(SurfaceHolder holder) 
        {
                /*List<Size> bla = camera.getParameters().getSupportedPreviewSizes();

                for(int i = 0; i < bla.size(); i++)
                {
                	Log.e("cam", bla.get(i).width + ":" + bla.get(i).height + ":fps:" + camera.getParameters().getPreviewFrameRate());
                }
                List<Integer> bla2 = camera.getParameters().getSupportedPreviewFrameRates();
                for(int i = 0; i < bla2.size(); i++)
                {
                	Log.e("cam", bla2.get(i).intValue()+" ");
                }
				*/
                try {
                        camera.setPreviewDisplay(holder);
                        camera.setPreviewCallback(cam.previewcallback);
                } catch (IOException e) { e.printStackTrace(); }
        }

        
        /* (non-Javadoc)
         * @see android.view.SurfaceHolder.Callback#surfaceDestroyed(android.view.SurfaceHolder)
         */
        public void surfaceDestroyed(SurfaceHolder holder)
        {
                camera.stopPreview();
                camera.release();
                
                camera = null;
        }
        
        /* (non-Javadoc)
         * @see android.view.View#onTouchEvent(android.view.MotionEvent)
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) 
        {
                return gesturedetector.onTouchEvent(event);
        }
        
        
        /* (non-Javadoc)
         * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.MotionEvent)
         */
        public boolean onDown(MotionEvent e) 
        {
                return false;
        }

        
        /* (non-Javadoc)
         * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
         */
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) 
        {
                return false;
        }

        
        /* (non-Javadoc)
         * @see android.view.GestureDetector.OnGestureListener#onLongPress(android.view.MotionEvent)
         */
        public void onLongPress(MotionEvent e) 
        {
                startTakePicture();
        }

        
        /* (non-Javadoc)
         * @see android.view.GestureDetector.OnGestureListener#onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)
         */
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,float distanceY)
        {
                return false;
        }

        
        /* (non-Javadoc)
         * @see android.view.GestureDetector.OnGestureListener#onShowPress(android.view.MotionEvent)
         */
        public void onShowPress(MotionEvent e) 
        {
        
        }

        
        /* (non-Javadoc)
         * @see android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.view.MotionEvent)
         */
        public boolean onSingleTapUp(MotionEvent e) {
                return false;
        }

        /**
         * Initialized the SurfaceHolder
         * @param context
         */
        private void initialize(Context context) 
        {
                holder = getHolder();
                holder.addCallback(this);
                holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                if(gesturedetector == null)
                gesturedetector = new GestureDetector(this); //wozu genau brauchen wir den?
        }
}