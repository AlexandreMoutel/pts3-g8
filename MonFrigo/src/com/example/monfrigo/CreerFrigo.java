package com.example.monfrigo;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CreerFrigo extends Activity {

	Button newFrigo;
	ArrayAdapter<String> adapter;
	 List<String> listeFrigo = MesFrigos.getMesFrigos();
	 ListView maListe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_frigo); 

		newFrigo = (Button) findViewById(R.id.button_new_frigo);
		
		listeFrigo = recupererListeFrigo();
		maListe = (ListView) findViewById(R.id.listViewFrigo);
		
		
		adapter = new ArrayAdapter<String>(this, R.layout.layout_liste_frigo, listeFrigo);
		maListe.setSelector(R.drawable.list_selector);
		maListe.setAdapter(adapter);
		
		maListe.setItemsCanFocus(false);
        maListe.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		newFrigo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				final EditText inputNomFrigo = new EditText(getBaseContext());

				new AlertDialog.Builder(CreerFrigo.this)
				.setTitle("Ajout de Frigo")
				.setMessage("Veuillez rentrer le nom du frigo")
				.setView(inputNomFrigo)
				.setPositiveButton("Ajouter", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{
						String nomFrigo = inputNomFrigo.getText().toString();
						MesFrigos.ajouterFrigo(nomFrigo);
						listeFrigo = recupererListeFrigo();
						adapter.insert(nomFrigo, 0);
					}
				})
				.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				})
				.show(); 
			}
		});
		
		//Test du drag and drop
		maListe.setOnItemLongClickListener(new OnItemLongClickListener() {
			myDragEventListener dragListener = new myDragEventListener();
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, final View v,
					int arg2, long arg3) {
				/*ClipData data = ClipData.newPlainText("", "");
		        DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
				v.startDrag(data, shadowBuilder, v, 0);
				v.setOnDragListener(dragListener);*/
				
				final EditText modifNomFrigo = new EditText(getBaseContext());
				
				new AlertDialog.Builder(CreerFrigo.this)
				.setTitle("Modification de Frigo")
				.setMessage("Nom du frigo")
				.setView(modifNomFrigo)
				.setPositiveButton("Modifier", new DialogInterface.OnClickListener()
				{ 
					public void onClick(DialogInterface dialog, int whichButton)
					{
						String nomFrigoActuel = (String) ((TextView) v).getText();
						String nomFrigo = modifNomFrigo.getText().toString();
						MesFrigos.setFrigoActuel(nomFrigoActuel);
						MesFrigos.getFrigoActuel().setNom(nomFrigo);
						listeFrigo = recupererListeFrigo();
						maListe = (ListView) findViewById(R.id.listViewFrigo);
						adapter = new ArrayAdapter<String>(CreerFrigo.this, R.layout.layout_liste_frigo, listeFrigo);
						maListe.setAdapter(adapter);
					}
				})
				.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

					}
				})
				.setNeutralButton("Supprimer", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{
						String nomFrigo = (String) ((TextView) v).getText();
						for(int i = 0; i < listeFrigo.size(); i++){
							if(listeFrigo.get(i) == nomFrigo)
								listeFrigo.remove(i);
						}
						listeFrigo = recupererListeFrigo();
						maListe = (ListView) findViewById(R.id.listViewFrigo);
						adapter = new ArrayAdapter<String>(CreerFrigo.this, R.layout.layout_liste_frigo, listeFrigo);
						maListe.setAdapter(adapter);
					}
				})
				.show(); 
				adapter.notifyDataSetChanged();
				return false;
			}
		});
		
		
		
		//Gérer la selection de frigo
		maListe.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				 MesFrigos.setFrigoActuel(((TextView) v).getText().toString());
			}
		});
	}

	private List<String> recupererListeFrigo() {
		listeFrigo = null;
		return MesFrigos.getMesFrigos();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		listeFrigo = recupererListeFrigo();
		maListe = (ListView) findViewById(R.id.listViewFrigo);
		adapter = new ArrayAdapter<String>(this, R.layout.layout_liste_frigo, listeFrigo);
		maListe.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creer_frigo, menu);
		return true;
	}
	
	protected class myDragEventListener implements OnDragListener {

		@Override
		public boolean onDrag(View v, DragEvent event) {
			final int action = event.getAction();
			
			switch(action){
			case DragEvent.ACTION_DROP: 
				Toast.makeText(getBaseContext(), "Drop", Toast.LENGTH_SHORT).show();
			break;
			case DragEvent.ACTION_DRAG_EXITED: 
				Toast.makeText(getBaseContext(), "Exit", Toast.LENGTH_SHORT).show();
			break;
			case DragEvent.ACTION_DRAG_STARTED: 
				Toast.makeText(getBaseContext(), "Start", Toast.LENGTH_SHORT).show();
				
			break;
			case DragEvent.ACTION_DRAG_ENTERED: 
				Toast.makeText(getBaseContext(), "Enter", Toast.LENGTH_SHORT).show();
			break;
			case DragEvent.ACTION_DRAG_ENDED: 
				Toast.makeText(getBaseContext(), "End", Toast.LENGTH_SHORT).show();
			break;
			case DragEvent.ACTION_DRAG_LOCATION: 
				Toast.makeText(getBaseContext(), "Location", Toast.LENGTH_SHORT).show();
			break;
			}
		
			return false;
		}
		
	}


}
