package com.multi.pingpong;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	Main_Canvas obj ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	obj = new Main_Canvas(this) ;
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(obj);
    }
}