package com.multi.pingpong;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	Main_Canvas gameCanvas ;
	int power,power1,power2;
	float downx,downy,movex,movey;
	float downx1,downy1,movex1,movey1;
	float downx2,downy2,movex2,movey2;
	int temp,temp1,temp2;
	
	
	Handler updateHandler = new Handler() {
		  @Override
		  public void handleMessage(Message msg) {
		//display each item in a single line
			  gameCanvas.update();
			  //gameCanvas.invalidate();
	            super.handleMessage(msg);
		     }
		 };
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	gameCanvas = new Main_Canvas(this) ;
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(gameCanvas);
        temp = 0;
        gameCanvas.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
				if(event.getY() < gameCanvas.height/2){
				switch(action){
				case MotionEvent.ACTION_DOWN:
					power1 = 0;
					temp1 = gameCanvas.pad1x;
					downx1 = event.getX();
					downy1 = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					movex1 = event.getX();
					movey1 = event.getY();
					power1 = (int) (movex1-downx1);
					gameCanvas.pad1x = power1 + temp1;
					break;
				case MotionEvent.ACTION_UP:
					power1 = 0;
					break; 
				}
				}
				else if(event.getY() > gameCanvas.height/2){
					switch(action){
					case MotionEvent.ACTION_DOWN:
						power2 = 0;
						temp2 = gameCanvas.pad2x;
						downx2 = event.getX();
						downy2 = event.getY();
						break;
					case MotionEvent.ACTION_MOVE:
						movex2 = event.getX();
						movey2 = event.getY();
						power2 = (int) (movex2-downx2);
						gameCanvas.pad2x = power2 + temp2;
						break;
					case MotionEvent.ACTION_UP:
						power2 = 0;
						break; 
					}
					}
				/*
				 //For multiplayer
				   switch(action){
					case MotionEvent.ACTION_DOWN:
						power = 0;
						temp = gameCanvas.pad1x;
						downx = event.getX();
						downy = event.getY();
						break;
					case MotionEvent.ACTION_MOVE:
						movex = event.getX();
						movey = event.getY();
						power = (int) (movex-downx);
						gameCanvas.pad1x = power + temp;
						break;
					case MotionEvent.ACTION_UP:
						power = 0;
						break;
				}
				*/
				return true;
			}
		});
        
        
        Thread ball_movement_thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 while(true){
	                 updateHandler.sendEmptyMessage(0);
	                 try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			}
		});
        ball_movement_thread.start();

    }
}