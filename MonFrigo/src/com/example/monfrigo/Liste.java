package com.example.monfrigo;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Liste extends Activity {
     
     
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_liste);
	     ListView maListe = (ListView) findViewById(R.id.listView_liste);
	     
	     List<Aliment> leFrigo = MesFrigos.getUnFrigo("Frigo1").getLeFrigo();
	          
	     ArrayAdapter<Aliment> adapter = new ArrayAdapter<Aliment>(this, android.R.layout.simple_list_item_1, leFrigo);
	     maListe.setAdapter(adapter);

	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste, menu);
		return true;
	}

}
