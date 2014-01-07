package com.example.monfrigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreen extends Activity {
	ImageView image;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        
        image = (ImageView) findViewById(R.id.imgLogo);
        
        image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
               Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
		});
        
        
    }

}
