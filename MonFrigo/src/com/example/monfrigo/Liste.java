package com.example.monfrigo;

import java.util.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Liste extends Activity {

	Button boutonTriAlpha;
	Button boutonTriDlc;
	Button boutonTriCategorie;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste);
		
		boutonTriAlpha = (Button) findViewById(R.id.button_tri_alpha);
		boutonTriDlc = (Button) findViewById(R.id.button_tri_dlc);
		boutonTriCategorie = (Button) findViewById(R.id.button_tri_categ);

		ListView maListe = (ListView) findViewById(R.id.listView_liste);

			final List<Aliment> leFrigo = MesFrigos.getUnFrigo("Frigo1").getLeFrigo();
			
			leFrigo.add(new Aliment("Steack", "Viande", "12/12/2012", 3));
			leFrigo.add(new Aliment("Haribo", "Bonbon", "13/12/2012", 3));
			leFrigo.add(new Aliment("Coca-Cola", "Boisson", "14/12/2012", 3));
			leFrigo.add(new Aliment("Truite", "Poisson", "12/11/2012", 3));
			leFrigo.add(new Aliment("Boeuf", "Viande", "12/11/2012", 3));
			leFrigo.add(new Aliment("Jus d'Orange", "Boisson", "12/12/2010", 3));
			

			final ArrayAdapter<Aliment> adapter = new ArrayAdapter<Aliment>(this, android.R.layout.simple_list_item_1, leFrigo);
			maListe.setAdapter(adapter);
			
			
			boutonTriAlpha.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//Tri par ordre Alphabétique
					
					Collections.sort(leFrigo, new Comparator<Aliment>(){
						public int compare(Aliment tc1, Aliment tc2) {
							return tc1.getNom().compareTo(tc2.getNom());
						}
					});
					
					adapter.notifyDataSetChanged(); //mise à jour de la listView
				}
			});
			
			boutonTriCategorie.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//Tri par type
					
					Collections.sort(leFrigo, new Comparator<Aliment>(){
						public int compare(Aliment tc1, Aliment tc2) {
							return tc1.getType().compareTo(tc2.getType());
						}
					});
					
					adapter.notifyDataSetChanged();
				}
			});
			
			boutonTriDlc.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					//Tri par date
					
					Collections.sort(leFrigo, new Comparator<Aliment>(){
						public int compare(Aliment tc1, Aliment tc2) {
							Date date1 = new Date(tc1.getDate());
							Date date2 = new Date(tc2.getDate());
							return date1.compareTo(date2);
						}
					});
					
					adapter.notifyDataSetChanged();
				}
			});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste, menu);
		return true;
	}

}
