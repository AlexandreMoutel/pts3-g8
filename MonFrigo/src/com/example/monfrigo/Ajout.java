package com.example.monfrigo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParser;

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
	public String laQuantite;
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
					laQuantite = quantite.getText().toString();
					//On Crée un aliment et on l'ajoute au frigo

					Aliment monAliment = new Aliment(leProduit, leTypeDeProduit, laDateDePerem, laQuantite);
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
				IntentIntegrator integrator = new IntentIntegrator();
				integrator.initiateScan(Ajout.this);
			}
		});	
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanResult != null) {
			Toast.makeText(getBaseContext(), "" + scanResult.getContents(), Toast.LENGTH_LONG).show();	


			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 

			// On télécharge le fichier JSON
			DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
			URI url = null;
			try {
				url = new URI("http://fr.openfoodfacts.org/api/v0/produit/"+scanResult.getContents()+".json");
			} catch (URISyntaxException e1) {
				Log.e("ERREUR", "Erreur dans l'accès à l'url");
				e1.printStackTrace();
			}

			HttpPost httppost = new HttpPost(url);
			httppost.setHeader("Content-type", "application/json");

			InputStream inputStream = null;
			String result = null;
			try {
				HttpResponse response = httpclient.execute(httppost);           
				HttpEntity entity = response.getEntity();

				inputStream = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
				StringBuilder sb = new StringBuilder();

				String line = null;
				while ((line = reader.readLine()) != null)
				{
					sb.append(line + "\n");
				}
				result = sb.toString();
			} catch (Exception e) { 
				Log.e("Erreur", "Erreur dans la récupération du fichier json" + e.toString());
			}
			finally {
				Log.e("SUCCESS", "Fichier téléchargé");
				try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
			}

			try {
				JSONObject jObject = new JSONObject(result);
				Log.e("test", "résultat : "+ jObject.toString());
			} catch (JSONException e) {
				Log.e("Erreur", "Erreur dans la récupération de donnée" + e.toString());
				e.printStackTrace();
			}
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajout, menu);
		//Modif inutile
		return true;
	}

}
