package com.example.monfrigo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MonFrigo extends Activity {
	ImageView image;
	ProgressBar progress;
	private static int SPLASH_TIME_OUT = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        
        image = (ImageView) findViewById(R.id.imageView1);
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        
        progress.setMax(100);
        image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
               Intent i = new Intent(MonFrigo.this, MainActivity.class);
                startActivity(i);
                finish();
                executor.shutdown();
            }
		});
        
        final Runnable task = new Runnable() {
            
            @Override
            public void run() {
            	progress.setProgress(progress.getProgress() + 30);
                if(progress.getProgress() == 100){
                	Intent i = new Intent(MonFrigo.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    executor.shutdown();
                }           
            }
        };
             
            executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
     
    
       /* new Handler().postDelayed(new Runnable() {
 
            @Override
            public void run() {
                progress.setProgress(progress.getProgress() + 30);
                Log.e("progres barre", "" + progress.getProgress());
                
                if(progress.getProgress() == 100){
                	Intent i = new Intent(MonFrigo.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);*/
        
        
    }

}
