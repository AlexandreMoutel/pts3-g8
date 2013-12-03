package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Frigo {
	private String nom;
	private final static String ALIMENT = "Aliment";
	private final static String NOM = "Nom";
	private final static String TYPE = "Type";
	private final static String DATEPEREMPTION = "Date de p�remption";
	private final static String QUANTITE = "Quantit�";
	private final static String FRIGOETRANGER = "Frigo";
	private SQLiteDatabase laBelleDindeDor�e;

	public void open(){
		laBelleDindeDor�e = MainActivity.getLaBelleDindeDor�e().getWritableDatabase();
	}

	public void close(){
		laBelleDindeDor�e.close();
	}

	public Frigo(String nom){
		super();
		this.nom = nom;
	}

	public void ajouterAliment(Aliment leJambon){
		open();
		ContentValues values = new ContentValues();
		values.put(NOM, leJambon.getNom());
		values.put(TYPE, leJambon.getType	());
		values.put(DATEPEREMPTION, leJambon.getDate());
		values.put(QUANTITE, leJambon.getQuantite());
		values.put(FRIGOETRANGER, this.getNom());
		laBelleDindeDor�e.insert(ALIMENT, null, values);
		close();
	}

	public void mangerTousLesAliment(Aliment lePetitGigot){
		open();
		laBelleDindeDor�e.delete(ALIMENT, NOM + " = " + lePetitGigot.getNom(), null);
		close();
	}

	public void transformerUnAliment(Aliment lePat�){
		open();
		ContentValues values = new ContentValues();
		values.put(NOM, lePat�.getNom());
		values.put(TYPE, lePat�.getType());
		values.put(DATEPEREMPTION, lePat�.getDate());
		values.put(QUANTITE, lePat�.getQuantite());
		values.put(FRIGOETRANGER, this.getNom());
		laBelleDindeDor�e.update(ALIMENT, values,  NOM + " = " + lePat�.getNom(), null);
		close();
	}
	 
	public void onChangeLeFrigo(Aliment lePat�, String nomNewFrigo){
		open();
		ContentValues values = new ContentValues();
		values.put(NOM, lePat�.getNom());
		values.put(TYPE, lePat�.getType());
		values.put(DATEPEREMPTION, lePat�.getDate());
		values.put(QUANTITE, lePat�.getQuantite());
		values.put(FRIGOETRANGER, nomNewFrigo);
		laBelleDindeDor�e.update(ALIMENT, values,  NOM + " = " + lePat�.getNom(), null);
		close();
	}
	
	public List<Aliment> getLeFrigo() {
		open();
		List<Aliment> leFrigo = new ArrayList<Aliment>();
		Cursor laDinde = laBelleDindeDor�e.query(ALIMENT, new String[] {NOM, TYPE, DATEPEREMPTION, QUANTITE}, null, null, null, null, null);

		if(laDinde.getCount() == 0){
			return null;
		}
		else{
			if(laDinde.moveToFirst()){
				do{
					leFrigo.add(new Aliment(laDinde.getString(0), laDinde.getString(1), laDinde.getString(2), laDinde.getString(3)));
				}while(laDinde.moveToNext());
			}
		}
		close();
		return leFrigo;
	}

	public String getNom(){
		return nom;
	}

	public void setNom(String nom) {
		String ancienFrigo = MesFrigos.getFrigoActuel().getNom();
		MesFrigos.setFrigoActuel(nom);
		for(Aliment a : this.getLeFrigo()){
			onChangeLeFrigo(a, nom);
			MesFrigos.onAChangerLeFrigo(this.nom ,nom);
		}
		this.nom = nom;
		MesFrigos.setFrigoActuel(ancienFrigo);
	}

}
