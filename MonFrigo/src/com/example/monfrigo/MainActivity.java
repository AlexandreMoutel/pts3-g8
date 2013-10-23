package com.example.monfrigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	public ImageButton goAjout = null;
	public ImageButton frigo = null;
	public ImageButton liste = null;
	public Button recette = null;
	public Button reglage = null;
	public Button exit = null;
	public MesFrigos leGrosFrigo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		goAjout = (ImageButton) findViewById(R.id.imageButton_ajout);
		frigo = (ImageButton) findViewById(R.id.buttonMonFrigo);
	    liste = (ImageButton) findViewById(R.id.imageButton_liste);
		//recette = (Button) findViewById(R.id.buttonRecettes);
		//reglage = (Button) findViewById(R.id.buttonReglages);
		//exit = (Button) findViewById(R.id.buttonExit);

		goAjout.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, Ajout.class);
				startActivity(intent);
			}
		});
		
		liste.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent(MainActivity.this, Liste.class);
				startActivity(intent1);
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}	





