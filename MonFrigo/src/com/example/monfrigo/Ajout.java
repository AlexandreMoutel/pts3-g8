package com.example.monfrigo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Ajout extends Activity {
public TextView titre = null;
public EditText saisie = null;
public Button ajouter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout);


		TextView titre = (TextView) findViewById(R.id.titreAjout);
		EditText saisie = (EditText) findViewById(R.id.saisieAjout);
		Button ajouter = (Button) findViewById(R.id.buttonAjout);
		
		
		ajouter.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Ajout.this, Liste.class);
				startActivity(intent);
			}
		
		});
			
			
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajout, menu);
		//Modif inutile
		return true;
	}

}
