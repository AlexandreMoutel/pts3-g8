package com.example.monfrigo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Liste extends ListActivity {

	Button boutonTriAlpha;
	Button boutonTriDlc;
	Button boutonTriCategorie;
	
	TextView listeVide;

	//Déclaration des tableaux
	String[] nom;
	String[] quantite;
	String[] type;
	String[] date;

	ArrayList<HashMap<String, Object>> aliment;
	HashMap<String, Object> temp;
	LayoutInflater inflater;
	String nomAliment;

	private CustomAdapter adapter;

	private List<Aliment> leFrigo;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste);



		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		listeVide = (TextView) findViewById(R.id.empty);
		listeVide.setText(" ");


		boutonTriAlpha = (Button) findViewById(R.id.button_tri_alpha);
		boutonTriDlc = (Button) findViewById(R.id.button_tri_dlc);
		boutonTriCategorie = (Button) findViewById(R.id.button_tri_categ);

		//final ListView maListe = (ListView) findViewById(R.id.listView_liste);
		final ListView maListe = getListView();
		maListe.setEmptyView(maListe.getEmptyView());
		leFrigo = recupererListeAliment(); 


		remplirTableau(leFrigo); //On remplit les tableaux suivant la liste pour faire la hashmap


		aliment = new ArrayList<HashMap<String, Object>>();


		remplirHashMap();
		adapter=new CustomAdapter(this, android.R.layout.activity_list_item, aliment, inflater); 

		//Ancien adapter
		//final ArrayAdapter<Aliment> adapter = new ArrayAdapter<Aliment>(this, android.R.layout.simple_list_item_1, leFrigo);
		maListe.setAdapter(adapter);

		maListe.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				new AlertDialog.Builder(Liste.this)
				.setTitle("Suppression d'aliment")
				.setMessage("Voulez vous supprimer cet aliment ?")
				.setPositiveButton("Supprimer", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{
						HashMap alimentSelect = (HashMap) maListe.getItemAtPosition(arg2);
						MesFrigos.getFrigoActuel().mangerTousLesAliment(new Aliment(alimentSelect.get("nom").toString(), alimentSelect.get("type").toString(), alimentSelect.get("date").toString(), alimentSelect.get("quantite").toString()));
						adapter.remove(alimentSelect);
					}
				})
				.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				})
				.show(); 
				return false;
			}

		});


		boutonTriAlpha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Tri par ordre Alphabétique
				Collections.sort(leFrigo, new Comparator<Aliment>(){

					@Override
					public int compare(Aliment a1, Aliment a2) {
						return a1.getNom().compareTo(a2.getNom());
					}
				});
				remplirTableau(leFrigo);
				remplirHashMap();
				adapter.notifyDataSetChanged();

			}
		});

		boutonTriCategorie.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Tri par type
				Collections.sort(leFrigo, new Comparator<Aliment>(){

					@Override
					public int compare(Aliment a1, Aliment a2) {
						return a1.getType().compareTo(a2.getType());
					}
				});
				remplirTableau(leFrigo);
				remplirHashMap();
				adapter.notifyDataSetChanged();
			}
		});

		boutonTriDlc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Tri par date
				Collections.sort(leFrigo, new Comparator<Aliment>(){

					@Override
					public int compare(Aliment a1, Aliment a2) {
						String aliment1 = String.valueOf(a1.getDate());
						String aliment2 = String.valueOf(a2.getDate());

						String[] sAliment1 = aliment1.split("/");
						String[] sAliment2 = aliment2.split("/");

						int jourAliment1 = Integer.parseInt(sAliment1[0]);
						int moisAliment1 = Integer.parseInt(sAliment1[1]);
						int anneeAliment1 = Integer.parseInt(sAliment1[2]);

						int jourAliment2 = Integer.parseInt(sAliment2[0]);
						int moisAliment2 = Integer.parseInt(sAliment2[1]);
						int anneeAliment2 = Integer.parseInt(sAliment2[2]);

						if(anneeAliment1 != anneeAliment2)
							return a1.getDate().substring(6).compareTo(a2.getDate().substring(6));

						if(moisAliment1 != moisAliment2)
							return a1.getDate().substring(3).compareTo(a2.getDate().substring(3));

						if(jourAliment1 != jourAliment2)
							return a1.getDate().substring(0).compareTo(a2.getDate().substring(0));

						return 1;


					}
				});
				remplirTableau(leFrigo);
				remplirHashMap();
				adapter.notifyDataSetChanged();
			}
		});
	}


	private void remplirHashMap() {

		if(leFrigo == null){
			aliment.removeAll(aliment);
			listeVide.setText("Aucun aliment");
		}else{
			int tailleHashMap = nom.length;
			listeVide.setText(" ");

			//On vide la liste
			aliment.removeAll(aliment);

			for(int i=0;i<tailleHashMap;i++)
			{
				temp = new HashMap<String, Object>();
				temp.put("nom", nom[i]);
				temp.put("date", date[i]);    
				temp.put("type", type[i]);
				temp.put("quantite", String.valueOf(quantite[i]));

				aliment.add(temp);
			}
		}

	}


	private void remplirTableau(List<Aliment> leFrigo) {
		if(leFrigo == null){
			listeVide.setText("Aucun aliment");
		}else{
			listeVide.setText(" ");
			//récupération taille du frigo
			int tailleFrigo = leFrigo.size();

			//On vide les tableaux
			nom = null;
			quantite = null;
			type = null;
			date = null;

			//Recupération des nom d'aliment
			nom = new String[tailleFrigo]; 
			for(int i = 0; i < leFrigo.size(); i++) {
				nom[i] = leFrigo.get(i).getNom();
			}

			//Recupération des quantite d'aliment

			quantite = new String[tailleFrigo]; 
			for(int i = 0; i < leFrigo.size(); i++) {
				quantite[i] = leFrigo.get(i).getQuantite();
			}

			//Recupération des type d'aliment
			type = new String[tailleFrigo];  
			for(int i = 0; i < leFrigo.size(); i++) {
				type[i] = leFrigo.get(i).getType();
			}

			//Recupération des date d'aliment
			date = new String[tailleFrigo];  
			for(int i = 0; i < leFrigo.size(); i++) {
				date[i] = leFrigo.get(i).getDate();

			}}
	}

	public List<Aliment> recupererListeAliment(){
		return MesFrigos.getFrigoActuel().getLeFrigo();
	}

	@Override
	protected void onResume() {
		super.onResume();
		leFrigo = recupererListeAliment();
		remplirTableau(leFrigo);
		remplirHashMap();
		adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste, menu);
		return true;
	}

}
