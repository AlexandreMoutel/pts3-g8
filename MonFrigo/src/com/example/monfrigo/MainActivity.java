package com.example.monfrigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	public Button goAjout = null;
	public Button frigo = null;
	public Button liste = null;
	public Button recette = null;
	public Button reglage = null;
	public Button exit = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		goAjout = (Button) findViewById(R.id.versAjout);
		frigo = (Button) findViewById(R.id.buttonMonFrigo);
		liste = (Button) findViewById(R.id.buttonListe);
		recette = (Button) findViewById(R.id.buttonRecettes);
		reglage = (Button) findViewById(R.id.buttonReglages);
		exit = (Button) findViewById(R.id.buttonExit);

		goAjout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Ajout.class);
				startActivity(intent);
			}
		});

	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}	





