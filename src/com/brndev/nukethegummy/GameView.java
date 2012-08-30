package com.brndev.nukethegummy;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//import com.revmob.RevMobAds;


public class GameView extends SurfaceView {
	
	private List<Bitmap> bits = new ArrayList<Bitmap>();

    private SurfaceHolder holder;

    private GameLoopThread gameLoopThread;
    
    private Resources res = getResources();
    
    private int Width = 1, Height = 1;

    private Boolean backgroundReady = false;
    
    final BitmapFactory.Options options = new BitmapFactory.Options();

    private long l = 0;
    
    private Boolean bo = false, yo = false;
    
    private int level = 0;

    private Bitmap bit;
    
    private Boolean freeplay, kidsplay;
    
    private int gummyColor=0;

    
    public void createScaleBits() {
    	
    	((NukeTheGummyActivity) getContext()).getIntent().getExtras().getInt("");

		if(Width>1 && Height>1)
		{
	    	if(yo == false)
	    	{
	    		//put levels in array list
		    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.re_microwave, options)));
		    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.openmicrowave,options)));
		    	
		    	if(gummyColor==0) {
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.greenopenmicro,options)));
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.greeninmicro,options)));
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.greeninmicro2,options)));
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.greeninmicro3,options)));
		    	}
		    	else if(gummyColor==1) {
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.orange2openmicro,options)));
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.orange2inmicro,options)));
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.orange2inmicro2,options)));
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.orange2inmicro3,options)));
		    	}
		    	else {
		    		bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.greenopenmicro,options)));
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.greeninmicro,options)));
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.greeninmicro2,options)));
			    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.greeninmicro3,options)));
		    	}

		    	yo = true;
	    	}
		}
    }
    
    
    
   public void setGummyColor(int colorIn) {
	   gummyColor = colorIn;
   }
    
	    	
	    	public Bitmap scaleBits(Bitmap b) {

	    			bit = b;
	    			
		    		{{ bit = Bitmap.createScaledBitmap(b,Width,Height,true);}};
		    		
		    		return bit;

	    	}


	    	private boolean blue=false,green=false;
	    	
	    	public void setColor(boolean b, boolean bb) {
	    		blue=b;
	    		green=bb;
	    	}

    
    
    public GameView(Context context, AttributeSet attributeSet) {
    	

          super(context, attributeSet);
          
      		freeplay=false;
      		
      		kidsplay=false;
      		
            //TapForTap.setDefaultAppId("1458b3f0-c351-012f-fada-4040d804a637");
            //TapForTap.checkIn(((AndroidPopCompleteActivity) getContext()));

            //final AdView adView = new AdView(((AndroidPopCompleteActivity) getContext()));
            
            //adView.setHeight(100);
            //adView.setWidth(LayoutParams.FILL_PARENT);
            


          gameLoopThread = new GameLoopThread(this);

          holder = getHolder();

          holder.addCallback(new SurfaceHolder.Callback() {



                 @Override

                 public void surfaceDestroyed(SurfaceHolder holder) {

                        boolean retry = true;

                        gameLoopThread.setRunning(false);

                        while (retry) {

                               try {

                                     gameLoopThread.join();

                                     retry = false;

                               } catch (InterruptedException e) {

                               }

                        }

                 }



                 @Override

                 public void surfaceCreated(SurfaceHolder holder) {

                        gameLoopThread.setRunning(true);

                        gameLoopThread.start();
                        

                 }
                 



                 @Override

                 public void surfaceChanged(SurfaceHolder holder, int format,

                               int width, int height) {
                	 
	                	 Width = width;
	                	 Height = height;
	                	 
	                	 if(bo==false) {
	                		 createScaleBits();
	                		 bo=true;
	                	 }
	                	 else {
	                		 
	                	 }
	                	 
                 }

          });
          
 
    } //end constructor

    

    
    private Paint paint = new Paint();
 
    //onDraw(canvas) method called when gameview is drawn which happens continuously once the game is running
    @Override
    protected void onDraw(Canvas canvas) {
    	
    	canvas.drawColor(Color.BLACK);
    	
    	if(yo==true) {
    		if(level==0) {
    			canvas.drawBitmap(bits.get(0),0,0,null);
    		}
    		else if(level==1) {
    			canvas.drawBitmap(bits.get(1), 0,0, null);
    		}
    		else if(level==2) {
    			canvas.drawBitmap(bits.get(2), 0,0, null);
    		}
    		else if(level==3) {
    			canvas.drawBitmap(bits.get(3), 0,0, null);
    			
				if(counter(3) && touched==true) {
		    		level=4;
				}
    		}
    		else if(level==4) {
    			canvas.drawBitmap(bits.get(4), 0,0, null);
    			
	    		if(counter(6) && touched==true) {
	    			level=5;
		    		touched=false;
		    		seconds=0;
	    		}
    		}
    		else if(level==5) {
    			canvas.drawBitmap(bits.get(5), 0,0, null);
    		}
    	}
    	else {
    		canvas.drawColor(Color.BLACK);
    	}

//ends onDraw(canvas)
}
    
    
    
    
    private boolean cooking=false;
    private boolean touched=false;
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	
    	if(cooking==false) {
	    	if(System.currentTimeMillis()-300>l) {
	    		l=System.currentTimeMillis();
	    		
		    	if(level==0) {
		    		level=1;
		    	}
		    	else if(level==1) {
		    		level=2;
		    	}
		    	else if(level==2) {
		    		level=3;
		    	}
		    	else if(level==3) {
		    		MediaPlayer mp = new MediaPlayer();
					mp = MediaPlayer.create((NukeTheGummyActivity) getContext(), R.raw.shorter_amplified);
					cooking=true;
					mp.start();
					
					touched=true;

					mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						
						@Override
						public void onCompletion(MediaPlayer mp) {
							// TODO Auto-generated method stub
							mp.release();
							cooking=false;
						}
					}); 
		    	}
		    	else if(level==5) {
		    		((NukeTheGummyActivity) getContext()).finish();
		    	}
		    	else {
		    		level=0;
		    	}
	    	}
	    	else {
	    		
	    	}
    	}
    	
    	
    	return true;
    } //end onTouchEvent()
    
    
    
    
    
    private int seconds=0;
    
    public boolean counter(int secs) {
    	do {
    		if(System.currentTimeMillis()-1000>l) {
    			l=System.currentTimeMillis();
    			seconds+=1;
    		}
    		else {
    			
    		}
    		
    		if(seconds>=secs) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	while(secs>=seconds);
    }


} //end class GameView





