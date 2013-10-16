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
//Bouton
public Button ajouter = null;
//

//Nom produit 
public EditText nomProduit = null;
public TextView leProduit = null;
//

//Date P�remption
public EditText editDatePerem = null;
public TextView laDateDePerem = null;
//

//Type de produit
public EditText editTypeProduit = null;
public TextView leTypeDeProduit = null;
//

//Quantit�
public EditText quantite = null;
public int laQuantite;
//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout);
		
		//Je cr�er un frigo parce que voila me faut un frigo
		final Frigo leBienRempli = new Frigo();
		//
		
		//On initialize les EditText
		nomProduit = (EditText) findViewById(R.id.saisieAjout);
		editDatePerem = (EditText) findViewById(R.id.datePerem);
		editTypeProduit = (EditText) findViewById(R.id.typeProd);
		quantite = (EditText) findViewById(R.id.quantite);
		//
		
		//On initialize le bouton
		ajouter = (Button) findViewById(R.id.buttonAjout);
		//
		
		//Lorsque que l'on appuie sur le bouton ajouter
		ajouter.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					//On r�cup�re la valeur des EditText
					leProduit.setText(nomProduit.getText());
					leTypeDeProduit.setText(editTypeProduit.getText());
					laDateDePerem.setText(editDatePerem.getText());
					laQuantite = Integer.parseInt(quantite.getText().toString());
					//
					
					//On Cr�e un aliment et on l'ajoute au frigo
					leBienRempli.ajouterAliment(new Aliment(leProduit, leTypeDeProduit, laDateDePerem, laQuantite));
					//
					
					//On cr�e la nouvelle activit�
					Intent intent = new Intent(Ajout.this, Liste.class);
					//
					
					//On lui transmet en param�tre les valeurs de l'aliment ajout�.
					Bundle tousLesString = new Bundle();
					tousLesString.putCharSequence("nom", leProduit.getText());
					tousLesString.putCharSequence("type", leTypeDeProduit.getText());
					tousLesString.putCharSequence("date", laDateDePerem.getText());
					tousLesString.putInt("quantite", laQuantite);
					intent.putExtras(tousLesString);
					//
					
					//On d�marre l'activit�
					startActivity(intent);
					//
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
