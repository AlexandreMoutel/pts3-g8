package com.example.monfrigo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreerFrigo extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_frigo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creer_frigo, menu);
		return true;
	}

}
