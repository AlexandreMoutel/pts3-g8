package com.example.monfrigo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Ajout extends Activity {
	//Bouton
	public Button ajouter = null;
	public Button scanner = null;
	//

	//Nom produit 
	public AutoCompleteTextView nomProduit = null;
	public String leProduit = null;
	//

	//Date Péremption
	public DatePicker editDatePerem = null;
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
		
		//On récupère le tableau de String créé dans le fichier string.xml
		String[] tableauAliments = getResources().getStringArray(R.array.tableau);

		//On récupère l'AutoCompleteTextView que l'on a créé dans le fichier main.xml
		final AutoCompleteTextView autoComplete = (AutoCompleteTextView) findViewById(R.id.saisieAjout);

		//On crée la liste d'autocomplétion à partir de notre tableau de string appelé tableauString
		//android.R.layout.simple_dropdown_item_1line permet de définir le style d'affichage de la liste
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, tableauAliments);

		//On affecte cette liste d'autocomplétion à notre objet d'autocomplétion
		autoComplete.setAdapter(adapter);


		//On initialise les EditText
		nomProduit = (AutoCompleteTextView) findViewById(R.id.saisieAjout);
		editDatePerem = (DatePicker) findViewById(R.id.datePerem);
		editTypeProduit = (EditText) findViewById(R.id.typeProd);
		quantite = (EditText) findViewById(R.id.quantite);
		//
 
		//On initialise le bouton
		ajouter = (Button) findViewById(R.id.buttonAjout);
		scanner = (Button) findViewById(R.id.button_scanner);
		MesFrigos.ajouterFrigo("Frigo1");
		/*
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Steack", "Viande", "12/12/2012", 3));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Haribo", "Bonbon", "13/12/2012", 5));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Coca-Cola", "Boisson", "14/12/2012", 3));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Truite", "Poisson", "12/11/2012", 3));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Boeuf", "Viande", "12/11/2012", 3));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Poisson pané", "Poisson", "12/12/2009", 9));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Gruyère", "Fromage", "12/09/2010", 3));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Pates", "Féculent", "12/12/2010", 3));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Patates", "Féculent", "12/12/2000", 8));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Eau", "Boisson", "12/12/2999", 99));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Côte de porc", "Viande", "02/12/2013", 4));
		MesFrigos.getUnFrigo("Frigo1").ajouterAliment(new Aliment("Lasagne", "Féculent", "12/05/2010", 1));
*/

		//Lorsque que l'on appuie sur le bouton ajouter
		ajouter.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				//Vérification si les champs sont bien rempli
				if("".equals(nomProduit.getText().toString()))
					Toast.makeText(Ajout.this,"Veuillez renseigner le nom de l'aliment.", Toast.LENGTH_LONG).show();
				else if("".equals(editDatePerem == null))
					Toast.makeText(Ajout.this,"Veuillez renseigner la date de péremption.", Toast.LENGTH_LONG).show();
				else if("".equals(editTypeProduit.getText().toString()))
					Toast.makeText(Ajout.this,"Veuillez renseigner le type.", Toast.LENGTH_LONG).show();
				else if("".equals(quantite.getText().toString()))
					Toast.makeText(Ajout.this,"Veuillez renseigner la quantité", Toast.LENGTH_LONG).show();
				else{

					String mois =  String.valueOf(editDatePerem.getMonth());
					String jour =  String.valueOf(editDatePerem.getDayOfMonth());
					String annee =  String.valueOf(editDatePerem.getYear());
					//On crée l'aliment et on met dans la liste
					leProduit = nomProduit.getText().toString();
					leTypeDeProduit = editTypeProduit.getText().toString();
					laDateDePerem = mois +"/"+ jour +"/" + annee;
					laQuantite = Integer.parseInt(quantite.getText().toString());
 
					//On Crée un aliment et on l'ajoute au frigo

					Aliment monAliment = new Aliment(leProduit, leTypeDeProduit, laDateDePerem, laQuantite);
					MesFrigos.ajouterFrigo("Frigo1");
					((Frigo) MesFrigos.getFrigoActuel()).ajouterAliment(monAliment);


					//Le message toast apparait et on reste sur la vue d'ajout
					String stringAliment = nomProduit.getText().toString();
					Toast.makeText(Ajout.this,"L'aliment " + stringAliment + " a bien été ajouté dans " + MesFrigos.getFrigoActuel().getNom(), Toast.LENGTH_LONG).show();	
				}
			}
		}); 
		
		scanner.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//On crée la nouvelle activité
				//Intent intent = new Intent(Ajout.this, Scanner.class);
				//On lance l'activité
				//startActivity(intent);
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
