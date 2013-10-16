package com.example.monfrigo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class Liste extends Activity {
	
	//On cr�e des TextView pour r�cup�rer les valeurs que l'on avait pass�es en param�tre
	public TextView leNom = null;
	public TextView leType = null;
	public TextView laDateDePeremption = null;
	public TextView laQuantite;
	//
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste);
		
		//On initialize les TextView
		leNom = (TextView) findViewById(R.id.afficheNom);
		leType = (TextView) findViewById(R.id.afficheType);
		laDateDePeremption = (TextView) findViewById(R.id.afficheDateDePeremption);
		laQuantite = (TextView) findViewById(R.id.afficheQuantite);
		//
		
		//On ajoute les informations de l'aliment dans les TextView
		Bundle jeRecupereTousLesString = this.getIntent().getExtras();
		leNom.setText(jeRecupereTousLesString.getCharSequence("nom"));
		leType.setText(jeRecupereTousLesString.getCharSequence("type"));
		laDateDePeremption.setText(jeRecupereTousLesString.getCharSequence("date"));
		laQuantite.setText(jeRecupereTousLesString.getInt("quantite"));
		//
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste, menu);
		return true;
	}

}
