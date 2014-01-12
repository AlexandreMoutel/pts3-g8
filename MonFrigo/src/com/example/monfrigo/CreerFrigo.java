package com.example.monfrigo;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CreerFrigo extends ListActivity {

	Button newFrigo;
	ArrayAdapter<String> adapter;
	List<String> listeFrigo = MesFrigos.getMesFrigos();
	ListView maListe;
	TextView emptyFrigo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creer_frigo);

		newFrigo = (Button) findViewById(R.id.button_new_frigo);
		emptyFrigo = (TextView) findViewById(R.id.textViewEmptyFrigo);


		listeFrigo = recupererListeFrigo();
		//maListe = (ListView) findViewById(R.id.listViewFrigo);
		maListe = getListView();
		maListe.setEmptyView(maListe.getEmptyView());

		adapter = new ArrayAdapter<String>(this, R.layout.layout_liste_frigo, listeFrigo);
		maListe.setSelector(R.drawable.list_selector);


		maListe.setItemsCanFocus(false);
		maListe.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		if(listeFrigo == null){
			emptyFrigo.setText("Il n' y actuellement aucun frigo de créé");
		}else{
			emptyFrigo.setText("");
			maListe.setAdapter(adapter);
		}



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
						adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.layout_liste_frigo, listeFrigo);
						//adapter.insert(nomFrigo, 0);
						maListe.setSelector(R.drawable.list_selector);
						maListe.setAdapter(adapter);

						maListe.setItemsCanFocus(false);
						maListe.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
						emptyFrigo.setText("");
						MesFrigos.setFrigoActuel(nomFrigo);
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
						String nomFrigo = modifNomFrigo.getText().toString();
						MesFrigos.getFrigoActuel().setNom(nomFrigo);
						listeFrigo = recupererListeFrigo();
						maListe = getListView();
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
							MesFrigos.supprimerFrigo(MesFrigos.getFrigoActuel().getNom());
							
							Log.e("DEBUG", "Frigo actuel : " + MesFrigos.getFrigoActuel().getNom());
						}
						listeFrigo = recupererListeFrigo();
						maListe = getListView();
						adapter = new ArrayAdapter<String>(CreerFrigo.this, R.layout.layout_liste_frigo, listeFrigo);

						if(listeFrigo == null){
							emptyFrigo.setText("Il n' y actuellement aucun frigo de créé");
						}
						else{
							emptyFrigo.setText("");
							maListe.setAdapter(adapter);
						}
						
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
				for(int i = 0; i < listeFrigo.size(); i++){
					if(maListe.getItemAtPosition(i) == MesFrigos.getFrigoActuel()){
						maListe.setSelection(i);
					}
				}
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
		Log.e("passage1", "passage1");
		listeFrigo = recupererListeFrigo();
		maListe = getListView();
		adapter = new ArrayAdapter<String>(this, R.layout.layout_liste_frigo, listeFrigo);
		if(listeFrigo != null){
			maListe.setAdapter(adapter);
			for(int i = 0; i < listeFrigo.size(); i++){
				if(MesFrigos.getFrigoActuel() != null)
					if(maListe.getItemAtPosition(i).toString().equals(MesFrigos.getFrigoActuel().getNom())){
						maListe.setItemChecked(i, true);
						maListe.setSelection(i);
						Log.e("passage", "passage");
					}
			}
		}

		adapter.notifyDataSetChanged();


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creer_frigo, menu);
		return true;
	}
}
