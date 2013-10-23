package com.example.monfrigo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.renderscript.Sampler.Value;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreerFrigo extends Activity {

	Button newFrigo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_frigo);

		newFrigo = (Button) findViewById(R.id.button_new_frigo);

		newFrigo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// Je crée une alerte Box pour récupérer le nom du frigo
				AlertDialog.Builder alert = new AlertDialog.Builder(getBaseContext());

				alert.setTitle("Title");
				alert.setMessage("Message");

				// EditText pour récupérer le nom
				final EditText input = new EditText(getBaseContext());
				alert.setView(input);

				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						
						//Le nom du frigo
						CharSequence value = input.getText();
						MesFrigos.ajouterFrigo(value.toString());
					}
				});
				
				//STOP !!!!!!!! On ferme tout !!!!!
				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
					}
				});
				
				// On affiche la magnifique DialogBox
				alert.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creer_frigo, menu);
		return true;
	}

}
