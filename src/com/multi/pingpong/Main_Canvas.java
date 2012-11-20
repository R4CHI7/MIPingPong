package com.multi.pingpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Main_Canvas extends View {
	
	private int height, width ;
	private Paint paint ;
	private int ballr, rect_width, goal_width_half ; 
	private float ratio ;
	private boolean flag ;

	public Main_Canvas(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		paint = new Paint() ;
		paint.setAntiAlias(true) ;
		ballr = 5 ;
		rect_width = 7 ;
		goal_width_half = 50 ;
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
		paint.setColor(Color.BLACK) ;
		canvas.drawPaint(paint) ;
		paint.setColor(Color.WHITE) ;
		canvas.drawRect(0, 0, width, height, paint) ;
		paint.setColor(Color.BLACK) ;
		canvas.drawRect(rect_width, rect_width, (width-rect_width), (height-rect_width), paint) ;
		canvas.drawRect((width/2)-goal_width_half, 0, (width/2)+goal_width_half, rect_width, paint) ;
		canvas.drawRect((width/2)-goal_width_half, height-rect_width, (width/2)+goal_width_half, height, paint) ;
	}

	private void init(Canvas canvas) {
		// TODO Auto-generated method stub
		height = canvas.getHeight() ;
		width = canvas.getWidth() ;
		ratio = height/width ;
		ballr*=ratio ;
		rect_width*=ratio ;
		goal_width_half*=ratio ;
		flag = true ;
	}
}
