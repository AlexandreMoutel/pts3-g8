package com.example.monfrigo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Ajout extends Activity {
	//Bouton
	public Button ajouter = null;
	public Button liste = null;
	//

	//Nom produit 
	public EditText nomProduit = null;
	public String leProduit = null;
	//

	//Date Péremption
	public EditText editDatePerem = null;
	public String laDateDePerem = null;
	//

	//Type de produit
	public EditText editTypeProduit = null;
	public String leTypeDeProduit = null;
	//

	//Quantité
	public EditText quantite = null;
	public int laQuantite;
	//

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout);

		//On initialise les EditText
		nomProduit = (EditText) findViewById(R.id.saisieAjout);
		editDatePerem = (EditText) findViewById(R.id.datePerem);
		editTypeProduit = (EditText) findViewById(R.id.typeProd);
		quantite = (EditText) findViewById(R.id.quantite);
		//

		//On initialise le bouton
		ajouter = (Button) findViewById(R.id.buttonAjout);
		liste = (Button) findViewById(R.id.button_liste);


		//Lorsque que l'on appuie sur le bouton ajouter
		ajouter.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Vérification si les champs sont bien rempli

				if("".equals(nomProduit.getText().toString()))
					Toast.makeText(Ajout.this,"Veuillez renseigner le nom de l'aliment.", Toast.LENGTH_LONG).show();
				else if("".equals(editDatePerem.getText().toString()))
					Toast.makeText(Ajout.this,"Veuillez renseigner la date de péremption.", Toast.LENGTH_LONG).show();
				else if("".equals(editTypeProduit.getText().toString()))
					Toast.makeText(Ajout.this,"Veuillez renseigner le type.", Toast.LENGTH_LONG).show();
				else if("".equals(quantite.getText().toString()))
					Toast.makeText(Ajout.this,"Veuillez renseigner la quantité", Toast.LENGTH_LONG).show();
				else{
					//On crée l'aliment et on met dans la liste
					leProduit = nomProduit.getText().toString();
					leTypeDeProduit = editTypeProduit.getText().toString();
					laDateDePerem = editDatePerem.getText().toString();
					laQuantite = Integer.parseInt(quantite.getText().toString());

					//On Crée un aliment et on l'ajoute au frigo PARTIE A FAIRE
					
					
					//Le message toast apparait et on reste sur la vue d'ajout
					String stringAliment = nomProduit.getText().toString();
					Toast.makeText(Ajout.this,"L'aliment " + stringAliment + " a bien été ajouté à votre liste.", Toast.LENGTH_LONG).show();	
				}
			}
		});

		liste.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//On crée la nouvelle activité
				Intent intent = new Intent(Ajout.this, Liste.class);
				//On lance l'activité
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
