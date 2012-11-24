package com.multi.pingpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Main_Canvas extends View {
	
	public int height;
	public int width ;
	private Paint paint ;
	private int ballr, rect_width, goal_width_half,paddle_width_half ; 
	private int right_wall, left_wall, top_wall, bottom_wall,speed,sx,sy;
	public int half_width;
	private int ballx, bally;
	private float ratio ;
	private boolean flag ;
	public int pad1x,pad2x;
	int zoom;

	
	Thread t=null;
	public Main_Canvas(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint = new Paint() ;
		paint.setAntiAlias(true) ;
		zoom = 2;
		ballr = 7*zoom ;
		rect_width = 10*zoom ;
		goal_width_half = 70*zoom ;
		pad1x=pad2x=0;
		flag = false ;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(!flag)
			init(canvas) ;
		render(canvas) ;
		update(canvas) ;
		
		
		paint.setColor(Color.RED) ;
		invalidate() ;
	}

	private void update(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

	private void render(Canvas canvas) {
		// TODO Auto-generated method stub
		
		//seeting background color
		paint.setColor(Color.BLACK) ;
		canvas.drawPaint(paint) ;
		
		//setting white walls
		paint.setColor(Color.WHITE) ;
		canvas.drawRect(0, 0, width, height, paint) ;
		//setting middle area and goals
		paint.setColor(Color.BLACK) ;
		canvas.drawRect(rect_width, rect_width, (width-rect_width), (height-rect_width), paint) ;
		canvas.drawRect((width/2)-goal_width_half, 0, (width/2)+goal_width_half, rect_width, paint) ;
		canvas.drawRect((width/2)-goal_width_half, height-rect_width, (width/2)+goal_width_half, height, paint) ;
		
		//setting paddles
		paint.setColor(Color.WHITE);
		if(pad1x+width/2 - paddle_width_half < rect_width+ballr/2)
			pad1x = paddle_width_half + rect_width - (width -ballr)/2;
		else if(pad1x+ width/2 + paddle_width_half > width - rect_width - ballr/2)
			pad1x = -paddle_width_half -rect_width+(width -ballr)/2;
		canvas.drawRect(pad1x+width/2 - paddle_width_half, rect_width*2,pad1x+ width/2 + paddle_width_half, rect_width*3, paint);
		if(pad2x+width/2 - paddle_width_half < rect_width+ballr/2)
			pad2x = paddle_width_half + rect_width - (width -ballr)/2;
		else if(pad2x+ width/2 + paddle_width_half > width - rect_width - ballr/2)
			pad2x = -paddle_width_half -rect_width+(width -ballr)/2;
		canvas.drawRect(pad2x+width/2 - paddle_width_half, height - rect_width*3,pad2x+width/2 + paddle_width_half, height - rect_width*2, paint);
		
		
		//ball
		paint.setColor(Color.YELLOW);
		canvas.drawCircle(ballx, bally, ballr, paint);
		ballx++;
		bally++;
		try {
			Thread.sleep(10,00);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init(Canvas canvas) {
		// TODO Auto-generated method stub
		height = canvas.getHeight() ;
		width = canvas.getWidth() ;
		half_width = width/2;
		ratio = height/width ;
		ballr*=ratio ;
		rect_width*=ratio ;
		goal_width_half*=ratio ;
		paddle_width_half = (goal_width_half - 6*ballr);
		ballx = width/2;
		bally = height/2;
		left_wall = rect_width;
		right_wall = width - rect_width;
		top_wall = rect_width;
		bottom_wall = height - rect_width;
		speed=1;
		sx=sy=speed;
		flag = true ;
	}
	
	// called by thread
    public void update() {
    	if(ballx-speed <= left_wall) sx=speed;
    	else if(ballx+speed >= right_wall) sx=-speed;
    	if(bally-speed <= top_wall+2*rect_width 
    			&& (ballx >=pad1x + half_width - paddle_width_half && ballx<=pad1x+half_width+paddle_width_half))sy=speed;
    	else if(bally-speed <= top_wall  
    			&& !(ballx >=half_width-paddle_width_half && ballx<=half_width+paddle_width_half))
    		sy=speed;
    	else if(bally+speed >= bottom_wall-2*rect_width 
    			&& (ballx >=pad2x + half_width - paddle_width_half && ballx<=pad2x+half_width+paddle_width_half))sy=-speed;
    	else if(bally+speed >= bottom_wall 
    			&& !(ballx >=half_width-paddle_width_half && ballx<=half_width+paddle_width_half))
    		sy=-speed;	
        ballx+=sx;
        bally+=sy;
    }
}
