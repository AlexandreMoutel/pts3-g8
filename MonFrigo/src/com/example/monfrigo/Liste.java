package com.example.monfrigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Liste extends Activity {
     
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_liste);
	     ListView maListe = (ListView) findViewById(R.id.listView_liste);
	     
	     List<Aliment> leFrigo = MesFrigos.getUnFrigo("Frigo1").getLeFrigo();
	          
	     ArrayAdapter<Aliment> adapter = new ArrayAdapter<Aliment>(this, android.R.layout.simple_list_item_1, leFrigo);
	    /*
	    
	     String[][] repertoire = new String[][]{
	     {"Bill Gates", "06 06 06 06 06"},
	     {"Niels Bohr", "05 05 05 05 05"},
	     {"Alexandre III de Macédoine", "04 04 04 04 04"}};
	     
	  
	     List<HashMap<String, String>> liste = new
	    ArrayList<HashMap<String, String>>();
	     
	     HashMap<String, String> element;
	    // Pour chaque personne dans notre répertoire…
	     for(int i = 0 ; i < repertoire.length ; i++) {
	     //… on crée un élément pour la liste…
	     element = new HashMap<String, String>();
	   
	     element.put("text1", repertoire[i][0]);
	   
	     element.put("text2", repertoire[i][1]);
	     liste.add(element);
	     }
	     
	     ListAdapter adapter = new SimpleAdapter(this, 
	     //Valeurs à insérer
	     liste, 
	
	     android.R.layout.simple_list_item_2,
	  
	     new String[] {"text1", "text2"}, 
	   
	      new int[] {android.R.id.text1, android.R.id.text2 });
	      //Pour finir, on donne à la ListView le SimpleAdapter
	      vue.setAdapter(adapter);
	     // maListe.setAdapter(adapter);
	      */
	      }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste, menu);
		return true;
	}

}
