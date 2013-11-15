package com.example.monfrigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.ListView;

public class AfficherListeDeCourse extends Activity {

	ArrayList<HashMap<String, Object>> aliment;
	HashMap<String, Object> temp;
	LayoutInflater inflater;
	
	String[] nom;
	int[] quantite;
	String[] type;
	String[] date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_afficher_liste_de_course);
		
		final ListView maListe = (ListView) findViewById(R.id.listView_liste);

		final List<Aliment> laListeDeCourse = ListeDeCourse.getLaBouffeQuiFautAcheter();
		
		((ListeDeCourse) laListeDeCourse).ajouterAliment(new Aliment("Steack", "Viande", "12/12/2012", 3));
		((ListeDeCourse) laListeDeCourse).ajouterAliment(new Aliment("Haribo", "Bonbon", "13/12/2012", 5));
		((ListeDeCourse) laListeDeCourse).ajouterAliment(new Aliment("Coca-Cola", "Boisson", "14/12/2012", 3));
		((ListeDeCourse) laListeDeCourse).ajouterAliment(new Aliment("Truite", "Poisson", "12/11/2012", 3));
		((ListeDeCourse) laListeDeCourse).ajouterAliment(new Aliment("Boeuf", "Viande", "12/11/2012", 3));
		((ListeDeCourse) laListeDeCourse).ajouterAliment(new Aliment("Poisson pané", "Poisson", "12/12/2009", 9));
		
		remplirTableau(laListeDeCourse);
		
		aliment = new ArrayList<HashMap<String,Object>>();
		
		remplirHashMap();
		
		final CustomAdapter adapter = new CustomAdapter(this, android.R.layout.activity_list_item, aliment, inflater);
	
		maListe.setAdapter(adapter);
	}
	
	private void remplirTableau(List<Aliment> laListeDeCourse) {
		int tailleFrigo = laListeDeCourse.size();

		//On vide les tableaux
		nom = null;
		quantite = null;
		type = null;
		date = null;

		//Recupération des nom d'aliment
		nom = new String[tailleFrigo]; 
		for(int i = 0; i < laListeDeCourse.size(); i++) {
			nom[i] = laListeDeCourse.get(i).getNom();
		}

		//Recupération des quantite d'aliment

		quantite = new int[tailleFrigo]; 
		for(int i = 0; i < laListeDeCourse.size(); i++) {
			quantite[i] = laListeDeCourse.get(i).getQuantite();
		}

		//Recupération des type d'aliment
		type = new String[tailleFrigo];  
		for(int i = 0; i < laListeDeCourse.size(); i++) {
			type[i] = laListeDeCourse.get(i).getType();
		}

		//Recupération des date d'aliment
		date = new String[tailleFrigo];  
		for(int i = 0; i < laListeDeCourse.size(); i++) {
			date[i] = laListeDeCourse.get(i).getDate();
		}
	}
	
	private void remplirHashMap() {

		int tailleHashMap = nom.length;


		//On vide la liste
		aliment.removeAll(aliment);

		for(int i=0;i<tailleHashMap;i++)
		{
			temp = new HashMap<String, Object>();

			temp.put("nom", nom[i]);
			temp.put("date", date[i]);    
			temp.put("type", type[i]);
			temp.put("quantite", quantite[i]);

			aliment.add(temp);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.afficher_liste_de_course, menu);
		return true;
	}

}
