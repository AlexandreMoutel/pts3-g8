package com.example.monfrigo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Frigo {
	public static boolean modifierNom = false;
	private String nom;
	private final static String ALIMENT = "Aliment";
	private final static String NOM = "Nom";
	private final static String TYPE = "Type";
	private final static String DATEPEREMPTION = "DateDePeremption";
	private final static String QUANTITE = "Quantite";
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
		values.put(TYPE, leJambon.getType());
		values.put(DATEPEREMPTION, leJambon.getDate());
		values.put(QUANTITE, leJambon.getQuantite());
		values.put(FRIGOETRANGER, this.getNom());
		laBelleDindeDor�e.insert(ALIMENT, null, values);
		close();
	}

	public void mangerTousLesAliment(Aliment lePetitGigot){
		open();
		laBelleDindeDor�e.delete(ALIMENT, NOM + " = '" + lePetitGigot.getNom() + "'", null);
		close();
	}
	public void mangerUnAliment(Aliment lePat�){
		open();
		ContentValues values = new ContentValues();
		values.put(NOM, lePat�.getNom());
		values.put(TYPE, lePat�.getType());
		values.put(DATEPEREMPTION, lePat�.getDate());
		values.put(QUANTITE, String.valueOf(Integer.parseInt(lePat�.getQuantite())-1));
		values.put(FRIGOETRANGER, this.getNom());
		laBelleDindeDor�e.update(ALIMENT, values,  NOM + " = '" + lePat�.getNom() + "'", null);
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
		laBelleDindeDor�e.update(ALIMENT, values,  NOM + " = '" + lePat�.getNom() + "'", null);
		close();
	}
	
	public void changerUnAlimentDeFrigo(Aliment lesBiscuits, String nomNouveauFrigo){
		open();
		ContentValues values = new ContentValues();
		values.put(NOM, lesBiscuits.getNom());
		values.put(TYPE, lesBiscuits.getType());
		values.put(DATEPEREMPTION, lesBiscuits.getDate());
		values.put(QUANTITE, lesBiscuits.getQuantite());
		values.put(FRIGOETRANGER, nomNouveauFrigo);
		laBelleDindeDor�e.update(ALIMENT, values, FRIGOETRANGER + " = '" + this.nom + "'", null);
		close();
	}

	public List<Aliment> getLaListeDeCourse() {
		open();
		List<Aliment> laListeDeCourse = new ArrayList<Aliment>();
		Cursor laDinde = laBelleDindeDor�e.query(ALIMENT, new String[] {NOM, TYPE, DATEPEREMPTION, QUANTITE, FRIGOETRANGER}, FRIGOETRANGER + " = '" + this.nom + "'", null, null, null, null);

		if(laDinde.getCount() == 0){
			return null;
		}
		else{
			if(laDinde.moveToFirst()){
				do{
					laListeDeCourse.add(new Aliment(laDinde.getString(0), laDinde.getString(1), laDinde.getString(2), laDinde.getString(3)));
				}while(laDinde.moveToNext());
			}
		}
		close();
		return laListeDeCourse;
	}
	
	public List<Aliment> getLeFrigo() {
		open();
		List<Aliment> leFrigo = new ArrayList<Aliment>();
		Cursor laDinde = laBelleDindeDor�e.query(ALIMENT, new String[] {NOM, TYPE, DATEPEREMPTION, QUANTITE, FRIGOETRANGER}, FRIGOETRANGER + " = '" + this.nom + "'", null, null, null, null);

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

	public void setNom(String nom){
		MesFrigos.supprimerFrigo(this.nom);
		if(this.getLeFrigo() != null){
			for(Aliment a : this.getLeFrigo()){
				changerUnAlimentDeFrigo(a, nom);
			}
		}
		MesFrigos.ajouterFrigo(nom);
		this.nom = nom;
	}

	public void setNomFrigoActuel(String nom) {
		this.nom = nom;
	}

}
