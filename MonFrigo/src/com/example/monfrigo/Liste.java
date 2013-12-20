package com.example.monfrigo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Liste extends Activity {

	Button boutonTriAlpha;
	Button boutonTriDlc;
	Button boutonTriCategorie;

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
	
	float historicX = Float.NaN, historicY = Float.NaN;
	static final int DELTA = 50;
	enum Direction {LEFT, RIGHT;}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste);



		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


		boutonTriAlpha = (Button) findViewById(R.id.button_tri_alpha);
		boutonTriDlc = (Button) findViewById(R.id.button_tri_dlc);
		boutonTriCategorie = (Button) findViewById(R.id.button_tri_categ);

		final ListView maListe = (ListView) findViewById(R.id.listView_liste);

		leFrigo = recupererListeAliment(); 


		remplirTableau(leFrigo); //On remplit les tableaux suivant la liste pour faire la hashmap


		aliment = new ArrayList<HashMap<String, Object>>();


		//now populate the ArrayList players
		remplirHashMap();
		adapter=new CustomAdapter(this, android.R.layout.activity_list_item, aliment, inflater); 

		//Ancien adapter
		//final ArrayAdapter<Aliment> adapter = new ArrayAdapter<Aliment>(this, android.R.layout.simple_list_item_1, leFrigo);
		maListe.setAdapter(adapter);
		maListe.setOnTouchListener(new OnTouchListener() {
		    @Override
		    public boolean onTouch(View view, MotionEvent event) 
		    {
		    	TextView nomAliment =  (TextView) (((TextView) view).getText());
		        switch (event.getAction()) 
		        {
		            case MotionEvent.ACTION_DOWN:
		            historicX = event.getX();
		            historicY = event.getY();
		            break;
		           case MotionEvent.ACTION_UP:
		            if (event.getX() - historicX < -DELTA) 
		            {
		            	for(Aliment a : MesFrigos.getFrigoActuel().getLeFrigo()){
		            		Log.e("AIDE", "Nom aliment = " + nomAliment + "Nom liste = " + a);
		            		/*if( a.getNom().equals(nomAliment.)){
		            			MesFrigos.getFrigoActuel().mangerUnAliment(a);
		            			adapter.notifyDataSetChanged();
			            		Toast.makeText(getBaseContext(), "Un "+nomAliment+" a bien été retiré", Toast.LENGTH_SHORT).show();
		            		}*/
		            	}
		                return true;
		            }
		            else if (event.getX() - historicX > DELTA)  
		            {
		            	for(Aliment a : MesFrigos.getFrigoActuel().getLeFrigo()){
		            		if( a.getNom().equals(nomAliment)){
		            			MesFrigos.getFrigoActuel().vomirUnAliment(a);
		            			adapter.notifyDataSetChanged();
		            			Toast.makeText(getBaseContext(), "Un "+nomAliment+" a bien été ajouté", Toast.LENGTH_SHORT).show();
		            		}
		            	}
		                return true;
		            }
		            leFrigo = recupererListeAliment(); 
		    		remplirTableau(leFrigo);
		    		aliment = new ArrayList<HashMap<String, Object>>();
		    		remplirHashMap();
		    		adapter=new CustomAdapter(getBaseContext(), android.R.layout.activity_list_item, aliment, inflater); 
		    		maListe.setAdapter(adapter);break;
		            default: return false;
		        }
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
				leFrigo = recupererListeAliment();
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
				leFrigo = recupererListeAliment();
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
						Date date1 = new Date(a1.getDate());
						Date date2 = new Date(a2.getDate());
						return date1.compareTo(date2);
					}
				});
				leFrigo = recupererListeAliment();
				remplirTableau(leFrigo);
				remplirHashMap();
				adapter.notifyDataSetChanged();
			}
		});
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
			temp.put("quantite", String.valueOf(quantite[i]));

			aliment.add(temp);
		}

	}


	private void remplirTableau(List<Aliment> leFrigo) {
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
		}
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
